package co.com.java.jpa.sale;


import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.model.sale.Sale;
import co.com.java.model.sale.gateways.SaleRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SaleRepositoryAdapter extends AdapterOperations<Sale, SaleEntityData, Integer, SaleEntityDataRepository> implements SaleRepository {
    public SaleRepositoryAdapter(SaleEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Sale.class/* change for domain model */));
    }

    @Override
    public void saveSale(Sale sale) {
        SaleEntityData s = toData(sale);
        repository.save(s);
    }

    @Override
    public List<Sale> getAllSales() {
        return repository.findAll().stream().map(o -> toEntity(o)).collect(Collectors.toList());
    }
}

