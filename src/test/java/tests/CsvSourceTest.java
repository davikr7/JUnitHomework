package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CsvSourceTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
    }

    @CsvSource({
            "Смартфоны, Apple",
            "Ноутбуки, Samsung"
    })
    @ParameterizedTest(name = "В результате выбора категории {0} должен появиться бренд {1}")
    @Tag("BLOCKER")

    void checkIphone(String productCategory, String productName) {
        open("https://quke.ru/");
        $$(".menu__item").findBy(text(productCategory)).click();
        $$(".sel2__inner").first().findElements(byText(productName));
    }
}
