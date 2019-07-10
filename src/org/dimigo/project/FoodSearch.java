package org.dimigo.project;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodSearch {
    static List<Food> foodList = new ArrayList<>();
    public static Map<String, List<Food>> rest = new HashMap();

    public static String search(int i) {
            try {
                String text = URLEncoder.encode(Integer.toString(i), "UTF-8");
                String apiURL = "http://data.ex.co.kr/openapi/restinfo/restBestfoodList?key=8898967791&type=json&numOfRows=99&pageNo=" + text;
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();

                return response.toString();
            } catch (Exception e) {
                System.out.println(e);
            }
        return null;
    }

    public static void parseFood() throws Exception {

        for (int i = 1; i < 34; i++) {
            String response = search(i);
            Map map = new ObjectMapper().readValue(response, Map.class);
            List<Map<String, String>> list = (List) map.get("list");
            for (Map<String, String> food : list) {
                String name = food.get("foodNm");
                String price = food.get("foodCost");
                String place = food.get("stdRestNm");
                String recom = food.get("recommendyn");
                String best = food.get("bestfoodyn");
                foodList.add(new Food(name, price, place, recom, best));

                if (!rest.containsKey(place)) {
                    rest.put(place, new ArrayList());
                }
                rest.get(place).add(new Food(name, price, place, recom, best));
            }
        }
    }

    public static List getFood(String restName) {

       return rest.get(restName);
    }
}
