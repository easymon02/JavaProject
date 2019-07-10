package org.dimigo.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FoodSearchController implements Initializable {
    @FXML
    ComboBox<String> cbSearch;

    @FXML
    TableView foodTableView;

    @FXML
    RadioButton recommend;

    @FXML
    RadioButton best;

    @FXML
    Label total;

    List<Food> foodList = new ArrayList<>();

    ObservableList<Food> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FoodSearch.parseFood();
            List<String> comboItems = new ArrayList();
            for(String item : FoodSearch.rest.keySet()) {
                comboItems.add(item);
            }
            cbSearch.setItems(FXCollections.observableList(comboItems));

            TableColumn select = (TableColumn) foodTableView.getColumns().get(0);
            select.setCellValueFactory(new PropertyValueFactory<Food, String>("select"));

            TableColumn name = (TableColumn) foodTableView.getColumns().get(1);
            name.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));

            TableColumn price = (TableColumn) foodTableView.getColumns().get(2);
            price.setCellValueFactory(new PropertyValueFactory<Food, String>("price"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSearchAction() {
            String text = cbSearch.getValue();
            foodList = FoodSearch.getFood(text);

            data = FXCollections.observableArrayList(foodList);
            foodTableView.setItems(data);

            recommend.setSelected(false);
            best.setSelected(false);
            for(Food s : foodList) {
                s.getSelect().setSelected(false);
            }
            total.setText("");
        }

    @FXML
    public void checkMenu() {
        List<Food> list = new ArrayList();
        try {
            if (recommend.isSelected()) {
                for (Food s : foodList) {
                    if (s.getRecom().equals("Y")) {
                        list.add(s);
                    }
                }
            } else if (best.isSelected()) {
                for (Food s : foodList) {
                    if (s.getBest().equals("Y")) {
                        list.add(s);
                    }
                }
            }
            total.setText("");
            for (Food s : foodList) {
                s.getSelect().setSelected(false);
            }
            data = FXCollections.observableArrayList(list);
            foodTableView.setItems(data);
        } catch (NullPointerException npe) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClick() {
        int sum = 0;
        for(Food s : foodList) {
            int price = Integer.parseInt(s.getPrice().replaceAll(",","").replaceAll("원",""));
            if(s.getSelect().isSelected() == true) {
                sum = sum + price;
            }
        }
        String t = String.format("%,d원", sum);
        total.setText(t);
    }
}
