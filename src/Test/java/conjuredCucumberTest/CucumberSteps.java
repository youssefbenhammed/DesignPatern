package conjuredCucumberTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.gildedrose.Inventory;

import static org.junit.Assert.assertEquals;

public class CucumberSteps {

    private Inventory inventory;

    @Given("I have a new inventory")
    public void i_have_a_new_inventory()
    {
        inventory =  new Inventory();
    }

    @Then("the quality of the conjured item is 6")
    public void the_quality_of_the_conjured_item_is_6()
    {
        assertEquals(6,inventory.getItems()[5].getQuality());
    }

    @When("I update the inventory")
    public void i_update_the_inventory()
    {
        inventory.updateQuality();
    }

    @Then("the quality of the conjured item is 4")
    public void the_quality_of_the_conjured_item_is_4()
    {
        assertEquals(4,inventory.getItems()[5].getQuality());
    }

}
