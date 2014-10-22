package nl.ordina.beer.automaticbrewing.boundary;

import nl.ordina.beer.automaticbrewing.control.RecipeBrewer;
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
    private RecipeBrewer brewer;

    @Test
    public void post_should_trigger_brewer_to_brew_recipe() {
        sut.recipeAdapter = new RecipeXmlAdapter();
        sut.post(RecipeBuilder.aRecipeJson());
        Mockito.verify(brewer).brew(RecipeBuilder.aRecipe());
    }

}
