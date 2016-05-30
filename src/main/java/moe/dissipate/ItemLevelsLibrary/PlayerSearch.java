package moe.dissipate.ItemLevelsLibrary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerSearch {

    /**
     *
     * @param name The Name of the player being searched for.
     * @param server The server that the player is from. If this string is left blank, it will search all servers.
     * @return Returns a HashSet of Players, containing a name, address to image, server and the url to follow if that
     * is the chosen player. If there are no players found with that name, this function returns NULL.
     */
    public static List<Player> Search(String name, String server){

        StringBuilder sb = new StringBuilder();
        sb.append("http://na.finalfantasyxiv.com/lodestone/character/?q=");
        sb.append(name.replaceAll(" ", "+"));
        if(!server.isEmpty())
            sb.append(String.format("&worldname=%s", server));

        Document characterSearchPage = new Document("");
        try {
            characterSearchPage = Jsoup.connect(sb.toString()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Player> players = new ArrayList<>();
        try{
            Elements clist = characterSearchPage.select("table").get(1).select("tr");
            for(Element e : clist.stream().limit(10).collect(Collectors.toList())){
                Element temp = e.getElementsByTag("th").first();
                String url = String.format("http://na.finalfantasyxiv.com%s", temp.select("a").attr("href"));
                String image = temp.select("img").attr("src");
                temp = e.getElementsByClass("player_name_gold").first();
                String charServer = temp.getElementsByTag("span").first().text();
                String charName = temp.getElementsByTag("a").first().text();
                Player tempPlayer = new Player(url, charName, image, charServer);
                players.add(tempPlayer);
            }
        } catch (IndexOutOfBoundsException ignored){
        }
        return players;

    }
}
