import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


            URL url = new URL("https://www.metaweather.com/api/location/search/?query=bucharest");
            URL urlTemperatures = new URL("https://www.metaweather.com/api/location/868274/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if(responseCode != 200) {
                throw new RuntimeException("HttpResponse Code :" + responseCode);
            }else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                if (informationString.toString().equals("[]")){
                    throw new RuntimeException("Nu a fost gasit nici un oras");
                }

                System.out.println(informationString);

                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(informationString.toString());

                System.out.println(dataObject.get(0));

                JSONObject contryData = (JSONObject) dataObject.get(0);

                System.out.println(contryData.get("title"));
            }





    }
}
