package anotaai.blueprint.catalogproductapi.services;


import anotaai.blueprint.catalogproductapi.domain.exceptions.CategoryNotFoundException;
import anotaai.blueprint.catalogproductapi.domain.exceptions.ProductNotFoundException;
import anotaai.blueprint.catalogproductapi.domain.product.Product;
import anotaai.blueprint.catalogproductapi.domain.product.ProductDTO;
import anotaai.blueprint.catalogproductapi.repositories.ProductRepository;
import anotaai.blueprint.catalogproductapi.services.aws.AwsSnsService;
import anotaai.blueprint.catalogproductapi.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    private final AwsSnsService snsService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, AwsSnsService snsService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.snsService = snsService;
    }



    public Product insert(ProductDTO productData) {

        categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        var newProduct = new Product(productData);
        this.productRepository.save(newProduct);

        snsService.publishEvent(new MessageDTO(newProduct.toString()));
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
                    .orElseThrow(CategoryNotFoundException::new);
            product.setCategoryId(productData.categoryId());
        }

        this.productRepository.save(product);
        snsService.publishEvent(new MessageDTO(product.toString()));
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
