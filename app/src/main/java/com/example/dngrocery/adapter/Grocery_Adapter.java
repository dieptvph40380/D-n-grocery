package com.example.dngrocery.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dngrocery.Detai_Grocery;
import com.example.dngrocery.R;
import com.example.dngrocery.login_screen.Login_and_Register;
import com.example.dngrocery.model.Cate_Grocery_model;
import com.example.dngrocery.model.Grocery_model;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class Grocery_Adapter extends RecyclerView.Adapter<Grocery_Adapter.ViewHolder>{

    List<Grocery_model> list;
    Context context;

    FirebaseFirestore database;
    FirebaseStorage storage;
    StorageReference storageReference;
    public Grocery_Adapter(List<Grocery_model> list, Context context, FirebaseFirestore database){
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
    public Grocery_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater .inflate(R.layout.item_grocery, parent, false);
        return new Grocery_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Grocery_Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getAnhsp()).into(holder.imgsp);
        holder.tensp.setText(list.get(position).getTensp());
        holder.giasp.setText(list.get(position).getGia());

        holder.imgsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,Detai_Grocery.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenloai,tensp,giasp;
        ImageView imgsp;
        FrameLayout test;
        LinearLayout tesst;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tensp=itemView.findViewById(R.id.tv_tensp);
            imgsp=itemView.findViewById(R.id.img_sp);
            giasp=itemView.findViewById(R.id.tv_giasp);
        }
    }
}
