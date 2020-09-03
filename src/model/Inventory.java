package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Musee Ullah
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Inserts a new Part into the Inventory.
     *
     * @param newPart A new Part.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /*public static Part lookupPart(int partId) {

    }
    public static ObservableList<Part> lookupPart(String partName) {
        
    }
    public static void updatePart(int index, Part selectedPart) {
        
    }
    public static boolean deletePart(Part selectedPart) {
        
    }*/

    /**
     * @return A list of all Parts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Inserts a new Product into the Inventory.
     *
     * @param newProduct A new Product.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /*public static Product lookupProduct(int productId) {

    }
    public static ObservableList<Product> lookupProduct(String productName) {
        
    }
    public static void updateProduct(int index, Product selectedProduct) {
        
    }
    public static boolean deleteProduct(Product selectedProduct) {
        
    }*/

    /**
     * @return A list of all Products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
