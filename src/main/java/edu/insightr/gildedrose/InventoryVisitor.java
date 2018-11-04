package edu.insightr.gildedrose;

public class InventoryVisitor implements MyVisitor{


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

    @Override
    public void visit(VisitedObject visitedObject)
    {
            for(int i=0;i<((Inventory)visitedObject).getItems().length;i++)
            {
                //Switch structure
                switch (((Inventory)visitedObject).getItems()[i].getName())
                {
                    case "Conjured Mana Cake":
                        ((Inventory)visitedObject).getItems()[i].setQuality(updateConjuredManaCakeQuality(((Inventory)visitedObject).getItems()[i]));
                        ((Inventory)visitedObject).getItems()[i].setSellIn(((Inventory)visitedObject).getItems()[i].getSellIn()-1);
                        break;

                    case "+5 Dexterity Vest":
                        ((Inventory)visitedObject).getItems()[i].setQuality(updateDexterityVestQuality(((Inventory)visitedObject).getItems()[i]));
                        ((Inventory)visitedObject).getItems()[i].setSellIn(((Inventory)visitedObject).getItems()[i].getSellIn()-1);
                        break;

                    case "Aged Brie":
                        ((Inventory)visitedObject).getItems()[i].setQuality(updateAgedBrieQuality(((Inventory)visitedObject).getItems()[i]));
                        ((Inventory)visitedObject).getItems()[i].setSellIn(((Inventory)visitedObject).getItems()[i].getSellIn()-1);
                        break;

                    case "Elixir of the Mongoose":
                        ((Inventory)visitedObject).getItems()[i].setQuality(updateElixirMongooseQuality(((Inventory)visitedObject).getItems()[i]));
                        ((Inventory)visitedObject).getItems()[i].setSellIn(((Inventory)visitedObject).getItems()[i].getSellIn()-1);
                        break;

                    case "Backstage passes to a TAFKAL80ETC concert":
                        ((Inventory)visitedObject).getItems()[i].setQuality(updateBackstageQuality(((Inventory)visitedObject).getItems()[i]));
                        ((Inventory)visitedObject).getItems()[i].setSellIn(((Inventory)visitedObject).getItems()[i].getSellIn()-1);
                        break;
                }
            }


    }
}
