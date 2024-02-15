package anotaai.blueprint.catalogproductapi.domain.product;

import anotaai.blueprint.catalogproductapi.domain.category.Category;

public record ProductDTO(String title, String desc, String ownerId, String categoryId, Integer price) {
}
