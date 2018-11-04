package edu.insightr.gildedrose;

public class Updater {

    public void update(Item item, String categorie) {

        CategoryUpdate categoryUpdate;

        switch (categorie) {
            case "Conjured Mana Cake":
                categoryUpdate=new UpdateConjuredManaCakeQuality();
                break;

            case "+5 Dexterity Vest":
                categoryUpdate = new UpdateNormalItemQuality();
                break;

            case "Aged Brie":
                categoryUpdate = new UpdateAgedBrieQuality();
                break;

            case "Elixir of the Mongoose":
                categoryUpdate = new UpdateNormalItemQuality();
                break;

            case "Backstage passes to a TAFKAL80ETC concert":
                categoryUpdate = new UpdateBackstageQuality();
                break;
            default:
                categoryUpdate=new CategoryUpdate() {
                    @Override
                    public int update(Item item) {
                        return item.getQuality();
                    }
                };

        }

        item.setQuality(categoryUpdate.update(item));
        item.setSellIn(item.getSellIn()-1);
    }

}

