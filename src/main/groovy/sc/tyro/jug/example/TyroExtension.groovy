package sc.tyro.jug.example

import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import sc.tyro.web.WebBundle

import static sc.tyro.jug.example.WebDriverExtension.webdriver

class TyroExtension implements BeforeAllCallback {
    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        WebBundle.init(webdriver)
    }
}
