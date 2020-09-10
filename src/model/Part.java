package model;

/**
 * A model for parts.
 *
 * @author Musee Ullah
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock, min, max;

    protected Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the unique identifier of the Part
     */
    public int getId() {
        return id;
    }

    /**
     * @param id an unique identifier to set for the Part
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name of the Part
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name of the Part to set to
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
}