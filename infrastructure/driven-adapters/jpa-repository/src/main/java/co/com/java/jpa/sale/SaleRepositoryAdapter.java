package co.com.java.jpa.sale;


import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.model.sale.Sale;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SaleRepositoryAdapter extends AdapterOperations<Sale, SaleEntityData, Integer, SaleEntityDataRepository> {
    public SaleRepositoryAdapter(SaleEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Sale.class/* change for domain model */));
    }
}

