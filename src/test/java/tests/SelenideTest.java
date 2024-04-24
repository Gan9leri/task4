/*Разработайте следующий автотест:
- Откройте страницу Selenide в Github
- Перейдите в раздел Wiki проекта
- Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
- Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
*/
package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;

public class SelenideTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void selenide_test() {
        //- Откройте страницу Selenide в Github
        open("/selenide/selenide");
        //- Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();
        //- Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-body .markdown-body ul").shouldHave(text("Soft assertions"));
        //- Откройте страницу SoftAssertions
        $("#wiki-body .markdown-body ul").$(byTagAndText("a", "Soft assertions")).click();
        //проверьте что внутри есть пример кода для JUnit5
        $("#wiki-body .markdown-body").shouldHave(text(
                """
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                          @Test
                          void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");
                
                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }
                """
        ));
    }
}