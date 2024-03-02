package anotaai.blueprint.catalogproductapi.domain.product;

import anotaai.blueprint.catalogproductapi.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
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
    private String categoryId;


    public Product(ProductDTO productData) {

        this.title = productData.title();
        this.desc = productData.desc();
        this.price = productData.price();
        this.ownerId = productData.ownerId();
        this.categoryId = productData.categoryId();
    }


    @Override
    public String toString() {

        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("title", this.title);
        json.put("desc", this.desc);
        json.put("price", this.price);
        json.put("ownerId", this.ownerId);
        json.put("categoryId", this.categoryId);
        json.put("type", "product");

        return json.toString();
    }

    public String stringToRemove(){
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("ownerId", this.ownerId);
        json.put("type", "remove-product");

        return json.toString();
    }
}
