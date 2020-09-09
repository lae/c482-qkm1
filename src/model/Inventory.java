package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Musee Ullah
 */
public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
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

    /**
     * Looks up a Part by ID.
     *
     * @param partId The unique identifier of the Part.
     * @return the first Part whose ID matches the given ID.
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }

        return null;
    }

    /**
     * Filters the Parts Inventory by a user-provided string.
     * Part names are matched in a case-insensitive fashion into a new list and returned.
     *
     * @param partName A lookup string to match against Part names.
     * @return A list of Parts that match the given string.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> result = FXCollections.observableArrayList();
        partName = partName.toLowerCase();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName)) {
                result.add(part);
            }
        }

        return result;
    }

    /**
     * Updates a Part.
     *
     * @param index   the position in the inventory list where the Part is stored.
     * @param newPart the updated Part object to update the inventory with.
     */
    public static void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }

    /**
     * Deletes a Part.
     *
     * @param selectedPart the Part object to delete.
     * @return true if the inventory contained the specified Part.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * @return A list of all Parts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Filters the Parts Inventory by a user-provided string.
     * Part names and IDs are matched in a case-insensitive fashion and are stored in a static variable.
     *
     * @param searchQuery A lookup string to match against Part names and IDs.
     * @return A list of filtered Parts.
     */
    public static ObservableList<Part> getFilteredParts(String searchQuery) {
        return getFilteredParts(searchQuery, false);
    }

    /**
     * Filters the Parts Inventory by a user-provided string.
     * Part names and IDs are matched in a case-insensitive fashion and are stored in a static variable.
     *
     * @param searchQuery A lookup string to match against Part names and IDs.
     * @param reset       if true, ensure that the search executes again
     * @return A list of filtered Parts.
     */
    public static ObservableList<Part> getFilteredParts(String searchQuery, boolean reset) {
        // If we get a query for the same string twice in a row, no extra work needs to be done.
        if (!reset && searchQuery.equals(lastPartSearch)) {
            return filteredParts;
        }

        filteredParts.clear();

        // If the search query is a positive integer, we want to lookup a Part whose ID matches.
        // However, it's possible that the part name also has digits, so we want to return those as well.
        if (searchQuery.matches("\\d+")) {
            int lookupId = Integer.parseInt(searchQuery);
            Part foundPart = Inventory.lookupPart(lookupId);
            if (foundPart != null) {
                filteredParts.add(foundPart);
            }
            ObservableList<Part> foundParts = Inventory.lookupPart(searchQuery);
            // There's a chance that lookupPart will include the Part found earlier, so ensure it's not duplicated.
            foundParts.removeIf(p -> (p.getId() == lookupId));
            filteredParts.addAll(foundParts);
        } else {
            filteredParts = Inventory.lookupPart(searchQuery);
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

    /**
     * Looks up a Product by ID.
     *
     * @param productId The unique identifier of the Product.
     * @return the first Product whose ID matches the given ID.
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }

        return null;
    }

    /**
     * Filters the Products Inventory by a user-provided string.
     * Product names are matched in a case-insensitive fashion into a new list and returned.
     *
     * @param productName A lookup string to match against Product names.
     * @return A list of Products that match the given string.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> result = FXCollections.observableArrayList();
        productName = productName.toLowerCase();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName)) {
                result.add(product);
            }
        }

        return result;
    }

    /**
     * Updates a Product.
     *
     * @param index      the position in the inventory list where the Product is stored.
     * @param newProduct the updated Product object to update the inventory with.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Deletes a Product.
     *
     * @param selectedProduct the Product object to delete.
     * @return true if the inventory contained the specified Product.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * @return A list of all Products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Filters the Products Inventory by a user-provided string.
     * Part names and IDs are matched in a case-insensitive fashion and are stored in a static variable.
     *
     * @param searchQuery A lookup string to match against Part names and IDs.
     * @return A list of filtered Parts.
     */
    public static ObservableList<Product> getFilteredProducts(String searchQuery) {
        return getFilteredProducts(searchQuery, false);
    }

    /**
     * Filters the Products Inventory by a user-provided string.
     * Part names and IDs are matched in a case-insensitive fashion and are stored in a static variable.
     *
     * @param searchQuery A lookup string to match against Part names and IDs.
     * @param reset       if true, ensure that the search executes again
     * @return A list of filtered Parts.
     */
    public static ObservableList<Product> getFilteredProducts(String searchQuery, boolean reset) {
        // This function could probably be de-duplicated but since Product and Part are their own parent classes,
        // it's not really a good idea for maintainability so leaving this as-is.

        // If we get a query for the same string twice in a row, no extra work needs to be done.
        if (!reset && searchQuery.equals(lastProductSearch)) {
            return filteredProducts;
        }

        filteredProducts.clear();
        // If the search query is a positive integer, we want to lookup a Product whose ID matches.
        // However, it's possible that the product name also has digits, so we want to return those as well.
        if (searchQuery.matches("\\d+")) {
            int lookupId = Integer.parseInt(searchQuery);
            Product foundProduct = Inventory.lookupProduct(lookupId);
            if (foundProduct != null) {
                filteredProducts.add(foundProduct);
            }
            ObservableList<Product> foundProducts = Inventory.lookupProduct(searchQuery);
            // There's a chance that lookupProduct will include the Product found earlier, so ensure it's not duplicated.
            foundProducts.removeIf(p -> (p.getId() == lookupId));
            filteredProducts.addAll(foundProducts);
        } else {
            filteredProducts = Inventory.lookupProduct(searchQuery);
        }

        // Store the search string associated with the current state of filteredProducts
        lastProductSearch = searchQuery;
        return filteredProducts;
    }

    /**
     * Identifies the next available Part ID.
     *
     * @return the next available Part ID.
     */
    public static int getNextPartId() {
        int max = 0;
        for (Part part : allParts) {
            int id = part.getId();
            if (id > max) {
                max = id;
            }
        }
        return max + 1;
    }
}
