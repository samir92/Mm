package com.samer.zamzam;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {
Fragment fragment;
    ListView listView;
    DriveLinks driveLinks;
    ArrayAdapter arrayAdapter;

    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.main, container, false);


        driveLinks=new DriveLinks();
        listView=(ListView)view.findViewById(R.id.listView);
        String[] artistlist=driveLinks.Artists;
        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.artist_list_view, R.id.textView_title,artistlist);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String artistName=driveLinks.Artists[position];
                Bundle bundle=new Bundle();
                bundle.putInt("artistNumber",position);
                bundle.putString("artistName",artistName);
                fragment=new ArtistFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.addToBackStack("mainMenu");
                fragment.setArguments(bundle);
                ft.replace(R.id.fragment,fragment);
                ft.commit();}
        });


        return view;
    }


}
