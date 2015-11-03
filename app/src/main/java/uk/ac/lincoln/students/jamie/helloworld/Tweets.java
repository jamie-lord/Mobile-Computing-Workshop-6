package uk.ac.lincoln.students.jamie.helloworld;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

// add below
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import java.util.ArrayList;

import android.widget.*;

import java.util.Date;

import android.util.Log;

public class Tweets extends AppCompatActivity {

    // array list to store tweet items from web service
    ArrayList<String> items = new ArrayList<String>();
    // json test string
    String jsonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        // start the  AsyncTask for calling the REST service using httpConnect class
        new AsyncTaskParseJson().execute();
    }


    // added asynctask class methods below -  you can make this class as a separate class file
    public class AsyncTaskParseJson extends AsyncTask<String, String, String>

    {

        // set the url of the web service to call
        String yourServiceUrl = "http://derekfoster.cloudapp.net/tweet/tweetledee/userjson.php?user=shaunlawson&c=10";

        @Override
// this method is used for......................
        protected void onPreExecute() {

        }

        @Override
// this method is used for...................
        protected String doInBackground(String... arg0) {

            try {
                // create new instance of the httpConnect class
                httpConnect jParser = new httpConnect();

                // get json string from service url
                String json = jParser.getJSONFromUrl(yourServiceUrl);

                // parse returned json string into json array
                JSONArray jsonArray = new JSONArray(json);

                // loop through json array and add each tweet to item in arrayList
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json_message = jsonArray.getJSONObject(i);

                    if (json_message != null) {
                        //add each tweet to ArrayList as an item
                        items.add(json_message.getString("text"));
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
// below method will run when service HTTP request is complete, will then bind tweet text in arrayList to ListView
        protected void onPostExecute(String strFromDoInBg) {
            // bind the values of the ArrayList to the ListView to display the tweets
            ListView list = (ListView)findViewById(R.id.tweetList);
            ArrayAdapter<String> tweetArrayAdapter = new ArrayAdapter<String>(Tweets.this, android.R.layout.simple_expandable_list_item_1, items);
            list.setAdapter(tweetArrayAdapter);
        }
    }
}