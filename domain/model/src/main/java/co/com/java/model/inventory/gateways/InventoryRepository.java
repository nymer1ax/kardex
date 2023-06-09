package co.com.java.model.inventory.gateways;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.product.Product;

import java.util.Optional;

public interface InventoryRepository {

    Optional<Inventory> findByProduct(Product product);
    void saveInventory(Inventory inventory);
}
