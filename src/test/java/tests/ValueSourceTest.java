package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ValueSourceTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
        Configuration.holdBrowserOpen = true;
    }

    @ValueSource(strings = {
            "кроссовки",
            "сапоги"
    })

    @ParameterizedTest(name = "Поиск осуществлен по значению {0}")
    @Tag("BLOCKER")
    void lamodaSearchTest(String productName) {
        open("https://www.lamoda.ru/");
        $("._input_82yd5_37").setValue(productName).pressEnter();
        $(".ui-catalog-search-head-title").shouldHave(text(productName));
    }
}
