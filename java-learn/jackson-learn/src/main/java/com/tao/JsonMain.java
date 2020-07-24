package com.tao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

/**
 * @author DongTao
 * @since 2018-09-05
 */
public class JsonMain {


    public static void main(String[] args) throws Exception {
        String jsonStr = "{\"name\":\"Dong\",\"age\":18,\"email\":\"a@gmail.com\"}";
        User user = new User("Dong", 18, "a@gmail.com", UserType.NAN);
        ObjectMapper objectMapper = new ObjectMapper();
        // javaBean to Json String
        String userStr = objectMapper.writeValueAsString(user);
        System.out.println(userStr);

        // Json String to javaBean
        User value = objectMapper.readValue(userStr, User.class);
        System.out.println(value);

        // Json String to JsonNode
        JsonNode readTree = objectMapper.readTree(jsonStr);
        System.out.println(readTree.get("name"));
        System.out.println(readTree.get("name").asText());
        System.out.println(readTree.path("name"));
        System.out.println(readTree.path("name").asText());

        JsonNode path = readTree.path("");  // miss node
        JsonNode node = readTree.get("");   // null
        JsonNode path1 = readTree.findPath(""); // miss node
        JsonNode value1 = readTree.findValue(""); // null

        // create Json
        ObjectNode objectNode = objectMapper.createObjectNode();

        objectNode.put("name", "tao");
        objectNode.put("age", 18);
        objectNode.put("email", "b@gmail.com");
        objectNode.putPOJO("dong", readTree);
        System.out.println(objectNode);

        // find
        ObjectNode parent = objectNode.findParent("dong");
        List<JsonNode> dong = objectNode.findParents("dong");
        JsonNode nodeValue = objectNode.findValue("name");
        List<JsonNode> values = objectNode.findValues("name");
        List<String> valuesAsText = objectNode.findValuesAsText("name");

        System.out.println(valuesAsText);

        // foreach
        System.out.println(objectNode);
        System.out.println(objectNode.get("dong").asText());

        objectNode.fields().forEachRemaining(entry -> System.out.println(entry.getValue().asText()));
        System.out.println("------------------");
        objectNode.forEach(jsonNode -> System.out.println(jsonNode.asText()));
    }


}
