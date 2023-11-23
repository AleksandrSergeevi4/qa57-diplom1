package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static Faker faker = new Faker(new Locale("en"));
    public static Faker fakerRu = new Faker(new Locale("ru"));

    @Value
    public static class CardInfo {
        String cardNumber;
    }

    public static CardInfo getApprovedCardNumber() {
        return new CardInfo("4444 4444 4444 4441");
    }

    public static CardInfo getDeclinedCardNumber() {
        return new CardInfo("4444 4444 4444 4442");
    }

    public static String getValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getValidYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getValidName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidCode() {
        return faker.numerify("###");
    }

    public static String getEmptyFieldCardNumber() {
        return "";
    }

    public static String getIncompleteCardNumber() {
        return "123456";
    }

    public static String getExistCardNumber() {
        return "4444 5555 3333 1111";
    }

    public static String getEmptyFieldMonth() {
        return "";
    }

    public static String getMoreThanNumberOfMonthsInYear() {
        return faker.backToTheFuture().date();
    }

    public static String getOneDigitInMonth() {
        return "3";
    }

    public static String getPreviousMonthsOfTheCurrentYear() {
        return LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getEmptyFieldYear() {
        return "";
    }

    public static String getOneDigitInYear() {
        return "1";
    }

    public static String getLessThanThisYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getMoreThanThisYear() {
        return LocalDate.now().plusYears(10).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getEmptyFieldName() {
        return "";
    }

    public static String getCyrillicFieldName() {
        return fakerRu.name().firstName() + " " + fakerRu.name().lastName();
    }

    public static String getOneFieldName() {
        return faker.name().firstName();
    }

    public static String getEmptyFieldCode() {
        return "";
    }

    public static String getIncompleteFieldCode() {
        return faker.numerify("##");
    }

    public static String getNullFieldCode() {
        return "000";
    }
}
