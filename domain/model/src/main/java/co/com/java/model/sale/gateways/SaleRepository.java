package co.com.java.model.sale.gateways;

import co.com.java.model.sale.Sale;

import java.util.List;

public interface SaleRepository {
    void saveSale(Sale sale);

    List<Sale> getAllSales();
}
