package com.example.pts.BecomeATutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;


import androidx.appcompat.app.AppCompatActivity;
import com.example.pts.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InformationActivity extends AppCompatActivity {

    private EditText editTextCity, editTextState, editTextDistance, editTextTime, editTextPrice;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        editTextCity = findViewById(R.id.editTextCity);
        editTextState = findViewById(R.id.editTextState);
        editTextDistance = findViewById(R.id.editTextDistance);
        editTextTime = findViewById(R.id.editTextTime);
        editTextPrice = findViewById(R.id.editTextPrice);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editTextCity.getText().toString().trim();
                String state = editTextState.getText().toString().trim();
                String distance = editTextDistance.getText().toString().trim();
                String time = editTextTime.getText().toString().trim();
                String price = editTextPrice.getText().toString().trim();


                String selectedCategory = getIntent().getStringExtra("categoryName");
                String firstName = getIntent().getStringExtra("firstName");
                String lastName = getIntent().getStringExtra("lastName");
                String email = getIntent().getStringExtra("email");
                String phone = getIntent().getStringExtra("phone");
                String qualifications = getIntent().getStringExtra("qualifications");
                String workexperience = getIntent().getStringExtra("workexperience");

                saveTutor(selectedCategory, firstName, lastName, email, phone, qualifications, workexperience, city, state, distance, time, price);

                finish();

            }
        });


    }

    private void saveTutor(String selectedCategory, String firstName, String lastName, String email, String phone, String qualifications, String workexperience, String city, String state, String distance, String time, String price) {
        try {
            String data= selectedCategory + "," + firstName + "," + lastName + "," + email + "," + phone + "," + qualifications + "," + workexperience + "," + city + "," + state + "," + distance + "," + time + "," + price + "\n";
            FileOutputStream fileOutputStream = openFileOutput("tutors.txt", MODE_APPEND);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();

            showToast("You have been registered as a tutor");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }



    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}