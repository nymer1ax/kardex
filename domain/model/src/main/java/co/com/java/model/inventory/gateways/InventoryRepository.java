package co.com.java.model.inventory.gateways;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository {

    List<Inventory> findByProduct(Product product);
    void saveInventory(Inventory inventory);
    List<Inventory> getAllInventory();

    void delete(Inventory currentInventory);
}
