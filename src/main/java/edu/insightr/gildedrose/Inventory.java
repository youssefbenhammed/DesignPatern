package edu.insightr.gildedrose;


public class Inventory {
    // TODO (PBZ) : indent your code to increase readability



    private Item[] items;

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {this.items = items;}


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

    }

    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateQuality()
    {
        // TODO (PBZ) read and correct warnings when possible
       for(int i=0;i<items.length;i++)
       {
           //Switch structure
           switch (items[i].getName())
           {
               case "Conjured Mana Cake":
                    items[i].setQuality(updateConjuredManaCakeQuality(items[i]));
                    items[i].setSellIn(items[i].getSellIn()-1);
                    break;

               case "+5 Dexterity Vest":
                    items[i].setQuality(updateDexterityVestQuality(items[i]));
                    items[i].setSellIn(items[i].getSellIn()-1);
                    break;

               case "Aged Brie":
                   items[i].setQuality(updateAgedBrieQuality(items[i]));
                   items[i].setSellIn(items[i].getSellIn()-1);
                   break;

               case "Elixir of the Mongoose":
                   items[i].setQuality(updateElixirMongooseQuality(items[i]));
                   items[i].setSellIn(items[i].getSellIn()-1);
                   break;

               case "Backstage passes to a TAFKAL80ETC concert":
                   items[i].setQuality(updateBackstageQuality(items[i]));
                   items[i].setSellIn(items[i].getSellIn()-1);
                   break;
           }
       }
    }


    public int updateElixirMongooseQuality(Item object) //Test : OK
    {
        int quality = object.getQuality();
        int sellIn = object.getSellIn();

        if(sellIn>0)
        {
            quality=quality-1;
        }
        else
        {
            quality=quality-2;
        }
        if(quality<0)
        {
            quality=0;
        }

        return quality;
    }

    public int updateBackstageQuality(Item object) //Test : Fail
    {
        int quality = object.getQuality();
        int sellIn= object.getSellIn();

        if(sellIn==0)
        {
            quality=0;
        }
        else if(sellIn<=5)
        {
            quality=quality+3;
        }
        else if(sellIn<=10)
        {
            quality=quality+2;
        }
        else
        {
            quality=quality+1;
        }

        if(quality>50)
        {
            quality=50;
        }

        return quality;
    }

    public int updateAgedBrieQuality(Item object) //Test : OK
    {
        int quality = object.getQuality();
        int sellIn = object.getSellIn();

        if(sellIn>0)
        {
            quality=quality+1;
        }
        else
        {
            quality=quality+2;
        }
        if(quality>50)
        {
            quality=50;
        }
        return quality;
    }

    public int updateDexterityVestQuality(Item object) //Test : OK
    {
        int quality=object.getQuality();
        int sellIn=object.getSellIn();

        if(sellIn>0)
        {
            quality=quality-1;
        }
        else
        {
            quality=quality-2;
        }
        if(quality<0)
        {
            quality=0;
        }
        return quality;
    }

    public int updateConjuredManaCakeQuality(Item object) //Test : OK
    {
        int quality=object.getQuality();
        int sellIn=object.getSellIn();
        if(sellIn>0)
        {
            quality=quality-2;
        }
        else
        {
            quality=quality-4;
        }
        if(quality<0)
        {
            quality=0;
        }
        return quality;

    }

    public void oldUpdateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName() != "Aged Brie"
                    && items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].getQuality() > 0) {
                    if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                        items[i].setQuality(items[i].getQuality() - 1);
                    }
                }
            } else {
                if (items[i].getQuality() < 50) {
                    items[i].setQuality(items[i].getQuality() + 1);

                    if (items[i].getName() == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getSellIn() < 11) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }

                        if (items[i].getSellIn() < 6) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                items[i].setSellIn(items[i].getSellIn() - 1);
            }

            if (items[i].getSellIn() < 0) {
                if (items[i].getName() != "Aged Brie") {
                    if (items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getQuality() > 0) {
                            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                                items[i].setQuality(items[i].getQuality() - 1);
                            }
                        }
                    } else {
                        items[i].setQuality(items[i].getQuality() - items[i].getQuality());
                    }
                } else {
                    if (items[i].getQuality() < 50) {
                        items[i].setQuality(items[i].getQuality() + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i <20; i++) {
            inventory.oldUpdateQuality();
            inventory.printInventory();
        }
    }
}
