package co.com.java.usecase.getavailableproducts;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAvailableProductsUseCaseTest {
    @Mock
    private InventoryRepository inventoryRepository;

    private GetAvailableProductsUseCase getAvailableProductsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getAvailableProductsUseCase = new GetAvailableProductsUseCase(inventoryRepository);
    }

    @Test
    void getAvailableProducts_ShouldReturnAvailableProducts_WhenInventoryNotEmpty() {
        // Arrange
        Product product1 = new Product(1, "Product 1", "test", 50.0);
        Product product2 = new Product(2, "Product 2", "test", 250.0);
        Product product3 = new Product(3, "Product 3", "test", 100.0);

        Inventory inventory1 = new Inventory(1, 10, product1);
        Inventory inventory2 = new Inventory(2, 0, product2);
        Inventory inventory3 = new Inventory(3, 5, product3);

        when(inventoryRepository.getAllInventory()).thenReturn(List.of(inventory1, inventory2, inventory3));

        // Act
        List<Product> availableProducts = getAvailableProductsUseCase.getAvailableProducts();

        // Assert
        assertEquals(2, availableProducts.size());
        assertEquals(product1, availableProducts.get(0));
        assertEquals(product3, availableProducts.get(1));
    }

    @Test
    void getAvailableProducts_ShouldReturnEmptyList_WhenInventoryIsEmpty() {
        // Arrange
        when(inventoryRepository.getAllInventory()).thenReturn(new ArrayList<>());

        // Act
        List<Product> availableProducts = getAvailableProductsUseCase.getAvailableProducts();

        // Assert
        assertEquals(0, availableProducts.size());
    }
}
