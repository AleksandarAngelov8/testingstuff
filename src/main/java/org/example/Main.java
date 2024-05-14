package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

import static spark.Spark.halt;

public class Main {
    public static void main(String[] args) {
        port(4567);
        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setClassForTemplateLoading(org.apache.ivy.Main.class, "/public");
        get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            StringWriter writer = new StringWriter();
            try {
                Template representativesTemplate = configuration.getTemplate("login.html");
                representativesTemplate.process(attributes, writer);
            } catch (Exception e) {
                halt(500);
            }
            return writer;
        });
    }
}