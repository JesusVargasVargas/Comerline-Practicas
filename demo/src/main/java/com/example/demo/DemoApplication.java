package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



@SpringBootApplication
@RestController
public class DemoApplication {
    public static void main(String[] args) {
      
      SpringApplication.run(DemoApplication.class, args);
    }
    @GetMapping("/chuck")
    public String chuck(@RequestParam(value = "quote", defaultValue = "I'm Chuck!") String quote) {

      try {

        URL url = new URL("https://api.chucknorris.io/jokes/random");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responsecode = conn.getResponseCode();

        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            scanner.close();

            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            JSONObject obj = data_obj;

            quote = (String) obj.get("value");
            
        }

      } catch (Exception e) {
        e.printStackTrace();
      }

      return quote;
    }
}