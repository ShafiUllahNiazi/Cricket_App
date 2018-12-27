package com.example.shafi.criclive;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View v = inflater.inflate(R.layout.card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OldMatchAdapter.ViewHolder viewHolder, final int i) {

        try {
            viewHolder.match_Title.setText(data.get(i).getJsonObject().getString("score"));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(),Main2Activity.class);
                    intent.putExtra("id","sss");
                    intent.putExtra("id2",data.get(i).getOldMatchesListsItem().getUnique_id());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView match_Title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            match_Title=itemView.findViewById(R.id.match_Title);

        }
    }
}
