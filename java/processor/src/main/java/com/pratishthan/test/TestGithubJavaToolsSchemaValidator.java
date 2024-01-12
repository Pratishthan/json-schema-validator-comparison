package com.pratishthan.test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.IOException;

public class TestGithubJavaToolsSchemaValidator {

        public static void main(String[] args) throws IOException, ProcessingException, JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            String schemaString = "{ \"type\": \"object\", \"properties\": { \"a\": { \"type\": \"integer\" }, \"b\": { \"type\": \"string\" }, \"c\": { \"type\": \"string\", \"format\": \"date\" }, \"d\": { \"type\": \"number\" } }, \"required\": [\"a\", \"d\"] }";
            JsonNode jsonSchemaNode = objectMapper.readTree(schemaString);
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(jsonSchemaNode);
            long begin = System.nanoTime();
            int i=0;
            while (i < 5000) {
                JsonNode jsonData = objectMapper.readTree("{\"a\":true, \"b\":\"string\", \"c\":\"2021-01-01\", \"d\":1.0}");
                ProcessingReport report = schema.validate(jsonData);
                i++;
            }
            long end = System.nanoTime();
            System.out.println("Time Taken"+ (end -begin));
            /*
            Time taken 5000 : 644087447, 634216244 , 615314709, 667312245 (600ms)
            Time taken 10000:1015064164,959777446,886831991,973601798  (900ms)
             */
            /*
            if (report.isSuccess()) {
                System.out.println("Valid data");
            } else {
                System.out.println("Invalid data. Errors:");
                report.forEach(message -> System.out.println(message));
            }
             */


        }

    }

