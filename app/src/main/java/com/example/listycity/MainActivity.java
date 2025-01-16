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
    EditText cityInput;
    Button addCityButton, deleteCityButton;
    int selectedCity = -1; // Keeps track of the selected city's position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link views to their XML IDs
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addCityButton = findViewById(R.id.confirm);
        deleteCityButton = findViewById(R.id.delete_city);

        // Initialize the list with two cities
        cities = new ArrayList<>();
        cities.add("Edmonton");
        cities.add("Vancouver");

        // Set up the adapter to show cities in the ListView
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, cities);
        cityList.setAdapter(cityAdapter);

        // Highlight the clicked city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = position; // Save which city is selected
            cityList.setItemChecked(position, true); // Highlight the selected city
        });

        // Add a new city when "Confirm" is clicked
        addCityButton.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString().trim();
            if (!newCity.isEmpty()) {
                cities.add(newCity); // Add the city to the list
                cityAdapter.notifyDataSetChanged(); // Update the ListView
                cityInput.setText(""); // Clear the input field
            } else {
                Toast.makeText(this, "Enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete the selected city when "Delete City" is clicked
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != -1) {
                cities.remove(selectedCity); // Remove the selected city
                cityAdapter.notifyDataSetChanged(); // Update the ListView
                cityList.clearChoices(); // Clear the selection
                selectedCity = -1; // Reset the selected city
            } else {
                Toast.makeText(this, "Select a city to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}