package com.example.pts.TutoringCategories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pts.R;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateCategoryActivity extends AppCompatActivity {

    private EditText editTextCategoryName, editTextCategoryDescription;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcategory);

        editTextCategoryName = findViewById(R.id.editTextCategoryName);
        editTextCategoryDescription = findViewById(R.id.editTextCategoryDescription);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryname = editTextCategoryName.getText().toString().trim();
                String categorydescription = editTextCategoryDescription.getText().toString().trim();

                createcategory(categoryname, categorydescription);
                showToast("New tutoring category was created");
                finish();

            }
        });

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
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}