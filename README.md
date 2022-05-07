## Description
Repository contains UI tests for ShoppingApp

### Run tests in IntelliJ Idea via TestNG
1. Create new TestNG run configuration
2. Select run options (e.g. "Class", select required class to run)
3. In "VM Options" field add parameter with local path to *.apk file (e.g. `-DappPath=/Users/user/ShoppingList.apk`)
4. Specify config name via `-DconfigName=someConfig` if required
5. Save and run configuration

### Run tests in IntelliJ Idea via Maven
1. Create new Maven run configuration
2. Select required run parameters (e.g. `clean test -DappPath=/Users/user/ShoppingList.apk`)
3. Specify config name via `-DconfigName=someConfig` if required
4. Save and run configuration

### Run tests in IntelliJ Idea via Maven and Surefire plugin
1. Create new Maven run configuration
2. Select required run parameters (e.g. `clean test -Dsurefire.suiteXmlFiles=testng.xml -DappPath=/Users/user/Android/ShoppingList.apk`)
3. Specify config name via `-DconfigName=someConfig` if required
4. Save and run configuration

### Allure report
When test run is completed data for Allure report  is generated as well. To open report run Maven task `allure:serve`

### Additional run parameters
* `appPath` - path to app file (local or in Browserstack format);
* `configName` - config name from `src/resources/*.json` file that will be run. When not provided - default one from `default.json` will be used
* `useBrowserstack` - `true` for remote run
* `bsUsername` - name of Browserstack user
* `bsPassword` - password for Browserstack user
