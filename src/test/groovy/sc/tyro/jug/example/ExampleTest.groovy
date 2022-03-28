package sc.tyro.jug.example


import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import sc.tyro.core.component.Dropdown
import sc.tyro.core.component.field.Field

import static sc.tyro.core.Tyro.*

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import static org.openqa.selenium.By.cssSelector
import static org.openqa.selenium.By.id
import static org.openqa.selenium.By.xpath
import static sc.tyro.jug.example.WebDriverExtension.webdriver

@ExtendWith([WebDriverExtension, TyroExtension])
class ExampleTest {
    @Test
    void sample_1() {
        webdriver.get('https://www.google.ca')
    }

    @Test
    void sample_2() {
        webdriver.get('https://tyro-sc.github.io/tyro-starters')

        WebElement email = webdriver.findElement(id('email'))
        assertThat(email.isDisplayed(), is(true))
        assertThat(email.getAttribute('value'), is(emptyString()))

        WebElement emailLabel = webdriver.findElement(cssSelector('[for=email]'))
        assertThat(emailLabel.getText(), is('Email'))

        WebElement password = webdriver.findElement(cssSelector('input[type=password]'))
        assertThat(password.isDisplayed(), is(true))
        assertThat(password.getAttribute('value'), is(emptyString()))

        WebElement passwordLabel = webdriver.findElement(xpath("//input[@type='password']/preceding-sibling::label"))
        assertThat(passwordLabel.getText(), is('Password'))

        WebElement language = webdriver.findElement(id('selection'))
        assertThat(language.isDisplayed(), is(true))

        WebElement languageLabel = webdriver.findElement(cssSelector('[for=selection]'))
        assertThat(languageLabel.getText(), is('Language'))

        Select select = new Select(language)
        assertThat(select.options, hasSize(2))
        assertThat(select.options[0].text, is('EN'))
        assertThat(select.options[1].text, is('FR'))
        assertThat(select.allSelectedOptions, hasSize(1))
        assertThat(select.allSelectedOptions[0].text, is('EN'))

        email.sendKeys('some@email.org')
        password.sendKeys('123456')
        select.selectByVisibleText('FR')

        assertThat(email.getAttribute('value'), is('some@email.org'))
        assertThat(password.getAttribute('value'), is('123456'))
        assertThat(select.allSelectedOptions[0].text, is('FR'))
    }

    @Test
    void sample_3() {
        visit 'https://tyro-sc.github.io/tyro-starters'

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

        fill email with 'email@email.org'
        fill password with '123456'
        on language select('FR')

        email.should { have value('email@email.org') }
        password.should { have value('123456') }
        language.should { have selectedItem('FR') }
    }

}
