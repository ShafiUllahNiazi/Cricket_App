package com.example.shafi.criclive;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OldMatchAdapter extends RecyclerView.Adapter<OldMatchAdapter.ViewHolder> {

    Context context;
    ArrayList<OldMatchesModelClass> data;

    public OldMatchAdapter(Context context, ArrayList<OldMatchesModelClass> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OldMatchAdapter.ViewHolder viewHolder, int i) {

        viewHolder.match_Title.setText(Long.toString(data.get(i).getUnique_id()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView match_Title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            match_Title=itemView.findViewById(R.id.match_Title);

        }
    }
}
