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

import com.example.dngrocery.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;

public class Cate_Grocery_Adapter extends RecyclerView.Adapter<Cate_Grocery_Adapter.ViewHolder>{

    FirebaseFirestore db;
    RecyclerView rcv;
    FloatingActionButton fab;
    Context context;
    @NonNull
    @Override
    public Cate_Grocery_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater .inflate(R.layout.item_cate_grocery, parent, false);
        return new Cate_Grocery_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cate_Grocery_Adapter.ViewHolder holder, int position) {
        holder.tesst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.test.setBackgroundResource(R.drawable.rounded_background2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tensp,gia,tenloai;
        Button btnxemct,btnthemGH,btnDatHang;
        ImageView imgdssp;
        FrameLayout test;
        LinearLayout tesst;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            test=itemView.findViewById(R.id.test);
            tesst=itemView.findViewById(R.id.tesst);
        }
    }
}
