package com.gisela.pointofsales.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Barang implements Parcelable {

    private String idBarang;
    private int hargaJual;
    private int hargaBeli;
    private String namaBarang;
    private int stock;

    public Barang() {
    }

    protected Barang(Parcel in) {
        idBarang = in.readString();
        hargaJual = in.readInt();
        hargaBeli = in.readInt();
        namaBarang = in.readString();
        stock = in.readInt();
    }

    public static final Creator<Barang> CREATOR = new Creator<Barang>() {
        @Override
        public Barang createFromParcel(Parcel in) {
            return new Barang(in);
        }

        @Override
        public Barang[] newArray(int size) {
            return new Barang[size];
        }
    };

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public int getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(int hargaJual) {
        this.hargaJual = hargaJual;
    }

    public int getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(int hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBarang);
        dest.writeInt(hargaJual);
        dest.writeInt(hargaBeli);
        dest.writeString(namaBarang);
        dest.writeInt(stock);
    }
}
