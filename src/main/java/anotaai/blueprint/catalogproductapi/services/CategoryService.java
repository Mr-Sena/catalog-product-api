package anotaai.blueprint.catalogproductapi.services;

import anotaai.blueprint.catalogproductapi.domain.category.Category;
import anotaai.blueprint.catalogproductapi.domain.category.CategoryDTO;
import anotaai.blueprint.catalogproductapi.domain.exceptions.CategoryNotFoundException;
import anotaai.blueprint.catalogproductapi.repositories.CategoryRepository;
import anotaai.blueprint.catalogproductapi.services.aws.AwsSnsService;
import anotaai.blueprint.catalogproductapi.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    private final AwsSnsService snsService;


    public CategoryService(CategoryRepository repository, AwsSnsService snsService) {
        this.repository = repository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO categoryData) {

        var newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        snsService.publishEvent(new MessageDTO(newCategory.toString()));
        return newCategory;
    }


    public Category update(CategoryDTO categoryData, String id) {

        var category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if (!categoryData.desc().isEmpty()) category.setDesc(categoryData.desc());

        this.repository.save(category);

        snsService.publishEvent(new MessageDTO(category.toString()));
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
