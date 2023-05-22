import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ChangeDateTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:7777/");
    }

    @Test
    void ShouldChangeMeetingDate() {
        DataGenerator.Registration.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int daysForFirstMeeting = 4;
        String firstMeetingDate = DataGenerator.generateDate(daysForFirstMeeting);
        int daysForSecondMeeting = 7;
        String secondMeetingDate = DataGenerator.generateDate(daysForSecondMeeting);
        $("[data-test-id = city] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(firstMeetingDate);
        $("[data-test-id = name] input").setValue(DataGenerator.generateName("ru")) ;
        $("[data-test-id = phone] input").setValue(DataGenerator.generatePhoneNumber("ru"));
        $("[data-test-id = agreement]").click();
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible,Duration.ofSeconds(15));
        $(".notification__title").shouldHave(Condition.text("Успешно!")).shouldBe(Condition.visible,
                Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " +
                firstMeetingDate)).shouldBe(Condition.visible);


        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id = date]  input").setValue(secondMeetingDate);
        $(".button__text").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id= replan-notification]").shouldBe(Condition.visible,Duration.ofSeconds(15));
        $("[data-test-id= replan-notification]").shouldHave
                (Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible,Duration.ofSeconds(15));
        $("[data-test-id= replan-notification] button").click();
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " +
                secondMeetingDate)).shouldBe(Condition.visible);


    }
}