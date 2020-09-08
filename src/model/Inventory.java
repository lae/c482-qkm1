package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Musee Ullah
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
    private static String lastPartSearch = "";
    private static String lastProductSearch = "";

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
     * Filters the Inventory by a user-provided string.
     * Part names and IDs are matched in a case-insensitive fashion and are stored in a static variable.
     *
     * @param searchQuery A lookup string to match against Part names and IDs.
     * @return A list of filtered Parts.
     */
    public static ObservableList<Part> getFilteredParts(String searchQuery) {
        // If we get a query for the same string twice in a row, no extra work needs to be done.
        if(searchQuery == lastPartSearch) {
            return filteredParts;
        }

        filteredParts.clear();
        for (Part part : allParts) {
            // Case-insensitive string matching
            if(part.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredParts.add(part);
            }
            else if(String.valueOf(part.getId()).contains(searchQuery)) {
                filteredParts.add(part);
            }
        }

        // Store the search string associated with the current state of filteredParts
        lastPartSearch = searchQuery;
        return filteredParts;
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

    /**
     * Filters the Inventory by a user-provided string.
     * Part names and IDs are matched in a case-insensitive fashion and are stored in a static variable.
     *
     * @param searchQuery A lookup string to match against Part names and IDs.
     * @return A list of filtered Parts.
     */
    public static ObservableList<Product> getFilteredProducts(String searchQuery) {
        // This function could probably be deduplicated but since Product and Part are their own parent classes,
        // it's not really a good idea for maintainability so leaving this as-is.

        // If we get a query for the same string twice in a row, no extra work needs to be done.
        if(searchQuery == lastProductSearch) {
            return filteredProducts;
        }

        filteredProducts.clear();
        for (Product product : allProducts) {
            // Case-insensitive string matching
            if(product.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredProducts.add(product);
            }
            else if(String.valueOf(product.getId()).contains(searchQuery)) {
                filteredProducts.add(product);
            }
        }

        // Store the search string associated with the current state of filteredProducts
        lastProductSearch = searchQuery;
        return filteredProducts;
    }
}
