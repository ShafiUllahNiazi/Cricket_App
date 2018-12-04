package com.example.shafi.livescoreapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<OldMatches> data;

    public MyAdapter(Context context, ArrayList<OldMatches> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.card, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.match_Title.setText(data.get(i).getTitle());
//        viewHolder.itemDescription.setText(data.get(i).getDescription());

//        viewHolder.itemName.setText("1");
//        viewHolder.itemDescription.setText("2");


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView match_Title;
        TextView itemDescription;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            match_Title = (TextView) itemView.findViewById(R.id.match_Title);
            itemDescription = (TextView) itemView.findViewById(R.id.itemDescription);
//            imageView = itemView.findViewById(R.id.imageView);
        }


    }
}
