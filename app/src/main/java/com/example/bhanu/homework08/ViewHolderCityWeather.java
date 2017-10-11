package com.example.bhanu.homework08;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by praka on 4/7/2017.
 */

public class ViewHolderCityWeather extends RecyclerView.ViewHolder {
    ImageView img;
    TextView textView;
    public ViewHolderCityWeather(View itemView) {
        super(itemView);
        img= (ImageView) itemView.findViewById(R.id.cloudImage);
        textView= (TextView) itemView.findViewById(R.id.textview);
    }
}
