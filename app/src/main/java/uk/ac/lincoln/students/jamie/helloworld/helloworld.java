package uk.ac.lincoln.students.jamie.helloworld;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.*;
import android.content.Intent;

public class helloworld extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_helloworld);
    }

    /** Called when the user touches the button */
    public void chageTextButton(View view) {
        // create an intent to start the activity called TestActivity
        Intent intent = new Intent(this, TestActivity.class);

        // find the textView control to change first
        TextView tv1 = (TextView)findViewById(R.id.changeText);

        // set visibility to visible
        tv1.setVisibility(View.VISIBLE);

        // edit textView control value
        tv1.setText("Hello TestActivity!");

        // get the new string value of the textView control
        String message = tv1.getText().toString();

        // pass string value of textView to new TestActivity
        intent.putExtra("testParameter", message);

        // start TestActivity!
        startActivity(intent);
    }

    /** Called when the user touches the button */
    public void getTweetData(View view) {

        // create to intent to start Tweets activity
        Intent intent = new Intent(this, Tweets.class);
        startActivity(intent);
    }
    /** Called when the user touches the top 40 button */
    public void getMusicData(View view) {

        // create an intent to start Music activity
        Intent intent = new Intent(this, Music.class);
        startActivity(intent);
    }
}
