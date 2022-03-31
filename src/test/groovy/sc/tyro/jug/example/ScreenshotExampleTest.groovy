package sc.tyro.jug.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.extension.ExtendWith
import sc.tyro.core.component.Dropdown
import sc.tyro.core.component.field.Field
import sc.tyro.jug.example.extension.ScreenshotExtension
import sc.tyro.jug.example.extension.TyroExtension
import sc.tyro.jug.example.extension.WebDriverExtension

import static sc.tyro.core.Tyro.*
import static sc.tyro.jug.example.extension.ScreenshotExtension.eyes
import static sc.tyro.jug.example.extension.WebDriverExtension.BASE_URL
import static sc.tyro.jug.example.extension.WebDriverExtension.webdriver

@ExtendWith([WebDriverExtension, TyroExtension, ScreenshotExtension])
class ScreenshotExampleTest {
    @Test
    void sample_1(TestInfo testInfo) {
        webdriver.get(BASE_URL + '/index.html' )

        Field email = field('Email')
        email.should {
            be visible
            be empty
            have label('Email')
        }

        Field password = field('Password')
        password.should {
            be visible
            be empty
            have label('Password')
        }

        Dropdown language = dropdown('Language')
        language.should {
            be visible
            have 2.items
            have items('EN', 'FR')
            have selectedItem('EN')
        }

        if(eyes) {
            eyes.open(webdriver, "JUG", testInfo.displayName)
            takeScreenshot("Sample Demo")
        }
    }
}
