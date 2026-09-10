package com.example.payments;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class task3 {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Ошибка: укажите пути к файлам");
            System.out.println("Первый аргумент: файл values.json");
            System.out.println("Второй аргумент: файл tests.json");
            System.out.println("Третий аргумент: файл report.json");
            return;
        }

        String path1 = args[0];
        String path2 = args[1];
        String path3 = args[2];
        Gson gson = new Gson();
        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        Map<Integer, String> results = new HashMap<>();

        try (BufferedReader in = new BufferedReader(new FileReader(path1));){
            JsonObject root = gson.fromJson(in, JsonObject.class);
            JsonArray values = root.getAsJsonArray("values");

            for(JsonElement element : root.getAsJsonArray("values")){
                JsonObject obj = element.getAsJsonObject();
                int id = obj.get("id").getAsInt();
                String value = obj.get("value").getAsString();
                results.put(id,value);
            }
        }

        JsonObject testsRoot;
        try (BufferedReader in = new BufferedReader(new FileReader(path2))){
            testsRoot = gson.fromJson(in, JsonObject.class);
        }

        for (JsonElement test : testsRoot.getAsJsonArray("tests")) {
            fillValues(test.getAsJsonObject(), results);
        }

        try (FileWriter w = new FileWriter(path3)) {
            pretty.toJson(testsRoot, w);
        }
    }

    private static void fillValues(JsonObject node,  Map<Integer, String> results) {
        int id = node.get("id").getAsInt();
        String title = node.get("title").getAsString();

        // Проверяем value
        boolean empty = !node.has("value")
                || node.get("value").isJsonNull()
                || node.get("value").getAsString().isEmpty();

        String value = "";
        if (node.has("value") && !node.get("value").isJsonNull()) {
            value = node.get("value").getAsString();
        }
        if (empty) {
            String newValue = results.get(id);
            if (newValue != null) {
                node.addProperty("value", newValue);
            }
        }

        if (node.has("values") && node.get("values").isJsonArray()) {
            for (JsonElement child : node.getAsJsonArray("values")) {
                fillValues(child.getAsJsonObject(), results);
            }
        }
    }


}



