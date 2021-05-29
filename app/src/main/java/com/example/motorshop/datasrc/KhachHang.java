package com.example.motorshop.datasrc;

public class KhachHang {

    private String cmnd;
    private String hoTen;
    private String diaChi;
    private String sdt;
    private String passKH;

    public KhachHang() { }

    public String getPassKH() {
        return passKH;
    }

    public void setPassKH(String passKH) {
        this.passKH = passKH;
    }

    public KhachHang(String cmnd, String hoTen, String diaChi, String sdt, String passKH) {
        this.cmnd = cmnd;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.passKH = passKH;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
