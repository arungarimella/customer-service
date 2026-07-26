package com.company.customer;

import groovy.lang.GroovyShell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ModifyGradleDepedencyVersion {
    public static void main(String[] args) {
        try {
            // Load the existing Gradle build file
            String buildScript = new String(Files.readAllBytes(Paths.get("build.gradle")));

            // Update the version using Groovy
            GroovyShell shell = new GroovyShell();
            shell.evaluate(buildScript + "\ndependencies {\n    implementation 'com.example:example-artifact:1.2.3'\n}");

            // Write the modified build file
            Files.write(Paths.get("build.gradle"), shell.getProperty("dependencies").toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
