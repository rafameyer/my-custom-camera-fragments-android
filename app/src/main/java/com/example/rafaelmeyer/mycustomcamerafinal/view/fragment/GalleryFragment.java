package com.example.rafaelmeyer.mycustomcamerafinal.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafaelmeyer.mycustomcamerafinal.R;
import com.example.rafaelmeyer.mycustomcamerafinal.controller.GalleryController;
import com.example.rafaelmeyer.mycustomcamerafinal.model.Item;
import com.example.rafaelmeyer.mycustomcamerafinal.view.adapter.MyPhotoAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael.meyer on 11/4/16.
 */
public class GalleryFragment extends Fragment implements MyPhotoAdapter.OnPassSelected, MyPhotoAdapter.OnPassFirstSelected{

    private GalleryController galleryController = new GalleryController();

    private String path = "/storage/emulated/0/Android/data/com.example.rafaelmeyer.mycustomcamerafinal/files/Pictures/";
    private File mGalleryFolder = new File(path);

    private MyPhotoAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private Toolbar toolbarSelected;
    private TextView textViewLabel;
    private ImageView imageViewDeleteToolbar;
    private ImageView imageViewBackArrowToolbar;
    private ImageView imageViewUnselectedToolbar;

    private Toolbar toolbarMain;
    private ImageButton imageButtonCameraFragment;
    private ImageButton imageButtonGalleryFragment;

    private int countSelected;
    private boolean isFirstAcitiviry = false;
    private List<Item> itemList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_gallery, container, false);

        loadingDataInMemory();
        initializationRecyclerView(view);

        return view;
    }

    private void loadingDataInMemory() {
        int size;
        if (mGalleryFolder.listFiles() == null) {
            size = 0;
        } else {
            size = mGalleryFolder.listFiles().length;
        }

        for (int i = 0; i < size; i++) {
            String file = mGalleryFolder.listFiles()[i].toString();
            Item item = new Item(file, false);
            itemList.add(item);
        }
    }

    private void initializationRecyclerView(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyPhotoAdapter(mGalleryFolder, itemList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void removeItemSelected() {
        galleryController.removeItemSelected(mGalleryFolder.listFiles().length, itemList);
    }

    private void deleteAllItemsSelected() {
        galleryController.deleteAllItemSelected(mGalleryFolder.listFiles(), itemList);
    }

    @Override
    public void onPassSelected(View v, File imageModel, Boolean isFirst, int count) {

    }

    @Override
    public void onPassFirstSelected(Boolean isFirst, int count) {

    }


}
