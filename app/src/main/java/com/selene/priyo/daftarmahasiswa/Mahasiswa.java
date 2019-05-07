package com.selene.priyo.daftarmahasiswa;


public class Mahasiswa {

    private String nim;
    private String nama;
    private String alamat;
    private Boolean jns_kel;

    //konstruktor
    Mahasiswa() {

    }

    Mahasiswa(String Nim, String Nama, String Alamat, Boolean JnsKel) {
        this.nim = Nim;
        this.nama = Nama;
        this.alamat = Alamat;
        this.jns_kel = JnsKel;
    }

    void setNim(String Nim) {
        this.nim = Nim;
    }

    void setNama(String Nama) {
        this.nama = Nama;
    }

    void setAlamat(String Alamat){
        this.alamat = Alamat;
    }

    void setJns_kel(Boolean JnsKel){
        this.jns_kel = JnsKel;
    }

    String getNim() {
        return this.nim;
    }

    String getNama() {
        return this.nama;
    }

    String getAlamat() {
        return this.alamat;
    }

    Boolean getJns_kel() {
        return this.jns_kel;
    }

}
