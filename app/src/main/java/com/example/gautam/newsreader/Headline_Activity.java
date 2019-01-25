package com.example.gautam.newsreader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Headline_Activity extends AppCompatActivity {
  public ArrayList<String>arrayList=new ArrayList<>();
    public ArrayList<String>arrayurl=new ArrayList<>();
    public ImageViewer2 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        DownloadTask task=new DownloadTask();
        String cat=intent.getStringExtra(Intent.EXTRA_TEXT);
        task.execute("https://newsapi.org/v2/top-headlines?country=us&category="+cat+"&apiKey=b76fa43715be45b88fc7e750cbaea638");
        setContentView(R.layout.activity_headline_);
        setTitle(cat);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler2);
        adapter=new ImageViewer2(arrayList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setListener(new ImageViewer2.Listener2() {
            @Override
            public void onClick(int position) {
                Intent iii=new Intent(Headline_Activity.this,Description_Activity.class);
                iii.putExtra(Intent.EXTRA_TEXT,arrayurl.get(position));
                startActivity(iii);
            }
        });
        /* adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        ListView listView=(ListView)findViewById(R.id.headline2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(Headline_Activity.this,Description_Activity.class);
                i.putExtra(Intent.EXTRA_TEXT,arrayurl.get(position));
                startActivity(i);
            }
        });*/
    }
    public class DownloadTask extends AsyncTask<String ,Void ,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String str="";
            URL url;
            HttpURLConnection urlConnection=null;
            try {
                url=new URL(strings[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream in=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while(data!=-1)
                {
                    char current=(char)data;
                    str+=current;
                    data=reader.read();
                }
                return str;
                //Log.i("json",str);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try { arrayList.clear();
                arrayurl.clear();
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=new JSONArray(jsonObject.getString("articles"));
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    arrayList.add(jsonObject1.getString("title"));
                    arrayurl.add(jsonObject1.getString("url"));
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
