package dto;

import java.util.Objects;

public class Item {

    private String name;
    private String code;
    private int cost;
    private int stockLeft;

    public Item(String code, String name, int cost, int stockLeft) {
        this.name = name;
        this.code = code;
        this.cost = cost;
        this.stockLeft = stockLeft;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getStockLeft() {
        return stockLeft;
    }

    public void setStockLeft(int stockLeft) {
        this.stockLeft = stockLeft;
    }

    @Override
    public String toString() {
        String out = getCode() + ": " + getName() + ": £" + String.format("%.2f", ((float) getCost() / 100));
        return out;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return cost == item.cost && stockLeft == item.stockLeft && name.equals(item.name) && code.equals(item.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, cost, stockLeft);
    }
}
