package main.ua.advanced.practice5.task9_auction;

public class Lot {
    private String nameLot;
    private int price;

    public Lot(String nameLot, int startPrice) {
        this.nameLot = nameLot;
        this.price = startPrice;
    }

    public String getNameLot() {
        return nameLot;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return "Lot " + nameLot + ", price = " + price + '}';
    }
}
