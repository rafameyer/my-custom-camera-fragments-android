package com.example.rafaelmeyer.mycustomcamerafinal.controller;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.rafaelmeyer.mycustomcamerafinal.R;
import com.example.rafaelmeyer.mycustomcamerafinal.view.fragment.CameraFragment;
import com.example.rafaelmeyer.mycustomcamerafinal.view.fragment.GalleryFragment;

/**
 * Created by rafael.meyer on 11/4/16.
 */
public class MainActivityController  {

    private Fragment myFragmentCamera;
    private Fragment myFragmentGallery;

    public void startFragmentCamera(AppCompatActivity fragment) {
        if (myFragmentCamera == null) {
            if (myFragmentGallery != null){
                fragment.getSupportFragmentManager().beginTransaction()
                        .remove(myFragmentGallery)
                        .commit();
                myFragmentGallery = null;
            }
            myFragmentCamera = new CameraFragment();
            fragment.getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentCamera, myFragmentCamera)
                    .commit();
        }
    }

    public void startFragmentGallery(AppCompatActivity fragment) {
        if (myFragmentGallery == null) {
            if (myFragmentCamera != null) {
                fragment.getSupportFragmentManager().beginTransaction()
                        .remove(myFragmentCamera)
                        .commit();
                myFragmentCamera = null;
            }
            myFragmentGallery = new GalleryFragment();

            fragment.getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentGallery, myFragmentGallery)
                    .commit();
        }
    }

    public boolean verifyIfCameraFragmentIsForeground() {
        boolean aux = false;
        if (myFragmentCamera != null) {
            aux = true;
        } else {
            if (myFragmentCamera == null) {
                aux = false;
            }
        }
        return aux;
    }

    public boolean verifyIfGalleryFragmentIsForeground() {
        if (myFragmentGallery != null) {
            return true;
        } else {
            return false;
        }
    }

}
