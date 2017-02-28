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
        contentValues.put(ThuChi.TABLE_STK, bankView.getBankSotk().toString());
        contentValues.put(ThuChi.TABLE_TIEN,bankView.getBankTien().toString());
        contentValues.put(ThuChi.TABLE_TIENBIENTHIEN, bankView.getBankTienbt());
        contentValues.put(ThuChi.TABLE_ID_MESSGER, bankView.getIdsms().toString());
        contentValues.put(ThuChi.TABLE_NGAY4, bankView.getBankDate().toString());
        contentValues.put(ThuChi.TABLE_LYDO, bankView.getBankLydo().toString());
        contentValues.put(ThuChi.TABLE_TYPE, bankView.getType().toString());
        contentValues.put(ThuChi.TABLE_TYPE_BANK,"VIETIN");
        sqLiteDatabase.insert(ThuChi.DB_BANK,null,contentValues);

    }
    public  void CapnhatkVietcom(BankView bankView){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThuChi.TABLE_STK, bankView.getBankSotk().toString());
        contentValues.put(ThuChi.TABLE_TIEN,bankView.getBankTien().toString());
        contentValues.put(ThuChi.TABLE_TIENBIENTHIEN, bankView.getBankTienbt());
        contentValues.put(ThuChi.TABLE_ID_MESSGER, bankView.getIdsms().toString());
        contentValues.put(ThuChi.TABLE_NGAY4, bankView.getBankDate().toString());
        contentValues.put(ThuChi.TABLE_LYDO, bankView.getBankLydo().toString());
        contentValues.put(ThuChi.TABLE_TYPE, bankView.getType().toString());
        contentValues.put(ThuChi.TABLE_TYPE_BANK,"VIETCOM");
        sqLiteDatabase.insert(ThuChi.DB_BANK,null,contentValues);

    }
    public List<BankView> laydsbank(String stk){
        List<BankView> dsnv = new ArrayList<BankView>();
        String b = "select * from "+ ThuChi.DB_BANK + " WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN' ) AND ("+ ThuChi.TABLE_STK +" = '"+ stk+"');";
        Cursor cusor = sqLiteDatabase.rawQuery(b,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            String tien = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TIEN));
            String lydo = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_LYDO));
            String tienbienthien = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TIENBIENTHIEN));
            String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY4));

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
        String b = "select * from "+ ThuChi.DB_BANK + " WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETCOM' ) AND ("+ ThuChi.TABLE_STK +" = '"+ stk+"');";
        Cursor cusor = sqLiteDatabase.rawQuery(b,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            String tien = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TIEN));
            String lydo = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_LYDO));
            String tienbienthien = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_TIENBIENTHIEN));
            String ngaythang = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_NGAY4));

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
        String a = "select "+ ThuChi.TABLE_STK +" from "+ ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN';";
        Cursor cusor = sqLiteDatabase.rawQuery(a,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            String sotaikhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_STK));
            dsnv.add(sotaikhoan);
            cusor.moveToNext();
        }
        return removeDuplicate(dsnv);
    }

    public ArrayList<String> laystk_vietcom(){
        ArrayList<String> dsnv = new ArrayList<String>();
        String a = "select "+ ThuChi.TABLE_STK +" from "+ ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE_BANK +" = 'VIETCOM';";
        Cursor cusor = sqLiteDatabase.rawQuery(a,null);
        cusor.moveToFirst();
        while(!cusor.isAfterLast()){
            String sotaikhoan = cusor.getString(cusor.getColumnIndex(ThuChi.TABLE_STK));
            dsnv.add(sotaikhoan);
            cusor.moveToNext();
        }
        return removeDuplicate(dsnv);
    }



    public int check_id(String id) {
        String a = "SELECT * FROM " + ThuChi.DB_BANK + " WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN' ) AND ("+ ThuChi.TABLE_ID_MESSGER +" = '"+ id +"');";

        Cursor cusor = sqLiteDatabase.rawQuery(a, null);
        int count = cusor.getCount();
        return count;
    }

    public int check_id_vietcom(String id) {
        String a = "SELECT * FROM " + ThuChi.DB_BANK + " WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETCOM' ) AND ("+ ThuChi.TABLE_ID_MESSGER +" = '"+ id +"');";
        Cursor cusor = sqLiteDatabase.rawQuery(a, null);
        int count = cusor.getCount();
        return count;
    }

    public String gettien() {
        String a = "SELECT "+ ThuChi.TABLE_TIEN +" FROM " + ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN';";
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
        String a = "SELECT "+ ThuChi.TABLE_TIEN +" FROM " + ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE_BANK +" = 'VIETCOM';";
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
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + " WHERE ("+ ThuChi.TABLE_TYPE +" = 'vao' ) AND ("+ ThuChi.TABLE_STK +" = '"+ stk+"') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00" ;
            String da2 = year + "-" + month + "-32" ;
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK  + " WHERE (" + ThuChi.TABLE_TYPE +" = 'vao') AND ("+ ThuChi.TABLE_NGAY4 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_STK+" = '"+stk+"') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
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
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + " WHERE ("+ ThuChi.TABLE_TYPE +" = 'ra' ) AND ("+ ThuChi.TABLE_STK +" = '"+ stk +"') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";

        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00" ;
            String da2 = year + "-" + month + "-32" ;
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK  + " WHERE (" + ThuChi.TABLE_TYPE +" = 'ra') AND ("+ ThuChi.TABLE_NGAY4 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_STK+" = '"+stk+"') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
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
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK+ " WHERE ("+ ThuChi.TABLE_TYPE +" = 'vao' ) AND ("+ ThuChi.TABLE_STK +" = '"+ stk+"') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK  + " WHERE (" + ThuChi.TABLE_TYPE +" = 'vao') AND ("+ ThuChi.TABLE_NGAY4 +" BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_STK+" = '"+stk+"') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
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
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + " WHERE ("+ ThuChi.TABLE_TYPE +" = 'ra' ) AND ("+ ThuChi.TABLE_STK +" = '"+ stk +"') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";

        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_thu = "SELECT SUM(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK  + " WHERE (" + ThuChi.TABLE_TYPE +" = 'ra') AND ("+ ThuChi.TABLE_NGAY4 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_STK+" = '"+stk+"') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
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
            Tong_chi = "SELECT " + ThuChi.TABLE_LYDO + " FROM " + ThuChi.DB_BANK + "  WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN' ) AND ("+ ThuChi.TABLE_TIENBIENTHIEN + " =( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE+" = 'vao'));";
            Tong_chi0 = "SELECT " + ThuChi.TABLE_NGAY4 + " FROM " + ThuChi.DB_BANK + " WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN' ) AND ("+ ThuChi.TABLE_TIENBIENTHIEN + " =( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE+" = 'vao'));";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = "00-"+ month + "-" +year+ " 00:00" ;
            String da2 = "32-"+ month + "-" +year + " 00:00" ;
            Tong_chi = "SELECT " + ThuChi.TABLE_LYDO + " FROM " + ThuChi.DB_BANK + " WHERE " + ThuChi.TABLE_TIENBIENTHIEN + "=( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + ") AND (" + ThuChi.TABLE_NGAY4 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_TYPE+" = 'vao') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
            Tong_chi0 = "SELECT " + ThuChi.TABLE_NGAY4 + " FROM " + ThuChi.DB_BANK + " WHERE " + ThuChi.TABLE_TIENBIENTHIEN + "=( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + ") AND (" + ThuChi.TABLE_NGAY4 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_TYPE+" = 'vao') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
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
            Tong_chi = "SELECT " + ThuChi.TABLE_LYDO + " FROM " + ThuChi.DB_BANK + "  WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETCOM' ) AND ("+ ThuChi.TABLE_TIENBIENTHIEN + " =( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE+" = 'ra'));";
            Tong_chi0 = "SELECT " + ThuChi.TABLE_NGAY4 + " FROM " + ThuChi.DB_BANK + "  WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETCOM' ) AND ("+ ThuChi.TABLE_TIENBIENTHIEN + " =( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE+" = 'ra'));";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = "00-"+ month + "-" +year+ " 00:00" ;
            String da2 = "32-"+ month + "-" +year + " 00:00" ;
            Tong_chi = "SELECT " + ThuChi.TABLE_LYDO + " FROM " + ThuChi.DB_BANK + " WHERE " + ThuChi.TABLE_TIENBIENTHIEN + "=( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + ") AND (" + ThuChi.TABLE_NGAY4 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_TYPE+" = 'ra') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
            Tong_chi0 = "SELECT " + ThuChi.TABLE_NGAY4 + " FROM " + ThuChi.DB_BANK + " WHERE " + ThuChi.TABLE_TIENBIENTHIEN + "=( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + ") AND (" + ThuChi.TABLE_NGAY4 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_TYPE+" = 'ra') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";

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
            Tong_chi = "SELECT " + ThuChi.TABLE_TIENBIENTHIEN + " FROM " + ThuChi.DB_BANK +" WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN' ) AND ("+ ThuChi.TABLE_TIENBIENTHIEN + " =( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE+" = 'ra'));";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_chi = "SELECT " + ThuChi.TABLE_TIENBIENTHIEN + " FROM " + ThuChi.DB_BANK + " WHERE " + ThuChi.TABLE_TIENBIENTHIEN + "=( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + ") AND (" + ThuChi.TABLE_NGAY4 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_TYPE +" = 'ra') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN');";
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
            Tong_chi = "SELECT " + ThuChi.TABLE_TIENBIENTHIEN + " FROM " + ThuChi.DB_BANK +" WHERE ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETIN' ) AND ("+ ThuChi.TABLE_TIENBIENTHIEN + " =( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + " WHERE "+ ThuChi.TABLE_TYPE+" = 'vao'));";
        } else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String da1 = year + "-" + month + "-00";
            String da2 = year + "-" + month + "-32";
            Tong_chi = "SELECT " + ThuChi.TABLE_TIENBIENTHIEN + " FROM " + ThuChi.DB_BANK + " WHERE " + ThuChi.TABLE_TIENBIENTHIEN + "=( SELECT max(" + ThuChi.TABLE_TIENBIENTHIEN + ") FROM " + ThuChi.DB_BANK + ") AND (" + ThuChi.TABLE_NGAY4 + " BETWEEN '" + da1 + "' AND '" + da2 + "') AND ("+ ThuChi.TABLE_TYPE +" = 'vao') AND ("+ ThuChi.TABLE_TYPE_BANK +" = 'VIETCOM');";
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
