package com.example.rafaelmeyer.mycustomcamerafinal.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rafaelmeyer.mycustomcamerafinal.R;
import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

/**
 * Created by rafael.meyer on 11/10/16.
 */
public class PhotoFragment extends Fragment {

    private ImageView imageViewPhoto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        imageViewPhoto = (ImageView) view.findViewById(R.id.imageViewPhoto);

        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("PhotoFragment");
        String fileBundle = fragment.getArguments().getString("jiripoca");

/*
        Bitmap bitmap = BitmapFactory.decodeFile(fileBundle);

        imageViewPhoto.setImageBitmap(bitmap);
*/

        Picasso.with(getActivity()).load(fileBundle).into(imageViewPhoto);

        return view;
    }


}
