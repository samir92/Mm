package com.samer.zamzam;


import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
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
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    Db_Favorite db_favorite;
    ArrayList<AppList> AppLists = new ArrayList<>();
    Button play,stop,pause,delete;
    TextView tvTitle, tvCurrentTime, tvTotalTime;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    ListView listView;
    ArrayList<String> titles;
    String title;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_favorite, container, false);

        play=(Button)view.findViewById(R.id.fav_btn_play);
        pause=(Button)view.findViewById(R.id.fav_btn_pause);
        stop=(Button)view.findViewById(R.id.fav_btn_stop);
        delete=(Button)view.findViewById(R.id.fav_btn_delete);
        listView=(ListView)view.findViewById(R.id.favListView);
        tvTitle=(TextView) view.findViewById(R.id.favtvTitle);
        tvTotalTime=(TextView) view.findViewById(R.id.favtvTotalTime);
        tvCurrentTime=(TextView) view.findViewById(R.id.favtvCurrentTime);
        seekBar=(SeekBar)view.findViewById(R.id.fav_seekBar);
        titles=new ArrayList<>();
        mediaPlayer=new MediaPlayer();
        db_favorite=new Db_Favorite(getActivity());

        getFavouriteList();

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,titles);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                title=titles.get(i);
                tvTitle.setText(title);
                tvTitle.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                mediaPlayer.stop();
                mediaPlayer.reset();
                view.setSelected(true);

                checkPermission();


            }});

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
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                    sleep(1000);
                                    currentPostion = mediaPlayer.getCurrentPosition();
                                    seekBar.setProgress(currentPostion);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    mediaPlayer.start();
                    updateSeekBar.start();}
            }});

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().getPath()+ "/Music/المكتبة_الصوتية/"+title;
                File file=new File(path);
                file.delete();
                db_favorite.Delete(title);
                showData();
                Toast.makeText(getActivity(),"deleted",Toast.LENGTH_LONG).show();
            }});
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

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

    public void showData(){
        ArrayList<String> listData=db_favorite.getAllFavorite();
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(arrayAdapter);

    }
    public void SoundTime() {
        seekBar.setMax(mediaPlayer.getDuration());
        int tim = (seekBar.getMax() / 1000);
        int m = tim / 60;
        int s = tim % 60;
        //////
        int tim0 = (seekBar.getProgress() / 1000);
        int m0 = tim0 / 60;
        int s0 = tim0 % 60;

        tvTotalTime.setText(m + " : " + s);
        tvCurrentTime.setText(m0 + " : " + s0);}

    public void checkPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // PERMISSION NOT GRANTED
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // FORCE TO REQUEST PERMISSIONS
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},200);
            } else {
                // REQUEST PERMISSIONS FIRST TIME!

                ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},200);
            }}
        else {//PERMISSION ACCEPTED!
            playMedia();}}

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 200:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //PERMISSION ACCEPTED!
                    playMedia();} else {
                    /*PERMISSION REFUSED */}
                break;
        }
    }


public void playMedia(){
    String path = Environment.getExternalStorageDirectory().getPath()+ "/Music/المكتبة_الصوتية/"+title;
    try {
        mediaPlayer.setDataSource(path);
        mediaPlayer.prepare();
        SoundTime();
    } catch (IOException e) {e.printStackTrace();}
    }


    public void getFavouriteList(){
        AppLists=db_favorite.getAllFavorite();
        if(AppLists.size()==0){Toast.makeText(getActivity(),R.string.emptylist,Toast.LENGTH_LONG).show();}
        else{
            for(int i=0;i<AppLists.size();i++){
                titles.add(AppLists.get(i).getTitle());}
        }}
}