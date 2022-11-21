package org.gu.mobile.android.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.gu.mobile.android.constants.Constants;
import org.gu.mobile.android.data.models.Config;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;

public class DriverFactory {
    static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static AppiumDriver createDriver() throws IOException {
        String configName = System.getProperty("configName", "default.json");
        Constants.CONFIG = readConfig(configName);
        DesiredCapabilities capabilities = getCapabilities();
        return new AndroidDriver(new URL(Constants.CONFIG.getUrl()), capabilities);
    }

    private static DesiredCapabilities getCapabilities() {
        return new DesiredCapabilities() {{
            setCapability(DEVICE_NAME, Constants.CONFIG.getDeviceName());
            setCapability(PLATFORM_NAME, Constants.CONFIG.getPlatformName());
            setCapability(PLATFORM_VERSION, Constants.CONFIG.getPlatformVersion());
            setCapability(APP, System.getProperty("appPath", Constants.CONFIG.getAppPath()));
            setCapability(AUTOMATION_NAME, Constants.CONFIG.getAutomationName());
            setCapability("project", "GridU project");
            setCapability("build", "GridU build");
            setCapability("name", "GridU name");
            setCapability(NEW_COMMAND_TIMEOUT, "30000");
        }};
    }

    private static Config readConfig(String configName) throws IOException {
        if(!configName.endsWith(".json")) {
            configName += ".json";
        }
        File configFile = new File(Constants.CONFIGS_PATH + configName);
        if(!configFile.exists() || configFile.isDirectory()) {
            logger.warn(String.format("File '%s' not found, default config is used", configName));
            configFile = new File(Constants.CONFIGS_PATH + "default.json");
        }

        ObjectMapper mapper = new ObjectMapper();
        Config config = mapper.readValue(configFile, Config.class);
        if(Boolean.getBoolean("useBrowserstack")) {
            String bsUsername = System.getProperty("bsUsername");
            String bsKey = System.getProperty("bsKey");
            config.setUrl("https://" + bsUsername + ":" + bsKey + "@" + Constants.BROWSERSTACK_URL);
        }
        return config;
    }
}
