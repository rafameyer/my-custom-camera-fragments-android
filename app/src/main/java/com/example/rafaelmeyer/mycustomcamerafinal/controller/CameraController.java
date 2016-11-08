package com.example.rafaelmeyer.mycustomcamerafinal.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.rafaelmeyer.mycustomcamerafinal.R;
import com.example.rafaelmeyer.mycustomcamerafinal.view.fragment.CameraFragment;
import com.example.rafaelmeyer.mycustomcamerafinal.view.fragment.GalleryFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rafael.meyer on 11/4/16.
 */
public class CameraController {

    public void addNewPhoto(Activity fragment, Bitmap bitmap) {
        File photoFile = null;
        try {
            photoFile = createImageFile(fragment, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createImageFile(Activity fragment, Bitmap bitmap) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PNG_" + timeStamp + "_";
        File storageDir = fragment.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, ".png", storageDir);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
