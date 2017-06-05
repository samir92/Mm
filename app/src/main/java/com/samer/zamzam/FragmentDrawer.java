package com.samer.zamzam;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

public class FragmentDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_drawer);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int fragments = getFragmentManager().getBackStackEntryCount();
            if (fragments > 0) {
                super.onBackPressed();
            } else {
                if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                    super.onBackPressed();
                } else {
                    Toast.makeText(getBaseContext(),R.string.backExit,
                            Toast.LENGTH_SHORT).show();
                }
                back_pressed = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            fragment=new MainMenuFragment();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment,fragment);
            ft.commit();


        } else if (id == R.id.nav_download) {
            fragment=new FavoriteFragment();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment,fragment);
            ft.commit();


        }else if (id == R.id.nav_collection) {
            fragment=new RecyclerView();
            FragmentManager fm=getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment,fragment);
            ft.commit();

        }

        else if (id == R.id.nav_about) {
            Intent intent=new Intent(this,SplashScreen.class);
            startActivity(intent);

        } else if (id == R.id.nav_rate) {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.samer.zamzam"));
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,"مكتبة زمزم الصوتية");
            intent.putExtra(Intent.EXTRA_TEXT,"مكتبة زمزم الصوتية" +
                    "\n https://play.google.com/store/apps/details?id=com.samer.zamzam");
            startActivity(Intent.createChooser(intent,"اختر تطبيق للمشاركة"));

        } else if (id == R.id.nav_send) {
            Intent msg=new Intent(Intent.ACTION_SEND);
            msg.putExtra(Intent.EXTRA_EMAIL, new String[] { "samir.alkaisey@gmail.com" });
            msg.putExtra(Intent.EXTRA_SUBJECT,"Zamzam Application");
            msg.setType("text/html");
            startActivity(msg);

        }else if (id == R.id.nav_exit) {
        this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
