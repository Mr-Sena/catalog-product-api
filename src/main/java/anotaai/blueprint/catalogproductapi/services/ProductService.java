package anotaai.blueprint.catalogproductapi.services;


import anotaai.blueprint.catalogproductapi.domain.exceptions.CategoryNotFoundException;
import anotaai.blueprint.catalogproductapi.domain.exceptions.ProductNotFoundException;
import anotaai.blueprint.catalogproductapi.domain.product.Product;
import anotaai.blueprint.catalogproductapi.domain.product.ProductDTO;
import anotaai.blueprint.catalogproductapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }



    public Product insert(ProductDTO productData) {

        var category = categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        var newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);
        return newProduct;
    }


    public Product update(ProductDTO productData, String id) {

        var product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if (!productData.title().isEmpty()) product.setTitle(productData.title());
        if (!productData.desc().isEmpty()) product.setDesc(productData.desc());
        if ((productData.price() != null)) product.setPrice(productData.price());

        if (productData.categoryId() != null) {
            categoryService.getById(productData.categoryId())
                    .ifPresent(product::setCategory);
        }

        this.productRepository.save(product);
        return product;
    }

    public List<Product> getAll() {

        return this.productRepository.findAll();
    }

    public void delete(String id) {

        var product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.productRepository.delete(product);
    }
}
