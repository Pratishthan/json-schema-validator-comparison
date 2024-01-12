package com.pratishthan.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.util.Set;

public class TestSchemeValidator {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String schemaStr = "{ \"type\": \"object\", \"properties\": { \"a\": { \"type\": \"integer\" }, \"b\": { \"type\": \"string\" }, \"c\": { \"type\": \"string\" }, \"d\": { \"type\": \"number\" } }, \"required\": [\"a\", \"d\"] }";
        ; // Your JSON Schema string here
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = schemaFactory.getSchema(schemaStr);
        long begin = System.nanoTime();
        int i=0;
        while (i < 10000) {
            String jsonDataString = "{ \"a\": \"a123\", \"b\": \"abc123\", \"c\": \"2024-01-01\", \"d\": 2.0 }";
            JsonNode jsonData = objectMapper.readTree(jsonDataString);

            Set<ValidationMessage> validationResult = schema.validate(jsonData);
            i++;
        }
        long end = System.nanoTime();
        System.out.println("Time Taken"+ (end -begin));
        // Time Taken:
        // 5000 : 153384327 ,173964764 , 149704091 ,169278403 ----- (160)ms
        // 10000: 272509228, 284028104, 277648545,276027037 --- (270)ms

        /*if (validationResult.isEmpty()) {
            System.out.println("Valid data");
        } else {
            validationResult.forEach(System.out::println);
        }*/
    }

}
