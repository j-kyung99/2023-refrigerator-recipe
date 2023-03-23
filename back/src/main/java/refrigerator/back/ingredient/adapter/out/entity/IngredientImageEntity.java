package refrigerator.back.ingredient.adapter.out.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ingredient_image")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_image_id")
    private Long ingredientImageId;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "ingredient_id", nullable = false)
    private Long ingredientId;
}
