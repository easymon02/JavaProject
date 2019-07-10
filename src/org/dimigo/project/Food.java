package org.dimigo.project;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class Food {

    private SimpleStringProperty name;
    private SimpleStringProperty price;
    private SimpleStringProperty place;
    private SimpleStringProperty recom;
    private SimpleStringProperty best;
    private CheckBox select;

    public Food(String name, String price, String place, String recom, String best) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(String.format("%,d원", Integer.parseInt(price)));
        this.place = new SimpleStringProperty(place);
        this.recom = new SimpleStringProperty(recom);
        this.best = new SimpleStringProperty(best);
        this.select = new CheckBox();
    }


    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getPlace() {
        return place.get();
    }

    public SimpleStringProperty placeProperty() {
        return place;
    }

    public void setPlace(String place) {
        this.place.set(place);
    }

    public String getRecom() {
        return recom.get();
    }

    public SimpleStringProperty recomProperty() {
        return recom;
    }

    public void setRecom(String recom) {
        this.recom.set(recom);
    }

    public String getBest() {
        return best.get();
    }

    public SimpleStringProperty bestProperty() {
        return best;
    }

    public void setBest(String best) {
        this.best.set(best);
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "Food{" + "name=" + name + ", price=" + price + ", place=" + place + ", recom=" + recom + ", best=" + best + '}';
    }


}
