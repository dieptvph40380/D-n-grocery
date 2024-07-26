package com.example.dngrocery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

public class Detai_Grocery extends AppCompatActivity {
    ImageView img_detail;
    ImageView imageView,img_Product;
    TextView tv_name,tv_des,tv_quantity,tv_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detai_grocery);

        img_detail=findViewById(R.id.imga_detail);
        tv_name=findViewById(R.id.tv_tensp_detail);
        tv_des=findViewById(R.id.tv_mota_detail);
        tv_price=findViewById(R.id.tv_gia_detail);

        Intent intent=getIntent();
        String image=intent.getStringExtra("image");
        String name=intent.getStringExtra("tensp");
        String des=intent.getStringExtra("motasp");
        String price=intent.getStringExtra("giasp");
        String quantiy=intent.getStringExtra("tenloai");
        tv_name.setText(name);
        tv_des.setText(des);
        tv_price.setText("Price:" +price+"$");
        // Kiểm tra xem các dữ liệu từ Intent có hợp lệ không
        if (image != null && !image.isEmpty()) {
            Glide.with(this)
                    .load(image)
                    .into(img_detail);
        } else {
            // Xử lý trường hợp image bị null hoặc empty, bạn có thể hiển thị hình ảnh mặc định
            img_detail.setImageResource(R.drawable.background); // Đảm bảo bạn có default_image trong drawable
        }

        if (name != null) {
            tv_name.setText(name);
        } else {
            tv_name.setText("No name available");
        }

        if (des != null) {
            tv_des.setText(des);
        } else {
            tv_des.setText("No description available");
        }

        if (price != null) {
            tv_price.setText("Price: " + price + "$");
        } else {
            tv_price.setText("Price: N/A");
        }
    }

    }