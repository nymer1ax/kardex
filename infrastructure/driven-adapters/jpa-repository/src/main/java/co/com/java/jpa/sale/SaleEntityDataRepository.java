package co.com.java.jpa.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SaleEntityDataRepository extends CrudRepository<SaleEntityData, Integer>, QueryByExampleExecutor<SaleEntityData>  {

    List<SaleEntityData> findAll();

}
