package edu.insightr.gildedrose;

public class UpdateBackstageQuality implements CategoryUpdate {
    @Override
    public int update(Item item) {
        int quality = item.getQuality();
        int sellIn= item.getSellIn();

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
}
