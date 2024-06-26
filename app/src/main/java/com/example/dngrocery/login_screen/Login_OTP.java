package com.example.dngrocery.login_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dngrocery.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Map;

public class Login_OTP extends AppCompatActivity {
    String phoneNumber;
    CountryCodePicker countryCodePicker;
    EditText phoneInput;
    Button btn_sendOtp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_otp);


        countryCodePicker=findViewById(R.id.login_countrycode);
        phoneInput=findViewById(R.id.login_mobile_number);
        btn_sendOtp=findViewById(R.id.send_otp_btn);
        progressBar=findViewById(R.id.login_progress_bar);

        progressBar.setVisibility(View.GONE);
        countryCodePicker.registerCarrierNumberEditText(phoneInput);
        btn_sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!countryCodePicker.isValidFullNumber()){
                    phoneInput.setError("Phone number not valid");
                    return;
                }
                Intent inten=new Intent(Login_OTP.this,Login_OTP2.class);
                inten.putExtra("phone",countryCodePicker.getFullNumberWithPlus());
                startActivity(inten);
            }
        });


//        Map<String,String>data=new HashMap<>();
//        FirebaseFirestore.getInstance().collection("test").add(data);

    }
}