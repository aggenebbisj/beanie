package nl.ordina.beer.brewing.recipe.boundary;

import nl.ordina.beer.brewing.control.Brewer;
import static nl.ordina.beer.brewing.recipe.entity.RecipeBuilder.aRecipe;
import static nl.ordina.beer.brewing.recipe.entity.RecipeBuilder.aRecipeJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RecipeResourceTest {

    @InjectMocks
    private RecipeResource sut;

    @Mock
    private Brewer brewer;

    @Test
    public void request_to_brew_recipe_should_add_all_actions_to_brewer() {
        sut.recipeAdapter = new RecipeXmlAdapter();
        sut.post(aRecipeJson());
        Mockito.verify(brewer).addActions(aRecipe().getSteps());
    }

}
