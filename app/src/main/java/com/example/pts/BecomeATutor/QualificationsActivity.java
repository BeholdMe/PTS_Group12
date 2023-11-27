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

public class QualificationsActivity extends AppCompatActivity {

    private EditText editTextQualifications, editTextWorkExperience;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualifications);

        editTextQualifications = findViewById(R.id.editTextQualifications);
        editTextWorkExperience = findViewById(R.id.editTextWorkExperience);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qualifications = editTextQualifications.getText().toString().trim();
                String workexperience = editTextWorkExperience.getText().toString().trim();

                String selectedCategory = getIntent().getStringExtra("categoryName");
                String firstName = getIntent().getStringExtra("firstName");
                String lastName = getIntent().getStringExtra("lastName");
                String email = getIntent().getStringExtra("email");
                String phone = getIntent().getStringExtra("phone");

                Intent intent2 = new Intent(QualificationsActivity.this, InformationActivity.class);
                intent2.putExtra("categoryName", selectedCategory);
                intent2.putExtra("firstName", firstName);
                intent2.putExtra("lastName", lastName);
                intent2.putExtra("email", email);
                intent2.putExtra("phone", phone);
                intent2.putExtra("qualifications", qualifications);
                intent2.putExtra("workexperience", workexperience);


                startActivity(intent2);

                finish();
            }
        });


    }
}