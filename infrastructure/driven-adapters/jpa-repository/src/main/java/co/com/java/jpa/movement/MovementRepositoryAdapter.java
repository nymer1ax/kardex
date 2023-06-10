package co.com.java.jpa.movement;

import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.jpa.product.ProductEntityData;
import co.com.java.model.movements.Movement;
import co.com.java.model.movements.gateways.MovementsRepository;
import co.com.java.model.movementtype.gateways.MovementtypeRepository;
import co.com.java.model.product.Product;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovementRepositoryAdapter extends AdapterOperations<Movement, MovementEntityData, Integer, MovementEntityDataRepository> implements MovementsRepository {

    public MovementRepositoryAdapter(MovementEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Movement.class/* change for domain model */));
    }

    @Override
    public void saveMovement(Movement movement) {

        Product p = movement.getProduct();

        ProductEntityData productEntityData = ProductEntityData
                .builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .build();

        MovementEntityData m = MovementEntityData
                .builder()
                .type(movement.getType())
                .quantity(movement.getQuantity())
                .product(productEntityData)
                .subtotal(movement.getSubtotal())
                .build();

        repository.save(m);
    }

    @Override
    public void saveAllMovements(List<Movement> movements) {
        List<MovementEntityData> e = movements.stream().map(movement ->
                {
                    Product p = movement.getProduct();

                    ProductEntityData productEntityData = ProductEntityData
                            .builder()
                            .id(p.getId())
                            .name(p.getName())
                            .description(p.getDescription())
                            .price(p.getPrice())
                            .build();

                    return MovementEntityData
                            .builder()
                            .type(movement.getType())
                            .quantity(movement.getQuantity())
                            .product(productEntityData)
                            .subtotal(movement.getSubtotal())
                            .build();
                }
        ).collect(Collectors.toList());
        e.forEach(o -> repository.save(o));
    }

    @Override
    public List<Movement> getAllMovements() {
        return repository.findAll().stream().map(o -> {
            ProductEntityData p = o.getProduct();
            Product productEntityData = Product
                    .builder()
                    .id(p.getId())
                    .name(p.getName())
                    .description(p.getDescription())
                    .price(p.getPrice())
                    .build();
            return Movement
                    .builder()
                    .id(o.getId())
                    .type(o.getType())
                    .quantity(o.getQuantity())
                    .product(productEntityData)
                    .subtotal(o.getSubtotal())
                    .build();

        }).collect(Collectors.toList());
    }
}
