package com.glacierwebcreative.firstbaptistchurchbigfork;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MembersListDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.members_list_display_view);

        downloadJSON("http://bfbaptistmembersapp.pettee.net/db_activity.php");
    }


    private void downloadJSON(final String urlWebservice) {

        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebservice);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) !=null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);


                try {
                    loadIntoListView(s);
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();

    }


    private void loadIntoListView(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
        ArrayList<Member> membersList = new ArrayList<>();


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String fNH = obj.getString("firstNameHusband");
            String fNW = obj.getString("firstNameWife");
            String lN = obj.getString("lastName");

            membersList.add(new Member(fNH, fNW, lN));
            //System.out.println(membersList);
        }
        ListView listView = findViewById(R.id.listView);

        MembersAdapter adapter = new MembersAdapter(this,  membersList);

        listView.setAdapter(adapter);
    }
}
