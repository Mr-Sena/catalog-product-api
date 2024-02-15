package anotaai.blueprint.catalogproductapi.services;

import anotaai.blueprint.catalogproductapi.domain.category.Category;
import anotaai.blueprint.catalogproductapi.domain.category.CategoryDTO;
import anotaai.blueprint.catalogproductapi.domain.exceptions.CategoryNotFoundException;
import anotaai.blueprint.catalogproductapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }



    public Category insert(CategoryDTO categoryData) {

        var newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        return newCategory;
    }


    public Category update(CategoryDTO categoryData, String id) {

        var category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if (!categoryData.desc().isEmpty()) category.setDesc(categoryData.desc());

        this.repository.save(category);
        return category;
    }

    public List<Category> getAll() {

        return this.repository.findAll();
    }


    public Optional<Category> getById(String id) {

        return this.repository.findById(id);
    }



    public void delete(String id) {

        var category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);
    }
}
