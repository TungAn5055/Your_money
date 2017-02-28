package com.github.tungan5055.yourmoney.SQLLite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by an on 4/16/2016.
 */
public class ThuChiDAO {
    SQLiteDatabase sqLiteDatabase;
    ThuChi thuChi;

    public ThuChiDAO(Context context) {
        thuChi = new ThuChi(context);
    }

    public void open() {
        sqLiteDatabase = thuChi.getWritableDatabase();
    }

    public void close() {
        thuChi.close();
    }

    public void themthuchi(ThuChiDTO tc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuChi.TABLE_SOTIEN, tc.getSotien());
        contentValues.put(ThuChi.TABLE_TENKHOAN, tc.getTenkhoan().toString());
        contentValues.put(ThuChi.TABLE_NGAY, tc.getNgaythang().toString());
        contentValues.put(ThuChi.TABLE_GHICHU, tc.getGhichu().toString());
        contentValues.put(ThuChi.IMAGE_SOURCE1, tc.getImage_source().toString());
        sqLiteDatabase.insert(ThuChi.DB_TABLETHUCHI, null, contentValues);
    }

    public void suathuchi(ThuChiDTO tc, String type, String id) {
        ContentValues contentValues = new ContentValues();
        if (type.equals("thu")) {
            contentValues.put(ThuChi.TABLE_SOTIEN, tc.getSotien());
            contentValues.put(ThuChi.TABLE_TENKHOAN, tc.getTenkhoan().toString());
            contentValues.put(ThuChi.TABLE_NGAY, tc.getNgaythang().toString());
            contentValues.put(ThuChi.TABLE_GHICHU, tc.getGhichu().toString());
            contentValues.put(ThuChi.IMAGE_SOURCE1, tc.getImage_source().toString());
            sqLiteDatabase.update(ThuChi.DB_TABLETHUCHI, contentValues, ThuChi.TABLE_ID + "=" + id, null);
        }
        if (type.equals("chi")) {
            contentValues.put(ThuChi.TABLE_SOTIEN2, tc.getSotien());
            contentValues.put(ThuChi.TABLE_TENKHOAN2, tc.getTenkhoan().toString());
            contentValues.put(ThuChi.TABLE_NGAY2, tc.getNgaythang().toString());
            contentValues.put(ThuChi.TABLE_GHICHU2, tc.getGhichu().toString());
            contentValues.put(ThuChi.IMAGE_SOURCE2, tc.getImage_source().toString());
            sqLiteDatabase.update(ThuChi.DB_TABLETHUCHI2, contentValues, ThuChi.TABLE_ID2 + "=" + id, null);
        }
    }

    public void themthuchi_chi(ThuChiDTO tc) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuChi.TABLE_SOTIEN2, tc.getSotien());
        contentValues.put(ThuChi.TABLE_TENKHOAN2, tc.getTenkhoan().toString());
        contentValues.put(ThuChi.TABLE_NGAY2, tc.getNgaythang().toString());
        contentValues.put(ThuChi.TABLE_GHICHU2, tc.getGhichu().toString());
        contentValues.put(ThuChi.IMAGE_SOURCE2, tc.getImage_source().toString());
        sqLiteDatabase.insert(ThuChi.DB_TABLETHUCHI2, null, contentValues);
    }

    public void themkhoanvay(ThuChiDTO tc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuChi.TABLE_SOTIEN3, tc.getSotien());
        contentValues.put(ThuChi.TABLE_TENKHOAN3, tc.getTenkhoan().toString());
        contentValues.put(ThuChi.TABLE_NGAY3, tc.getNgaythang().toString());
        contentValues.put(ThuChi.TABLE_GHICHU3, tc.getGhichu().toString());
        sqLiteDatabase.insert(ThuChi.DB_TABLETHUCHI3, null, contentValues);
    }

    public void themimage(ThuChiDTO tc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuChi.IMAGE_NAME, tc.getImage_name().toString());
        contentValues.put(ThuChi.IMAGE_SOURCE, tc.getImage_source().toString());
        sqLiteDatabase.insert(ThuChi.DB_TABLE_IMAGE, null, contentValues);
    }

    public List<ThuChiDTO> laydanhsachthuchi() {
        List<ThuChiDTO> dsnv2 = new ArrayList<ThuChiDTO>();
        String b = "select * from " + ThuChi.DB_TABLETHUCHI;
        Cursor cusor = sqLiteDatabase.rawQuery(b, null);
        cusor.moveToFirst();
        while (!cusor.isAfterLast()) {
            int id = cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_ID));
            String tenkhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TENKHOAN));
            int sotien = cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_SOTIEN));
            String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY));
            String source = cusor.getString(cusor.getColumnIndex(ThuChi.IMAGE_SOURCE1));
            ThuChiDTO tc = new ThuChiDTO();
            tc.setId(id);
            tc.setTenkhoan(tenkhoan);
            tc.setSotien(sotien);
            tc.setNgaythang(ngaythang);
            tc.setImage_source(source);
            dsnv2.add(tc);
            cusor.moveToNext();
        }
        String b0 = "select * from " + ThuChi.DB_TABLETHUCHI2;
        cusor = sqLiteDatabase.rawQuery(b0, null);
        cusor.moveToFirst();
        while (!cusor.isAfterLast()) {
            int id = cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_ID2));
            String tenkhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TENKHOAN2));
            String sotien = "-" + cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_SOTIEN2));
            String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY2));
            String source = cusor.getString(cusor.getColumnIndex(ThuChi.IMAGE_SOURCE2));
            ThuChiDTO tc = new ThuChiDTO();
            tc.setId(id);
            tc.setTenkhoan(tenkhoan);
            tc.setSotien(Integer.parseInt(sotien));
            tc.setNgaythang(ngaythang);
            tc.setImage_source(source);
            dsnv2.add(tc);
            cusor.moveToNext();
        }

        return dsnv2;
    }

    public List<ThuChiDTO> laydanhsachsearch(int isthu, int ischi, int isno, int isbank, int isten, int isngay, String text) {
        List<ThuChiDTO> dsnv2 = new ArrayList<ThuChiDTO>();
        if (isten == 1) {
            if (isthu == 1) {
                String b = "SELECT * FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_TENKHOAN + " LIKE '%" + text + "%' ;";
                Cursor cusor = sqLiteDatabase.rawQuery(b, null);
                cusor.moveToFirst();
                while (!cusor.isAfterLast()) {
                    String tenkhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TENKHOAN));
                    int sotien = cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_SOTIEN));
                    String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY));
                    ThuChiDTO tc = new ThuChiDTO();
                    tc.setTenkhoan(tenkhoan);
                    tc.setSotien(sotien);
                    tc.setNgaythang(ngaythang);
                    dsnv2.add(tc);
                    cusor.moveToNext();
                }
            }
            if (ischi == 1) {
                String b = "SELECT * FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_TENKHOAN2 + " LIKE '%" + text + "%' ;";
                ;
                Cursor cusor = sqLiteDatabase.rawQuery(b, null);
                cusor.moveToFirst();
                while (!cusor.isAfterLast()) {
                    String tenkhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TENKHOAN2));
                    int sotien = cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_SOTIEN2));
                    String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY2));
                    ThuChiDTO tc = new ThuChiDTO();
                    tc.setTenkhoan(tenkhoan);
                    tc.setSotien(sotien);
                    tc.setNgaythang(ngaythang);
                    dsnv2.add(tc);
                    cusor.moveToNext();
                }
            }

            if (isno == 1) {
                String b = "SELECT * FROM " + ThuChi.DB_TABLETHUCHI3 + " WHERE " + ThuChi.TABLE_TENKHOAN3 + " LIKE '%" + text + "%' ;";
                ;
                Cursor cusor = sqLiteDatabase.rawQuery(b, null);
                cusor.moveToFirst();
                while (!cusor.isAfterLast()) {
                    String tenkhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TENKHOAN3));
                    int sotien = cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_SOTIEN3));
                    String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY3));
                    ThuChiDTO tc = new ThuChiDTO();
                    tc.setTenkhoan(tenkhoan);
                    tc.setSotien(sotien);
                    tc.setNgaythang(ngaythang);
                    dsnv2.add(tc);
                    cusor.moveToNext();
                }
            }
            if (isbank == 1) {

            }
        }

        if (isngay == 1) {
            if (isthu == 1) {
                String b = "SELECT * FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_NGAY + " = '" + text + "' ;";
                Cursor cusor = sqLiteDatabase.rawQuery(b, null);
                cusor.moveToFirst();
                while (!cusor.isAfterLast()) {
                    String tenkhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TENKHOAN));
                    int sotien = cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_SOTIEN));
                    String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY));
                    ThuChiDTO tc = new ThuChiDTO();
                    tc.setTenkhoan(tenkhoan);
                    tc.setSotien(sotien);
                    tc.setNgaythang(ngaythang);
                    dsnv2.add(tc);
                    cusor.moveToNext();
                }
            }
            if (ischi == 1) {
                String b = "SELECT * FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_NGAY2 + " = '" + text + "' ;";
                ;
                Cursor cusor = sqLiteDatabase.rawQuery(b, null);
                cusor.moveToFirst();
                while (!cusor.isAfterLast()) {
                    String tenkhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TENKHOAN2));
                    int sotien = cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_SOTIEN2));
                    String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY2));
                    ThuChiDTO tc = new ThuChiDTO();
                    tc.setTenkhoan(tenkhoan);
                    tc.setSotien(sotien);
                    tc.setNgaythang(ngaythang);
                    dsnv2.add(tc);
                    cusor.moveToNext();
                }
            }

            if (isno == 1) {
                String b = "SELECT * FROM " + ThuChi.DB_TABLETHUCHI3 + " WHERE " + ThuChi.TABLE_NGAY3 + " = '" + text + "' ;";
                Cursor cusor = sqLiteDatabase.rawQuery(b, null);
                cusor.moveToFirst();
                while (!cusor.isAfterLast()) {
                    String tenkhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TENKHOAN3));
                    int sotien = cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_SOTIEN3));
                    String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY3));
                    ThuChiDTO tc = new ThuChiDTO();
                    tc.setTenkhoan(tenkhoan);
                    tc.setSotien(sotien);
                    tc.setNgaythang(ngaythang);
                    dsnv2.add(tc);
                    cusor.moveToNext();
                }
            }
            if (isbank == 1) {

            }
        }
        return dsnv2;
    }

    public List<ThuChiDTO> laydanhsachvay() {
        List<ThuChiDTO> dsnv3 = new ArrayList<ThuChiDTO>();
        String b = "select * from "+ ThuChi.DB_TABLETHUCHI3;
        Cursor cusor= sqLiteDatabase.rawQuery(b,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            /*int id= cusor.getInt(cusor.getColumnIndex(ThuChi.TABLE_ID3));*/
            String tenkhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TENKHOAN3));
            String sotien = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_SOTIEN3));
            String  ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY3));
            ThuChiDTO tc = new ThuChiDTO();
        /*    tc.setId(id);*/
            tc.setTenkhoan(tenkhoan);
            tc.setSotien(Integer.valueOf(sotien));
            tc.setNgaythang(ngaythang);
            dsnv3.add(tc);
            cusor.moveToNext();
        }
        return dsnv3;
    }

    public List<ThuChiDTO> layimage() {
        List<ThuChiDTO> dsnv5 = new ArrayList<ThuChiDTO>();
        String b = "select * from " + ThuChi.DB_TABLE_IMAGE;
        Cursor cusor = sqLiteDatabase.rawQuery(b, null);
        cusor.moveToFirst();
        while (!cusor.isAfterLast()) {
            int id = cusor.getInt(cusor.getColumnIndex(ThuChi.IMAGE_ID));
            String name = cusor.getString(cusor.getColumnIndex(ThuChi.IMAGE_NAME));
            String source = cusor.getString(cusor.getColumnIndex(ThuChi.IMAGE_SOURCE));

            ThuChiDTO tc = new ThuChiDTO();
            tc.setId(id);
            tc.setImage_name(name);
            tc.setImage_source(source);

            dsnv5.add(tc);
            cusor.moveToNext();
        }
        return dsnv5;
    }

    public int getTong_no() {
        int b = 0;
        String Tong_no = "SELECT SUM(" + ThuChi.TABLE_SOTIEN3 + ") FROM " + ThuChi.DB_TABLETHUCHI3 + ";";
        Cursor c = sqLiteDatabase.rawQuery(Tong_no, null);
        if (c.moveToFirst()) {
            b = c.getInt(0);
        } else {
            b = 9999;
        }
        c.close();
        return b;
    }

    public String getghichu(String id) {
        String ghichu;
        String b = "SELECT " + ThuChi.TABLE_GHICHU + " FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_ID + "=" + id + ";";
        Cursor cusor = sqLiteDatabase.rawQuery(b, null);
        if (cusor.moveToFirst()) {
            ghichu = cusor.getString(0);
        } else {
            ghichu = "helo";
        }
        cusor.close();
        return ghichu;
    }
    public String getghichuchi(String id) {
        String ghichu;
        String b = "SELECT " + ThuChi.TABLE_GHICHU2 + " FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_ID2 + "=" + id + ";";
        Cursor cusor = sqLiteDatabase.rawQuery(b, null);
        if (cusor.moveToFirst()) {
            ghichu = cusor.getString(0);
        } else {
            ghichu = "helo";
        }
        cusor.close();
        return ghichu;
    }

    public int getTong_thu(String month) {
        int b = 0;
        String Tong_thu;
        if (month.equals("All")) {
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_SOTIEN + ") FROM " + ThuChi.DB_TABLETHUCHI + " ;";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_SOTIEN + ") FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_NGAY + " BETWEEN '" + da1 + "' AND '" + da2 + "';";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_thu, null);
        if (c.moveToFirst()) {
            b = c.getInt(0);
        } else {
            b = 9999;
        }
        c.close();
        return b;

    }

    public int getTong_chi(String month) {
        int cd = 0;
        String Tong_chi;
        if (month.equals("All")) {
            Tong_chi = "SELECT SUM(" + ThuChi.TABLE_SOTIEN2 + ") FROM " + ThuChi.DB_TABLETHUCHI2 + ";";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_chi = "SELECT SUM(" + ThuChi.TABLE_SOTIEN2 + ") FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_NGAY2 + " BETWEEN '" + da1 + "' AND '" + da2 + "';";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_chi, null);
        if (c.moveToFirst()) {
            cd = c.getInt(0);
        } else {
            cd = 9999;
        }
        c.close();
        return cd;
    }


    public String getmaxtongthu(String month) {
        String cd;
        String Tong_chi, Tong_chi0;
        if (month.equals("All")) {
            Tong_chi = "SELECT " + ThuChi.TABLE_TENKHOAN + " FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_SOTIEN + "=( SELECT max(" + ThuChi.TABLE_SOTIEN + ") FROM " + ThuChi.DB_TABLETHUCHI + ");";
            Tong_chi0 = "SELECT " + ThuChi.TABLE_NGAY + " FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_SOTIEN + "=( SELECT max(" + ThuChi.TABLE_SOTIEN + ") FROM " + ThuChi.DB_TABLETHUCHI + ");";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_chi = "SELECT " + ThuChi.TABLE_TENKHOAN + " FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_SOTIEN + "=( SELECT max(" + ThuChi.TABLE_SOTIEN + ") FROM " + ThuChi.DB_TABLETHUCHI + ") AND " + ThuChi.TABLE_NGAY + " BETWEEN '" + da1 + "' AND '" + da2 + "';";
            Tong_chi0 = "SELECT " + ThuChi.TABLE_NGAY + " FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_SOTIEN + "=( SELECT max(" + ThuChi.TABLE_SOTIEN + ") FROM " + ThuChi.DB_TABLETHUCHI + ") AND " + ThuChi.TABLE_NGAY + " BETWEEN '" + da1 + "' AND '" + da2 + "';";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_chi, null);
        Cursor c0 = sqLiteDatabase.rawQuery(Tong_chi0, null);
        if (c.moveToFirst() && c0.moveToFirst()) {
            cd = c.getString(0) + "-----" + c0.getString(0);
        } else {
            cd = "000";
        }
        c.close();

        return cd;

    }


    public String getmaxtongchi(String month) {
        String cd;
        String Tong_chi, Tong_chi0;
        if (month.equals("All")) {
            Tong_chi = "SELECT " + ThuChi.TABLE_TENKHOAN2 + " FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_SOTIEN2 + "=( SELECT max(" + ThuChi.TABLE_SOTIEN2 + ") FROM " + ThuChi.DB_TABLETHUCHI2 + ");";
            Tong_chi0 = "SELECT " + ThuChi.TABLE_NGAY2 + " FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_SOTIEN2 + "=( SELECT max(" + ThuChi.TABLE_SOTIEN2 + ") FROM " + ThuChi.DB_TABLETHUCHI2 + ");";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_chi = "SELECT " + ThuChi.TABLE_TENKHOAN2 + " FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_SOTIEN2 + "=( SELECT max(" + ThuChi.TABLE_SOTIEN2 + ") FROM " + ThuChi.DB_TABLETHUCHI2 + ") AND " + ThuChi.TABLE_NGAY2 + " BETWEEN '" + da1 + "' AND '" + da2 + "';";
            Tong_chi0 = "SELECT " + ThuChi.TABLE_NGAY2 + " FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_SOTIEN2 + "=( SELECT max(" + ThuChi.TABLE_SOTIEN2 + ") FROM " + ThuChi.DB_TABLETHUCHI2 + ") AND " + ThuChi.TABLE_NGAY2 + " BETWEEN '" + da1 + "' AND '" + da2 + "';";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_chi, null);
        Cursor c0 = sqLiteDatabase.rawQuery(Tong_chi0, null);
        if (c.moveToFirst() && c0.moveToFirst()) {
            cd = c.getString(0) + "----" + c0.getString(0);
        } else {
            cd = "000";
        }
        c.close();
        return cd;
    }

    public int getmaxchimoney(String month) {
        int cd;
        String Tong_chi;
        if (month.equals("All")) {
            Tong_chi = "SELECT " + ThuChi.TABLE_SOTIEN2 + " FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_SOTIEN2 + "=( SELECT max(" + ThuChi.TABLE_SOTIEN2 + ") FROM " + ThuChi.DB_TABLETHUCHI2 + ");";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_chi = "SELECT " + ThuChi.TABLE_SOTIEN2 + " FROM " + ThuChi.DB_TABLETHUCHI2 + " WHERE " + ThuChi.TABLE_SOTIEN2 + "=( SELECT max(" + ThuChi.TABLE_SOTIEN2 + ") FROM " + ThuChi.DB_TABLETHUCHI2 + ") AND " + ThuChi.TABLE_NGAY2 + " BETWEEN '" + da1 + "' AND '" + da2 + "';";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_chi, null);
        if (c.moveToFirst()) {
            cd = Integer.valueOf(c.getString(0)) ;
        } else {
            cd = 0;
        }
        c.close();
        return cd;
    }

    public int getmaxthumoney(String month) {
        int cd;
        String Tong_chi;
        if (month.equals("All")) {
            Tong_chi = "SELECT " + ThuChi.TABLE_SOTIEN + " FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_SOTIEN + "=( SELECT max(" + ThuChi.TABLE_SOTIEN + ") FROM " + ThuChi.DB_TABLETHUCHI + ");";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_chi = "SELECT " + ThuChi.TABLE_SOTIEN + " FROM " + ThuChi.DB_TABLETHUCHI + " WHERE " + ThuChi.TABLE_SOTIEN + "=( SELECT max(" + ThuChi.TABLE_SOTIEN + ") FROM " + ThuChi.DB_TABLETHUCHI + ") AND " + ThuChi.TABLE_NGAY2 + " BETWEEN '" + da1 + "' AND '" + da2 + "';";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_chi, null);
        if (c.moveToFirst()) {
            cd = Integer.valueOf(c.getString(0)) ;
        } else {
            cd = 0;
        }
        c.close();
        return cd;
    }


    public void xoathu(ThuChiDTO thuChiDTO) {
        sqLiteDatabase.delete(ThuChi.DB_TABLETHUCHI, ThuChi.TABLE_ID + "=" + thuChiDTO.getId(), null);

    }

    public void xoachi(ThuChiDTO thuChiDTO2) {
        sqLiteDatabase.delete(ThuChi.DB_TABLETHUCHI2, ThuChi.TABLE_ID2 + "=" + thuChiDTO2.getId(), null);

    }

    public void xoano(ThuChiDTO thuChiDTO3) {
        sqLiteDatabase.delete(ThuChi.DB_TABLETHUCHI3, ThuChi.TABLE_ID3 + "=" + thuChiDTO3.getId(), null);

    }

}
