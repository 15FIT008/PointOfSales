package com.gisela.pointofsales.entity;

public class Transaksi {

    private User user;
    private String idTransaksi;
    private String nominalTransaksi;
    private String tanggalTransaksi;

    public Transaksi() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNominalTransaksi() {
        return nominalTransaksi;
    }

    public void setNominalTransaksi(String nominalTransaksi) {
        this.nominalTransaksi = nominalTransaksi;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
}
