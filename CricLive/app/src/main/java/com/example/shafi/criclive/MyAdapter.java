package com.example.shafi.criclive;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
//    ArrayList<LiveMatchesModelClass> data;
    ArrayList<LiveMatchesDescriptiveModelClass> data;

    shaficlass object;

//    public MyAdapter(Context context, ArrayList<LiveMatchesModelClass> data) {
//        this.context = context;
//        this.data = data;
//    }
    public MyAdapter(Context context, ArrayList<LiveMatchesDescriptiveModelClass> data) {
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

//        viewHolder.match_Title.setText(Long.toString(data.get(i).getUnique_id()));
        try {
            viewHolder.match_Title.setText(data.get(i).getJsonObject().getString("score"));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent main2= new Intent(context.getApplicationContext(),Main2Activity.class);
                    main2.putExtra("id","DDDDDDDD");
                    main2.putExtra("id2",data.get(i).getLiveMatchesListsItem().getUnique_id());
                    context.startActivity(main2);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }


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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            match_Title = (TextView) itemView.findViewById(R.id.match_Title);

//            itemDescription = (TextView) itemView.findViewById(R.id.itemDescription);
//            imageView = itemView.findViewById(R.id.imageView);
        }


    }

    public void shaficonfirm(shaficlass context1){
        this.object= (shaficlass) context1;


    }

    public interface shaficlass{
        public void shafimethod(String s);
    }
}
