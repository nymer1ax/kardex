package co.com.java.jpa.sale;


import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.model.sale.Sale;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.function.Function;
@Repository
public class SaleRepositoryAdapter extends AdapterOperations<Sale, SaleEntityData, Integer, SaleEntityDataRepository> {

    protected SaleRepositoryAdapter(SaleEntityDataRepository repository, ObjectMapper mapper, Function<SaleEntityData, Sale> toEntityFn) {
        super(repository, mapper, d -> mapper.map(d, Sale.class));
    }
}

