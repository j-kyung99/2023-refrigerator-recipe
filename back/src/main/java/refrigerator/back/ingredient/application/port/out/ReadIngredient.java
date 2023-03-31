package refrigerator.back.ingredient.application.port.out;

import refrigerator.back.ingredient.adapter.in.dto.IngredientDetailResponseDTO;
import refrigerator.back.ingredient.adapter.in.dto.IngredientRegisteredResponseDTO;
import refrigerator.back.ingredient.adapter.in.dto.IngredientResponseDTO;
import refrigerator.back.ingredient.application.domain.Ingredient;
import refrigerator.back.ingredient.application.domain.IngredientSearchCondition;
import refrigerator.back.ingredient.application.domain.RegisteredIngredient;

import java.util.List;

public interface ReadIngredient {

    Ingredient getIngredientById(Long id);

    IngredientDetailResponseDTO getIngredientDetail(Long id);

    List<IngredientResponseDTO> getIngredientList(IngredientSearchCondition condition, int page, int size);

    List<IngredientResponseDTO> getIngredientListOfAll(String email);

    List<IngredientRegisteredResponseDTO> getIngredientListOfRegistered();
}
