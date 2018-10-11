package com.example.gautam.newsreader;


import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ImageViewer extends RecyclerView.Adapter<ImageViewer.ViewHolder> {
    private ArrayList<String> titles;
    private ArrayList<Bitmap>images;
    Listener listener;
    interface Listener {
        void onClick(int    Position);
    }
    ImageViewer(ArrayList<String>titles,ArrayList<Bitmap> images)
    {
        this.titles=titles;
        this.images=images;
    }

    @NonNull
    @Override
    public ImageViewer.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView CC=(CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.captioned_image,viewGroup,false);
        return new ViewHolder(CC);

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
            CardView ccc=viewHolder.cardView;
        ImageView imageView=(ImageView)ccc.findViewById(R.id.imageView2);
        imageView.setImageBitmap(images.get(i));
        TextView textView=(TextView)ccc.findViewById(R.id.textView);
        textView.setText(titles.get(i));
        ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    listener.onClick(i);
                }
            }
        });
    }
    public  void setListener(Listener listener)
    {
        this.listener=listener;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ViewHolder(CardView CV)
        {
            super(CV);
            cardView=CV;
        }

   }
}
