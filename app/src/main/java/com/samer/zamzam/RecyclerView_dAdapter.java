package com.samer.zamzam;

/**
 * Created by Salim3dd on 01/12/2016.
 */



import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerView_dAdapter extends  RecyclerView.Adapter<RecyclerView_dAdapter.ViewHolder>{

    private List<AppList> List_Item;
    private Context context;
    Fragment fragment;
    DriveLinks driveLinks;
    public RecyclerView_dAdapter(List<AppList> list_Item, Context context) {
        List_Item = list_Item;
        this.context = context;
    }

    @Override
    public RecyclerView_dAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_menu, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        driveLinks=new DriveLinks();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView_dAdapter.ViewHolder holder, final int position) {

        holder.TextName.setText(List_Item.get(position).getName());
        holder.imageView.setImageResource(List_Item.get(position).getImg());
        Picasso.with(context).load(List_Item.get(position).Img).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String artistName=driveLinks.Artists[position];
                Bundle bundle=new Bundle();
                bundle.putInt("artistNumber",position);
                bundle.putString("artistName",artistName);
                fragment=new ArtistFragment();
                FragmentManager fm=((AppCompatActivity) context).getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.addToBackStack("mainMenu");
                fragment.setArguments(bundle);
                ft.replace(R.id.fragment,fragment);
                ft.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != List_Item ? List_Item.size() : 0);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView;
        private TextView TextName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            TextName = (TextView) view.findViewById(R.id.TextName);
        }
    }
}

