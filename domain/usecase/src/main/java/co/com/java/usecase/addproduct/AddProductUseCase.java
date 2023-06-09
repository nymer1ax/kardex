package co.com.java.usecase.addproduct;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.movements.Movement;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.movementtype.MovementType;
import co.com.java.model.product.Product;
import co.com.java.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AddProductUseCase {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final MovementsRepository movementsRepository;

    public void addProduct(Product product, Integer quantity) {

        Product p = validateProductExistence(product);

        List<Inventory> inventory = inventoryRepository.findByProduct(p);

        if (!inventory.isEmpty()) {
            for (Inventory i : inventory) {
                i.setQuantity(i.getQuantity() + quantity);
                inventoryRepository.saveInventory(i);
            }
        }
        Inventory newInventory = Inventory.builder()
                .product(p)
                .quantity(quantity)
                .build();

        inventoryRepository.saveInventory(newInventory);

        Movement movement = Movement
                .builder()
                .product(p)
                .quantity(quantity)
                .type(MovementType.IN)
                .build();

        movementsRepository.saveMovement(movement);

    }

    private Product validateProductExistence(Product product) {
        Optional<Product> p = productRepository.findByProductId(product.getId());
        if (p.isPresent()) {
            return p.get();
        }
        return productRepository.saveProduct(product);
    }

}
