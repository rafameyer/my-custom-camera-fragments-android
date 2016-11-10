package com.example.rafaelmeyer.mycustomcamerafinal.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.rafaelmeyer.mycustomcamerafinal.R;
import com.example.rafaelmeyer.mycustomcamerafinal.model.Item;
import com.example.rafaelmeyer.mycustomcamerafinal.view.adapter.MyPhotoAdapter;
import com.example.rafaelmeyer.mycustomcamerafinal.view.fragment.CameraFragment;
import com.example.rafaelmeyer.mycustomcamerafinal.view.fragment.GalleryFragment;
import com.example.rafaelmeyer.mycustomcamerafinal.view.fragment.PhotoFragment;

import java.io.File;
import java.util.List;

/**
 * Created by rafael.meyer on 11/4/16.
 */
public class GalleryController {

    private Fragment myPhotoFragment;
    private Fragment myCameraFragment;
    private MyPhotoAdapter myPhotoAdapter = new MyPhotoAdapter();

    public void startCameraFragmentFromGalleryFragment(AppCompatActivity activity, Fragment fragment) {
        myCameraFragment = new CameraFragment();
        activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.contentCamera, myCameraFragment, "CameraFragment")
                .commit();

        activity.getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commit();
    }

    public void removeItemSelected(int length, List<Item> itemList) {
        for (int i = length - 1; i >= 0; i--) {
            if (itemList.get(i).getSelected()) {
                itemList.get(i).setSelected(false);
                myPhotoAdapter.setCountSelected(myPhotoAdapter.getCountSelected() - 1);
            }
        }
    }

    public void deleteAllItemSelected(File[] files, List<Item> itemList) {
        int size = files.length - 1;
        for (int i = size; i >= 0; i--) {
            if (itemList.get(i).getSelected()) {
                itemList.get(i).setSelected(false);
                File fileModel = files[i];
                fileModel.delete();
                myPhotoAdapter.setCountSelected(myPhotoAdapter.getCountSelected() - 1);
            }
        }
    }

    public boolean verifyIfGalleryIsForeground() {
        if (myCameraFragment != null) {
            return true;
        } else {
            return false;
        }
    }

    public void startPhotoFragmentFromGalleryFragment(AppCompatActivity activity, File imageFile, View v) {
        Bundle bundle = new Bundle();
        bundle.putString("jiripoca", imageFile.toURI().toString());

        myPhotoFragment = new PhotoFragment();
        myPhotoFragment.setArguments(bundle);

        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentGallery, myPhotoFragment, "PhotoFragment")
                .addToBackStack("PhotoFragment")
                //.addSharedElement(v.findViewById(R.id.imageViewItem), activity.getString(R.string.transition_name))
                .commit();
    }
}
