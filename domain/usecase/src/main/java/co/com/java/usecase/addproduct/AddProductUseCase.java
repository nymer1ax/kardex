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

        Inventory inventory = consolidateInventories(p);

        if (inventory != null) {
            inventory.setQuantity(inventory.getQuantity() + quantity);
            inventoryRepository.saveInventory(inventory);
        } else {
            Inventory newInventory = Inventory.builder()
                    .product(p)
                    .quantity(quantity)
                    .build();
            inventoryRepository.saveInventory(newInventory);
        }

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

    public Inventory consolidateInventories(Product product) {
        List<Inventory> inventoryList = inventoryRepository.findByProduct(product);

        if (!inventoryList.isEmpty()) {
            Inventory firstInventory = inventoryList.get(0);

            // Sumar la cantidad a partir del segundo inventario
            for (int i = 1; i < inventoryList.size(); i++) {
                Inventory currentInventory = inventoryList.get(i);
                firstInventory.setQuantity(firstInventory.getQuantity() + currentInventory.getQuantity());

                // Eliminar los inventarios adicionales
                inventoryRepository.delete(currentInventory);
            }

            // Actualizar el inventario consolidado
            inventoryRepository.saveInventory(firstInventory);


            return firstInventory; // Devolver el inventario consolidado
        }

        return null; // Si no hay inventarios, devolver null
    }

}
