package com.example.rafaelmeyer.mycustomcamerafinal.view.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rafaelmeyer.mycustomcamerafinal.R;
import com.example.rafaelmeyer.mycustomcamerafinal.controller.MainActivityController;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public AppCompatActivity appCompatActivity;
    private MainActivityController mainActivityController = new MainActivityController();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appCompatActivity = this;

        mainActivityController.startFragmentCamera(this);

    }

    @Override
    public void onBackPressed() {
        if (mainActivityController.verifyIfCameraGalleryIsForeground()) {
            mainActivityController.startFragmentCamera(this);
        } else {
            finish();
        }
    }

}
