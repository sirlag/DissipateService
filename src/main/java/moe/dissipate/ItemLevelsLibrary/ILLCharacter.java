package moe.dissipate.ItemLevelsLibrary;

import java.util.HashSet;

public class ILLCharacter {
    private String cName;
    private HashSet<ILLItem> items;
    private int itemLevel, totalItemLevel;

    public ILLCharacter(String name, HashSet<ILLItem> items) {
        cName = name;
        this.items = items;
        totalItemLevel = calcTotalItemLevel();
        itemLevel = calcTotalItemLevel()/13;
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

    public HashSet<ILLItem> getItems() {
        return items;
    }

    private int calcTotalItemLevel(){
        int ilvl = 0;
        if (items.size() == 12)
            if (items.stream().findFirst().isPresent())
                ilvl += items.stream().findFirst().get().getItemLevel();
        for(ILLItem item : items){
            ilvl +=  item.getName().matches("Soul of the") ? 0: item.getItemLevel();
        }
        return ilvl;
    }
}
