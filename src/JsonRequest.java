import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonRequest {

    private static HttpURLConnection con;

    public JSONObject getData(String urlRequest) throws IOException, JSONException {

        //var url = "https://viacep.com.br/ws/08040440/json/";
    	

        try {

            var myurl = new URL(urlRequest);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {

                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            String jsonsrt = content.toString();
            
            JSONObject my_obj = new JSONObject(jsonsrt);

            System.out.println(content);
            //System.out.println(my_obj.getString("logradouro"));
            
            return my_obj;

        } finally {

            con.disconnect();
        }
    }
}