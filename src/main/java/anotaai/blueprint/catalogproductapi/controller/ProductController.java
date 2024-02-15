package anotaai.blueprint.catalogproductapi.controller;

import anotaai.blueprint.catalogproductapi.domain.product.Product;
import anotaai.blueprint.catalogproductapi.domain.product.ProductDTO;
import anotaai.blueprint.catalogproductapi.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productData) {

        var newProduct = this.service.insert(productData);

        return ResponseEntity.ok().body(newProduct);
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAll () {

        List<Product> products = this.service.getAll();
        return ResponseEntity.ok().body(products);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> update (@RequestBody ProductDTO productData, @PathVariable("id") String id) {

        var theProduct = this.service.update(productData, id);

        return ResponseEntity.ok().body(theProduct);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Product> remove (@PathVariable("id") String id) {

        this.service.delete(id);

        return ResponseEntity.ok().build();
    }


}
