package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CsvFileSourceTest {

    @CsvFileSource(resources = "/testData.csv")
    @ParameterizedTest(name = "Для локали {0} отображается результат {1}")
    @Tag("BLOCKER")
    void checkFootballer(String footballer, String result) {
        open("https://www.sports.ru/");
        $(".search-block-trigger").click();
        $(".search-block__input").setValue(footballer).pressEnter();
        $(".search-result").shouldHave(text(result));
    }

}
