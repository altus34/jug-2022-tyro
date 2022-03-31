package sc.tyro.jug.example

import io.github.bonigarcia.wdm.WebDriverManager
import io.javalin.Javalin
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.WebDriver

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import static io.javalin.http.staticfiles.Location.CLASSPATH
import static java.net.InetAddress.getByName

class WebDriverExtension implements BeforeAllCallback, AfterAllCallback {
    public static String BASE_URL
    public static WebDriver webdriver
    private static WebDriverManager wdm
    private static Javalin app

    @Override
    void beforeAll(ExtensionContext extensionContext) throws Exception {
        app = Javalin.create({
            config -> config.addStaticFiles("/webapp", CLASSPATH)
        }).start(0)

        DatagramSocket socket = new DatagramSocket()
        socket.connect(getByName("8.8.8.8"), 10002)
        String host_ip = socket.localAddress.hostAddress
        BASE_URL = "http://${host_ip}:${app.port()}/"

        wdm = chromedriver()
//                .browserVersion('48')
//        wdm = edgedriver()
//                .browserVersion('99.0')

//        wdm.browserInDocker()
//                .dockerLang("ES")
//                .enableVnc()
//        .enableRecording()
        webdriver = wdm.create()
    }

    @Override
    void afterAll(ExtensionContext extensionContext) throws Exception {
        webdriver.quit()
        wdm.quit()
    }
}
