package com.application.budgetplanner.dataModel;

public class PlanItem {
    private String itemName;
    private int itemAmount;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    @Override
    public String toString() {
        return "PlanItem{" +
                "itemName='" + itemName + '\'' +
                ", itemAmount=" + itemAmount +
                '}';
    }
}
