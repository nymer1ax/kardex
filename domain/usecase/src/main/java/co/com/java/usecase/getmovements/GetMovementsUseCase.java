package co.com.java.usecase.getmovements;

import co.com.java.model.movements.Movement;
import co.com.java.model.movements.gateways.MovementsRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetMovementsUseCase {
    private final MovementsRepository movementsRepository;
    List<Movement> getAllMovements(){
        return movementsRepository.getAllMovements();
    }
}
