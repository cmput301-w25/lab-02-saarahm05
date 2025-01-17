package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText inputCity;
    Button addCityButton, confirmButton, deleteCityButton;
    int selectedCityIndex = -1; // Store the selected city's index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        inputCity = findViewById(R.id.input_city);
        addCityButton = findViewById(R.id.add_city_button);
        confirmButton = findViewById(R.id.confirm_button);
        deleteCityButton = findViewById(R.id.delete_city_button);

        // Initialize the city list
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin"};
        dataList = new ArrayList<>(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // Initially hide the input field and confirm button
        inputCity.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);

        // Add City button logic
        addCityButton.setOnClickListener(v -> {
            inputCity.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.VISIBLE);
        });

        // Confirm button logic to add city
        confirmButton.setOnClickListener(v -> {
            String cityName = inputCity.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                inputCity.setText(""); // Clear the input
                inputCity.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
                Toast.makeText(this, cityName + " added!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "City name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete City button logic
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCityIndex != -1) {
                String removedCity = dataList.remove(selectedCityIndex);
                cityAdapter.notifyDataSetChanged();
                selectedCityIndex = -1; // Reset selection
                Toast.makeText(this, removedCity + " deleted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No city selected to delete", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle city selection from ListView
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCityIndex = position; // Set the selected city's index
            String selectedCity = dataList.get(position);
            Toast.makeText(this, selectedCity + " selected", Toast.LENGTH_SHORT).show();
        });
    }
}
