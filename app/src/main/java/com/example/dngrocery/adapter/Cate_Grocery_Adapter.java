package com.example.dngrocery.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dngrocery.R;
import com.example.dngrocery.model.Cate_Grocery_model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;
import java.util.List;

public class Cate_Grocery_Adapter extends RecyclerView.Adapter<Cate_Grocery_Adapter.ViewHolder>{
    List<Cate_Grocery_model> list;
    Context context;

    FirebaseFirestore database;
    FirebaseStorage storage;
    StorageReference storageReference;

    public Cate_Grocery_Adapter(List<Cate_Grocery_model> list, Context context, FirebaseFirestore database){
        this.list=list;
        this.context = context;
        this.database = database;


        // Khởi tạo FirebaseStorage
        storage = FirebaseStorage.getInstance();

        // Khởi tạo StorageReference
        storageReference = storage.getReference();
    }
    @NonNull
    @Override
    public Cate_Grocery_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater .inflate(R.layout.item_cate_grocery, parent, false);
        return new Cate_Grocery_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cate_Grocery_Adapter.ViewHolder holder, int position) {
        holder.tenloai.setText(list.get(position).getTenloai());
        Glide.with(context).load(list.get(position).getAnhloai()).into(holder.imgloai);
//        holder.tesst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.test.setBackgroundResource(R.drawable.rounded_background2);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenloai;
        ImageView imgloai;
        FrameLayout test;
        LinearLayout tesst;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            test=itemView.findViewById(R.id.test);
            tesst=itemView.findViewById(R.id.tesst);
            tenloai=itemView.findViewById(R.id.tenloai);
            imgloai=itemView.findViewById(R.id.anhloai);
        }
    }
}
