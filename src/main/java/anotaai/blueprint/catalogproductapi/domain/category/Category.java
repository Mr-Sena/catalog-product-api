package anotaai.blueprint.catalogproductapi.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    private String title;
    private String desc;
    private String ownerId;

    public Category(CategoryDTO categoryData) {

        this.title = categoryData.title();
        this.desc = categoryData.desc();
        this.ownerId = categoryData.ownerId();
    }
}
