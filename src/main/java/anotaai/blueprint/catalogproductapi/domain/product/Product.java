package anotaai.blueprint.catalogproductapi.domain.product;

import anotaai.blueprint.catalogproductapi.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "products")
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    private String title;
    private String desc;
    private String ownerId;
    private Integer price; // The value will be multiplied by 100 for preserver the decimal values.
    private Category category;

    public Product(ProductDTO productData) {

        this.title = productData.title();
        this.desc = productData.desc();
        this.price = productData.price();
        this.ownerId = productData.ownerId();
    }
}
