package com.example.pts.LoginAndRegistration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pts.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private TextView textViewRegister;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPhone, editTextNewUsername, editTextNewPassword, editTextSecurity;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        textViewRegister = findViewById(R.id.textViewRegister);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextNewUsername = findViewById(R.id.editTextNewUsername);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);
        editTextSecurity = findViewById(R.id.editTextSecurity);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = editTextFirstName.getText().toString().trim();
                String lastName = editTextLastName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String newUsername = editTextNewUsername.getText().toString().trim();
                String newPassword = editTextNewPassword.getText().toString().trim();
                String security = editTextSecurity.getText().toString().trim();

                if(newUsername.length()<8){

                    showToast("Username should be 8 characters long");

                }
                else if(!isValidPassword(newPassword)) {
                    showToast("Password must include one letter, number, Capital letter, and wild character");
                }
                else {
                    register(newUsername, newPassword, security, firstName, lastName, email, phone);
                    showToast("Successfully Registered");
                    finish();
                }
            }
        });


    }

    private void register(String newUsername, String newPassword, String security, String firstName, String lastName, String email, String phone) {
        try {
            String data = newUsername + "," + newPassword +  "," + security +  "," + firstName +  "," + lastName +  "," + email +  "," + phone + "\n";
            FileOutputStream fileOutputStream = openFileOutput("login.txt", MODE_APPEND);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
