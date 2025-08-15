package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServidorPage {

    private static final int TIMEOUT_SECONDS = 20;
    private static final String MENSAGEM_ERRO_SERVIDOR = "Não foi possível recuperar o certificado do servidor. Tente mais tarde.";

    private AppiumDriver<?> driver;
    private WebDriverWait wait;

    public ServidorPage(AppiumDriver<?> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
    }


    public void validarEnderecoServidor(String nomeServidor) {
        try {
            clicarSelecionarServidor();
            setNomeServidor(nomeServidor);
            clicarBotaoContinue();
            validarMensagemErroServidor();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar endereço do servidor", e);
        }
    }

    private void clicarSelecionarServidor() {
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

    private void validarMensagemErroServidor() {
        MobileElement mensagem = (MobileElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"android:id/message\")")));

        String textoRecebido = mensagem.getText();
        Assert.assertEquals(MENSAGEM_ERRO_SERVIDOR, textoRecebido);
    }
}
