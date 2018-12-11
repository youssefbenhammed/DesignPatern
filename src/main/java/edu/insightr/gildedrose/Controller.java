package edu.insightr.gildedrose;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Inventory inventory = new Inventory();

    @FXML private FileChooser fileChooser = new FileChooser();

    @FXML private PieChart pieChart;
    @FXML private BarChart<String,Number> barChart;
    @FXML private BarChart<String,Number> barChart2;
    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> name;
    @FXML private TableColumn<Item, String> sellIn;
    @FXML private TableColumn<Item, String> quality;
    @FXML private ComboBox object_name;
    @FXML private TextField sell_In;
    @FXML private TextField quality_;
    @FXML private HBox barChart_;
    @FXML private HBox barChart2_;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellIn.setCellValueFactory(new PropertyValueFactory<>("sellIn"));
        quality.setCellValueFactory(new PropertyValueFactory<>("quality"));
        fileChooser.setTitle("Import Json File");

        tableView.getItems().setAll(inventory.getItems());

        pieChart.setLabelsVisible(false);
        pieChart.setTitle("Inventory");

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
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")));
        xAxis.setLabel("SellIn");

        NumberAxis yAxis = new NumberAxis("Number of items", 0, 5, 1);

        barChart = new BarChart<>(xAxis,yAxis);
        barChart.setTitle("Number of items in function of the Sellin");



        CategoryAxis xAxis2 = new CategoryAxis();
        xAxis2.setLabel("Creation date");

        NumberAxis yAxis2 = new NumberAxis("Number of items", 0, 5, 1);

        barChart2 = new BarChart<>(xAxis2,yAxis2);
        barChart2.setTitle("Number of items in function of the creation date");

        updateCharts();
    }

    @FXML
    protected void update() {

        inventory.updateQuality();
        tableView.refresh();
        updateCharts();
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

        updateCharts();

        tableView.getItems().setAll(inventory.getItems());
        tableView.getItems();
        tableView.refresh();
    }

    private void updateCharts()
    {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("+5 Dexterity Vest", inventory.count()[0]),
                        new PieChart.Data("Aged Brie", inventory.count()[1]),
                        new PieChart.Data("Elixir of the Mongoose", inventory.count()[2]),
                        new PieChart.Data("Sulfuras, Hand of Ragnaros", inventory.count()[3]),
                        new PieChart.Data("Backstage passes to a TAFKAL80ETC concert", inventory.count()[4]),
                        new PieChart.Data("Conjured Mana Cake", inventory.count()[5]));
        pieChart.setData(pieChartData);

        barChart.getData().clear();

        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.setName("items");

        for(int i = 0; i < 10; i++)
        {
            series.getData().add(new XYChart.Data<>("" + i + "", inventory.countNbrSellIn()[i]));
        }

        barChart.getData().addAll(series);

        barChart_.getChildren().add(barChart);


        barChart2.getData().clear();

        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
        series2.setName("items");

        List<LocalDate> dates = inventory.creationDates();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        for (LocalDate d : dates) {
            String formattedDate = d.format(formatter);
            series2.getData().add(new XYChart.Data<>(formattedDate, inventory.countDates()[dates.indexOf(d)]));
        }
        barChart2.getData().addAll(series2);

        barChart2_.getChildren().add(barChart2);
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
