package refrigerator.back.recipe.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import refrigerator.back.recipe.adapter.out.dto.OutRecipeDto;
import refrigerator.back.recipe.adapter.out.repository.RecipeSelectQueryRepository;
import refrigerator.back.recipe.application.port.out.CheckMemberBookmarkedStatusPort;
import refrigerator.back.recipe.application.port.out.GetRecipeBasicsDataPort;

@Repository
@RequiredArgsConstructor
public class QueryRecipeBasicDataAdapter implements GetRecipeBasicsDataPort, CheckMemberBookmarkedStatusPort {

    private final RecipeSelectQueryRepository repository;

    @Override
    public OutRecipeDto getData(Long recipeId) {
        return repository.selectRecipeBasics(recipeId);
    }

    @Override
    public Boolean getStatus(Long recipeId, String memberId) {
        Long result = repository.selectBookmarkByMemberId(recipeId, memberId);
        return result != null;
    }
}
