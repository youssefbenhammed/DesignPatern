package edu.insightr.gildedrose;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Inventory inventory=new Inventory();

    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> name;
    @FXML private TableColumn<Item, String> sellIn;
    @FXML private TableColumn<Item, String> quality;
    @FXML private ComboBox Object_name;
    @FXML private TextField SellIn;
    @FXML private TextField Quality;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        sellIn.setCellValueFactory(new PropertyValueFactory<Item, String>("sellIn"));
        quality.setCellValueFactory(new PropertyValueFactory<Item, String>("quality"));

        tableView.getItems().setAll(inventory.getItems());

        List<String> gvalues = new ArrayList<String>();
        gvalues.add("+5 Dexterity Vest");
        gvalues.add("Aged Brie");
        gvalues.add("Elixir of the Mongoose");
        gvalues.add("Sulfuras, Hand of Ragnaros");
        gvalues.add("Backstage passes to a TAFKAL80ETC concert");
        gvalues.add("Conjured Mana Cake");
        ObservableList<String> names = FXCollections.observableArrayList(gvalues);
        Object_name.setItems(names);
    }

    @FXML
    protected void update(ActionEvent event) {

        inventory.updateQuality();
        tableView.refresh();
    }

    @FXML
    protected void add(ActionEvent event) {

        String item_name;
        int item_sell_in;
        int quality;

        item_name=Object_name.getValue().toString();
        item_sell_in=  Integer.parseInt(SellIn.getText());
        quality=  Integer.parseInt(Quality.getText());

        Item addedItem = new Item(item_name,item_sell_in,quality);

        Item[] items = new Item[this.inventory.getItems().length+1];

        for(int i=0;i<this.inventory.getItems().length;i++)
        {
            items[i]=this.inventory.getItems()[i];
        }
        items[this.inventory.getItems().length]=addedItem;

        inventory=new Inventory(items);

        tableView.getItems().setAll(inventory.getItems());
        tableView.getItems();
        tableView.refresh();
    }



}