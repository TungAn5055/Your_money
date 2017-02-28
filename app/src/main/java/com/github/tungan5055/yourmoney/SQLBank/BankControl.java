package com.github.tungan5055.yourmoney.SQLBank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import com.github.tungan5055.yourmoney.SQLLite.ThuChi;

/**
 * Created by TungAn on 5/24/2016.
 */
public class BankControl {
    SQLiteDatabase sqLiteDatabase;
    ThuChi thuChi;

    public  BankControl(Context context){thuChi =  new ThuChi(context);}
    public void open(){sqLiteDatabase = thuChi.getWritableDatabase();}
    public void close(){sqLiteDatabase.close();}

    public  void CapnhatkVietin(BankView bankView){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuChi.table_STK, bankView.getBankSotk().toString());
        contentValues.put(ThuChi.table_tien,bankView.getBankTien().toString());
        contentValues.put(ThuChi.table_tienbienthien, bankView.getBankTienbt());
        contentValues.put(ThuChi.table_id_messger, bankView.getIdsms().toString());
        contentValues.put(ThuChi.table_ngay, bankView.getBankDate().toString());
        contentValues.put(ThuChi.table_lydo, bankView.getBankLydo().toString());
        contentValues.put(ThuChi.table_type, bankView.getType().toString());
        sqLiteDatabase.insert(ThuChi.TABLE_Vietinbank,null,contentValues);

    }
    public  void CapnhatkViecom(BankView bankView){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuChi.table_STK0, bankView.getBankSotk().toString());
        contentValues.put(ThuChi.table_tien0,bankView.getBankTien().toString());
        contentValues.put(ThuChi.table_tienbienthien0, bankView.getBankTienbt());
        contentValues.put(ThuChi.table_id_messger0, bankView.getIdsms().toString());
        contentValues.put(ThuChi.table_ngay0, bankView.getBankDate().toString());
        contentValues.put(ThuChi.table_lydo0, bankView.getBankLydo().toString());
        contentValues.put(ThuChi.table_type0, bankView.getType().toString());
        sqLiteDatabase.insert(ThuChi.TABLE_Viecombank,null,contentValues);

    }
    public List<BankView> laydsbank(String stk){
        List<BankView> dsnv = new ArrayList<BankView>();
        String b = "select * from "+ ThuChi.TABLE_Vietinbank +" where "+ ThuChi.table_STK +" = '"+ stk +"';";
        Cursor cusor = sqLiteDatabase.rawQuery(b,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            String tien = cusor.getString(cusor.getColumnIndex(ThuChi.table_tien));
            String lydo = cusor.getString(cusor.getColumnIndex(ThuChi.table_lydo));
            String tienbienthien = cusor.getString(cusor.getColumnIndex(ThuChi.table_tienbienthien));
            String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.table_ngay));

            BankView bankView = new BankView();
            bankView.setBankDate(ngaythang);
            bankView.setBankTien(tien);
            bankView.setBankLydo(lydo);
            bankView.setBankTienbt(Integer.valueOf(tienbienthien));

            dsnv.add(bankView);
            cusor.moveToNext();
        }
        return dsnv;
    }

    public List<BankView> laydsbank_vietcom(String stk){
        List<BankView> dsnv = new ArrayList<BankView>();
        String b = "select * from "+ ThuChi.TABLE_Viecombank +" where "+ ThuChi.table_STK0 +" = '"+ stk +"';";
        Cursor cusor = sqLiteDatabase.rawQuery(b,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            String tien = cusor.getString(cusor.getColumnIndex(ThuChi.table_tien0));
            String lydo = cusor.getString(cusor.getColumnIndex(ThuChi.table_lydo0));
            String tienbienthien = cusor.getString(cusor.getColumnIndex(ThuChi.table_tienbienthien0));
            String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.table_ngay0));

            BankView bankView = new BankView();
            bankView.setBankDate(ngaythang);
            bankView.setBankTien(tien);
            bankView.setBankLydo(lydo);
            bankView.setBankTienbt(Integer.valueOf(tienbienthien));

            dsnv.add(bankView);
            cusor.moveToNext();
        }
        return dsnv;
    }

    public ArrayList<String> laystk_vietin(){
        ArrayList<String> dsnv = new ArrayList<String>();
        String a = "select "+ ThuChi.table_STK +" from "+ ThuChi.TABLE_Vietinbank;
        Cursor cusor = sqLiteDatabase.rawQuery(a,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            String sotaikhoan = cusor.getString(cusor.getColumnIndex(ThuChi.table_STK));
            dsnv.add(sotaikhoan);
            cusor.moveToNext();
        }
        return removeDuplicate(dsnv);
    }

    public ArrayList<String> laystk_vietcom(){
        ArrayList<String> dsnv = new ArrayList<String>();
        String a = "select "+ ThuChi.table_STK0 +" from "+ ThuChi.TABLE_Viecombank;
        Cursor cusor = sqLiteDatabase.rawQuery(a,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            String sotaikhoan = cusor.getString(cusor.getColumnIndex(ThuChi.table_STK0));
            dsnv.add(sotaikhoan);
            cusor.moveToNext();
        }
        return removeDuplicate(dsnv);
    }



    public int check_id(String id) {
        String a = "SELECT * FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_id_messger + " = '" + id + "' ;";
        Cursor cusor = sqLiteDatabase.rawQuery(a, null);
        int count = cusor.getCount();
        return count;
    }

    public int check_id_vietcom(String id) {
        String a = "SELECT * FROM " + ThuChi.TABLE_Viecombank + " WHERE " + ThuChi.table_id_messger0 + " = '" + id + "' ;";
        Cursor cusor = sqLiteDatabase.rawQuery(a, null);
        int count = cusor.getCount();
        return count;
    }

    public String gettien() {
        String a = "SELECT "+ ThuChi.table_tien +" FROM " + ThuChi.TABLE_Vietinbank + " ;";
        Cursor c = sqLiteDatabase.rawQuery(a, null);
        String count;
        if (c.moveToLast()) {
            count =c.getString(0);
        } else {
            count = "0";
        }
        c.close();
        return count;
    }
    public String gettien_vietcom() {
        String a = "SELECT "+ ThuChi.table_tien0 +" FROM " + ThuChi.TABLE_Viecombank + " ;";
        Cursor c = sqLiteDatabase.rawQuery(a, null);
        String count;
        if (c.moveToLast()) {
            count =c.getString(0);
        } else {
            count = "0";
        }
        c.close();
        return count;
    }
    public int gettien_thu(String month,  String stk) {
        int b = 0;
        String Tong_thu;
        if (month.equals("All")) {
            Tong_thu = "SELECT SUM(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + " WHERE ("+ ThuChi.table_type +" = 'vao' ) AND ("+ ThuChi.table_STK +" = '"+ stk+"');";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00" ;
            String da2 = year + "-" + month + "-32" ;
            Tong_thu = "SELECT SUM(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank  + " WHERE (" + ThuChi.table_type +" = 'vao') AND ("+ ThuChi.table_ngay + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_STK+" = '"+stk+"');";
        }

        Cursor c = sqLiteDatabase.rawQuery(Tong_thu, null);
        if (c.moveToFirst()) {
            b = c.getInt(0);
        } else {
            b = 0;
        }
        c.close();
        return b;
    }

    public int gettien_chi(String month, String stk) {
        int b = 0;
        String Tong_thu;
        if (month.equals("All")) {
            Tong_thu = "SELECT SUM(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + " WHERE ("+ ThuChi.table_type +" = 'ra' ) AND ("+ ThuChi.table_STK +" = '"+ stk +"');";

        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00" ;
            String da2 = year + "-" + month + "-32" ;
            Tong_thu = "SELECT SUM(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank  + " WHERE (" + ThuChi.table_type +" = 'ra') AND ("+ ThuChi.table_ngay + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_STK+" = '"+stk+"');";
        }

       Cursor c = sqLiteDatabase.rawQuery(Tong_thu, null);

        if (c.moveToFirst()) {
            b = c.getInt(0);
        } else {
            b = 0;
        }
        c.close();
        return b;
    }
    public int gettien_thu_vietcom(String month,  String stk) {
        int b = 0;
        String Tong_thu;
        if (month.equals("All")) {
            Tong_thu = "SELECT SUM(" + ThuChi.table_tienbienthien0 + ") FROM " + ThuChi.TABLE_Viecombank+ " WHERE ("+ ThuChi.table_type0 +" = 'vao' ) AND ("+ ThuChi.table_STK0 +" = '"+ stk+"');";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_thu = "SELECT SUM(" + ThuChi.table_tienbienthien0 + ") FROM " + ThuChi.TABLE_Viecombank  + " WHERE (" + ThuChi.table_type0 +" = 'vao') AND ("+ ThuChi.table_ngay0 +" BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_STK0+" = '"+stk+"');";
        }

        Cursor c = sqLiteDatabase.rawQuery(Tong_thu, null);
        if (c.moveToFirst()) {
            b = c.getInt(0);
        } else {
            b = 0;
        }
        c.close();
        return b;
    }

    public int gettien_chi_vietcom(String month, String stk) {
        int b = 0;
        String Tong_thu;
        if (month.equals("All")) {
            Tong_thu = "SELECT SUM(" + ThuChi.table_tienbienthien0 + ") FROM " + ThuChi.TABLE_Viecombank + " WHERE ("+ ThuChi.table_type0 +" = 'ra' ) AND ("+ ThuChi.table_STK0 +" = '"+ stk +"');";

        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_thu = "SELECT SUM(" + ThuChi.table_tienbienthien0 + ") FROM " + ThuChi.TABLE_Viecombank  + " WHERE (" + ThuChi.table_type0 +" = 'ra') AND ("+ ThuChi.table_ngay0 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_STK0+" = '"+stk+"');";
        }

        Cursor c = sqLiteDatabase.rawQuery(Tong_thu, null);

        if (c.moveToFirst()) {
            b = c.getInt(0);
        } else {
            b = 0;
        }
        c.close();
        return b;
    }
    public String getmaxtongthu(String month) {
        String cd;
        String Tong_chi, Tong_chi0;
        if (month.equals("All")) {
            Tong_chi = "SELECT " + ThuChi.table_lydo + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + " =( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + " WHERE "+ ThuChi.table_type+" = 'vao');";
            Tong_chi0 = "SELECT " + ThuChi.table_ngay + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + " =( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + " WHERE "+ ThuChi.table_type+" = 'vao');";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = "00-"+ month + "-" +year+ " 00:00" ;
            String da2 = "32-"+ month + "-" +year + " 00:00" ;
            Tong_chi = "SELECT " + ThuChi.table_lydo + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + "=( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + ") AND (" + ThuChi.table_ngay + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_type+" = 'vao');";
            Tong_chi0 = "SELECT " + ThuChi.table_ngay + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + "=( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + ") AND (" + ThuChi.table_ngay + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_type+" = 'vao');";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_chi, null);
        Cursor c0 = sqLiteDatabase.rawQuery(Tong_chi0, null);
        if (c.moveToFirst() && c0.moveToFirst()) {
            cd = c.getString(0) + "--" + c0.getString(0);
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
            Tong_chi = "SELECT " + ThuChi.table_lydo + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + " =( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + " WHERE "+ ThuChi.table_type+" = 'ra');";
            Tong_chi0 = "SELECT " + ThuChi.table_ngay + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + " =( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + " WHERE "+ ThuChi.table_type+" = 'ra');";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = "00-"+ month + "-" +year+ " 00:00" ;
            String da2 = "32-"+ month + "-" +year + " 00:00" ;
            Tong_chi = "SELECT " + ThuChi.table_lydo + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + "=( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + ") AND (" + ThuChi.table_ngay + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_type+" = 'ra');";
            Tong_chi0 = "SELECT " + ThuChi.table_ngay + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + "=( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + ") AND (" + ThuChi.table_ngay + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_type+" = 'ra');";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_chi, null);
        Cursor c0 = sqLiteDatabase.rawQuery(Tong_chi0, null);
        if (c.moveToFirst() && c0.moveToFirst()) {
            cd = c.getString(0) + "--" + c0.getString(0);
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
            Tong_chi = "SELECT " + ThuChi.table_tienbienthien + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + "=( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + " WHERE "+ ThuChi.table_type +" = 'ra');";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_chi = "SELECT " + ThuChi.table_tienbienthien + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + "=( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + ") AND (" + ThuChi.table_ngay + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_type +" = 'ra');";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_chi, null);
        if (c.moveToFirst()) {
            cd = Integer.valueOf(c.getString(0) );
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
            Tong_chi = "SELECT " + ThuChi.table_tienbienthien + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + "=( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + " WHERE "+ ThuChi.table_type +" = 'vao');";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_chi = "SELECT " + ThuChi.table_tienbienthien + " FROM " + ThuChi.TABLE_Vietinbank + " WHERE " + ThuChi.table_tienbienthien + "=( SELECT max(" + ThuChi.table_tienbienthien + ") FROM " + ThuChi.TABLE_Vietinbank + ") AND (" + ThuChi.table_ngay + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.table_type +" = 'vao');";
        }
        Cursor c = sqLiteDatabase.rawQuery(Tong_chi, null);
        if (c.moveToFirst()) {
            cd = Integer.valueOf(c.getString(0));
        } else {
            cd = 0;
        }
        c.close();
        return cd;
    }

    public static ArrayList removeDuplicate(ArrayList arrList)
    {
        HashSet h = new HashSet(arrList);
        arrList.clear();
        arrList.addAll(h);

        return arrList;

    }
}
