package com.example.dngrocery.model;

import java.util.HashMap;

public class Grocery_model {
    private String anhsp;
    private String tensp;
    private String mota;
    private String tenloai;
    private String gia;

    public HashMap<String, Object> convertHashMap(){
        HashMap<String, Object> grocery = new HashMap<>();
        grocery.put("anhsp",anhsp);
        grocery.put("gia",gia);
        grocery.put("mota",mota);
        grocery.put("tenloai",tenloai);
        grocery.put("tensp",tensp);

        return grocery;
    }

    public Grocery_model() {
    }

    public Grocery_model(String anhsp, String tensp, String mota, String tenloai, String gia) {
        this.anhsp = anhsp;
        this.tensp = tensp;
        this.mota = mota;
        this.tenloai = tenloai;
        this.gia = gia;
    }

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
