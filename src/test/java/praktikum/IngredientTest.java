package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType expectedType;
    private final String expectedName;
    private final float expectedPrice;

    public IngredientTest(IngredientType expectedType, String expectedName, float expectedPrice) {
        this.expectedType = expectedType;
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Тестовые данные: тип={0}, название={1}, цена={2}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.FILLING, "cutlet", 200.0f},
                {IngredientType.SAUCE, "", 0.0f},
                {IngredientType.FILLING, null, -1.0f}
        });
    }

    @Test
    public void constructor_ShouldSetFieldsCorrectly() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        assertNotNull("Объект Ingredient не должен быть null", ingredient);
        assertEquals("Тип ингредиента должен совпадать", expectedType, ingredient.type);
        assertEquals("Название ингредиента должно совпадать", expectedName, ingredient.name);
        assertEquals("Цена ингредиента должна совпадать", expectedPrice, ingredient.price, 0.001f);
    }

    @Test
    public void getType_ShouldReturnCorrectValue() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        IngredientType actualType = ingredient.getType();
        assertEquals("Метод getType() вернул неверное значение", expectedType, actualType);
    }

    @Test
    public void getName_ShouldReturnCorrectValue() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        String actualName = ingredient.getName();
        assertEquals("Метод getName() вернул неверное значение", expectedName, actualName);
    }

    @Test
    public void getPrice_ShouldReturnCorrectValue() {
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        float actualPrice = ingredient.getPrice();
        assertEquals("Метод getPrice() вернул неверное значение",
                expectedPrice, actualPrice, 0.001f);
    }
}