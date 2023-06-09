package co.com.java.usecase.getsales;

import co.com.java.model.inventory.gateways.InventoryRepository;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.product.gateways.ProductRepository;
import co.com.java.model.sale.Sale;
import co.com.java.model.sale.gateways.SaleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetSalesUseCase {

    private final SaleRepository saleRepository;

    public List<Sale> getSales(){
        return saleRepository.getAllSales();
    }

}
