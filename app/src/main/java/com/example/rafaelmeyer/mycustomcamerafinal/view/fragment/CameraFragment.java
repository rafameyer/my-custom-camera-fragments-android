package com.example.rafaelmeyer.mycustomcamerafinal.view.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.rafaelmeyer.mycustomcamerafinal.R;
import com.example.rafaelmeyer.mycustomcamerafinal.controller.CameraController;

import java.io.IOException;

/**
 * Created by rafael.meyer on 11/4/16.
 */
public class CameraFragment extends Fragment implements SurfaceHolder.Callback {

    private CameraController cameraController = new CameraController();
    private static final String TAG = "CameraFragment";

    private AppCompatActivity activity;

    private Camera myCamera;
    private SurfaceView mySurfaceView;
    private SurfaceHolder mySurfaceHolder;

    private Fragment fragment = new Fragment();
    private boolean previewing = false;

    private int REQUEST_CAMERA = 1001;
    private int REQUEST_WRITE_READ = 1002;

    private FloatingActionButton myFAPTakePicture;
    private FloatingActionButton myFAPCancelPicture;
    private FloatingActionButton myFAPAddPicture;

    private String mCurrentPhotoPath;

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: onResume");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_camera, container, false);

        mySurfaceView = (SurfaceView) view.findViewById(R.id.surfaceView);

        inicializeSurfaceView();

        activity = (AppCompatActivity) this.getActivity();
        fragment = this;

        myFAPTakePicture = (FloatingActionButton) view.findViewById(R.id.fapTakePicture);
        myFAPCancelPicture = (FloatingActionButton) view.findViewById(R.id.fapCancelPicture);
        myFAPAddPicture = (FloatingActionButton) view.findViewById(R.id.fapAddPicture);

        myFAPTakePicture.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFAPTakePicture.setVisibility(View.INVISIBLE);
                        myFAPCancelPicture.setVisibility(View.VISIBLE);
                        myFAPAddPicture.setVisibility(View.VISIBLE);
                        myCamera.takePicture(null, null, mPictureCallback);
                    }
                }
        );

        return view;
    }

    private void inicializeSurfaceView() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {

            mySurfaceHolder = mySurfaceView.getHolder();
            mySurfaceHolder.addCallback(this);

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myCamera = Camera.open();
        myCamera.setDisplayOrientation(90);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (previewing) {
            myCamera.stopPreview();
            previewing = false;
        } else {
            try {
                myCamera.setPreviewDisplay(mySurfaceHolder);
                myCamera.startPreview();
                previewing = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        myCamera.stopPreview();
        myCamera.release();
        myCamera = null;
    }

    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(final byte[] data, Camera camera) {
            myFAPCancelPicture.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myCamera.startPreview();
                            myFAPAddPicture.setVisibility(View.INVISIBLE);
                            myFAPCancelPicture.setVisibility(View.INVISIBLE);
                            myFAPTakePicture.setVisibility(View.VISIBLE);
                        }
                    }
            );

            myFAPAddPicture.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            cameraController.addNewPhoto(activity, bitmap);
                            myFAPAddPicture.setVisibility(View.INVISIBLE);
                            myFAPCancelPicture.setVisibility(View.INVISIBLE);
                            myFAPTakePicture.setVisibility(View.VISIBLE);
                            myCamera.startPreview();
                        }
                    }
            );
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}