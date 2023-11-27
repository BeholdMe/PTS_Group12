package com.example.pts.Search;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import com.example.pts.R;


import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView listViewTutor;
    private Button btnSearch;
    private EditText editTextCategory;
    private Spinner tutorSpinner;
    TutorAdapter adapter;
    String selectedSort = "";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        listViewTutor = findViewById(R.id.listViewTutor);
        btnSearch = findViewById(R.id.btnSearch);
        editTextCategory = findViewById(R.id.editTextCategory);
        tutorSpinner = findViewById(R.id.tutorSpinner);

        /*saveTutor("selectedCategory", "firstName", "lastName", "email", "phone", "qualifications", "workexperience", "city", "state", "20", "time", "100") ;
        saveTutor("Math", "Math", "Tutor", "email.gmail.com", "1234", "degree", "job", "Arlington", "Tx", "1", "MW 10-12", "100") ;
        saveTutor("Math", "Math", "Tutor", "email.gmail.com", "1234", "degree", "job", "Arlington", "Tx", "2", "MW 10-12", "50") ;
        saveTutor("Math", "Math", "Tutor", "email.gmail.com", "1234", "degree", "job", "Arlington", "Tx", "3", "MW 10-12", "10") ;
        saveTutor("Piano", "Piano", "Tutor", "email.gmail.com", "1234", "degree", "job", "Arlington", "Tx", "100", "TT 10-12", "70") ;*/

        String[] sort = {"price", "distance"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sort);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tutorSpinner.setAdapter(spinnerAdapter);

        ArrayList<Tutor> tutors = viewtutor();

        //tutors.add(new Tutor("Firstname LastName", "Category", "Dallas Tx", "email.com", "100"));
        //tutors.add(new Tutor("Firstname LastName", "Category", "Dallas Tx", "email.com", "10"));

        adapter = new TutorAdapter(this, tutors);
        listViewTutor.setAdapter(adapter);

        tutorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedSort = (String) parentView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedSort = "None";
            }

        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                String selectedCategory = editTextCategory.getText().toString().trim();

                ArrayList<Tutor> result = searchAndSort(tutors, selectedCategory, selectedSort);
                tutors.clear();
                tutors.addAll(result);
                adapter.notifyDataSetChanged();

            }
        });
    }


    private ArrayList<Tutor> viewtutor() {
        ArrayList<Tutor> list = new ArrayList<>();
        String line = "";
        int n = 0;
        try {
            FileInputStream fileInputStream = openFileInput("tutors.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 12 ) {
                    list.add(new Tutor(parts[1].trim() + " " + parts[2].trim(),  parts[0].trim(),  parts[7].trim() + " " + parts[8].trim(), parts[9].trim(),  parts[10].trim(),  parts[11].trim()));
                    n += 1;
                }
            }


            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;


    }
/*
    private void saveTutor(String selectedCategory, String firstName, String lastName, String email, String phone, String qualifications, String workexperience, String city, String state, String distance, String time, String price) {
        try {


            String data= selectedCategory + "," + firstName + "," + lastName + "," + email + "," + phone + "," + qualifications + "," + workexperience + "," + city + "," + state + "," + distance + "," + time + "," + price + "\n";
            FileOutputStream fileOutputStream = openFileOutput("tutors.txt", MODE_APPEND);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();


        } catch (IOException e) {
            e.printStackTrace();

        }
    }
*/
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<Tutor> searchAndSort(ArrayList<Tutor> tutors, String searchCategory, String sortBy) {

        ArrayList<Tutor> filteredTutors = new ArrayList<>();
        for (Tutor tutor : tutors) {
            if (tutor.getCategory().equals(searchCategory)) {
                filteredTutors.add(tutor);
            }
        }


        if ("price".equalsIgnoreCase(sortBy)) {
            Collections.sort(filteredTutors, Comparator.comparing(t -> Integer.parseInt(t.getPrice())));
        } else if ("distance".equalsIgnoreCase(sortBy)) {
            Collections.sort(filteredTutors, Comparator.comparing(t -> Integer.parseInt(t.getDistance())));
        } else {
            Collections.sort(filteredTutors, Comparator.comparing(Tutor::getCategory));
        }

        return filteredTutors;
    }


}
