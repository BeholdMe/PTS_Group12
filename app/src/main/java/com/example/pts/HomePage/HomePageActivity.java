package com.example.pts.HomePage;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import com.example.pts.R;
import android.widget.Button;
import android.view.View;

import android.os.Bundle;

public class HomePageActivity extends AppCompatActivity {

    private Button btnTutoringCategories, btnBecomeATutor, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //The three vars are the buttons from the layout of HomePage
        btnTutoringCategories = findViewById(R.id.btnTutoringCategories);
        btnBecomeATutor = findViewById(R.id.btnBecomeATutor);
        btnSearch = findViewById(R.id.btnSearchHome);

        //Listeners to send user to the page they click on
        btnTutoringCategories.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.TutoringCategories.ViewCategoryActivity.class));
            }
        });

        btnBecomeATutor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.BecomeATutor.BecomeATutorActivity.class));
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.Search.SearchActivity.class));
            }
        });
    }
}

/*Put in Android Manifest file in case other doesn't work
Put in build.gradle dependencies if doesn't work
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'com.google.android.material:material:1.10.0'

<activity
            android:name=".HomePage.HomePageActivity"
            android:exported="false"
            android:label="@string/title_activity_home_page"
            android:theme="@style/Theme.PTS.NoActionBar" />
 */

/*
content_home_page.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior">

    <fragment
        android:id="@+id/nav_host_fragment_content_home_page"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/nav_graph" />
</androidx.constraintlayout.widget.ConstraintLayout>

 */