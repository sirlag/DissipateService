package moe.dissipate.ItemLevelsLibrary;

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
}
