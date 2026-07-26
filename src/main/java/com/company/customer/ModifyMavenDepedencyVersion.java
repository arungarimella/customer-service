package com.company.customer;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModifyMavenDepedencyVersion {
    public static void main(String[] args) {
        try {
            // Load the existing Maven POM file
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));

            // Find the dependency by groupId and artifactId
            for (Dependency dependency : model.getDependencies()) {
                if ("com.example".equals(dependency.getGroupId()) && "example-artifact".equals(dependency.getArtifactId())) {
                    // Update the version
                    dependency.setVersion("1.2.3"); // New version
                }
            }

            // Write the modified POM file
            MavenXpp3Writer writer = new MavenXpp3Writer();
            writer.write(new FileWriter("pom.xml"), model);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
