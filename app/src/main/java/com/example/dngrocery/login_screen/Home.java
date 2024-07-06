package com.example.dngrocery.login_screen;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.dngrocery.R;
import com.example.dngrocery.fragment.Bookmark_Fragment;
import com.example.dngrocery.fragment.Home_Fragment;
import com.example.dngrocery.fragment.Noti_Fragment;
import com.example.dngrocery.fragment.Profile_Fragment;

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
                Fragment fragment = null;
                switch (model.getId()){
                    case home:
                        fragment =new Home_Fragment();
                        loadFragment(fragment);
                        break;
                    case bookmark:
                        fragment =new Bookmark_Fragment();
                        loadFragment(fragment);
                        break;

                    case notification:
                        fragment =new Noti_Fragment();
                        loadFragment(fragment);
                        break;
                    case person:
                        fragment =new Profile_Fragment();
                        loadFragment(fragment);
                        break;
                }
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                String name;
                switch (model.getId()){
                    case home:


                            break;
                    case bookmark:


                        break;

                    case notification:


                        break;
                    case person:


                        break;
                }
                return null;
            }
        });
        // Hiển thị Fragment mặc định khi ứng dụng khởi động
        if (savedInstanceState == null) {
            bottomNavigation.show(home, true);  // Hiển thị mục Home là mục được chọn
            loadFragment(new Home_Fragment());  // Hiển thị HomeFragment mặc định
        }

    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}