package com.example.bhanu.homework08;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link currentCityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link currentCityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class currentCityFragment extends Fragment {
    private static final String ARG_CITY = "CITY";

    private City city;
    PrettyTime prettyTime;
    private OnFragmentInteractionListener mListener;

    public currentCityFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static currentCityFragment newInstance(String param1, String param2) {
        currentCityFragment fragment = new currentCityFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prettyTime=new PrettyTime();
        if (getArguments() != null) {
            city = (City) getArguments().getSerializable(MainActivity.CURRENT_CITY_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_currentcity, container, false);
        TextView citytext= (TextView) v.findViewById(R.id.cityTextView);
        citytext.setText(city.getName()+","+city.getCountry());
        TextView weatherText= (TextView) v.findViewById(R.id.weatherTextView);
        weatherText.setText(city.getWeatherText());
        ImageView cloudImage= (ImageView) v.findViewById(R.id.cloudImage);
        Picasso.with(getActivity()).load(city.getWeatherIconUrl()).into(cloudImage);

        TextView temparature= (TextView) v.findViewById(R.id.tempararureTextView);
        char degree = '\u00B0';
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        Log.d("demo","shared prefe"+sharedPreferences.getAll());
        String format=sharedPreferences.getString(MainActivity.TEMP_FORMAT_KEY,MainActivity.TEMP_CELSIUS);
        Log.d("demo","shared pref format "+format);
        if(format.equalsIgnoreCase(MainActivity.TEMP_CELSIUS)){
            Log.d("demo","celcius temp selected");
            temparature.setText("Temparature: "+city.getTemparatureC()+degree+"C");
        }
        else{
            temparature.setText("Temparature: "+city.getTemparatureF()+degree+"F");
        }
        TextView updatedtime= (TextView) v.findViewById(R.id.UpdatedTime);
        updatedtime.setText("Updated "+prettyTime.format(new Date(city.getUpdatedTime())));
        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void setCurrentCityButtonClicked();

    }
}
