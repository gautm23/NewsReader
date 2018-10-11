package com.example.gautam.newsreader;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImageViewer2 extends RecyclerView.Adapter<ImageViewer2.ViewHolder> {
    ArrayList<String> news;
    ImageViewer2(ArrayList<String> news)
    {
        this.news=news;
    }
    Listener2 listener2;
    interface Listener2 {
      void  onClick(int position);
    }
    @NonNull
    @Override
    public ImageViewer2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cc=(CardView)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.captioned_image2,viewGroup,false);
        return new ViewHolder(cc);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        CardView ccc=viewHolder.cardView;
        TextView textView=(TextView)ccc.findViewById(R.id.textView22);
        textView.setText(news.get(i));
        ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener2!=null)
                {
                    listener2.onClick(i);
                }
            }
        });
    }

    public  void setListener(Listener2 listener)
    {
        this.listener2=listener;
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ViewHolder(CardView cv)
        {
            super(cv);
            cardView=cv;
        }

    }
}
