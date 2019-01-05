package com.example.shafi.criclive.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shafi.criclive.R;
import com.example.shafi.criclive.models.BattingCardModel;

import java.util.ArrayList;

public class BattingCardAdapter  extends RecyclerView.Adapter<BattingCardAdapter.ViewHolder>{

    Context context;
    ArrayList<BattingCardModel> data;

    public BattingCardAdapter(Context context, ArrayList<BattingCardModel> data){
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.battingcarditem, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.batsman.setText(data.get(i).getBatsman());
        viewHolder.dinfo.setText(data.get(i).getDismissal_info());
        viewHolder.r.setText((data.get(i).getRuns()+""));
        viewHolder.b.setText((data.get(i).getBalls()+""));
        viewHolder.f.setText((data.get(i).getFours()+""));
        viewHolder.s.setText((data.get(i).getSixes()+""));
//        viewHolder.sr.setText((data.get(i).getsR()+""));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView batsman,dinfo,r,b,f,s,sr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            batsman = (TextView) itemView.findViewById(R.id.batsman);
            dinfo = (TextView) itemView.findViewById(R.id.dismissal_info);
            r= itemView.findViewById(R.id.runs);
            b= itemView.findViewById(R.id.balls);
            f= itemView.findViewById(R.id.fours);
            s= itemView.findViewById(R.id.sixes);
//            sr= itemView.findViewById(R.id.sr);


        }


    }
}
