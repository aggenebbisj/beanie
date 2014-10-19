package nl.ordina.brewery.recipe.boundary;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import nl.ordina.brewery.recipe.entity.Recipe;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RecipeParserTest {

    private final String jsonString = "{  \"name\":\"TestBeer\",\"steps\":[ {  \"type\":\"AddIngredient\",\"ingredient\":{  \"type\":\"water\",\"volume\":{ \"amount\":10,\"unit\":\"LITER\"}}},{  \"type\":\"ChangeTemperature\",\"temperature\":{  \"value\":65,\"unit\":\"CELSIUS\"}},{ \"type\":\"AddIngredient\",\"ingredient\":{  \"type\":\"malt\",\"volume\":{  \"amount\":1,\"unit\":\"LITER\"}}},{ \"type\":\"StableTemperature\",\"duration\":\"PT30M\"}]}";
    private JsonObject json;
    
    @Before
    public void setUp() {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        json = jsonReader.readObject();
    }

    @Ignore
    @Test
    public void test_parsing_of_valid_recipe() {
        RecipeParser sut = new RecipeParser();
//        sut.ingredientParser = new IngredientParser();
        
        Recipe result = sut.parseRecipe(json);
        System.out.println(result);
    }
    
}
