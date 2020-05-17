// First Baptist Church Bigfork     MainActivity.java

package com.glacierwebcreative.firstbaptistchurchbigfork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button submitLoginButton;
    String result = "";
    String usernameString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        submitLoginButton = (Button) findViewById(R.id.submit_login);
        submitLoginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameString = username.getText().toString();
                passwordString = password.getText().toString();

                AttemptLogin attemptLogin = new AttemptLogin();
                attemptLogin.execute(usernameString, passwordString);
            }
        });
    }


    private class AttemptLogin extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("password", passwordString);
            params.put("username", usernameString);

            System.out.println("Initial Mappings  " + params);

            StringBuilder sbParams = new StringBuilder();
            int i = 0;
            for (String key : params.keySet()) {
                try {
                    if (i != 0) {
                        sbParams.append("&");
                    }
                    sbParams.append(key).append("=").append(URLEncoder.encode(params.get(key), "UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                i++;
            }

            System.out.println("POST String  " + sbParams);

            try {
                String url = "https://bfbaptistmembersapp.pettee.net/login_db_activity.php";
                URL urlObj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(sbParams.toString());
                writer.flush();
                writer.close();

                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json);
                }
                result = stringBuilder.toString().trim();

                System.out.println("Result  " + result);
                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(String result) {
            System.out.println("Good Login  " + result);
        }

    }
}

