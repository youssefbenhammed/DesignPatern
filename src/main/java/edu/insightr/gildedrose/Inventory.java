package edu.insightr.gildedrose;


public class Inventory {


    private Item[] items;

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) { this.items = items; }


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

    public void PrintInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void UpdateQuality()
    {
       for(Item i : items)
       {
           //Switch structure
           switch (i.getName())
           {
               case "Conjured Mana Cake":
                    i.setQuality(UpdateConjuredManaCakeQuality(i));
                    i.setSellIn(i.getSellIn() - 1);
                    break;

               case "+5 Dexterity Vest":
                    i.setQuality(UpdateDexterityVestQuality(i));
                    i.setSellIn(i.getSellIn() - 1);
                    break;

               case "Aged Brie":
                   i.setQuality(UpdateAgedBrieQuality(i));
                   i.setSellIn(i.getSellIn() - 1);
                   break;

               case "Elixir of the Mongoose":
                   i.setQuality(UpdateElixirMongooseQuality(i));
                   i.setSellIn(i.getSellIn() - 1);
                   break;

               case "Backstage passes to a TAFKAL80ETC concert":
                   i.setQuality(UpdateBackstageQuality(i));
                   i.setSellIn(i.getSellIn() - 1);
                   break;
           }
       }
    }


    public int UpdateElixirMongooseQuality(Item object) //Test : OK
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

    public int UpdateBackstageQuality(Item object) //Test : Fail
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

    public int UpdateAgedBrieQuality(Item object) //Test : OK
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

    public int UpdateDexterityVestQuality(Item object) //Test : OK
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

    public int UpdateConjuredManaCakeQuality(Item object) //Test : OK
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

    public int[] CountNbrSellIn()
    {
        int[] result = {0, 0, 0, 0, 0, 0, 0};
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
            }
        }
        return result;

    }

    public int[] Count()
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
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 20; i++) {
            inventory.UpdateQuality();
            inventory.PrintInventory();
        }
    }
}
