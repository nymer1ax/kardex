package co.com.java.jpa.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ProductEntityDataRepository extends CrudRepository<ProductEntityData, Integer>, QueryByExampleExecutor<ProductEntityData> {

    List<ProductEntityData> findAll();
    List<ProductEntityData> findAllByIdIn(List<Integer> ids);


}
