package com.github.tungan5055.yourmoney.SQLLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by an on 4/16/2016.
 */
public class ThuChi extends SQLiteOpenHelper {

    public static String DB_Thuchi = "Quanlythuchi";
    public static int DB_VERSION = 1;

    public static String DB_TABLETHUCHI = "tblthu";
    public static String TABLE_ID = "_id";
    public static String TABLE_SOTIEN = "sotien";
    public static String TABLE_TENKHOAN = "tenkhoan";
    public static String TABLE_NGAY = "ngaythang";
    public static String TABLE_GHICHU = "ghichu";
    public static String IMAGE_SOURCE1 = "image_source1";

    public static String DB_TABLETHUCHI2 = "tblchi";
    public static String TABLE_ID2 = "_id2";
    public static String TABLE_SOTIEN2 = "sotien1";
    public static String TABLE_TENKHOAN2 = "tenkhoan1";
    public static String TABLE_NGAY2 = "ngaythang1";
    public static String TABLE_GHICHU2 = "ghichu1";
    public static String IMAGE_SOURCE2 = "image_source2";

    public static String DB_TABLETHUCHI3 = "tblvay";
    public static String TABLE_ID3 = "_id3";
    public static String TABLE_SOTIEN3 = "sotien2";
    public static String TABLE_TENKHOAN3 = "nguoivay";
    public static String TABLE_NGAY3 = "ngaytra";
    public static String TABLE_GHICHU3 = "ghichu2";


    public static String TABLE_Vietinbank = "Vietinbank";
    public static String TABLE_ID4 = "_id";
    public static String table_STK = "SoTaiKhoan";
    public static String table_tien = "Tien";
    public static String table_id_messger = "idmess";
    public static String table_ngay = "ngay";
    public static String table_tienbienthien = "tienbienthien";
    public static String table_lydo = "lydo";
    public static String table_type = "type";

    public static String TABLE_Viecombank = "Viecombank";
    public static String TABLE_ID5 = "_id";
    public static String table_STK0 = "SoTaiKhoan0";
    public static String table_tien0 = "Tien0";
    public static String table_id_messger0 = "idmess0";
    public static String table_ngay0 = "ngay0";
    public static String table_tienbienthien0 = "tienbienthien0";
    public static String table_lydo0 = "lydo0";
    public static String table_type0 = "type0";

    public static String DB_TABLE_IMAGE = "tblimage";
    public static String IMAGE_ID = "_id4";
    public static String IMAGE_NAME = "image_name";
    public static String IMAGE_SOURCE = "image_source";



    SQLiteDatabase db;
    String[] name = {
            "Lương",
            "Ăn uống",
            "Đổ xăng",
            "Cưới hỏi",
            "Bán đồ",
            "Mua sắm",
            "Di chuyển",
            "Tiền điện",
            "Tiền điện thoại",
            "Đi nhậu",
            "Du lịch",
            "Khám bệnh",
            "Tiền lãi",
            "Nội trợ",
            "Sinh nhật",
            "Taxi",
            "Từ thiện",
            "Học",
            "khoản khác"
    };

    String[] imageId = {
            "@drawable/ic_luong",
            "@drawable/ic_anuong",
            "@drawable/ic_doxang",
            "@drawable/ic_cuoihoi",
            "@drawable/ic_bando",
            "@drawable/ic_muasam",
            "@drawable/ic_dichuyen",
            "@drawable/ic_dien",
            "@drawable/ic_dienthoai",
            "@drawable/ic_dinhau",
            "@drawable/ic_dulich",
            "@drawable/ic_khambenh",
            "@drawable/ic_tienlai",
            "@drawable/ic_noitro",
            "@drawable/ic_sinhnhat",
            "@drawable/ic_taxi",
            "@drawable/ic_tuthien",
            "@drawable/ic_hoc",
            "@drawable/ic_khoankhac",
    };

    public ThuChi(Context context) {
        super(context, DB_Thuchi, null, DB_VERSION);
        context.deleteDatabase("Quanlythuchi0");
    }

    public void onCreate(SQLiteDatabase db) {
        String taoBang = "CREATE TABLE " + DB_TABLETHUCHI + "(" + TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_SOTIEN + " INTEGER," + TABLE_TENKHOAN + " TEXT," + TABLE_NGAY + " DATE," + TABLE_GHICHU + " TEXT," + IMAGE_SOURCE1 + " TEXT);";
        String taoBang2 = "CREATE TABLE " + DB_TABLETHUCHI2 + "(" + TABLE_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_SOTIEN2 + " INTEGER," + TABLE_TENKHOAN2 + " TEXT," + TABLE_NGAY2 + " DATE," + TABLE_GHICHU2 + " TEXT," + IMAGE_SOURCE2 + " TEXT);";
        String taoBang3 = "CREATE TABLE " + DB_TABLETHUCHI3 + "(" + TABLE_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT," + TABLE_SOTIEN3 + " INTEGER," + TABLE_TENKHOAN3 + " TEXT," + TABLE_NGAY3 + " DATE," + TABLE_GHICHU3 + " TEXT);";
        String taoBang4 = "CREATE TABLE " + TABLE_Vietinbank + "(" + TABLE_ID4 + " INTEGER PRIMARY KEY AUTOINCREMENT," + table_STK + " TEXT," + table_id_messger + " TEXT," + table_tien + " INTEGER," + table_ngay + " DATE," + table_tienbienthien + " INTEGER," + table_lydo + " TEXT," + table_type + " TEXT);";
        String taoBang5 = "CREATE TABLE " + TABLE_Viecombank + "(" + TABLE_ID5 + " INTEGER PRIMARY KEY AUTOINCREMENT," + table_STK0 + " TEXT," + table_id_messger0 + " TEXT," + table_tien0 + " INTEGER," + table_ngay0 + " DATE," + table_tienbienthien0 + " INTEGER," + table_lydo0 + " TEXT," + table_type0 + " TEXT);";
        String taoBang6 = "CREATE TABLE " + DB_TABLE_IMAGE + "(" + IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + IMAGE_NAME + " INTEGER," + IMAGE_SOURCE + " TEXT);";

        db.execSQL(taoBang);
        db.execSQL(taoBang2);
        db.execSQL(taoBang3);
        db.execSQL(taoBang4);
        db.execSQL(taoBang5);
        db.execSQL(taoBang6);

        for (int i = 0; i <= imageId.length - 1; i++) {
            String Insert_Data = "INSERT INTO  " + DB_TABLE_IMAGE + " (_id4, image_name, image_source) VALUES(" + i + ",'" + name[i] + "','" + imageId[i] + "')";
            db.execSQL(Insert_Data);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + TABLE_ID);
        db.execSQL("DROP TABLE IF EXITS " + TABLE_ID2);
        db.execSQL("DROP TABLE IF EXITS " + TABLE_ID3);
        db.execSQL("DROP TABLE IF EXITS " + TABLE_ID4);
        db.execSQL("DROP TABLE IF EXITS " + TABLE_ID5);
        db.execSQL("DROP TABLE IF EXITS " + IMAGE_ID);
        onCreate(db);
    }
}
