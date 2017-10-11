package com.example.bhanu.homework08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;



public class SavedCities extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_CITIES_LIST = "citieslist";
    public static final String ARG_DATA_MANAGER = "dataManager";
    ArrayList<City> citiesList;
    DatabaseDatamanager datamanager;

    public SavedCities() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SavedCities newInstance(String param1, String param2) {
        SavedCities fragment = new SavedCities();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            citiesList = (ArrayList<City>) getArguments().getSerializable(ARG_CITIES_LIST);
            MainActivity activity= (MainActivity)getActivity();
            this.datamanager=activity.getDatamanager();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_saved_cities, container, false);
        RecyclerView recyclerView= (RecyclerView) v.findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecycleAdapter(citiesList,datamanager,getActivity()));
        return  v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
