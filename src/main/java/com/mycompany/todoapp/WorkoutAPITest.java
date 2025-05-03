package com.mycompany.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class WorkoutAPITest {

    String apiUrl;

    public static void main(String[] args) throws IOException {
        WorkoutAPITest test = new WorkoutAPITest();
        Scanner kbd = new Scanner(System.in);
        String muscle, difficulty;
        System.out.print("Enter muscle type: ");
        muscle = kbd.nextLine();
        System.out.print("Enter expereince level: ");
        difficulty = kbd.nextLine();
        String workout = test.GetWorkout(muscle, difficulty);
        System.out.println(workout);
    }

    public WorkoutAPITest() {
        apiUrl = "https://api.api-ninjas.com/v1/exercises?";
    }

    public String GetWorkout(String muscle, String difficulty) throws MalformedURLException, IOException {
        URL url = new URL(apiUrl + "muscle=" + muscle + "&difficulty=" + difficulty);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("X-Api-Key", "yfOadcuc40IC3nXg2I/zZQ==iyrwjirDShtEpxQa");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);
        String res = root.toString();
        JSONArray json = new JSONArray(res);
        List<Object> names = getValuesForKey(json, "name");
        System.out.println(names);
        res = "";
        for(int i = 0; i < names.size(); i++){
            res = res + (String)names.get(i) + "\n";
        }
        return res;
    }
    
    public List<Object> getValuesForKey(JSONArray jsonArray, String key) {
        List<Object> values = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.has(key)) {
                values.add(jsonObject.get(key));
            }
        }
        return values;
    }
}
