
package controller;
import com.example.filiera.dto.CreateProductRequest;
import com.example.filiera.dto.ProductResponse;
import com.example.filiera.domain.Prodotto;
import com.example.filiera.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController @RequestMapping("/api/products") @Tag(name="Prodotti")
public class ProductController {

    private final ProductService service;
    public ProductController(ProductService s){this.service=s;}

    @Operation(summary="Carica un prodotto")
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductRequest req){
        Prodotto p=service.create(req);
        return ResponseEntity.ok(ProductService.toResp(p));
    }
}
