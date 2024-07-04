package com.example.dngrocery.login_screen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dngrocery.MainActivity;
import com.example.dngrocery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_and_Register extends AppCompatActivity {
    TextView signUp, logIn,ForgetPass;
    LinearLayout signUpLayout, logInLayout;
    Button btn_logIn,btn_SingIn;
    TextInputEditText email,password,emails,passwords,passwords01;
    ImageView img_gg,img_fb,imgotp;
    FirebaseAuth firebaseAuth;

    @RequiresApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_and_register);

        signUp = findViewById(R.id.singUp);
        logIn = findViewById(R.id.logIn);
        signUpLayout = findViewById(R.id.singUpLayout);
        logInLayout = findViewById(R.id.logInLayout);

        email=findViewById(R.id.eMail);
        emails=findViewById(R.id.eMails);
        password=findViewById(R.id.passwords);
        passwords=findViewById(R.id.passwordss);
        passwords01=findViewById(R.id.passwords01);

        ForgetPass=findViewById(R.id.ForgetPass);

        img_fb=findViewById(R.id.img_fb);
        img_gg=findViewById(R.id.img_gg);
        imgotp=findViewById(R.id.img_otp);


        btn_logIn = findViewById(R.id.btn_logIn);
        btn_SingIn = findViewById(R.id.btn_SingIn);
        firebaseAuth=FirebaseAuth.getInstance();


        ///Sự kiện text signUp
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_register();

                btn_SingIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String emal=emails.getText().toString().trim();
                        String pass=passwords.getText().toString().trim();

                        if (TextUtils.isEmpty(emal)) {
                            // Xử lý trường hợp email rỗng
                            emails.setError("Email ko đc để trống");
                            Toast.makeText(getApplicationContext(), "Email không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (TextUtils.isEmpty(pass)) {
                            // Xử lý trường hợp mật khẩu rỗng
                            Toast.makeText(getApplicationContext(), "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        firebaseAuth.createUserWithEmailAndPassword(emal,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                                    layout_login();
                                    emails.setText("");
                                    passwords.setText("");

                                }else {
                                    Toast.makeText(Login_and_Register.this,"Lỗi : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                    }
                });


            }
        });

        ///Sự kiện text logIn
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_login();
            }
        });

        ///Sự Kiện button Login
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emal=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if (TextUtils.isEmpty(emal)) {
                    // Xử lý trường hợp email rỗng
                    Toast.makeText(getApplicationContext(), "Email không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(emal,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }else {
                            Toast.makeText(Login_and_Register.this,"Lỗi : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        ////Quên MK
        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emals=email.getText().toString().trim();
                if (TextUtils.isEmpty(emals)) {
                    // Xử lý trường hợp email rỗng
                    Toast.makeText(getApplicationContext(), "Email không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.sendPasswordResetEmail(emals).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Vui lòng kiểm tra email",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Login_and_Register.this,"Lỗi : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        ///Sự kiện OTP
        imgotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login_OTP.class));
                finish();
            }
        });

        img_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login_and_Register.this, "Tính năng hiện đang cập nhật", Toast.LENGTH_SHORT).show();
            }
        });
        img_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login_and_Register.this, "Tính năng hiện đang cập nhật", Toast.LENGTH_SHORT).show();
            }
        });

    }
    ///Layout login register
    public void layout_login(){
        signUp.setBackground(null);
        signUp.setTextColor(getResources().getColor(R.color.pinkColor, null));
        logIn.setBackground(getResources().getDrawable(R.drawable.switch_trcks, null));
        signUpLayout.setVisibility(View.GONE);
        logInLayout.setVisibility(View.VISIBLE);
        logIn.setTextColor(getResources().getColor(R.color.textColor, null));
        btn_logIn.setVisibility(View.VISIBLE);
        btn_SingIn.setVisibility(View.GONE);
    }
    public void layout_register(){
        signUp.setBackground(getResources().getDrawable(R.drawable.switch_trcks, null));
        signUp.setTextColor(getResources().getColor(R.color.textColor, null));
        logIn.setBackground(null);
        signUpLayout.setVisibility(View.VISIBLE);
        logInLayout.setVisibility(View.GONE);
        logIn.setTextColor(getResources().getColor(R.color.pinkColor, null));
        btn_logIn.setVisibility(View.GONE);
        btn_SingIn.setVisibility(View.VISIBLE);
    }
}

