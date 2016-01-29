package epitech.intratek.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Sebastien on 21/01/2016.
 */
public class ApiCalls
{
    // Singleton
    private static ApiCalls instance;

    private ApiCalls() {}

    public static ApiCalls getInstance()
    {
        if (instance == null)
            instance = new ApiCalls();
        return (instance);
    }
    // End Singleton

    private String BaseUrl = "https://epitech-api.herokuapp.com/";

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();

        boolean first = true;

        for(Map.Entry<String, String> entry : params.entrySet())
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public String performGetCall(String requestUrl, HashMap<String, String> getDataParams)
    {
        try {
            String url = BaseUrl + requestUrl + getPostDataString(getDataParams);
            URL obj = new URL(url);
            System.out.println("URL = " + url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return (response.toString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return ("");
    }

    public String  performPostCall(String requestURL, HashMap<String, String> postDataParams)
    {
        URL url;
        String response = "";
        try
        {
            url = new URL(BaseUrl + requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null)
                {
                    response+=line;
                }
            }
            else
            {
                response="";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
    }

    return response;
    }

    public String  performDeleteCall(String requestURL, HashMap<String, String> deleteDataParams)
    {
        URL url = null;
        try {
            url = new URL(requestURL + getPostDataString(deleteDataParams));
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("DELETE");
            System.out.println(httpURLConnection.getResponseCode());
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return "";
    }
}
