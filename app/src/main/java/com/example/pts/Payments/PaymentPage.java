package com.example.pts.Payments;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pts.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentPage extends AppCompatActivity
{
    //Buttons for the PayPal, Credit Cards, and Apple/Google Pay
    Button btnPaypal, btnCC, btnPhonePay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        //The three vars are the buttons from the layout of HomePage
        btnPaypal = findViewById(R.id.btnPaypal);
        btnCC = findViewById(R.id.btnCC);
        btnPhonePay = findViewById(R.id.btnPhonePay);

        //Listeners to send user to the page they click on
        /*
            Website for PayPal Integration
            https://developer.paypal.com/docs/archive/paypal-here/merchant-onboarding/
        */
        btnPaypal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Currently using default startactivity to another page/activity
                //Will change given GooglePay/PayPal integration
                startActivity(new Intent(com.example.pts.Payments.PaymentPage.this, com.example.pts.Payments.CreditCardPayments.class));
            }
        });

        btnCC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.Payments.PaymentPage.this, com.example.pts.Payments.CreditCardPayments.class));
            }
        });

        /*
            Google Pay: https://developers.google.com/pay/api/android/guides/setup
            Both PayPal and Google Pay seem to allow credit card transaction as well, so might do that
            instead of making our own
         */
        btnPhonePay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.Payments.PaymentPage.this, com.example.pts.Payments.CreditCardPayments.class));
            }
        });
    }

    //Potential saved Credit Card payment option as class? or as attribute of user class?
    //Save credit card number, name, security code, zip code, and expiration date

    //Use of chromium to handle cash payment
    //Template
    /*<service
        android:name=".SampleIsReadyToPayService"
        android:exported="true">
        <intent-filter>
            <action android:name="org.chromium.intent.action.IS_READY_TO_PAY" />
        </intent-filter>
    </service>
     */


}