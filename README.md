# jug-2022-tyro

https://aerokube.com/images/latest/

https://bonigarcia.dev/webdrivermanager/

https://saucelabs.com/
https://www.browserstack.com/
https://aerokube.com/



@CssIdentifier('a.btn')
class LinkButton extends Button {
    @Override
    String text() {
        provider.eval(this.id(), "it.is('input') ? it.val() : it.text().trim()")
    }`
}


@CssIdentifier('select')
class SimpleDropDown extends Select {
    @Override
    String label() {
        this.items()[0].value()
    }
}

@CssIdentifier('div.dropdown')
class ComplexDropDown extends Dropdown {
    @Override
    String label() {
        this.provider.find(Button, By.expression('#' + id() + ' button:first')).text()
    }

    @Override
    List<Group> groups() {
        throw new UnsupportedOperationException()
    }

    @Override
    Group group(String value) {
        throw new UnsupportedOperationException()
    }

    @Override
    List<Li> items() {
        provider.findAll(Li, By.expression('#' + id() + ' li'))
    }

    @Override
    Li item(String value) {
        items().find { it.value() == value }
    }

    @Override
    boolean empty() {
        items().empty
    }

    @Override
    boolean valid() {
        throw new UnsupportedOperationException()
    }

    @Override
    String errorMessage() {
        throw new UnsupportedOperationException()
    }
}
