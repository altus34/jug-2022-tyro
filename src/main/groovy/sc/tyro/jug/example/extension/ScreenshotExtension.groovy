package sc.tyro.jug.example.extension

import com.applitools.eyes.BatchInfo
import com.applitools.eyes.EyesRunner
import com.applitools.eyes.selenium.Configuration
import com.applitools.eyes.selenium.Eyes
import com.applitools.eyes.visualgrid.services.RunnerOptions
import com.applitools.eyes.visualgrid.services.VisualGridRunner
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import sc.tyro.core.Config
import sc.tyro.provider.EyesProvider

import static java.lang.System.getenv

class ScreenshotExtension implements BeforeAllCallback, AfterAllCallback {
    public static Eyes eyes
    private static EyesRunner runner

    @Override
    void beforeAll(ExtensionContext context) throws Exception {
        setupEyes()
        Config.screenshotProvider = new EyesProvider(eyes)
    }

    @Override
    void afterAll(ExtensionContext context) throws Exception {
        if (eyes) {
            eyes.closeAsync()
            runner.getAllTestResults(true)
        }
    }

    private static void setupEyes() {
        String serverURL = getenv('APPLITOOLS_SERVER_URL')
        String apiKey = getenv('APPLITOOLS_API_KEY')
        String batchId = getenv('APPLITOOLS_BATCH_ID')
        String branch = getenv('GITHUB_HEAD_REF')

        if (serverURL != null && apiKey != null && batchId != null) {
            runner = new VisualGridRunner(new RunnerOptions().testConcurrency(5))
            eyes = new Eyes(runner)

            BatchInfo batchInfo = new BatchInfo("DEMO")
            batchInfo.setId(batchId)

            Configuration config = eyes.configuration
            config.apiKey = apiKey
            config.serverUrl = serverURL
            config.forceFullPageScreenshot = true
            config.batch = batchInfo

            eyes.configuration = config
            eyes.branchName = branch
        }
    }
}
