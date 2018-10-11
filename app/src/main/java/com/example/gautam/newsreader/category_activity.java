package com.example.gautam.newsreader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class category_activity extends AppCompatActivity {
   private    ArrayList<String> cat=new ArrayList<>();
   private  ArrayList<Bitmap>   img=new ArrayList<>();
  private ImageViewer adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);cat.add("business");cat.add("sports");cat.add("entertainment");
        cat.add("health");cat.add("science");cat.add("technology");
        img.add( BitmapFactory.decodeResource(getResources(),R.drawable.download));
        img.add( BitmapFactory.decodeResource(getResources(),R.drawable.sports));
        img.add( BitmapFactory.decodeResource(getResources(),R.drawable.entertainment));
        img.add( BitmapFactory.decodeResource(getResources(),R.drawable.health));
        img.add( BitmapFactory.decodeResource(getResources(),R.drawable.science));
        img.add( BitmapFactory.decodeResource(getResources(),R.drawable.technology));
        setContentView(R.layout.activity_category_activity);
        setTitle("News Categories");
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler);
        adapter=new ImageViewer( cat, img);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.setListener(new ImageViewer.Listener() {
            @Override
            public void onClick(int Position) {
                Intent iii=new Intent(category_activity.this,Headline_Activity.class);
                iii.putExtra(Intent.EXTRA_TEXT,cat.get(Position));
                startActivity(iii);
            }
        });
       /* ListView listView=(ListView) findViewById(R.id.categorylist);
        ArrayAdapter<String> ladapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cat);
        listView.setAdapter(ladapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(category_activity.this,Headline_Activity.class);
                i.putExtra(Intent.EXTRA_TEXT,cat.get(position));
                startActivity(i);
            }
        });*/
    }
}
