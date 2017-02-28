package com.github.tungan5055.yourmoney.SQLLite;

/**
 * Created by an on 4/16/2016.
 */
public class ThuChiDTO {
    int id;
    int sotien;
    String tenkhoan;
    String ngaythang;
    String ghichu;
    String image_name;
    String image_source;



    int Tong =0;
    public int layTong(int a){
        Tong = Tong + a;
        return Tong;

    }
    public int getSotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkhoan() {
        return tenkhoan;
    }

    public void setTenkhoan(String tenkhoan) {
        this.tenkhoan = tenkhoan;
    }

    public String getNgaythang() {
        return ngaythang;
    }

    public void setNgaythang(String ngaythang) {
        this.ngaythang = ngaythang;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_source(){
        return image_source;
    }
    public void setImage_source(String image_source){
        this.image_source = image_source;
    }


}
