package com.samer.zamzam;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class RecyclerView extends Fragment {



    private android.support.v7.widget.RecyclerView recyclerView;
    private RecyclerView_dAdapter recyclerView_dAdapter;
    public List<AppList> listItems;
DriveLinks driveLinks;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_main, container, false);

        driveLinks=new DriveLinks();


        recyclerView = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.m_RecyclerView);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        listItems = new ArrayList<>();
for(int i=0;i<8;i++){listItems.add(new AppList(driveLinks.Artists[i],driveLinks.photos[i]));}

        recyclerView_dAdapter = new RecyclerView_dAdapter(listItems, getActivity());
        recyclerView.setAdapter(recyclerView_dAdapter);
        return view;}}
