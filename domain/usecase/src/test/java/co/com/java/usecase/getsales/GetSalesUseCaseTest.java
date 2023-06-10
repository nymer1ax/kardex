package co.com.java.usecase.getsales;

import co.com.java.model.product.Product;
import co.com.java.model.sale.Sale;
import co.com.java.model.sale.gateways.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetSalesUseCaseTest {
    @Mock
    private SaleRepository saleRepository;

    private GetSalesUseCase getSalesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getSalesUseCase = new GetSalesUseCase(saleRepository);
    }

    @Test
    void getSales_ShouldReturnSaleList_WhenSalesNotEmpty() {
        // Arrange

        Product product1 = new Product(1, "Product 1", "test", 50.0);

        List<Product> products = List.of(product1);
        List<Integer> quantities = List.of(10);

        Sale sale1 = new Sale(1, LocalDate.now(), products, quantities, new BigDecimal(500));

        when(saleRepository.getAllSales()).thenReturn(List.of(sale1));

        // Act
        List<Sale> saleList = getSalesUseCase.getSales();

        // Assert
        assertEquals(1, saleList.size());
        assertEquals(sale1, saleList.get(0));
    }

    @Test
    void getSales_ShouldReturnEmptyList_WhenSalesIsEmpty() {
        // Arrange
        when(saleRepository.getAllSales()).thenReturn(new ArrayList<>());

        // Act
        List<Sale> saleList = getSalesUseCase.getSales();

        // Assert
        assertEquals(0, saleList.size());
    }
}
