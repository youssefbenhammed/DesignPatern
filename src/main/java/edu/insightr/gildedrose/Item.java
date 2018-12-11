package edu.insightr.gildedrose;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.time.LocalDate;

public class Item {

    private String name;
    private int sellIn;
    private int quality;
    private LocalDate date;
    @FXML
    public Button sell;

    public Item(String name, int sellIn, int quality) {
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;

        this.sell=new Button("Sell");
        this.date = LocalDate.now();
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

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) {
        this.date = date;
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

}