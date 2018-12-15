package com.example.shafi.criclive;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultsMatches extends Fragment implements MyAdapter.shaficlass {

    private static final String TAG ="Tab1Fragment";

    private Button btnTest;

    TextView textView;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<OldMatches> list;


    Button button;
    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.results_matches,container,false);
//        textView = view.findViewById(R.id.textView2);
//        list=new ArrayList<>();
//        list.add(new OldMatches("r1","",""));
//        context = getActivity();
//        button = view.findViewById(R.id.button1);
//
//        recyclerView = view.findViewById(R.id.recyclerView2);
//
//        layoutManager = new LinearLayoutManager(context);
//        recyclerView.setLayoutManager(layoutManager);
//        // define an adapter
//
////        mAdapter = new MyAdapter();
//        mAdapter = new MyAdapter(context,list);
//
//        mAdapter.shaficonfirm(ResultsMatches.this);
//
//
//
//
//        recyclerView.setAdapter(mAdapter);
//
//
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = "https://cricapi.com/api/cricket?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
//                ApiRead myclass = new ApiRead(mAdapter,list);
//                myclass.execute(s);
//                list.add(new OldMatches("2","2","3"));
//                mAdapter.notifyItemInserted(list.size()-1);
//
//                Toast.makeText(getActivity(), "Button Tab 1 ", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }





    @Override
    public void shafimethod(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
    }



}
