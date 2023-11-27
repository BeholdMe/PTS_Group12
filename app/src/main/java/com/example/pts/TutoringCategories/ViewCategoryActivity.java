package com.example.pts.TutoringCategories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.pts.R;

import java.util.ArrayList;
import java.util.List;

public class ViewCategoryActivity extends AppCompatActivity {

    private ListView listViewCategory;
    private Button btnCreateNewCategory;
    //private ArrayList<String> list = new ArrayList<>();
    //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myArrayList);
    //ArrayList<Category> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcategory);

        listViewCategory = findViewById(R.id.listViewCategory);
        btnCreateNewCategory = findViewById(R.id.btnCreateNewCategory);


        ArrayList<Category> categories = viewcategory();


        CategoryAdapter adapter = new CategoryAdapter(this, categories);
        listViewCategory.setAdapter(adapter);


        btnCreateNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(ViewCategoryActivity.this, CreateCategoryActivity.class));
                finish();

            }
        });

    }
    private ArrayList<Category> viewcategory() {
        ArrayList<Category> list = new ArrayList<>();
        String line = "";
        int n = 0;
        try {
            FileInputStream fileInputStream = openFileInput("categories.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 ) {
                    list.add(new Category(parts[0].trim(),  parts[1].trim()));
                    n += 1;
                }
            }


            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;


    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}