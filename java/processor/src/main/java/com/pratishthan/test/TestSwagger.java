package com.pratishthan.test;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.model.Request;
import com.atlassian.oai.validator.model.SimpleRequest;
import com.atlassian.oai.validator.report.JsonValidationReportFormat;
import com.atlassian.oai.validator.report.ValidationReport;


public class TestSwagger {
    public static void main(String  [] args){
        String schema =  "{\n" +
                "  \"openapi\": \"3.0.0\",\n" +
                "  \"info\": {\n" +
                "    \"title\": \"Sample API\",\n" +
                "    \"version\": \"1.0.0\"\n" +
                "  },\n" +
                "  \"paths\": {\n" +
                "    \"/myobject\": {\n" +
                "      \"post\": {\n" +
                "        \"summary\": \"Create a new MyObject\",\n" +
                "        \"operationId\": \"createMyObject\",\n" +
                "        \"requestBody\": {\n" +
                "          \"description\": \"MyObject to create\",\n" +
                "          \"required\": true,\n" +
                "          \"content\": {\n" +
                "            \"application/json\": {\n" +
                "              \"schema\": {\n" +
                "                \"$ref\": \"#/components/schemas/MyObject\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        },\n" +
                "        \"responses\": {\n" +
                "          \"201\": {\n" +
                "            \"description\": \"MyObject created successfully\"\n" +
                "          },\n" +
                "          \"400\": {\n" +
                "            \"description\": \"Invalid input\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"components\": {\n" +
                "    \"schemas\": {\n" +
                "      \"MyObject\": {\n" +
                "        \"type\": \"object\",\n" +
                "        \"required\": [\"a\", \"d\"],\n" +
                "        \"properties\": {\n" +
                "          \"a\": {\n" +
                "            \"type\": \"integer\",\n" +
                "            \"description\": \"An integer value that is mandatory.\"\n" +
                "          },\n" +
                "          \"b\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"pattern\": \"^[a-zA-Z0-9]*$\",\n" +
                "            \"description\": \"A non-mandatory alphanumeric string.\"\n" +
                "          },\n" +
                "          \"c\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"format\": \"date\",\n" +
                "            \"description\": \"A date field with the format YYYY-MM-DD.\"\n" +
                "          },\n" +
                "          \"d\": {\n" +
                "            \"type\": \"number\",\n" +
                "            \"format\": \"double\",\n" +
                "            \"description\": \"A mandatory double.\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n";

        OpenApiInteractionValidator validator = OpenApiInteractionValidator.createForInlineApiSpecification(schema).build();
        long begin = System.nanoTime();

        int i=0;
        while (i < 10000) {

            String jsonPayload = "{\"a\": \"timepass\",\"d\":\"123\"}";
            Request payload = new SimpleRequest.Builder(Request.Method.POST, "/myobject").withContentType("application/json").withBody(jsonPayload).build();
            ValidationReport report = validator.validateRequest(payload);
            i++;
        }
        long end = System.nanoTime();
        System.out.println("Time Taken"+ (end -begin));
        // Time Taken:
        // 5000 :  1655515527,1620021329,1646150974 1554909304 ----- (1600)ms
        // 10000: 2326555217, 2193626131 ,2234561687 ,2155002234 - (2200) ms

        //  System.out.println(JsonValidationReportFormat.getInstance().apply(report));


    }
}
