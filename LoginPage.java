package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    public static final int TIMEOUT_SECONDS = 20;

    private static final String MENSAGEM_LOGIN_INVALIDO = "Usuário Inválido!";

    private AppiumDriver<?> driver;

    private WebDriverWait wait;

    public LoginPage(AppiumDriver<?> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
    }

    public void validarLogin(String nomeServidor,String email){
        clicarSelecionarServidor();
        setNomeServidor(nomeServidor);
        clicarBotaoContinue();
        informarEmail(email);
        clicarBotaoContinueEmail();
        validarMensagemLogin();
    }


    public void clicarSelecionarServidor(){

        wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().text(\"Selecione um Servidor\")"))).click();

    }

    private void setNomeServidor(String nomeServidor) {
        WebElement campoTexto = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")));
        campoTexto.click();
        campoTexto.clear();
        campoTexto.sendKeys(nomeServidor);
    }

    private void clicarBotaoContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().text(\"Continue\")"))).click();
    }

    private void informarEmail(String email){
        WebElement campoEmail = wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().text(\"example@company.com\")")));
        campoEmail.click();
        driver.hideKeyboard();
        campoEmail.sendKeys(email);

    }

    private void clicarBotaoContinueEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(
                MobileBy.AndroidUIAutomator("new UiSelector().text(\"Continue\")"))).click();
    }

    private void validarMensagemLogin() {
        MobileElement mensagem = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"android:id/message\")")));

        String textoRecebido = mensagem.getText();
        Assert.assertEquals(MENSAGEM_LOGIN_INVALIDO, textoRecebido);
    }



}
