package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private final SelenideElement heading = $(byText("Ваши карты"));
    private final String balanceStart = ", баланс: ";
    private final String balanceFinish = " р.";
    private final ElementsCollection cards = $$(".list__item div");

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance(String id) {
        var text = cards.findBy(attribute("data-test-id", id)).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart) + balanceStart.length();
        var finish = text.indexOf(balanceFinish);
        String value = text.substring(start, finish);
        return Integer.parseInt(value);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(attribute("data-test-id", cardInfo.getCardTestId()))
                .$("button").click();
        return new TransferPage();
    }
}

