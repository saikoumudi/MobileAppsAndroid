package com.example.bhanu.homework08;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by praka on 4/6/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
   TextView cityText;
    TextView temparatureText;
    ImageView star;
    public ViewHolder(View itemView) {
        super(itemView);
        cityText= (TextView) itemView.findViewById(R.id.cityRecycler);
        temparatureText= (TextView) itemView.findViewById(R.id.temparatureRecycler);
         star= (ImageView) itemView.findViewById(R.id.starImage);

    }
}
