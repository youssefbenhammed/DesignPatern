package edu.insightr.gildedrose;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inventory {


    private  Item[] items;

    private List<Item> buyItems;
    private  List<Item> soldItems;

    private double bankBalance;


    //GET SET :
    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) { this.items = items; }

    public List<Item> getBuyItems() {
        return buyItems;
    }

    public void setBuyItems(List<Item> buyItems) {
        this.buyItems = buyItems;
    }

    public List<Item> getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(List<Item> soldItems) {
        this.soldItems = soldItems;
    }

    //INITIALISATION :

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };
        items[0].setBuyingDate(LocalDate.of(2018, 12, 1));
        items[1].setBuyingDate(LocalDate.of(2018, 12, 1));
        items[2].setBuyingDate(LocalDate.of(2018, 12, 2));
        items[3].setBuyingDate(LocalDate.of(2018, 12, 3));
        items[4].setBuyingDate(LocalDate.of(2018, 12, 3));
        items[5].setBuyingDate(LocalDate.of(2018, 12, 3));

        this.soldItems = new ArrayList<>();
    }

    //METHODES :

    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    //
    public void updateQuality()
    {
       for(Item i : items)
       {
           //Switch structure
           switch (i.getName())
           {
               case "Conjured Mana Cake":
                    i.setQuality(updateConjuredManaCakeQuality(i));
                    i.setSellIn(i.getSellIn() - 1);
                    break;

               case "+5 Dexterity Vest":
                    i.setQuality(updateDexterityVestQuality(i));
                    i.setSellIn(i.getSellIn() - 1);
                    break;

               case "Aged Brie":
                   i.setQuality(updateAgedBrieQuality(i));
                   i.setSellIn(i.getSellIn() - 1);
                   break;

               case "Elixir of the Mongoose":
                   i.setQuality(updateElixirMongooseQuality(i));
                   i.setSellIn(i.getSellIn() - 1);
                   break;

               case "Backstage passes to a TAFKAL80ETC concert":
                   i.setQuality(updateBackstageQuality(i));
                   i.setSellIn(i.getSellIn() - 1);
                   break;
           }
       }
    }


    public int updateElixirMongooseQuality(Item object) //Test : OK
    {
        int quality = object.getQuality();
        int sellIn = object.getSellIn();

        if(sellIn > 0)
        {
            quality = quality - 1;
        }
        else
        {
            quality = quality - 2;
        }
        if(quality < 0)
        {
            quality = 0;
        }

        return quality;
    }

    public int updateBackstageQuality(Item object) //Test : Fail
    {
        int quality = object.getQuality();
        int sellIn = object.getSellIn();

        if(sellIn == 0)
        {
            quality = 0;
        }
        else if(sellIn <= 5)
        {
            quality = quality + 3;
        }
        else if(sellIn <= 10)
        {
            quality = quality + 2;
        }
        else
        {
            quality = quality + 1;
        }

        if(quality > 50)
        {
            quality = 50;
        }

        return quality;
    }

    public int updateAgedBrieQuality(Item object) //Test : OK
    {
        int quality = object.getQuality();
        int sellIn = object.getSellIn();

        if(sellIn > 0)
        {
            quality = quality + 1;
        }
        else
        {
            quality = quality + 2;
        }
        if(quality > 50)
        {
            quality = 50;
        }
        return quality;
    }

    public int updateDexterityVestQuality(Item object) //Test : OK
    {
        int quality = object.getQuality();
        int sellIn = object.getSellIn();

        if(sellIn > 0)
        {
            quality = quality - 1;
        }
        else
        {
            quality = quality - 2;
        }
        if(quality < 0)
        {
            quality = 0;
        }
        return quality;
    }

    public int updateConjuredManaCakeQuality(Item object) //Test : OK
    {
        int quality = object.getQuality();
        int sellIn = object.getSellIn();
        if(sellIn > 0)
        {
            quality = quality - 2;
        }
        else
        {
            quality = quality - 4;
        }
        if(quality < 0)
        {
            quality = 0;
        }
        return quality;

    }

    public List<LocalDate> creationDates()
    {
        List<LocalDate> dates = new ArrayList<>();
        for (Item i : items) {
            if(!exists(dates, i.getBuyingDate()))
                dates.add(i.getBuyingDate());
        }
        return dates;
    }

    public List<LocalDate> sellingDates()
    {
        List<LocalDate> dates = new ArrayList<>();
        for (Item i : soldItems) {
            if(!exists(dates, i.getSellingDate()))
                dates.add(i.getSellingDate());
        }
        return dates;
    }

    public int[] countDates()
    {
        List<LocalDate> dates = creationDates();
        int[] count = {0, 0, 0, 0, 0, 0, 0};
        for (Item i : items) {
            LocalDate d = i.getBuyingDate();
            count[dates.indexOf(d)] += 1;
        }
        return count;
    }

    public boolean exists(List<LocalDate> dates, LocalDate date)
    {
        if(dates == null)
            return false;

        for (LocalDate d : dates) {
            if(date == d)
                return true;
        }
        return false;
    }

    public int[] countSellings()
    {
        List<LocalDate> dates = sellingDates();
        int[] count = {0, 0, 0, 0, 0, 0, 0};
        for (Item i : soldItems) {
            LocalDate d = i.getSellingDate();
            count[dates.indexOf(d)] += 1;
        }
        return count;
    }

    public int[] countNbrSellIn()
    {
        int[] result = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (Item i : items) {
            switch (i.getSellIn()) {
                case 0:
                    result[0] += 1;
                    break;

                case 1:
                    result[1] += 1;
                    break;

                case 2:
                    result[2] += 1;
                    break;

                case 3:
                    result[3] += 1;
                    break;

                case 4:
                    result[4] += 1;
                    break;
                case 5:
                    result[5] += 1;
                    break;
                case 6:
                    result[6] += 1;
                    break;
                case 7:
                    result[7] += 1;
                    break;
                case 8:
                    result[8] += 1;
                    break;
                case 9:
                    result[9] += 1;
                    break;
                case 10:
                    result[10] += 1;
                    break;
                case 11:
                    result[11] += 1;
                    break;
            }
        }
        return result;
    }

    public int[] count()
    {
        int[] result = {0, 0, 0, 0, 0, 0};
        for (Item i : items) {
            switch (i.getName()) {
                case "Conjured Mana Cake":
                    result[5] += 1;
                    break;

                case "+5 Dexterity Vest":
                    result[0] += 1;
                    break;

                case "Aged Brie":
                    result[1] += 1;
                    break;

                case "Elixir of the Mongoose":
                    result[2] += 1;
                    break;

                case "Sulfuras, Hand of Ragnaros":
                    result[3] += 1;
                    break;

                case "Backstage passes to a TAFKAL80ETC concert":
                    result[4] += 1;
                    break;
            }
        }
        return result;
    }



    public Item[] delet(Item item)
    {
        int rang=0;
        Item[] newItems = new Item[items.length-1];
        for(int i=0;i<items.length;i++)
        {
            if(items[i].isEqual(item)==1)
            {
                rang=i;
            }
        }
        for(int i=0;i<rang;i++)
        {
                newItems[i]=items[i];
        }
        for(int i=rang+1;i<items.length;i++)
        {
            newItems[i-1]=items[i];
        }
        return newItems;

    }
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 20; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
    }
}
