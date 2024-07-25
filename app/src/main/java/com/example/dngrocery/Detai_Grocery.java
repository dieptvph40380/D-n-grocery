package com.example.dngrocery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detai_grocery);

        img_detail=findViewById(R.id.imga_detail);

////        Glide.with(this)
////                .load(R.drawable.background)
////                .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(12)))
////                .into(img_detail);
//        MaterialCardView cardView = findViewById(R.id.card_view);
//
//        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
//                .toBuilder()
//                .setBottomLeftCorner(CornerFamily.ROUNDED, 16)
//                .build();
//
//        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
//        cardView.setBackground(shapeDrawable);

    }
}