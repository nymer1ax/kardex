package co.com.java.api;
import co.com.java.api.request.AddProductRequest;
import co.com.java.api.request.CreateSaleRequest;
import co.com.java.model.inventory.Inventory;
import co.com.java.model.movements.Movement;
import co.com.java.model.product.Product;
import co.com.java.model.sale.Sale;
import co.com.java.usecase.addproduct.AddProductUseCase;
import co.com.java.usecase.createsale.CreateSaleUseCase;
import co.com.java.usecase.getavailableproducts.GetAvailableProductsUseCase;
import co.com.java.usecase.getinventory.GetInventoryUseCase;
import co.com.java.usecase.getmovements.GetMovementsUseCase;
import co.com.java.usecase.getsales.GetSalesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
@RequiredArgsConstructor
public class ApiRest {

    private final CreateSaleUseCase createSaleUseCase;
    private final GetMovementsUseCase getMovementsUseCase;
    private final GetSalesUseCase getSalesUseCase;
    private final GetInventoryUseCase getInventoryUseCase;
    private final GetAvailableProductsUseCase getAvailableProductsUseCase;
    private final AddProductUseCase addProductUseCase;

    @PostMapping(path = "/products")
    public ResponseEntity addProduct(@RequestBody AddProductRequest addProductRequest) {
        addProductUseCase.addProduct(addProductRequest.getProduct(), addProductRequest.getQuantity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/movements")
    public ResponseEntity<List<Movement>> getMovements() {
         return ResponseEntity.status(HttpStatus.OK).body(getMovementsUseCase.getAllMovements());
    }

    @GetMapping("/sale")
    public ResponseEntity<List<Sale>> getSale(){
        return ResponseEntity.status(HttpStatus.OK).body(getSalesUseCase.getSales());
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getInventory(){
        return ResponseEntity.status(HttpStatus.OK).body(getInventoryUseCase.getAllInventory());
    }

    @GetMapping("/products/available")
    public ResponseEntity<List<Product>> getAvailableProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(getAvailableProductsUseCase.getAvailableProducts());
    }

    @PostMapping("/sale")
    public ResponseEntity createSale(@RequestBody CreateSaleRequest sale){
        createSaleUseCase.createSale(sale.getQuantities(), sale.getDate(), sale.getProducts());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
