package moe.dissipate.ItemLevelsLibrary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ILLItem {

    private int itemLevel;
    private String name;
    private String imgurl;

    public ILLItem(int itemLevel, String name, String imgurl) {
        this.itemLevel = itemLevel;
        this.name = name;
        this.imgurl = imgurl;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public String getName() {
        return name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public static HashSet<ILLItem> getItems(String profileURL){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://na.finalfantasyxiv.com/lodestone"+profileURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
        Elements contents = doc.getElementsByClass("item_detail_box");

        List<Element> reducedList = contents.subList(0, contents.size()/2);
        return reducedList.stream().map(e -> new ILLItem(Integer.parseInt(e.getElementsByClass("pt3").first().text().replaceAll("[^\\d.]", "")),
                e.getElementsByClass("item_name").first().text(),
                e.getElementsByClass("ic_reflection").first().attr("src"))).collect(Collectors.toCollection(HashSet::new));

    }
}
