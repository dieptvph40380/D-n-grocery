package com.example.dngrocery.login_screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.dngrocery.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Home extends AppCompatActivity {
    protected  final int home=1;
    protected  final int bookmark=2;


    protected  final int notification=3;
    protected  final int person=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        MeowBottomNavigation bottomNavigation=findViewById(R.id.meowBottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(home,R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(bookmark,R.drawable.bookmark));
        bottomNavigation.add(new MeowBottomNavigation.Model(notification,R.drawable.notifications));
        bottomNavigation.add(new MeowBottomNavigation.Model(person,R.drawable.person));


        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                Toast.makeText(Home.this, "Item Click", Toast.LENGTH_SHORT).show();
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                String name;
                switch (model.getId()){
                    case home: name="Home";
                        Intent intent=new Intent(Home.this, Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                            break;
                    case bookmark: name="Bookmark";
                        Intent intent1=new Intent(Home.this, Home.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                        break;

                    case notification: name="Notification";
                        break;
                    case person: name="Dash";
                        Intent inten1t=new Intent(Home.this, Wellcome.class);
                        startActivity(inten1t);
                        break;
                }
                bottomNavigation.setCount(notification,"9");
                return null;
            }
        });
    }
}