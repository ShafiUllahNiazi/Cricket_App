package com.example.shafi.criclive.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shafi.criclive.R;
import com.example.shafi.criclive.models.OldMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.UpcomingMatchesModelClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UpcomingMatchesAdapter extends RecyclerView.Adapter<UpcomingMatchesAdapter.ViewHolder> {

    Context context;
    ArrayList<UpcomingMatchesModelClass> data;

    public UpcomingMatchesAdapter(Context context, ArrayList<UpcomingMatchesModelClass> data) {
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.match_Title.setText(data.get(i).getTeam_1()+" vs "+ data.get(i).getTeam_2() );
        setTeam1Flag(data,viewHolder,i);
        setTeam2Flag(data,viewHolder,i);
        String rawDate = data.get(i).getDateTimeGMT();
        String date = getDate(rawDate);
        viewHolder.date.setText(date);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void setTeam1Flag(ArrayList<UpcomingMatchesModelClass> data, UpcomingMatchesAdapter.ViewHolder viewHolder, int position){
        String team_1 = data.get(position).getTeam_1();


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

    private void setTeam2Flag(ArrayList<UpcomingMatchesModelClass> data, UpcomingMatchesAdapter.ViewHolder viewHolder, int position){

        String team_2 = data.get(position).getTeam_2();

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

    private String getDate (String rawDate){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = dateFormat.parse(rawDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //If you need time just put specific format for time like 'HH:mm:ss'
        String dateStr = formatter.format(date);
        return dateStr;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView match_Title;
        TextView date;
        ImageView team1;
        ImageView team2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            match_Title=itemView.findViewById(R.id.match_Title2);
            date = itemView.findViewById(R.id.winnerTeam);
            team1 = itemView.findViewById(R.id.teamOld1);
            team2 = itemView.findViewById(R.id.teamOld2);


        }
    }
}
