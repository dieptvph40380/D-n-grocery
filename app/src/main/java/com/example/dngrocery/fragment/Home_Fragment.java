package com.example.dngrocery.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dngrocery.R;
import com.example.dngrocery.adapter.Cate_Grocery_Adapter;
import com.example.dngrocery.adapter.Grocery_Adapter;
import com.example.dngrocery.model.Cate_Grocery_model;
import com.example.dngrocery.model.Grocery_model;
import com.example.dngrocery.recyclerview.GridSpacingItemDecorationRC;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Home_Fragment extends Fragment {

    FirebaseFirestore db;
    RecyclerView rcv_cate,rcv_grocery;

    ArrayList<Cate_Grocery_model> loaiList = new ArrayList<>();
    Cate_Grocery_Adapter adapter_loai;
    ArrayList<Grocery_model> spList = new ArrayList<>();
    Grocery_Adapter adapter_sp;


    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_, container, false);

        rcv_cate=view.findViewById(R.id.Cate_Grocery);
        rcv_grocery=view.findViewById(R.id.Grocery);

        db=FirebaseFirestore.getInstance();

        //Loại
        adapter_loai = new Cate_Grocery_Adapter(loaiList,getContext(),db);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rcv_cate.setLayoutManager(linearLayoutManager);
        rcv_cate.setAdapter(adapter_loai);

        ListenFirebaseFirestore_Cate();

        // Sản phẩm
        adapter_sp = new Grocery_Adapter(spList,getContext(),db);
        int spanCount = 2; // Số cột trong GridLayoutManager
        int spacing = dpToPx(10, getContext()); ; // Khoảng cách giữa các item (đơn vị: dp)
        boolean includeEdge = true; // Có bao gồm khoảng cách ở cạnh
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        rcv_grocery.setLayoutManager(gridLayoutManager);
        rcv_grocery.setAdapter(adapter_sp);

        ListenFirebaseFirestore_Grocery();


        return view;
    }
    // Helper method to convert dp to px
    public static int dpToPx(int dp, Context context) {
        return Math.round(dp * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    private void ListenFirebaseFirestore_Cate(){
        db.collection("cate_grocery")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("TAG", "fail", error);
                            return;
                        }
                        if(value != null){
                            for (DocumentChange dc: value.getDocumentChanges()){
                                switch (dc.getType()){
                                    case ADDED:{
                                        Cate_Grocery_model newU = dc.getDocument().toObject(Cate_Grocery_model.class);
                                        loaiList.add(newU);
                                        adapter_loai.notifyItemInserted(loaiList.size() - 1);
                                        break;
                                    }
                                    case MODIFIED:{
                                        Cate_Grocery_model update = dc.getDocument().toObject(Cate_Grocery_model.class);
                                        if(dc.getOldIndex() == dc.getNewIndex()){
                                            loaiList.set(dc.getOldIndex(), update);
                                            adapter_loai.notifyItemChanged(dc.getOldIndex());

                                        } else {
                                            loaiList.remove(dc.getOldIndex());
                                            loaiList.add(update);
                                            adapter_loai.notifyItemMoved(dc.getOldIndex(), dc.getNewIndex());

                                        }
                                        break;
                                    }
                                    case REMOVED:{
                                        dc.getDocument().toObject(Cate_Grocery_model.class);
                                        loaiList.remove(dc.getOldIndex());
                                        adapter_loai.notifyItemRemoved(dc.getOldIndex());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });
    }
    private void ListenFirebaseFirestore_Grocery(){
        db.collection("grocery")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("TAG", "fail", error);
                            return;
                        }
                        if(value != null){
                            for (DocumentChange dc: value.getDocumentChanges()){
                                switch (dc.getType()){
                                    case ADDED:{
                                        Grocery_model newU = dc.getDocument().toObject(Grocery_model.class);
                                        spList.add(newU);
                                        adapter_sp.notifyItemInserted(spList.size() - 1);
                                        break;
                                    }
                                    case MODIFIED:{
                                        Grocery_model update = dc.getDocument().toObject(Grocery_model.class);
                                        if(dc.getOldIndex() == dc.getNewIndex()){
                                            spList.set(dc.getOldIndex(), update);
                                            adapter_sp.notifyItemChanged(dc.getOldIndex());

                                        } else {
                                            spList.remove(dc.getOldIndex());
                                            spList.add(update);
                                            adapter_sp.notifyItemMoved(dc.getOldIndex(), dc.getNewIndex());

                                        }
                                        break;
                                    }
                                    case REMOVED:{
                                        dc.getDocument().toObject(Grocery_model.class);
                                        spList.remove(dc.getOldIndex());
                                        adapter_sp.notifyItemRemoved(dc.getOldIndex());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });
    }
}