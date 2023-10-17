package com.voss.section2.config;

import org.apache.commons.cli.*;

public class Config {
    private String baseUrl;
    private String driverPath;
    private String browserName;

    public Config(String[] args) {
        Options options = new Options();
        options.addOption("h", "baseUrl", true, "Base URL for the website");
        options.addOption("d", "driverPath", true, "Path to the driver executable");
        options.addOption("b", "browser", true, "Name of the browser (chrome or firefox)");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error parsing command-line arguments: " + e.getMessage());
        }

        baseUrl = cmd.getOptionValue("baseUrl", "https://ultimateqa.com/automation/");
        driverPath = cmd.getOptionValue("driverPath", "/usr/local/bin/chromedriver");
        browserName = getBrowserName(cmd.getOptionValue("browser"));
    }

    private String getBrowserName(String arg) {
        if (arg != null) {
            String lowerCaseArg = arg.toLowerCase();
            if (lowerCaseArg.equals("chrome") || lowerCaseArg.equals("firefox")) {
                return lowerCaseArg;
            }
        }
        return null;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public String getBrowserName() {
        return browserName;
    }
}
