package com.example.dngrocery.model;

import com.google.firebase.Timestamp;

import java.util.HashMap;

public class Cate_Grocery_model {
    private String anhloai;
    private String tenloai;

    public Cate_Grocery_model() {
    }
    public HashMap<String, Object> convertHashMap(){
        HashMap<String, Object> cate_grocery = new HashMap<>();
        cate_grocery.put("tenloai",tenloai);
        cate_grocery.put("anhloai",anhloai);

        return cate_grocery;
    }
    public Cate_Grocery_model(String anhloai, String tenloai) {
        this.anhloai = anhloai;
        this.tenloai = tenloai;
    }

    public String getAnhloai() {
        return anhloai;
    }

    public void setAnhloai(String anhloai) {
        this.anhloai = anhloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

}
