package com.example.pts.Payments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pts.R;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Idk what this is for, it just keeps giving me error if I don't write it
@RequiresApi(api = Build.VERSION_CODES.O)
public class CreditCardPayments extends AppCompatActivity {

    //Button to submit the credit card
    private Button btnSubmitCC;
    private EditText editTxtCCNumber, editTxt1stNameCC, editTxt2ndNameCC, editTxtCCSecurity, editDateCC;
    private LocalDate currentDate = LocalDate.now();
    private LocalDate expDateCC;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_payments);

        btnSubmitCC = findViewById(R.id.btnSubmitCC);
        editTxtCCNumber = findViewById(R.id.editTxtCCNumber);
        editTxt1stNameCC = findViewById(R.id.editTextFirstName);
        editTxt2ndNameCC = findViewById(R.id.editTextLastName);
        editTxtCCSecurity = findViewById(R.id.editTxtCCSecurity);
        editDateCC = findViewById(R.id.editTextDate);

        btnSubmitCC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //String variables for card number, security code, and expiration date

                String ccNumber = editTxtCCNumber.getText().toString().trim();
                String cc1stName = editTxt1stNameCC.getText().toString().trim();
                String cc2ndName = editTxt2ndNameCC.getText().toString().trim();
                String ccSecurity = editTxtCCSecurity.getText().toString().trim();
                String ccDate = editDateCC.getText().toString().trim();

                //Use the editDateCC string to parse into expDateCC with pattern MM/yy
                expDateCC = LocalDate.parse(ccDate, DateTimeFormatter.ofPattern("MM-yy"));


                //Both the ccNumber and the ccSecurity uses number input so
                //it should not have any non-digit characters
                if (ccNumber.length() != 16)
                {
                    showToast("Credit Card Number should be 16 digits long");
                }
                else if(ccSecurity.length() < 3 || ccSecurity.length() > 4) //Check for length cuz Idk how to check for matchign the actual card w/o cash handling stuff
                {
                    showToast("CVS code is not required length (3 or 4)");
                }

                //Also try to match first name & last name w/the first name of the current user that is logged in
                //Potential problem w/above = Using another family member's card, using another friend's card
                //Therefore, allow any first name or last name so long as they are not empty or non-alphabet
                else if(!checkNameAllAlpha(cc1stName) || !checkNameAllAlpha(cc2ndName))
                {
                    showToast("Invalid name");
                }else if(expDateCC.isAfter(currentDate))
                {
                    showToast("Your card has expired. Please use a different card!");
                }
                else
                {
                    showToast("Successfully Entered Info. Receipt will be sent out shortyly!");
                    finish();
                }

            }
        });
    }

    public boolean checkNameAllAlpha(String name)
    {
        //Check if empty, return false if empty
        if (name == null || name == "")
        {
            return false;
        }

        // Traverse the string from start to end
        for (int i = 0; i < name.length(); i++)
        {
            // Check if the char is not a letter then return false,
            // else return true
            if (!Character.isLetter(name.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    /*
    Potential use to save credit cards in a separate file
    private void submitCC(String ccNumber, String ccSecurity, String ccDate)
    {
        try {
            String data = newUsername + "," + newPassword + "," + security + "," + firstName + "," + lastName + "," + email + "," + phone + "\n";
            FileOutputStream fileOutputStream = openFileOutput("login.txt", MODE_APPEND);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */


    //Show message using Toast widget
    private void showToast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}


    /*
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
     */