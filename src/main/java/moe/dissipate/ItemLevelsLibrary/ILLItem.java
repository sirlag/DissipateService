package moe.dissipate.ItemLevelsLibrary;

public class ILLItem {

    private int itemLevel;
    private String name, imgurl, slot;

    public ILLItem(int itemLevel, String name, String imgurl, String slot) {
        this.itemLevel = itemLevel;
        this.name = name;
        this.imgurl = imgurl;
        this.slot = slot;
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

    public String getSlot() {
        return slot;
    }
}
