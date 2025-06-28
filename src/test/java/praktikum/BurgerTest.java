package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Mock
    private Ingredient ingredient3;

    private final List<Ingredient> ingredients;
    private final float expectedPrice;

    public BurgerTest(List<Ingredient> ingredients, float expectedPrice) {
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {Arrays.asList(createMockIngredient(100.0f)), 200.0f + 100.0f},
                {Arrays.asList(
                        createMockIngredient(50.0f),
                        createMockIngredient(75.0f)
                ), 200.0f + 125.0f},
                {Arrays.asList(
                        createMockIngredient(10.0f),
                        createMockIngredient(20.0f),
                        createMockIngredient(30.0f)
                ), 200.0f + 60.0f}
        });
    }

    private static Ingredient createMockIngredient(float price) {
        Ingredient ingredient = org.mockito.Mockito.mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(bun.getPrice()).thenReturn(100.0f);
        burger.setBuns(bun);

        when(ingredient1.getPrice()).thenReturn(50.0f);
        when(ingredient2.getPrice()).thenReturn(75.0f);
        when(ingredient3.getPrice()).thenReturn(25.0f);
    }

    @Test
    public void testSetBuns() {
        Burger testBurger = new Burger();
        testBurger.setBuns(bun);
        assertEquals(bun, testBurger.bun);
    }

    @Test
    public void testAddIngredient() {
        int initialSize = burger.ingredients.size();
        burger.addIngredient(ingredient1);
        assertEquals(initialSize + 1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(ingredient1));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        int initialSize = burger.ingredients.size();
        burger.removeIngredient(0);
        assertEquals(initialSize - 1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        Ingredient firstBeforeMove = burger.ingredients.get(0);
        Ingredient secondBeforeMove = burger.ingredients.get(1);

        burger.moveIngredient(0, 1);

        assertEquals(firstBeforeMove, burger.ingredients.get(1));
        assertEquals(secondBeforeMove, burger.ingredients.get(0));
    }

    @Test
    public void testGetPrice() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expected = 100.0f * 2 + 50.0f + 75.0f;
        assertEquals(expected, burger.getPrice(), 0.01);
    }

    @Test
    public void testGetReceipt() {
        when(bun.getName()).thenReturn("white bun");
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("chili sauce");

        burger.addIngredient(ingredient1);

        String expected = "(==== white bun ====)\n" +
                "= sauce chili sauce =\n" +
                "(==== white bun ====)\n" +
                "\nPrice: 250,000000\n";
        assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void testParameterizedGetPrice() {
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }
}