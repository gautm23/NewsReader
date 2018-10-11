package com.example.gautam.newsreader;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_main);
        TextView textView=(TextView) findViewById(R.id.text);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(MainActivity.this, category_activity.class));
                finish();
            }
        }, 2 * 1000);

    }
}
