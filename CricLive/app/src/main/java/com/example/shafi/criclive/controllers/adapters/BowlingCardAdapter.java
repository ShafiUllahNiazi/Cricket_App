package com.example.shafi.criclive.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shafi.criclive.R;
import com.example.shafi.criclive.models.BowlingModelClass;

import java.util.ArrayList;

public class BowlingCardAdapter extends RecyclerView.Adapter<BowlingCardAdapter.ViewHolder> {

    Context context;
    ArrayList<BowlingModelClass> data;

    public BowlingCardAdapter(Context context, ArrayList<BowlingModelClass> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.bowlingcarditem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bowler.setText(data.get(i).getBowler());
        viewHolder.o.setText(data.get(i).getO());
        viewHolder.m.setText((data.get(i).getM()+""));
        viewHolder.r.setText((data.get(i).getR()+""));
        viewHolder.w.setText((data.get(i).getW()+""));
        viewHolder.econ.setText((data.get(i).getEcon()+""));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView bowler,o,m,r,w,econ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bowler = (TextView) itemView.findViewById(R.id.bowler);
            o = (TextView) itemView.findViewById(R.id.overs);
            m= itemView.findViewById(R.id.maiden);
            r= itemView.findViewById(R.id.runsBowler);
            w= itemView.findViewById(R.id.wickets);
            econ= itemView.findViewById(R.id.econ);

        }
    }
}
