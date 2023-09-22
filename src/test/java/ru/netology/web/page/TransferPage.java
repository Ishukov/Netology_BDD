package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement transferHead = $(byText("Пополнение карты"));

    private final SelenideElement amountInput = $("[data-test-id='amount'] input");

    private final SelenideElement fromInput = $("[data-test-id='from'] input");

    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");

    private final SelenideElement errorMessage = $("[data-test-id='error-message']");


    public TransferPage() {
        transferHead.shouldBe(Condition.visible);
    }


    public void makeTransfer(String amount, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amount);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public DashboardPage makeValidTransfer(String amount, DataHelper.CardInfo cardInfo) {
        makeTransfer(amount, cardInfo);
        return new DashboardPage();
    }

    public void checkErrorMessage(String errorText) {
        errorMessage.shouldHave(Condition.exactText(errorText), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}
