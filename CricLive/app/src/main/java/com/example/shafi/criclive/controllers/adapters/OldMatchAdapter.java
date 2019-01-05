package com.example.shafi.criclive.controllers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shafi.criclive.R;
import com.example.shafi.criclive.controllers.activities.Main2Activity;
import com.example.shafi.criclive.models.LiveMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.OldMatchesDescriptiveModelClass;

import org.json.JSONException;

import java.util.ArrayList;

public class OldMatchAdapter extends RecyclerView.Adapter<OldMatchAdapter.ViewHolder> {

    Context context;
    ArrayList<OldMatchesDescriptiveModelClass> data;

    public OldMatchAdapter(Context context, ArrayList<OldMatchesDescriptiveModelClass> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.card2, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OldMatchAdapter.ViewHolder viewHolder, final int i) {

        try {
            viewHolder.match_Title.setText(data.get(i).getJsonObject().getString("score"));
            setTeam1Flag(data,viewHolder,i);
            setTeam2Flag(data,viewHolder,i);
            String winner_team = data.get(i).getOldMatchesListsItem().getWinner_team();

            if (winner_team.equals("")){
                viewHolder.winnerTeam.setText("Winner Team is not available " );
            }else {
                viewHolder.winnerTeam.setText("Winner Team " + winner_team);
            }

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, data.get(i).getOldMatchesListsItem().getUnique_id()+"", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context.getApplicationContext(),Main2Activity.class);
                    intent.putExtra("id","sss");
                    intent.putExtra("id2",data.get(i).getOldMatchesListsItem().getUnique_id());
                    intent.putExtra("winner_team",data.get(i).getOldMatchesListsItem().getWinner_team());
                    intent.putExtra("summaryType","Old");
                    try {
                        intent.putExtra("score",data.get(i).getJsonObject().getString("score"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    context.startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private void setTeam1Flag(ArrayList<OldMatchesDescriptiveModelClass> data, OldMatchAdapter.ViewHolder viewHolder, int position){
        String team_1 = data.get(position).getOldMatchesListsItem().getTeam_1();


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

    private void setTeam2Flag(ArrayList<OldMatchesDescriptiveModelClass> data, OldMatchAdapter.ViewHolder viewHolder, int position){

        String team_2 = data.get(position).getOldMatchesListsItem().getTeam_2();

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView match_Title;
        TextView winnerTeam;
        ImageView team1;
        ImageView team2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            match_Title=itemView.findViewById(R.id.match_Title2);
            winnerTeam = itemView.findViewById(R.id.winnerTeam);
            team1 = itemView.findViewById(R.id.teamOld2);
            team2 = itemView.findViewById(R.id.teamOld2);


        }
    }
}
