package com.example.pts.LoginAndRegistration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pts.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ForgotPasswordActivity extends AppCompatActivity{

    private TextView textViewForgot, textViewDynamic;
    private EditText editTextForgotUsername, editTextForgotEmail, editTextForgotSecurity;
    private Button btnSubmitForgot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editTextForgotUsername = findViewById(R.id.editTextForgotUsername);
        editTextForgotEmail = findViewById(R.id.editTextForgotEmail);
        editTextForgotSecurity = findViewById(R.id.editTextForgotSecurity);
        btnSubmitForgot = findViewById(R.id.btnSubmitForgot);

        btnSubmitForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextForgotUsername.getText().toString().trim();
                String email = editTextForgotEmail.getText().toString().trim();
                String security = editTextForgotSecurity.getText().toString().trim();

                String password = search(username, email, security);
                //textViewDynamic.setText(password);
                showToast(password);

            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String search(String username, String email, String security) {
        try {
            FileInputStream fileInputStream = openFileInput("login.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[0].equals(username)) {
                    if (parts[5].equals(email) && parts[2].equals(security)) {
                        return "Your Password is: " + parts[1];
                    }
                    else{
                        return "Email or Security question is wrong";
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Username is not registered";
    }
}

