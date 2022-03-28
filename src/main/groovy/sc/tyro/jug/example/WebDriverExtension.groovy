package sc.tyro.jug.example

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.WebDriver

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver

class WebDriverExtension implements BeforeAllCallback, AfterAllCallback {
    public static WebDriver webdriver
    private static WebDriverManager wdm

    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        wdm = chromedriver()
        webdriver = wdm.create()
    }

    @Override
    void afterAll(ExtensionContext extensionContext) throws Exception {
        webdriver.quit()
        wdm.quit()
    }


}
