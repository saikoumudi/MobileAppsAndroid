package com.example.bhanu.homework08;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by praka on 4/7/2017.
 */

public class CityWeatherAdapter extends RecyclerView.Adapter<ViewHolderCityWeather> {
    public static interface ChangeCurrentDay{
        public void changecurrentDay(int day);
    }

    ArrayList<DayForecast> dayForecasts;
    ChangeCurrentDay interface1;
    SimpleDateFormat simpleDateFormat;
    Activity activity;
    public CityWeatherAdapter(ArrayList<DayForecast> dayForecasts, ChangeCurrentDay interface1, Activity activity) {
        this.dayForecasts = dayForecasts;
        this.interface1 = interface1;
        this.activity=activity;
        simpleDateFormat=new SimpleDateFormat("d 'th' MMM ''yy");
    }

    @Override
    public ViewHolderCityWeather onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cityweatherchild,parent,false);
        ViewHolderCityWeather holder=new ViewHolderCityWeather(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolderCityWeather holder, final int position) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              interface1.changecurrentDay(position);
          }
      });
        holder.textView.setText(simpleDateFormat.format(new Date(dayForecasts.get(position).getDate())));
        Picasso.with(activity).load(dayForecasts.get(position).getDayCloudUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return dayForecasts.size();
    }
}
