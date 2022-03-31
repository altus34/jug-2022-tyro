package sc.tyro.jug.example.extension

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import sc.tyro.web.WebBundle

import static WebDriverExtension.webdriver

class TyroExtension implements BeforeAllCallback {
    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        WebBundle.init(webdriver)
    }
}
