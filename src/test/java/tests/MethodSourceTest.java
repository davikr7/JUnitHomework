package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MethodSourceTest {

    static Stream<Arguments> selenideLocaleDataProvider(){
        return Stream.of(
                Arguments.of(Locale.RU, List.of("Кешбэк", "Рабита Мобайл", "Apple Pay", "Google Pay", "Словарь", "Блог", "Рабита Бот", "Кредитный калькулятор")),
                Arguments.of(Locale.EN, List.of("Cashback", "Rabita Mobile", "Apple Pay", "Google Pay", "Vocabulary", "Blog", "Rabita Bot", "Loan calculator"))
        );
    }

    @MethodSource("selenideLocaleDataProvider")
    @ParameterizedTest(name = "Для языка {0} отображаются кнопки {1}")
    @Tag("BLOCKER")

    void rabitaBankSiteShouldContainAllOfButtonsForGivenLocale(Locale locale, List<String> buttons) {
        open("https://www.rabitabank.com/");
        $(".header__lang").hover().$$(".header__lang__list").findBy(text(locale.name())).click();
        $(".header__open-menu").click();
        $$("#header-nav .header__nav__other li").shouldHave(texts(buttons));
    }
}