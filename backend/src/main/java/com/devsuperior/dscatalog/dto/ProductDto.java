package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Size(min = 5, max = 60, message = "Must have between 5 and 60 characters")
    @NotBlank(message = "Required field")
    private String name;
    @NotBlank(message = "Required field")
    private String description;
    @Positive(message = "Price should is positive")
    private Double price;
    private String imgUrl;
    @PastOrPresent(message = "Date does not can will future")
    private Instant date;

    private List<CategoryDto> categories = new ArrayList<>();

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDto(Product product) {
        BeanUtils.copyProperties(product, this);
    }

    public ProductDto(Product product, Set<Category> categories) {
        this(product);
        categories.forEach(cat -> this.categories.add(new CategoryDto(cat)));
    }
}
