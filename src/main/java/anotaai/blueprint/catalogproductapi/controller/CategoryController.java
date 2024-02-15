package anotaai.blueprint.catalogproductapi.controller;

import anotaai.blueprint.catalogproductapi.domain.category.Category;
import anotaai.blueprint.catalogproductapi.domain.category.CategoryDTO;
import anotaai.blueprint.catalogproductapi.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    //@RequestBody ---> Request que recebe um corpo e converte automaticamente para um objeto Java.
    //@RequestParam --→ Optional url param (?)
    //@PathVariable --→ Parâmetro obrigatório para a url.


    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData) {

        var newCategory = this.service.insert(categoryData);

        return ResponseEntity.ok().body(newCategory);
    }


    @GetMapping
    public ResponseEntity<List<Category>> getAll () {

        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Category> update (@RequestBody CategoryDTO categoryData, @PathVariable("id") String id) {

        var theCategory = this.service.update(categoryData, id);

        return ResponseEntity.ok().body(theCategory);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Category> remove (@PathVariable("id") String id) {

        this.service.delete(id);

        return ResponseEntity.ok().build();
    }
}
