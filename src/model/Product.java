package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A model for products.
 *
 * @author Musee Ullah
 */
public class Product {
    private final ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock, min, max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        /* REQUIREMENT G, BULLET 1
         * I was running into a NullPointerException when attempting to associate a Part with a Product, e.g.:
         *      product.addAssociatedPart(Inventory.lookupPart(2));
         * That meant that I was trying to use something that was undefined, and the stack trace pointed to it
         * occurring within the addAssociatedPart function. lookupPart was returning a Part object just fine, so
         * the issue was with the associatedParts instance variable.
         * Turns out that while it was declared, it was not initialized so the add class method was trying to modify
         * something that didn't exist. To resolve this, I initialized an empty ArrayList here when a Product object
         * is created.
         */
        this.associatedParts = FXCollections.observableArrayList();
    }

    /**
     * @return the unique identifier of the Product
     */
    public int getId() {
        return id;
    }

    /**
     * @param id an unique identifier to set for the Product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name of the Product
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of the Product to set to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the unit price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the unit price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the amount of inventory available
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the amount of inventory available to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the minimum
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the minimum to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the maximum
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the maximum to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds an associated Part.
     *
     * @param part the Part to associate.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes an associated Part reference.
     *
     * @param selectedAssociatedPart the Part to delete.
     * @return true if list contained the specified Part.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Gets a list of associated Parts.
     *
     * @return a list of associated Parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
