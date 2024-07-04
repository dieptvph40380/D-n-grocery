package com.example.dngrocery.login_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dngrocery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Login_OTP2 extends AppCompatActivity {

    EditText otpInput;
    Button btn_next;
    Long timout =60L;
    ProgressBar progressBar;
    TextView resendTv;
    String phoneNumber;
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    FirebaseAuth firebaseAuth;
    private PhoneAuthCredential autoRetrievedCredential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_otp2);

        otpInput=findViewById(R.id.login_otp);
        btn_next=findViewById(R.id.login_next_btn);
        progressBar=findViewById(R.id.login_progress_bar);
        resendTv=findViewById(R.id.resend_otp_textview);

        firebaseAuth=FirebaseAuth.getInstance();

        phoneNumber=getIntent().getExtras().getString("phone");
        Toast.makeText(getApplicationContext(),phoneNumber,Toast.LENGTH_SHORT).show();

        sendOTP(phoneNumber,false);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredOTP= otpInput.getText().toString();
                if (verificationCode != null && !verificationCode.isEmpty()) {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, enteredOTP);
                    setInprogress(true);
                    signIn(credential);
                } else {
                    Toast.makeText(Login_OTP2.this, "Vui lòng đợi mã xác minh được gửi", Toast.LENGTH_SHORT).show();
                }

            }
        });

        resendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOTP(phoneNumber,true);
            }
        });
        
    }

    public void sendOTP(String phoneNumber,boolean isResend){
        startResendTimer();
        setInprogress(true);
        PhoneAuthOptions.Builder builder=
                PhoneAuthOptions
                        .newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(timout, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Log.d("Login_OTP2", "onVerificationCompleted triggered");
                                setInprogress(false);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(Login_OTP2.this, "OTP gửi thất bại", Toast.LENGTH_SHORT).show();
                                setInprogress(false);
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                verificationCode=s;
                                resendingToken =forceResendingToken;
                                Toast.makeText(Login_OTP2.this, "OTP đã được gửi", Toast.LENGTH_SHORT).show();
                                setInprogress(false);
                            }
                        });
        if(isResend){
            if (resendingToken != null) {
                PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
            } else {
                Toast.makeText(Login_OTP2.this, "Token hết hạn, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                setInprogress(false);
            }
        }else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }
    public void setInprogress(boolean inprogress){
        if(inprogress){
            progressBar.setVisibility(View.VISIBLE);
            btn_next.setVisibility(View.GONE);
        }else {
            progressBar.setVisibility(View.GONE);
            btn_next.setVisibility(View.VISIBLE);
        }
    }

    public void signIn(PhoneAuthCredential phoneAuthCredential){
        setInprogress(true);
        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                setInprogress(false);
                if (task.isSuccessful()) {
                    // Đăng nhập thành công
                    Intent intent = new Intent(Login_OTP2.this, Login_OTP3.class);
                    intent.putExtra("phone", phoneNumber);
                    Toast.makeText(Login_OTP2.this, "Đúng OTP", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish(); // Kết thúc hoạt động hiện tại
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(Login_OTP2.this, "Sai OTP", Toast.LENGTH_SHORT).show();
//                    String errorMessage = "Sự cố đăng nhập: " + (task.getException() != null ? task.getException().getMessage() : "Không xác định");
//                    Toast.makeText(Login_OTP2.this, errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void startResendTimer() {
        resendTv.setEnabled(false);
        timout = 60L; // Khởi tạo lại biến timout nếu cần thiết
        new CountDownTimer(timout * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timout--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resendTv.setText("Resend OTP in " + timout + " seconds");
                    }
                });
            }

            @Override
            public void onFinish() {
                timout = 60L;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resendTv.setText("Resend OTP ");
                        resendTv.setEnabled(true);
                    }
                });
            }
        }.start();
    }
}