package com.example.rafaelmeyer.mycustomcamerafinal.controller;

import com.example.rafaelmeyer.mycustomcamerafinal.model.Item;
import com.example.rafaelmeyer.mycustomcamerafinal.view.adapter.MyPhotoAdapter;

import java.io.File;
import java.util.List;

/**
 * Created by rafael.meyer on 11/4/16.
 */
public class GalleryController {

    private MyPhotoAdapter myPhotoAdapter;

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
}
