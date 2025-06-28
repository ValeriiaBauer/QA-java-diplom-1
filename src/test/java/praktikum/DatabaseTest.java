package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class DatabaseTest {
    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAvailableBuns_ReturnsNewCopy() {
        List<Bun> firstCall = database.availableBuns();
        List<Bun> secondCall = database.availableBuns();

        assertNotSame("Должна возвращаться новая копия списка", firstCall, secondCall);
        assertEquals("Содержимое списков должно быть идентичным", firstCall, secondCall);
    }

    @Test
    public void testAvailableIngredients_ReturnsNewCopy() {
        List<Ingredient> firstCall = database.availableIngredients();
        List<Ingredient> secondCall = database.availableIngredients();

        assertNotSame("Должна возвращаться новая копия списка", firstCall, secondCall);
        assertEquals("Содержимое списков должно быть идентичным", firstCall, secondCall);
    }

    @Test
    public void testAvailableBuns_ContainsCorrectData() {
        List<Bun> buns = database.availableBuns();
        assertEquals(3, buns.size());
        assertBunExists(buns, "black bun", 100);
        assertBunExists(buns, "white bun", 200);
        assertBunExists(buns, "red bun", 300);
    }

    @Test
    public void testAvailableIngredients_ContainsCorrectData() {
        List<Ingredient> ingredients = database.availableIngredients();
        assertEquals(6, ingredients.size());
        assertIngredientExists(ingredients, IngredientType.SAUCE, "hot sauce", 100);
        assertIngredientExists(ingredients, IngredientType.FILLING, "dinosaur", 200);
    }

    private void assertBunExists(List<Bun> buns, String name, float price) {
        assertTrue(buns.stream().anyMatch(b ->
                name.equals(b.getName()) &&
                        Math.abs(price - b.getPrice()) < 0.001
        ));
    }

    private void assertIngredientExists(List<Ingredient> ingredients,
                                        IngredientType type,
                                        String name,
                                        float price) {
        assertTrue(ingredients.stream().anyMatch(i ->
                type == i.getType() &&
                        name.equals(i.getName()) &&
                        Math.abs(price - i.getPrice()) < 0.001
        ));
    }
}