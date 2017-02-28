package com.github.tungan5055.yourmoney.SQLBank;

/**
 * Created by TungAn on 5/24/2016.
 */
public class BankView {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    int bankTienbt;
    String bankNametk;
    String bankTien;
    String bankDate;
    String bankLydo;
    String bankSotk;
    String idsms;
    String type;


    public String getBankTien() {
        return bankTien;
    }

    public void setBankTien(String bankTien) {
        this.bankTien = bankTien;
    }

    public String getBankDate() {
        return bankDate;
    }

    public void setBankDate(String bankDate) {
        this.bankDate = bankDate;
    }

    public int getBankTienbt() {
        return bankTienbt;
    }

    public void setBankTienbt(int bankTienbt) {
        this.bankTienbt = bankTienbt;
    }

    public String getBankLydo() {
        return bankLydo;
    }

    public void setBankLydo(String bankLydo) {
        this.bankLydo = bankLydo;
    }

    public String getBankSotk() {
        return bankSotk;
    }

    public void setBankSotk(String bankSotk) {
        this.bankSotk = bankSotk;
    }

    public String getIdsms(){return idsms;}

    public void setIdsms(String id){ this.idsms = id;}

    public String getType(){return type;}

    public void setType(String type){ this.type = type;}


}
