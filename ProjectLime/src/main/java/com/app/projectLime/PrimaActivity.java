package com.app.projectLime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class PrimaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Glide.with(this)
                .load(account.getPhotoUrl()) // add your image url
                .transform(new CircleTransform(this)) // applying the image transformer
                .into(imageView);*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.float_settings_menu, menu);

        TextView usr = findViewById(R.id.username);
        TextView email = findViewById(R.id.email);
        ImageView photourl = findViewById(R.id.photourl);

         if (((MyApplication) this.getApplication()).getUsername() != null) {

             usr.setText(((MyApplication) this.getApplication()).getUsername());
             email.setText(((MyApplication) this.getApplication()).getEmail());


             if(Objects.equals(usr.getText().toString(), getString(R.string.android_studio))){
                 Glide.with(this)
                         .load(((MyApplication) this.getApplication()).getImage())
                         .into(photourl);
             } else {
                 Glide.with(this)
                         .load(((MyApplication) this.getApplication()).getPhotoUrl())
                         .transform(new CircleTransform(this))
                         .into(photourl);
             }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super. onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_qrtools) {
            Intent ac = new Intent(getApplicationContext(), QRcodeActivity.class);
            startActivity(ac);
        } else if (id == R.id.nav_gallery) {
            Intent ac = new Intent(getApplicationContext(), ConsoleSonyActivity.class);
            startActivity(ac);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_settings) {
            Intent ac = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(ac);
        } else if (id == R.id.nav_gamepad) {
            Intent ac = new Intent(getApplicationContext(), BrandConsoleActivity.class);
            startActivity(ac);
        } else if (id == R.id.nav_share) {
            Intent ac = new Intent(getApplicationContext(), TestDBActivity.class);
            startActivity(ac);
        } else if (id == R.id.nav_rating) {
            Intent ac = new Intent(getApplicationContext(), RatingActivity.class);
            startActivity(ac);
        } else if (id == R.id.nav_login) {
            Intent ac = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(ac);
        } else if (id == R.id.nav_backup) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
