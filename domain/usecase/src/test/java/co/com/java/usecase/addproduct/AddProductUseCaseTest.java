package co.com.java.usecase.addproduct;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.movements.Movement;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.movementtype.MovementType;
import co.com.java.model.product.Product;
import co.com.java.model.product.gateways.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddProductUseCaseTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private MovementsRepository movementsRepository;

    private AddProductUseCase addProductUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addProductUseCase = new AddProductUseCase(productRepository, inventoryRepository, movementsRepository);
    }

    @Test
    void addProduct_ShouldIncreaseInventoryQuantity_WhenInventoryExists() {
        // Arrange
        Product product = new Product(123, "Test", "Product", 100.0);
        Inventory inventory = new Inventory(1,10, product);
        when(productRepository.findByProductId(123)).thenReturn(Optional.of(product));
        when(inventoryRepository.findByProduct(product)).thenReturn(List.of(inventory));

        // Act
        addProductUseCase.addProduct(product, 5);

        // Assert
        verify(inventoryRepository, times(1)).saveInventory(inventory);
        verify(movementsRepository, times(1)).saveMovement(any(Movement.class));
    }

    @Test
    void addProduct_ShouldCreateNewInventory_WhenInventoryDoesNotExist() {
        // Arrange
        Product product = new Product(123, "Test", "Product", 100.0);
        when(productRepository.findByProductId(123)).thenReturn(Optional.of(product));
        when(inventoryRepository.findByProduct(product)).thenReturn(new ArrayList<>());

        // Act
        addProductUseCase.addProduct(product, 5);

        // Assert
        verify(inventoryRepository, times(1)).saveInventory(any(Inventory.class));
        verify(movementsRepository, times(1)).saveMovement(any(Movement.class));
    }

    @Test
    void addProduct_ShouldCreateNewProductAndInventory_WhenProductIdIsNull() {
        // Arrange
        Product product = new Product(null, "Test", "Product", 100.0);
        when(productRepository.findAllByName("Test Product")).thenReturn(List.of());
        when(productRepository.saveProduct(product)).thenReturn(product);

        // Act
        addProductUseCase.addProduct(product, 5);

        // Assert
        verify(productRepository, times(1)).saveProduct(product);
        verify(inventoryRepository, times(1)).saveInventory(any(Inventory.class));
        verify(movementsRepository, times(1)).saveMovement(any(Movement.class));
    }
}
