package co.com.java.usecase.addproduct;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.movements.Movement;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.movementtype.MovementType;
import co.com.java.model.product.Product;
import co.com.java.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AddProductUseCase {
    private ProductRepository productRepository;
    private InventoryRepository inventoryRepository;
    private MovementsRepository movementsRepository;

    public void addProduct(Product product, Integer quantity) {

        Optional<Inventory> inventory = inventoryRepository.findByProduct(product);

        if (inventory.isEmpty()) {
            Inventory i = inventory.get();
            i.setQuantity(i.getQuantity() + quantity);
        }
        Inventory newInventory = Inventory.builder()
                .product(product)
                .quantity(quantity)
                .build();
        inventoryRepository.saveInventory(newInventory);

        Movement movement = Movement
                .builder()
                .product(product)
                .quantity(quantity)
                .type(MovementType.IN)
                .build();

        movementsRepository.saveMovement(movement);

    }

}
