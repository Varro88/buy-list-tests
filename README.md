## Description
Repository contains UI tests for ShoppingApp

## Project structure:
 * `src/main/java/org/gu/mobile/android/constants` - constant values used through the project
 * `src/main/java/org/gu/mobile/android/data` - POJO classes for the main entities and parameters for tests
 * `src/main/java/org/gu/mobile/android/driver` - WebDriver initialization related
 * `src/main/java/org/gu/mobile/android/pages` - application pages
 * `src/main/java/org/gu/mobile/android/utils` - utility classes for common actions
 * `src/test/java/org/gu/mobile/android` - tests themselves

## Run tests in IntelliJ IDEA

### Using TestNG
1. Create new TestNG run configuration
2. Select run options (e.g. "Class", select required class to run)
3. In "VM Options" field add parameter with local path to *.apk file (e.g. `-DappPath=/Users/user/ShoppingList.apk`)
4. Specify config name via `-DconfigName=someConfig` if required
5. Save and run configuration

### Using Maven and Surefire plugin
1. Create new Maven run configuration
2. Select required run parameters (e.g. `clean test -Dsurefire.suiteXmlFiles=testng.xml -DappPath=/Users/user/ShoppingList.apk`)
3. Specify config name via `-DconfigName=someConfig` if required
4. Save and run configuration

## Browserstack tests run from Jenkins
In your Maven job add `-DuseBrowserstack=true` and provide `bsUsername` and `bsPassword` variables, e.g.

`mvn test -DappPath=bs://4a9c463e4edc25b15069b6effb48ed8ab70dba25 -DuseBrowserstack=true -DbsUsername=bsuser -DbsPassword=bspass`

## Local Allure report
When test run is completed data for Allure report is generated as well. To open report run Maven task `allure:serve`

## All run parameters
* `appPath` - path to app file (local or in Browserstack format);
* `configName` - config name from `src/main/resources/` file that will be run. When not provided - default one from `default.json` is used
* `useBrowserstack` - `true` for remote run
* `bsUsername` - name of Browserstack user
* `bsPassword` - password for Browserstack user
