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
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BecomeATutorActivity extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPhone, editTextNewUsername, editTextNewPassword, editTextSecurity;
    private Button btnNext;
    String selectedCategory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_becomeatutor);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        btnNext = findViewById(R.id.btnNext);

        /*createcategory("Math", "Mathematics");
        createcategory("French", "Language");
        createcategory("Piano", "Instrument");*/



        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        List<String> categoriesList = readCategoriesFromFile("categories.txt");
        String[] categories = categoriesList.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedCategory = (String) parentView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = editTextFirstName.getText().toString().trim();
                String lastName = editTextLastName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();

                Intent intent = new Intent(BecomeATutorActivity.this, QualificationsActivity.class);
                intent.putExtra("categoryName", selectedCategory);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);

                startActivity(intent);
                finish();
            }
        });

    }

/*
    private List<String> readCategoriesFromFile(String filename) {
        List<String> categoriesList = new ArrayList<>();
        InputStream inputStream = null;

        try {
            inputStream = getAssets().open(filename);
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                categoriesList.add(scanner.nextLine());
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return categoriesList;
    }
*/

    private List<String> readCategoriesFromFile(String filename) {
        List<String> list = new ArrayList<>();
        String line = "";
        int n = 0;

        try {
            FileInputStream fileInputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 ) {
                    list.add(parts[0]);
                    n += 1;
                }
            }


            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;


    }

    private void createcategory(String categoryName, String categoryDescription) {
        try {
            String data = categoryName + "," + categoryDescription + "\n";
            FileOutputStream fileOutputStream = openFileOutput("categories.txt", MODE_APPEND);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
