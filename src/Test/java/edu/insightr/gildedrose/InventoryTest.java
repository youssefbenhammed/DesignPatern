package edu.insightr.gildedrose;




import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class InventoryTest  {

    @Test
    public void testDexterityVestQuality() {//OK
        Item dexterityVest= new Item("+5 Dexterity Vest",10,20);
        Item dexterityVest_altered = new Item("+5 Dexterity Vest",-1,20);
        Item dexterityVest_SellinN_Quality0 = new Item("+5 Dexterity Vest",-2,0);

        Item[] listeItems = new Item[]{dexterityVest,dexterityVest_altered,dexterityVest_SellinN_Quality0};
        Inventory inventaire = new Inventory(listeItems);

        inventaire.updateQuality();

        assertEquals("La qualité doit être décrémenté de 1 pour \"+5 Dexterity Vest\" si sellin >0",19,inventaire.getItems()[0].getQuality());
        assertEquals("la qualité doit etre décrémenté de 2 pour +5 Dexterity Vest si sllin<=0",18,inventaire.getItems()[1].getQuality());
        assertEquals("La qualité doit etre supérieur ou égale a 0 ",0,inventaire.getItems()[2].getQuality());
    }

    @Test
    public void testAgedBrieQuality()
    {
        Item AgedBrie_SellinP =new Item("Aged Brie", 1, 0);
        Item AgedBrie_Sellin0 =new Item("Aged Brie", 0, 4);
        Item AgedBrie_SellinN =new Item("Aged Brie", -1, 2);

        Item[] listeItems = new Item[]{AgedBrie_SellinP,AgedBrie_Sellin0,AgedBrie_SellinN,
                new Item("Aged Brie", -2, 49),
                new Item("Aged Brie", -2, 50),
                new Item("Aged Brie", -2, -6)};
        Inventory inventaire=new Inventory(listeItems);

        inventaire.updateQuality();

        assertEquals("La qualité d'un Aged Brie doit être augmenté de 1 si il a un sell in > 0",1,inventaire.getItems()[0].getQuality());
        assertEquals("La qualité d'un Aged Brie doit être augmenté de 2 si il a un sell in = 0",6,inventaire.getItems()[1].getQuality());
        assertEquals("La qualité d'un Aged Brie doit être augmenté de 2 si il a un sell in < 0",4,inventaire.getItems()[2].getQuality());
        assertEquals("La qualité maximum d'un Aged Brie est de 50",50,inventaire.getItems()[3].getQuality());
        assertEquals("La qualité maximum d'un Aged Brie est de 50",50,inventaire.getItems()[4].getQuality());
    }

    @Test
    public void testElixirMongooseQuality()
    {

        Item ElixirMongoose_SellInP = new Item("Elixir of the Mongoose", 5, 7);
        Item ElixirMongoose_SellIn0 = new Item("Elixir of the Mongoose", 0, 7);
        Item ElixirMongoose_SellInN = new Item("Elixir of the Mongoose", -3, 7);

        Item[] listeItems = new Item[]{ElixirMongoose_SellInP,ElixirMongoose_SellIn0,ElixirMongoose_SellInN};
        Inventory inventaire=new Inventory(listeItems);

        inventaire.updateQuality();

        assertEquals("La qualité d'un Elixir Mangoose doit être diminué de 1 si il a un sell in > 0",6,inventaire.getItems()[0].getQuality());
        assertEquals("La qualité d'un Elixir Mangoose doit être diminué de 2 si il a un sell in = 0",5,inventaire.getItems()[1].getQuality());
        assertEquals("La qualité d'un Elixir Mangoose doit être diminué de 2 si il a un sell in < 0",5,inventaire.getItems()[2].getQuality());
    }

    @Test
    public void testSulfurasQuality()
    {
        Item[] listeItems = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
        Inventory inventaire=new Inventory(listeItems);


        inventaire.updateQuality();

        assertEquals("La qualité d'un Sulfuras ne change jamais",80,inventaire.getItems()[0].getQuality());
    }

    @Test
    public void testBackstageQuality()
    {

        Item[] listeItems = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 1,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 2,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 3,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 4,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 6,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 7,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 8,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 9,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 11,20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 14,50)
        };
        Inventory inventaire=new Inventory(listeItems);


        inventaire.updateQuality();
        inventaire.printInventory();


        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert passe a 0 si SellIn <=0",0,inventaire.getItems()[0].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 3 si SellIn=1",23,inventaire.getItems()[1].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 3  si SellIn =2",23,inventaire.getItems()[2].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 3  si SellIn =3",23,inventaire.getItems()[3].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 3 si SellIn =4",23,inventaire.getItems()[4].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 3  si SellIn =5",23,inventaire.getItems()[5].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 2 0 si SellIn =6",22,inventaire.getItems()[6].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 2  si SellIn =7",22,inventaire.getItems()[7].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 2 si SellIn =8",22,inventaire.getItems()[8].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 2 si SellIn =9",22,inventaire.getItems()[9].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert paugmente de 2 si SellIn =10",22,inventaire.getItems()[10].getQuality());
        assertEquals("La qualité d'un Backstage passes to a TAFKAL80ETC concert augmente de 1 si SellIn >10",21,inventaire.getItems()[11].getQuality());
        assertEquals("La qualité maximum d'un Backstage passes to a TAFKAL80ETC concert est de 50",50,inventaire.getItems()[12].getQuality());

        //new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
    }

    @Test
    public void testConjuredManaCakeQuality()
    {
        Item[] listeItems = new Item[]{new Item("Conjured Mana Cake", -1, 6),
                                       new Item("Conjured Mana Cake", 0, 6),
                                       new Item("Conjured Mana Cake", 1, 6),
                                       new Item("Conjured Mana Cake", 3, 0),
                                       new Item("Conjured Mana Cake", 0, 0)};
        Inventory inventaire=new Inventory(listeItems);


        inventaire.updateQuality();
        inventaire.printInventory();

        assertEquals("",2,inventaire.getItems()[0].getQuality());
        assertEquals("",2,inventaire.getItems()[1].getQuality());
        assertEquals("",4,inventaire.getItems()[2].getQuality());
        assertEquals("",0,inventaire.getItems()[3].getQuality());
        assertEquals("",0,inventaire.getItems()[4].getQuality());

    }
}