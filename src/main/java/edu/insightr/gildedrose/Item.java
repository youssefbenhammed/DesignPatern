package edu.insightr.gildedrose;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.time.LocalDate;

public class Item {

    private String name;
    private int sellIn;
    private int quality;
    private LocalDate buyingDate;
    private LocalDate sellingDate;
    @FXML private Button sell;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public Item(String name, int sellIn, int quality) {
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;

        this.sell=new Button("Sell");

        this.buyingDate = LocalDate.now();
        this.sellingDate = null;

        this.price=sellIn*0.1+quality*2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public LocalDate getBuyingDate() { return buyingDate; }

    public void setBuyingDate(LocalDate buyingDate) {
        this.buyingDate = buyingDate;
    }

    public Button getSell() {
        return sell;
    }

    public void setSell(Button button) {
        this.sell = button;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", sellIn=" + sellIn +
                ", quality=" + quality +
                '}';
    }

    public LocalDate getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(LocalDate sellingDate) {
        this.sellingDate = sellingDate;
    }

    public int isEqual(Item item)
    {

        if(item.name.equals(name) && item.sellIn==sellIn && item.quality==quality && item.buyingDate.equals(buyingDate) )
        {
            return 1;
        }
        return 0;
    }

    public Item getItem()
    {
        return this;
    }
}