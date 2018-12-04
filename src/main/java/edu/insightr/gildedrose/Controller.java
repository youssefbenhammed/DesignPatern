package edu.insightr.gildedrose;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Inventory inventory = new Inventory();

    @FXML FileChooser fileChooser = new FileChooser();

    @FXML private PieChart pieChart;
    @FXML private BarChart barChart;
    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> name;
    @FXML private TableColumn<Item, String> sellIn;
    @FXML private TableColumn<Item, String> quality;
    @FXML private ComboBox object_name;
    @FXML private TextField sell_In;
    @FXML private TextField quality_;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellIn.setCellValueFactory(new PropertyValueFactory<>("sellIn"));
        quality.setCellValueFactory(new PropertyValueFactory<>("quality"));
        fileChooser.setTitle("Import Json File");

        tableView.getItems().setAll(inventory.getItems());


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("+5 Dexterity Vest", inventory.count()[0]),
                        new PieChart.Data("Aged Brie", inventory.count()[1]),
                        new PieChart.Data("Elixir of the Mongoose", inventory.count()[2]),
                        new PieChart.Data("Sulfuras, Hand of Ragnaros", inventory.count()[3]),
                        new PieChart.Data("Backstage passes to a TAFKAL80ETC concert", inventory.count()[4]),
                        new PieChart.Data("Conjured Mana Cake", inventory.count()[5]));

        pieChart.setLabelsVisible(false);
        pieChart.setTitle("Inventory");
        pieChart.setData(pieChartData);

        List<String> gvalues = new ArrayList<>();
        gvalues.add("+5 Dexterity Vest");
        gvalues.add("Aged Brie");
        gvalues.add("Elixir of the Mongoose");
        gvalues.add("Sulfuras, Hand of Ragnaros");
        gvalues.add("Backstage passes to a TAFKAL80ETC concert");
        gvalues.add("Conjured Mana Cake");
        ObservableList<String> names = FXCollections.observableArrayList(gvalues);
        object_name.setItems(names);


        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<>(xAxis,yAxis);
        barChart.setTitle("Barchart");
        xAxis.setLabel("SellIn");
        yAxis.setLabel("Number of items");

        XYChart.Series series = new XYChart.Series();
        series.setName("items");
        series.getData().add(new XYChart.Data(inventory.getItems()[0].getSellIn(), inventory.count()[0]));
        series.getData().add(new XYChart.Data(inventory.getItems()[1].getSellIn(), inventory.count()[1]));
        series.getData().add(new XYChart.Data(inventory.getItems()[2].getSellIn(), inventory.count()[2]));
        series.getData().add(new XYChart.Data(inventory.getItems()[3].getSellIn(), inventory.count()[3]));
        series.getData().add(new XYChart.Data(inventory.getItems()[4].getSellIn(), inventory.count()[4]));
        series.getData().add(new XYChart.Data(inventory.getItems()[5].getSellIn(), inventory.count()[5]));

        barChart.getData().add(series);
    }

    @FXML
    protected void update() {

        inventory.updateQuality();
        tableView.refresh();
    }

    @FXML
    protected void add() {

        String item_name;
        int item_sell_in;
        int quality;

        item_name = object_name.getValue().toString();
        item_sell_in =  Integer.parseInt(sell_In.getText());
        quality =  Integer.parseInt(quality_.getText());

        Item addedItem = new Item(item_name,item_sell_in,quality);

        Item[] items = new Item[this.inventory.getItems().length+1];

        for(int i = 0; i < this.inventory.getItems().length; i++)
        {
            items[i] = this.inventory.getItems()[i];
        }
        items[this.inventory.getItems().length] = addedItem;

        inventory = new Inventory(items);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("+5 Dexterity Vest", inventory.count()[0]),
                        new PieChart.Data("Aged Brie", inventory.count()[1]),
                        new PieChart.Data("Elixir of the Mongoose", inventory.count()[2]),
                        new PieChart.Data("Sulfuras, Hand of Ragnaros", inventory.count()[3]),
                        new PieChart.Data("Backstage passes to a TAFKAL80ETC concert", inventory.count()[4]),
                        new PieChart.Data("Conjured Mana Cake", inventory.count()[5]));
        pieChart.setData(pieChartData);

        tableView.getItems().setAll(inventory.getItems());
        tableView.getItems();
        tableView.refresh();
    }

    @FXML
    protected void import_()
    {
        //TODO Modifier lors du choix du fichier "Enregistrer" en "Ouvrir"
        Gson gson = new Gson();

        String jsonContent = "";
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Json File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json", "*.json")
        );
        File file = fileChooser.showSaveDialog(stage);


        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine())!= null) {
                jsonContent += st;
            }

            Inventory importedInventory = gson.fromJson(jsonContent,Inventory.class);
            inventory.setItems(importedInventory.getItems());
            inventory.printInventory();

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("+5 Dexterity Vest", inventory.count()[0]),
                            new PieChart.Data("Aged Brie", inventory.count()[1]),
                            new PieChart.Data("Elixir of the Mongoose", inventory.count()[2]),
                            new PieChart.Data("Sulfuras, Hand of Ragnaros", inventory.count()[3]),
                            new PieChart.Data("Backstage passes to a TAFKAL80ETC concert", inventory.count()[4]),
                            new PieChart.Data("Conjured Mana Cake", inventory.count()[5]));
            pieChart.setData(pieChartData);

            tableView.getItems().setAll(inventory.getItems());
            tableView.getItems();
            tableView.refresh();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


}
