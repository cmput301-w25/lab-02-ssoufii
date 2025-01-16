package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> cities;
    int selectedCity = -1;//holds current position of selected city

    EditText cityInput; //this UI component lets us type the city name
    Button addCityButton, deleteCityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //calls the onCreate() method from the parent class (AppCompatActivity) to initialize the activity correctly
        setContentView(R.layout.activity_main); //this the actual link between this file and the layout file activity main

        //*** VERY necessary links between the java variables used later and their corresponding UI elements in activity_main
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);


        addCityButton = findViewById(R.id.confirm);
        deleteCityButton = findViewById(R.id.delete_city);

        cities = new ArrayList<>();

        cities.add("Edmonton"); //initially putting one city

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, cities);
        cityList.setAdapter(cityAdapter); //this adapter connect the cities list to the ListView

        // highlight the clicked city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = position; // save which city is selected
            cityList.setItemChecked(position, true);
        });

        // Add a new city when "Confirm" is clicked
        addCityButton.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString(); //retrival
            if (!newCity.isEmpty()) { //has something been typed?????yes
                cities.add(newCity); // then, add the city to the list
                cityAdapter.notifyDataSetChanged(); // update the ListView
                cityInput.setText("");
            } //nothing if else
        });

        // Delete the selected city when "Delete City" is clicked
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != -1) {
                cities.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                cityList.clearChoices();
                selectedCity = -1; // this a reset on the selected city
            }
        });
    }
}