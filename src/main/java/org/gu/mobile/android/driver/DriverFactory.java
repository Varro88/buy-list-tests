package org.gu.mobile.android.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.gu.mobile.android.constants.Constants;
import org.gu.mobile.android.data.models.Config;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static io.appium.java_client.remote.MobileCapabilityType.*;

public class DriverFactory {
    public static AppiumDriver createDriver() throws IOException {
        String configName = System.getProperty("configName", "default");
        Constants.CONFIG = readConfig("src/main/resources/" + configName + ".json");
        DesiredCapabilities capabilities = getCapabilities();
        return new AndroidDriver<MobileElement>(new URL(Constants.CONFIG.getUrl()), capabilities);
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

    private static Config readConfig(String configPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //File configFile = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("fileName")).getFile());
        //File configFile = new File(Resources.getResource(configPath).getFile());
        //File configFile = new File(getClass().getClassLoader().getResource("configPath").getFile());

        Config config = mapper.readValue(new File(configPath), Config.class);
        if(Boolean.getBoolean("useBrowserstack")) {
            String bsUsername = System.getProperty("bsUsername");
            String bsKey = System.getProperty("bsKey");
            config.setUrl("https://" + bsUsername + ":" + bsKey + "@" + Constants.BROWSERSTACK_URL);
        }
        return config;
    }
}
