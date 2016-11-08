package com.example.rafaelmeyer.mycustomcamerafinal.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.rafaelmeyer.mycustomcamerafinal.R;
import com.example.rafaelmeyer.mycustomcamerafinal.controller.MainActivityController;

public class MainActivity extends AppCompatActivity {

    private MainActivityController mainActivityController = new MainActivityController();
    public AppCompatActivity appCompatActivity;

    private Toolbar toolbarMain;
    private ImageButton imageButtonCamera;
    private ImageButton imageButtonGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appCompatActivity = this;

        mainActivityController.startFragmentCamera(this);

        if (mainActivityController.verifyIfCameraGalleryIsForeground()) {
            imageButtonGallery.setVisibility(View.INVISIBLE);
            imageButtonCamera.setVisibility(View.VISIBLE);
        }

        imageButtonCamera = (ImageButton) findViewById(R.id.imageButtonCameraFragment);
        imageButtonGallery = (ImageButton) findViewById(R.id.imageButtonGalleryFragment);

        imageButtonCamera.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainActivityController.startFragmentCamera(appCompatActivity);

                        imageButtonGallery.setVisibility(View.VISIBLE);
                        imageButtonCamera.setVisibility(View.INVISIBLE);
                    }
                }
        );

        imageButtonGallery.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainActivityController.startFragmentGallery(appCompatActivity);

                        imageButtonGallery.setVisibility(View.INVISIBLE);
                        imageButtonCamera.setVisibility(View.VISIBLE);
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        if (mainActivityController.verifyIfCameraGalleryIsForeground()) {
            mainActivityController.startFragmentCamera(this);

            imageButtonGallery.setVisibility(View.VISIBLE);
            imageButtonCamera.setVisibility(View.INVISIBLE);
        } else {
            finish();
        }
    }
}
