package org.example;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Sigga {
ServidorPage servidorPage;
LoginPage loginPage;
    private AndroidDriver<MobileElement> driver;


    @Before
    public void conexaoApp() throws MalformedURLException {
        DesiredCapabilities sigga = new DesiredCapabilities();
        sigga.setCapability("platformName", "Android");
        sigga.setCapability("deviceName", "0084681463");
        sigga.setCapability("appPackage", "com.sigga.platform.empower.pr");
        sigga.setCapability("appActivity", "crc6442cd7d981b8a9420.MainActivity");
        sigga.setCapability("noReset", true);

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), sigga);
    }

    @Test
    public void validarEnderecoServidor() {
        servidorPage = new ServidorPage(driver);
        servidorPage.validarEnderecoServidor("ABC");

        }


    @Test
    public void validaEmailLogin(){
        loginPage = new LoginPage(driver);
        loginPage.validarLogin("stg","thiago@teste.com");

    }

    @After
    public void encerrarSessao() {
        if (driver != null) {
            driver.quit();
        }
    }
}
