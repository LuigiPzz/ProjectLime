package com.app.projectLime;

import android.app.Application;
import android.net.Uri;

/**
 * Created by luigi.pozzi on 22/02/2018.
 * singleton, salvataggio variabili globali
 */

public class MyApplication extends Application {



    private String username;
    private String email;
    private Uri photourl;




    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPhotoUrl(Uri photourl) {
        this.photourl = photourl;
    }
    public Uri getPhotoUrl() {
        return photourl;
    }


}