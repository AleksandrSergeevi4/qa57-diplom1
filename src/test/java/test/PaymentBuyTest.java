package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PaymentPage;
import pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentBuyTest {
    private PaymentPage paymentPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        var startPage = new StartPage();
        paymentPage = startPage.choosePaymentPage();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    static void teardown() {
        cleanDatabase();
    }

    @Test
    void shouldSuccessOperationPayment() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationSuccessOperation();
        var expected = "APPROVED";
        var actual = SQLHelper.getPaymentStatusOperation();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFailedOperationPayment() {
        var cardNumber = String.valueOf(DataHelper.getDeclinedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationFailedOperation();
        var expected = "DECLINED";
        var actual = SQLHelper.getPaymentStatusOperation();
        assertEquals(expected, actual);
    }

    @Test
    void shouldEmptyFieldCardNumber() {
        var cardNumber = DataHelper.getEmptyFieldCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorCardNumber();
    }

    @Test
    void shouldIncompleteCardNumber() {
        var cardNumber = DataHelper.getIncompleteCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorCardNumber();
    }

    @Test
    void shouldExistCardNumber() {
        var cardNumber = DataHelper.getExistCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationSuccessOperation();
    }

    @Test
    void shouldEmptyFieldMonth() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getEmptyFieldMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorMonthNumber();
    }

    @Test
    void shouldMoreNumberOfMonthsInYear() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getMoreThanNumberOfMonthsInYear();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorValidPeriodCard();
    }

    @Test
    void shouldOneDigitInMonth() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getOneDigitInMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorMonthNumber();
    }

    @Test
    void shouldPreviousMonthsOfTheCurrentYear() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getPreviousMonthsOfTheCurrentYear();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorValidPeriodCard();
    }

    @Test
    void shouldEmptyFieldYear() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getEmptyFieldYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorYear();
    }

    @Test
    void shouldOneDigitInYear() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getOneDigitInYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorYear();
    }

    @Test
    void shouldLessThanThisYear() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getLessThanThisYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorValidPeriodYear();
    }

    @Test
    void shouldMoreThanThisYear() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getMoreThanThisYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorNotExistYear();
    }

    @Test
    void shouldEmptyFieldName() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getEmptyFieldName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorName();
    }

    @Test
    void shouldCyrillicFieldName() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getCyrillicFieldName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorName();
    }

    @Test
    void shouldOneFieldName() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getOneFieldName();
        var code = DataHelper.getValidCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorName();
    }

    @Test
    void shouldEmptyFieldCode() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getEmptyFieldCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorCode();
    }

    @Test
    void shouldIncompleteFieldCode() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getIncompleteFieldCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorCode();
    }

    @Test
    void shouldNullFieldCode() {
        var cardNumber = String.valueOf(DataHelper.getApprovedCardNumber());
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var name = DataHelper.getValidName();
        var code = DataHelper.getNullFieldCode();
        paymentPage.payment(cardNumber, month, year, name, code);
        paymentPage.notificationErrorCode();
    }
}