package co.com.java.usecase.createsale;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.product.Product;
import co.com.java.model.product.gateways.ProductRepository;
import co.com.java.model.sale.Sale;
import co.com.java.model.sale.gateways.SaleRepository;
import co.com.java.usecase.exceptions.InsufficientStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateSaleUseCaseTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private MovementsRepository movementsRepository;
    @Mock
    private SaleRepository saleRepository;

    private CreateSaleUseCase createSaleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createSaleUseCase = new CreateSaleUseCase(productRepository, inventoryRepository, movementsRepository, saleRepository);
    }

    @Test
    void createSale_ShouldThrowRuntimeException_WhenQuantitiesListIsNull() {
        // Arrange
        LocalDate date = LocalDate.now();
        List<Integer> productsList = List.of(1, 2, 3);

        // Act and Assert
        assertThrows(RuntimeException.class, () ->
                createSaleUseCase.createSale(null, date, productsList));
    }

    @Test
    void createSale_ShouldThrowRuntimeException_WhenDateIsNull() {
        // Arrange
        List<Integer> quantitiesList = List.of(1, 2, 3);
        List<Integer> productsList = List.of(1, 2, 3);

        // Act and Assert
        assertThrows(RuntimeException.class, () ->
                createSaleUseCase.createSale(quantitiesList, null, productsList));
    }

    @Test
    void createSale_ShouldThrowRuntimeException_WhenProductsListIsNull() {
        // Arrange
        LocalDate date = LocalDate.now();
        List<Integer> quantitiesList = List.of(1, 2, 3);

        // Act and Assert
        assertThrows(RuntimeException.class, () ->
                createSaleUseCase.createSale(quantitiesList, date, null));
    }

    @Test
    void createSale_ShouldThrowInsufficientStockException_WhenInventoryInsufficient() {
        // Arrange
        LocalDate date = LocalDate.now();
        //List of purchase of 60 elements
        List<Integer> quantitiesList = List.of(60);
        List<Integer> productsList = List.of(1);

        Product product1 = new Product(1, "Product 1", "Test", 100.0);
        //but only 1 in stock
        Inventory inventory1 = new Inventory(1, 1, product1);

        when(productRepository.findByProductId(1)).thenReturn(Optional.of(product1));
        when(inventoryRepository.getAllInventory()).thenReturn(List.of(inventory1));

        // Act and Assert
        assertThrows(InsufficientStockException.class, () ->
                createSaleUseCase.createSale(quantitiesList, date, productsList));
    }



    @Test
    void createSale_ShouldSaveSale_WhenInventorySufficient() {
        // Arrange
        LocalDate date = LocalDate.now();
        //List of purchase of 60 elements
        List<Integer> quantitiesList = List.of(60);
        List<Integer> productsList = List.of(1);

        Product product1 = new Product(1, "Product 1", "Test", 100.0);
        //but only 1 in stock
        Inventory inventory1 = new Inventory(1, 100, product1);

        when(productRepository.findByProductId(1)).thenReturn(Optional.of(product1));
        when(inventoryRepository.getAllInventory()).thenReturn(List.of(inventory1));
        when(productRepository.findByProductId(1)).thenReturn(Optional.of(product1));
        when(inventoryRepository.getAllInventory()).thenReturn(List.of(inventory1));

        // Act
        createSaleUseCase.createSale(quantitiesList, date, productsList);

        // Assert
        verify(inventoryRepository, times(1)).saveInventory(any(Inventory.class));
        verify(movementsRepository, times(1)).saveAllMovements(anyList());
        verify(saleRepository, times(1)).saveSale(any(Sale.class));
    }
}
