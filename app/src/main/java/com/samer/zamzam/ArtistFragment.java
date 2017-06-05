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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment {
    ListView listView;
    ArrayAdapter arrayAdapter;
    DriveLinks driveLinks;
    TextView ArtistTitle;
    Fragment fragment;
    public ArtistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View view=inflater.inflate(R.layout.fragment_artist, container, false);

        driveLinks=new DriveLinks();
        listView = (ListView) view.findViewById(R.id.artist_listView);
        ArtistTitle = (TextView) view.findViewById(R.id.artist_title);

        Bundle bundle=getArguments();
        final int artistNumber = bundle.getInt("artistNumber");
        final String artistName=bundle.getString("artistName");
        ArtistTitle.setText(artistName);
        if(artistNumber==0){
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.artist_list_view, R.id.textView_title,driveLinks.quran);
            listView.setAdapter(arrayAdapter);}
        else if(artistNumber==1){
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.artist_list_view, R.id.textView_title,driveLinks.Hadeeth);
            listView.setAdapter(arrayAdapter);}
        else if(artistNumber==2){
        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.artist_list_view, R.id.textView_title,driveLinks.tarekSwuidan);
        listView.setAdapter(arrayAdapter);}
        else if(artistNumber==3){
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.artist_list_view, R.id.textView_title,driveLinks.nabeelAl3awadi);
            listView.setAdapter(arrayAdapter);}
        else if(artistNumber==4){
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.artist_list_view, R.id.textView_title,driveLinks.ibrahimFaqi);
            listView.setAdapter(arrayAdapter);}
        else if(artistNumber==5){
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.artist_list_view, R.id.textView_title,driveLinks.nabulsi);
            listView.setAdapter(arrayAdapter);}
        else if(artistNumber==6){
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.artist_list_view, R.id.textView_title,driveLinks.Alqarni);
            listView.setAdapter(arrayAdapter);}
        else if(artistNumber==7){
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.artist_list_view, R.id.textView_title,driveLinks.Al3uraifi);
            listView.setAdapter(arrayAdapter);}

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putInt("artistNumber",artistNumber);
                bundle.putInt("programNumber",position);
                if(artistNumber==0){
                    bundle.putString("programName",driveLinks.quran[position]);}
                if(artistNumber==1){
                    bundle.putString("programName",driveLinks.Hadeeth[position]);}
                if(artistNumber==2){
                bundle.putString("programName",driveLinks.tarekSwuidan[position]);}
                else if(artistNumber==3){
                    bundle.putString("programName",driveLinks.nabeelAl3awadi[position]);}
                else if(artistNumber==4){
                bundle.putString("programName",driveLinks.ibrahimFaqi[position]);}
                else if(artistNumber==5){
                    bundle.putString("programName",driveLinks.nabulsi[position]);}
                else if(artistNumber==6){
                    bundle.putString("programName",driveLinks.Alqarni[position]);}
                else if(artistNumber==7){
                    bundle.putString("programName",driveLinks.Al3uraifi[position]);}
                fragment=new CourseFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.addToBackStack("artist");
                fragment.setArguments(bundle);
                ft.replace(R.id.fragment,fragment);
                ft.commit();}
        });



        return view;}

}
