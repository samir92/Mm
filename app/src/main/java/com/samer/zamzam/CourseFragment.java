package com.samer.zamzam;


import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment {

    ListView listView;
    MediaPlayer mediaPlayer = new MediaPlayer();
    ArrayList<AppList> AppLists = new ArrayList<>();
    Button btn_play, btn_pause, btn_stop,btn_download;
    TextView tvTitle, tvCurrentTime, tvTotalTime,courseName;
    ListAdapter listAdapter;
    DownloadManager downloadManager;
    String Link,Title;
    SeekBar seekBar;
    Db_Favorite db_favorite;
    DriveLinks driveLinks;


    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_online, container, false);

        listView = (ListView) view.findViewById(R.id.listView2);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        btn_play = (Button) view.findViewById(R.id.btn_play);
        btn_pause = (Button) view.findViewById(R.id.btn_pause);
        btn_stop = (Button) view.findViewById(R.id.btn_stop);
        btn_download = (Button) view.findViewById(R.id.btn_download);

        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvCurrentTime = (TextView) view.findViewById(R.id.tvCurrentTime);
        tvTotalTime = (TextView) view.findViewById(R.id.tvTotalTime);
        courseName = (TextView) view.findViewById(R.id.CourseTopText);

        db_favorite=new Db_Favorite(getActivity());
        driveLinks=new DriveLinks();

        Bundle bundle=getArguments();
        final int artistNumber = bundle.getInt("artistNumber");
        final int programNumber=bundle.getInt("programNumber");
        String programName=bundle.getString("programName");

        courseName.setText(programName);
        if (artistNumber == 0&&programNumber==0) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.MKH_mjwd_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==1) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.AbdAlbaset_mjwd_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==2) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.AbdAlrzaqDulaimiMjwd_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==3) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.AlmnshawyMjwd_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==4) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.AlmnshawyM3lm_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==5) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.s3dAlghamdi_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==6) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.mshary_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==7) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.ahmadAl3gmi_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==8) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.waleed_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==9) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.n3maAlhasan_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==10) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.abuBakerAlshatri_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==11) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.alsudaisi_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==12) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.fares3bad_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==13) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.maher_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==14) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.naser_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==15) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.hani_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==16) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.idres_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==17) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.tawfek_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==18) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.s3dKurdi_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 0&&programNumber==19) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.AbdAlbasedEnglish_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 1&&programNumber==0) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.RyadAlsalhen_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 1&&programNumber==1) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.Sharh40_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==0) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title, driveLinks.saharAlquran_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==1) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.AlrsoulAlinsan_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==2) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.AlamatniAlhayat1_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==3) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.AlamatniAlhayat2_title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==4) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.qesa1_title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==5) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.qesa2_title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==6) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.Almobd3on_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==7) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.AlamniAltarekh_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==8) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.Rawae3Hathartna_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 2&&programNumber==9) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.TarekhnaFeElmezan_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==0) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.arwa3Alqasas_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==1) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.fadael_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==2) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.bader_Tilte);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==3) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.makarem_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==4) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.alsafwa_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==5) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.halYastwean_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==6) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.qesatAlfarouq_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==7) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.yaAllah_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==8) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.AlseraNabeel_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==9) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.mashahed1_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==10) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.mashahed2_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==11) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.mashahed3_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 3&&programNumber==12) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.mashahed4_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 4&&programNumber==0) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.tarekAlnagah_title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 4&&programNumber==1) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.nadiAlnagah_title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 5&&programNumber==0) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.waYatafkron_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 5&&programNumber==1) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.AflaYatafakron_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 5&&programNumber==2) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.ALislamAlghayb_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 6&&programNumber==0) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.A3lamAltab3en_Title);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 6&&programNumber==1) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.olaekAbaee_Titles);
            listView.setAdapter(listAdapter);}
        else if (artistNumber == 7&&programNumber==0) {
            listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_custom_layout, R.id.textView_title,driveLinks.al3thraWaAlmseh_Titles);
            listView.setAdapter(listAdapter);}

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected()){
                if (!mediaPlayer.isPlaying()) {
                    Thread updateSeekBar;
                    updateSeekBar = new Thread() {
                        @Override
                        public void run() {
                            int SoundDuration = mediaPlayer.getDuration();
                            int currentPostion = 0;
                            seekBar.setMax(SoundDuration);
                            while (currentPostion < SoundDuration) {
                                try {
                                    sleep(100);
                                    currentPostion = mediaPlayer.getCurrentPosition();
                                    seekBar.setProgress(currentPostion);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    mediaPlayer.start();
                    updateSeekBar.start();
                }}else if(!isNetworkConnected()){
                    Toast.makeText(getActivity(),R.string.noInternetConnection,Toast.LENGTH_LONG).show();}

            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();

            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkConnected()){
                    if (Build.VERSION.SDK_INT >= 23) {
                        // Marshmallow+
                        try {checkPermission();}
                        catch(Exception ev){
                            Toast.makeText(getActivity(),R.string.ErrorDownload,Toast.LENGTH_LONG).show();}
                    } else {
                        // Pre-Marshmallow

                        linkDownload();}

            }else if(!isNetworkConnected()){
                    Toast.makeText(getActivity(),R.string.noInternetConnection,Toast.LENGTH_LONG).show();}}
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) mediaPlayer.seekTo(i);
                SoundTime();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(artistNumber==0&&programNumber==0){
                    Link=driveLinks.MKH_Links[i];
                    Title=driveLinks.MKH_mjwd_Titles[i];}
                else if(artistNumber==0&&programNumber==1){
                    Link=driveLinks.AbdlAlbaset_Links[i];
                    Title=driveLinks.AbdAlbaset_mjwd_Titles[i];}
                else if(artistNumber==0&&programNumber==2){
                    Link=driveLinks.AbdAlrzaqDulaimiMjwd_Links[i];
                    Title=driveLinks.AbdAlrzaqDulaimiMjwd_Titles[i];}
                else if(artistNumber==0&&programNumber==3){
                    Link=driveLinks.AlmnshawyMjwd_Links[i];
                    Title=driveLinks.AlmnshawyMjwd_Titles[i];}
                else if(artistNumber==0&&programNumber==4){
                    Link=driveLinks.AlmnshawyM3lm_Links[i];
                    Title=driveLinks.AlmnshawyM3lm_Titles[i];}
                else if(artistNumber==0&&programNumber==5){
                    Link=driveLinks.s3dAlghamdi_Links[i];
                    Title=driveLinks.s3dAlghamdi_Titles[i];}
                else if(artistNumber==0&&programNumber==6){
                    Link=driveLinks.mshary_Links[i];
                    Title=driveLinks.mshary_Titles[i];}
                else if(artistNumber==0&&programNumber==7){
                    Link=driveLinks.ahmadAl3gmi_Links[i];
                    Title=driveLinks.ahmadAl3gmi_Titles[i];}
                else if(artistNumber==0&&programNumber==8){
                    Link=driveLinks.waleed_Links[i];
                    Title=driveLinks.waleed_Titles[i];}
                else if(artistNumber==0&&programNumber==9){
                    Link=driveLinks.n3maAlhasan_Links[i];
                    Title=driveLinks.n3maAlhasan_Titles[i];}
                else if(artistNumber==0&&programNumber==10){
                    Link=driveLinks.abuBakerAlshatri_Links[i];
                    Title=driveLinks.abuBakerAlshatri_Titles[i];}
                else if(artistNumber==0&&programNumber==11){
                    Link=driveLinks.alsudaisi_Links[i];
                    Title=driveLinks.alsudaisi_Titles[i];}
                else if(artistNumber==0&&programNumber==12){
                    Link=driveLinks.fares3bad_Links[i];
                    Title=driveLinks.fares3bad_Titles[i];}
                else if(artistNumber==0&&programNumber==13){
                    Link=driveLinks.maher_Links[i];
                    Title=driveLinks.maher_Titles[i];}
                else if(artistNumber==0&&programNumber==14){
                    Link=driveLinks.naser_Links[i];
                    Title=driveLinks.naser_Titles[i];}
                else if(artistNumber==0&&programNumber==15){
                    Link=driveLinks.hani_Links[i];
                    Title=driveLinks.hani_Titles[i];}
                else if(artistNumber==0&&programNumber==16){
                    Link=driveLinks.idres_Links[i];
                    Title=driveLinks.idres_Titles[i];}
                else if(artistNumber==0&&programNumber==17){
                    Link=driveLinks.tawfek_Links[i];
                    Title=driveLinks.tawfek_Titles[i];}
                else if(artistNumber==0&&programNumber==18){
                    Link=driveLinks.s3dKurdi_Links[i];
                    Title=driveLinks.s3dKurdi_Titles[i];}
                else if(artistNumber==0&&programNumber==19){
                    Link=driveLinks.AbdAlbasedEnglish_Links[i];
                    Title=driveLinks.AbdAlbasedEnglish_Titles[i];}
                else if(artistNumber==1&&programNumber==0){
                    Link=driveLinks.RyadAlsalhen_Links[i];
                    Title=driveLinks.RyadAlsalhen_Titles[i];}
                else if(artistNumber==1&&programNumber==1){
                    Link=driveLinks.Sharh40_Links[i];
                    Title=driveLinks.Sharh40_Titles[i];}
                else if(artistNumber==2&&programNumber==0){
                    Link=driveLinks.saharAlquran_links[i];
                    Title=driveLinks.saharAlquran_Title[i];}
                else if(artistNumber==2&&programNumber==1){
                    Link=driveLinks.AlrsoulAlinsan_links[i];
                    Title=driveLinks.AlrsoulAlinsan_Title[i];}
                else if(artistNumber==2&&programNumber==2){
                    Link=driveLinks.AlamatniAlhayat1_links[i];
                    Title=driveLinks.AlamatniAlhayat1_Titles[i];}
                else if(artistNumber==2&&programNumber==3){
                    Link=driveLinks.AlamatniAlhayat2_links[i];
                    Title=driveLinks.AlamatniAlhayat2_title[i];}
                else if(artistNumber==2&&programNumber==4){
                    Link=driveLinks.qesa1_drive[i];
                    Title=driveLinks.qesa1_title[i];}
                else if(artistNumber==2&&programNumber==5){
                    Link=driveLinks.qesa2_drive[i];
                    Title=driveLinks.qesa2_title[i];}
                else if(artistNumber==2&&programNumber==6){
                    Link=driveLinks.Almobd3on_Links[i];
                    Title=driveLinks.Almobd3on_Title[i];}
                else if(artistNumber==2&&programNumber==7){
                    Link=driveLinks.AlamaniAltarekh_links[i];
                    Title=driveLinks.AlamniAltarekh_Title[i];}
                else if(artistNumber==2&&programNumber==8){
                    Link=driveLinks.Rawae3Hathartna_links[i];
                    Title=driveLinks.Rawae3Hathartna_Title[i];}
                else if(artistNumber==2&&programNumber==9){
                    Link=driveLinks.TarekhnaFeElmezan_Links[i];
                    Title=driveLinks.TarekhnaFeElmezan_Titles[i];}
                else if(artistNumber==3&&programNumber==0){
                    Link=driveLinks.arwa3Alqasas_Links[i];
                    Title=driveLinks.arwa3Alqasas_Title[i];}
                else if(artistNumber==3&&programNumber==1){
                    Link=driveLinks.fadael_Links[i];
                    Title=driveLinks.fadael_Titles[i];}
                else if(artistNumber==3&&programNumber==2){
                    Link=driveLinks.bader_links[i];
                    Title=driveLinks.bader_Tilte[i];}
                else if(artistNumber==3&&programNumber==3){
                    Link=driveLinks.mkarem_Links[i];
                    Title=driveLinks.makarem_Title[i];}
                else if(artistNumber==3&&programNumber==4){
                    Link=driveLinks.alsafwa_Links[i];
                    Title=driveLinks.alsafwa_Title[i];}
                else if(artistNumber==3&&programNumber==5){
                    Link=driveLinks.halYastwean_Links[i];
                    Title=driveLinks.halYastwean_Title[i];}
                else if(artistNumber==3&&programNumber==6){
                    Link=driveLinks.qesatAlfarouq_Links[i];
                    Title=driveLinks.qesatAlfarouq_Title[i];}
                else if(artistNumber==3&&programNumber==7){
                    Link=driveLinks.yaAllah_Links[i];
                    Title=driveLinks.yaAllah_Titles[i];}
                else if(artistNumber==3&&programNumber==8){
                    Link=driveLinks.AlseraNabeel_Links[i];
                    Title=driveLinks.AlseraNabeel_Title[i];}
                else if(artistNumber==3&&programNumber==9){
                    Link=driveLinks.mashahed1_Links[i];
                    Title=driveLinks.mashahed1_Titles[i];}
                else if(artistNumber==3&&programNumber==10){
                    Link=driveLinks.mashahed2_Links[i];
                    Title=driveLinks.mashahed2_Titles[i];}
                else if(artistNumber==3&&programNumber==11){
                    Link=driveLinks.mashahed3_Links[i];
                    Title=driveLinks.mashahed3_Titles[i];}
                else if(artistNumber==3&&programNumber==12){
                    Link=driveLinks.mashahed4_Links[i];
                    Title=driveLinks.mashahed4_Titles[i];}
                else if(artistNumber==4&&programNumber==0){
                    Link=driveLinks.tarekAlnagah_links[i];
                    Title=driveLinks.tarekAlnagah_title[i];}
                else if(artistNumber==4&&programNumber==1){
                    Link=driveLinks.nadiAlnagah_links[i];
                    Title=driveLinks.nadiAlnagah_title[i];}
                else if(artistNumber==5&&programNumber==0){
                    Link=driveLinks.waYatafkron_Links[i];
                    Title=driveLinks.waYatafkron_Title[i];}
                else if(artistNumber==5&&programNumber==1){
                    Link=driveLinks.AflaYatafakron_Links[i];
                    Title=driveLinks.AflaYatafakron_Title[i];}
                else if(artistNumber==5&&programNumber==2){
                    Link=driveLinks.ALislamAlghayb_Links[i];
                    Title=driveLinks.ALislamAlghayb_Titles[i];}
                else if(artistNumber==6&&programNumber==0){
                    Link=driveLinks.A3lamAltab3en_Links[i];
                    Title=driveLinks.A3lamAltab3en_Title[i];}
                else if(artistNumber==6&&programNumber==1){
                    Link=driveLinks.olaekAbaee_Links[i];
                    Title=driveLinks.olaekAbaee_Titles[i];}
                else if(artistNumber==7&&programNumber==0){
                    Link=driveLinks.al3thraWaAlmseh_Links[i];
                    Title=driveLinks.al3thraWaAlmseh_Titles[i];}

                tvTitle.setText(Title);
                tvTitle.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                mediaPlayer.stop();
                mediaPlayer.reset();
                view.setSelected(true);
                try {
                    mediaPlayer.setDataSource(Link);
                    mediaPlayer.prepare();
                    //sound.start();

                    SoundTime();
                } catch (IOException e) {e.printStackTrace();}

            }});

        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                if (state == TelephonyManager.CALL_STATE_RINGING) {
                    //INCOMING call
                    //do all necessary action to pause the audio
                    if(mediaPlayer!=null){//check mp

                        if(mediaPlayer.isPlaying()){

                            mediaPlayer.pause();
                        }
                    }

                } else if(state == TelephonyManager.CALL_STATE_IDLE) {
                    //Not IN CALL
                    //do anything if the phone-state is idle

                    if(mediaPlayer!=null){//check mp

                        if(!mediaPlayer.isPlaying()){

                            mediaPlayer.start();
                        }
                    }
                } else if(state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    //A call is dialing, active or on hold
                    //do all necessary action to pause the audio
                    //do something here
                    if(mediaPlayer!=null){//check mp

                        if(mediaPlayer.isPlaying()){

                            mediaPlayer.pause();
                        }
                    }
                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };//end PhoneStateListener

        TelephonyManager mgr = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        if(mgr != null) {
            mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }

        return view;}


    private void SoundTime() {
        seekBar.setMax(mediaPlayer.getDuration());
        int tim = (seekBar.getMax() / 1000);
        int m = tim / 60;
        int s = tim % 60;
        //////
        int tim0 = (seekBar.getProgress() / 1000);
        int m0 = tim0 / 60;
        int s0 = tim0 % 60;

        tvTotalTime.setText(m + " : " + s);
        tvCurrentTime.setText(m0 + " : " + s0);
    }
    public void checkPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // PERMISSION NOT GRANTED
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // FORCE TO REQUEST PERMISSIONS
                Toast.makeText(getActivity(),R.string.RequestAcceptPermission,Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            } else {
                // REQUEST PERMISSIONS FIRST TIME!
                ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            }}
        else {//PERMISSION ACCEPTED!
            Toast.makeText(getActivity(),R.string.AcceptedPermission,Toast.LENGTH_LONG).show();
            linkDownload();}}
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //PERMISSION ACCEPTED!
                    Toast.makeText(getActivity(),R.string.AcceptedPermission,Toast.LENGTH_LONG).show();
                    linkDownload();

                } else {
                    //PERMISSION REFUSED
                    Toast.makeText(getActivity(),R.string.RefusedPermissin,Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void linkDownload(){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Link));
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC+ "/المكتبة_الصوتية",Title);
        downloadManager=(DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
        addToFavouriteList(Title);}

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;}

    public void addToFavouriteList(String title){
        int check=db_favorite.getCheckListFavorite(title);
        if (check>0){Toast.makeText(getActivity(),"عذرا الملف موجود مسبقا",Toast.LENGTH_LONG).show();}
        else{db_favorite.insert(title);
            Toast.makeText(getActivity(),"تمت الاضافة الى التنزيلات",Toast.LENGTH_LONG).show();}}





    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();}

}
