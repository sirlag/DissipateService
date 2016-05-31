package moe.dissipate.ItemLevelsLibrary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ILLCharacter {
    private String cName, characterPictureURL;
    private List<ILLItem> leftItems, items, rightItems;
    private int itemLevel, totalItemLevel;

    public ILLCharacter(String slug) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://na.finalfantasyxiv.com/lodestone/character/" + slug).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
        Elements contents = doc.getElementsByClass("item_detail_box");

        List<Element> reducedList = contents.subList(0, contents.size() / 2);
        items = reducedList.stream().map(e ->
                new ILLItem(
                        Integer.parseInt(e.getElementsByClass("db-tooltip__item__level").first().text().replaceAll("[^\\d.]", "")),
                        e.getElementsByClass("db-tooltip__item__name").first().text(),
                        e.getElementsByClass("db-tooltip__item__icon__item_image").first().attr("src"),
                        e.getElementsByClass("db-tooltip__item__category").first().text())).collect(Collectors.toList());
        rightItems = items.stream().filter((e) -> e.getSlot().matches("Sheild")
                || e.getSlot().matches("Necklace")
                || e.getSlot().matches("Bracelets")
                || e.getSlot().matches("Ring")
                || e.getSlot().matches("Earrings")
                || e.getSlot().matches("Soul Crystal")).collect(Collectors.toList());
        leftItems = items.stream().filter(e -> !(rightItems.contains(e))).collect(Collectors.toList());
        totalItemLevel = calcTotalItemLevel();
        itemLevel = calcTotalItemLevel() / 13;
        cName = doc.getElementsByTag("title").first().text().replace(" | FINAL FANTASY XIV, The Lodestone", "");
        characterPictureURL = doc.getElementsByClass("bg_chara_264").first().getElementsByTag("img").first().attr("src");
    }


    public String getCName() {
        return cName;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public int getTotalItemLevel(){
        return totalItemLevel;
    }

    public List<ILLItem> getItems() {
        return items;
    }

    public List<ILLItem> getLeftItems() {
        return leftItems;
    }

    public List<ILLItem> getRightItems() {
        return rightItems;
    }

    public String getCharacterPictureURL() {
        return characterPictureURL;
    }

    private int calcTotalItemLevel(){
        int ilvl = 0;
        if (items.size() == 13)
            if (items.stream().findFirst().isPresent())
                ilvl += items.stream().findFirst().get().getItemLevel();
        for(ILLItem item : items){
            ilvl +=  item.getName().matches("Soul of the .*") ? 0: item.getItemLevel();
        }
        return ilvl;
    }
}
