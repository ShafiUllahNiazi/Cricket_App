package com.example.shafi.criclive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MatchSummary  extends Fragment {

    TextView textView2;

    private String data;

    public MatchSummary() {

    }
//    public void setData (String data) {
//        this.data = data;
//
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_summary,container,false);
        textView2 = view.findViewById(R.id.textView2);
        data = getArguments().getString("unique_id");
        textView2.setText(data);
        Toast.makeText(getActivity(), "jjj "+data, Toast.LENGTH_SHORT).show();
//        Log.d("DATAA",data);

        return view;
    }
}
