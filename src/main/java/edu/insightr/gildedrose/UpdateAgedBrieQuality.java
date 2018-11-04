package edu.insightr.gildedrose;

public class UpdateAgedBrieQuality implements CategoryUpdate {
    @Override
    public int update(Item item) {
        int quality = item.getQuality();
        int sellIn = item.getSellIn();

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
}
