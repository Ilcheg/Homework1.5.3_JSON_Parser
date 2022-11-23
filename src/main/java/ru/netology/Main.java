package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String json = readString("new_Data.json");
        List<Employee> list = jsonToList(json);
        System.out.println(list);
    }

    private static ArrayList<Employee> jsonToList(String json) {
        ArrayList<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray employee = (JSONArray) parser.parse(json);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for (Object emp : employee) {
                list.add(gson.fromJson(emp.toString(), Employee.class));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String readString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}