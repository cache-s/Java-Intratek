package epitech.intratek.api;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import chazot_a.epitech.intratek.Login;

public class ApiCalls {


    private boolean isCo;
    private String  token;
    private String mLogin;
    private String mPassword;
    private Context app;

    private static String url_api = "https://epitech-api.herokuapp.com/";
    private ProgressDialog pDialog;

    public boolean Login(String login, String password, Context app) {
        mLogin = login;
        mPassword = password;
        this.app = app;
        new ConnectAccount().execute();
        System.out.println("Returning " + isCo);
        return isCo;
    }

    class ConnectAccount extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(app);
            pDialog.setTitle("Minute papillon !");
            pDialog.setMessage("Connexion au compte...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        public String performPostCall(String requestURL, HashMap<String, String> params) {
            URL url;
            String response = "";
            try {
                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String,String> param : params.entrySet()) {
                    if (postData.length() != 0) postData.append('&');
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                System.out.println(postData.toString());
                url = new URL(requestURL);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                for ( int c = in.read(); c != -1; c = in.read() )
                    response = response + (char)c;
            } catch (Exception e) {
                System.err.println("Error : " + e.toString());
            }
            System.out.println(response);
            return response;
        }

        protected String doInBackground(String... args) {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("login", mLogin);
            params.put("password", mPassword);

            String jsonText = performPostCall(url_api + "login", params);
            if (jsonText.contains("token"))
            {
                isCo = true;
                try {
                    JSONObject jsonObj = new JSONObject(jsonText);
                    token = jsonObj.get("token").toString();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
                isCo = false;
            return "";
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();
        }
    }
}
