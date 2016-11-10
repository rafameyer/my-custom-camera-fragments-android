package com.example.rafaelmeyer.mycustomcamerafinal.view.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rafaelmeyer.mycustomcamerafinal.R;
import com.example.rafaelmeyer.mycustomcamerafinal.model.Item;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael.meyer on 11/7/16.
 */
public class MyPhotoAdapter extends RecyclerView.Adapter<MyPhotoAdapter.ViewHolder> {

    private static final String TAG = "MyAdapter";
    public boolean isFirst = false;
    private int countSelected;

    private File imagesFile;
    private List<Item> itemList = new ArrayList<>();

    private OnPassSelected myOnPassSelected;
    private OnPassFirstSelected myOnPassFirstSelected;

    public MyPhotoAdapter() {

    }

    public int getCountSelected() {
        return countSelected;
    }

    public void setCountSelected(int countSelected) {
        this.countSelected = countSelected;
    }

    public File getImagesFile() {
        return imagesFile;
    }

    public MyPhotoAdapter(File imagesFile, List<Item> itemList) {
        this.imagesFile = imagesFile;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        File imageFile = new File(getImagesFile().listFiles()[position].toURI());
        Picasso.with(holder.itemView.getContext()).load(imageFile).resize(250,250).centerCrop().into(holder.imageViewItem);

        if (itemList.get(position).getSelected()) {
            holder.cardViewItem.setBackgroundColor(Color.BLUE);
            holder.imageViewItemSelected.setVisibility(View.VISIBLE);
        } else {
            holder.cardViewItem.setBackgroundColor(Color.WHITE);
            holder.imageViewItemSelected.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        if (getImagesFile().listFiles() == null) {
            return 0;
        }
        return getImagesFile().listFiles().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView imageViewItemSelected;
        ImageView imageViewItem;
        CardView cardViewItem;

        public ViewHolder(View itemView) {
            super(itemView);

            imageViewItemSelected = (ImageView) itemView.findViewById(R.id.imageViewItemSelected);
            imageViewItem = (ImageView) itemView.findViewById(R.id.imageViewItem);
            cardViewItem = (CardView) itemView.findViewById(R.id.cardViewItem);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (isFirst) {
                if (!itemList.get(getAdapterPosition()).getSelected()) {
                    itemList.get(getAdapterPosition()).setSelected(true);
                    countSelected++;
                    if (myOnPassSelected != null) {
                        myOnPassSelected.onPassSelected(v, getImagesFile().listFiles()[getAdapterPosition()], isFirst, countSelected, itemView.findViewById(R.id.imageViewItem));
                    }
                } else {
                    if (countSelected > 0) {
                        itemList.get(getAdapterPosition()).setSelected(false);
                        countSelected--;
                    }
                    if (countSelected == 0) {
                        if (myOnPassSelected != null) {
                            myOnPassSelected.onPassSelected(v, getImagesFile().listFiles()[getAdapterPosition()], isFirst, countSelected, itemView.findViewById(R.id.imageViewItem));
                        }
                        isFirst = false;
                    }
                }
            } else {
                if (myOnPassSelected != null) {
                    myOnPassSelected.onPassSelected(v, getImagesFile().listFiles()[getAdapterPosition()], isFirst, countSelected, itemView.findViewById(R.id.imageViewItem));
                }
            }
            notifyDataSetChanged();
        }

        @Override
        public boolean onLongClick(View v) {
            if (!isFirst && countSelected == 0) {
                countSelected++;
                isFirst = true;
                itemList.get(getAdapterPosition()).setSelected(true);
                if (myOnPassFirstSelected != null) {
                    myOnPassFirstSelected.onPassFirstSelected(isFirst, countSelected);
                }
                notifyDataSetChanged();
                return true;
            }
            return false;
        }
    }

    public interface OnPassFirstSelected {
        void onPassFirstSelected(Boolean isFirst, int count);
    }

    public interface OnPassSelected {
        void onPassSelected(View v, File imageModel, Boolean isFirst, int count, View view);
    }

    public void setMyOnPassSelected(OnPassSelected myOnPassSelected) {
        this.myOnPassSelected = myOnPassSelected;
    }

    public void setMyOnPassFirstSelected(OnPassFirstSelected myOnPassFirstSelected) {
        this.myOnPassFirstSelected = myOnPassFirstSelected;
    }

}
