package com.gisela.pointofsales.entity;


import java.util.List;

public class DetailTransaksi {
    private Transaksi transaksi;
    private List<Barang> barang;

    public DetailTransaksi() {
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<Barang> getBarang() {
        return barang;
    }

    public void setBarang(List<Barang> barang) {
        this.barang = barang;
    }
}
