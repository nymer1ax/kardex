package co.com.java.jpa.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ProductEntityDataRepository extends CrudRepository<ProductEntityData, Integer>, QueryByExampleExecutor<ProductEntityData> {
}
