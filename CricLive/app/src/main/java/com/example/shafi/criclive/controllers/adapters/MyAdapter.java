package com.example.shafi.criclive.controllers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.shafi.criclive.R;
import com.example.shafi.criclive.controllers.activities.Main2Activity;
import com.example.shafi.criclive.models.LiveMatchesDescriptiveModelClass;

import org.json.JSONException;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<LiveMatchesDescriptiveModelClass> data;

    public MyAdapter(Context context, ArrayList<LiveMatchesDescriptiveModelClass> data) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.card, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        try {
            viewHolder.match_Title.setText(data.get(i).getJsonObject().getString("score"));
            setTeam1Flag(data,viewHolder,i);
            setTeam2Flag(data,viewHolder,i);
//            if(data.get(i).getLiveMatchesListsItem().getTeam_1())
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent main2 = new Intent(context.getApplicationContext(), Main2Activity.class);
                    main2.putExtra("id", "DDDDDDDD");
                    main2.putExtra("id2", data.get(i).getLiveMatchesListsItem().getUnique_id());
                    main2.putExtra("summaryType","Live");
                    context.startActivity(main2);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setTeam1Flag(ArrayList<LiveMatchesDescriptiveModelClass> data,ViewHolder viewHolder,int position){
        String team_1 = data.get(position).getLiveMatchesListsItem().getTeam_1();


        if(team_1.equals("Pakistan")){

            viewHolder.team1.setImageResource(R.drawable.pak);
        }if(team_1.equals("South Africa")){
            viewHolder.team1.setImageResource(R.drawable.sa);
        }if(team_1.equals("Australia")){
            viewHolder.team1.setImageResource(R.drawable.aus);
        }
        if(team_1.equals("India")){
            viewHolder.team1.setImageResource(R.drawable.in);
        }
        if(team_1.equals("Sri Lanka")){
            viewHolder.team1.setImageResource(R.drawable.lk);
        }if(team_1.equals("New Zealand")){
            viewHolder.team1.setImageResource(R.drawable.nz);
        }


    }

    private void setTeam2Flag(ArrayList<LiveMatchesDescriptiveModelClass> data,ViewHolder viewHolder,int position){

        String team_2 = data.get(position).getLiveMatchesListsItem().getTeam_2();

        if(team_2.equals("Pakistan")){
            viewHolder.team2.setImageResource(R.drawable.pak);
        }
        if(team_2.equals("South Africa")){
            viewHolder.team2.setImageResource(R.drawable.sa);
        }
        if(team_2.equals("Australia")){
            viewHolder.team2.setImageResource(R.drawable.aus);
        }

        if(team_2.equals("India")){
            viewHolder.team2.setImageResource(R.drawable.in);
        }
        if(team_2.equals("Sri Lanka")){
            viewHolder.team2.setImageResource(R.drawable.lk);
        }
        if(team_2.equals("New Zealand")){
            viewHolder.team2.setImageResource(R.drawable.nz);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView match_Title;
        ImageView team1;
        ImageView team2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            match_Title = (TextView) itemView.findViewById(R.id.match_Title);
            team1 = itemView.findViewById(R.id.team1);
            team2 = itemView.findViewById(R.id.team2);



        }


    }

}
