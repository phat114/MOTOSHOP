package com.example.motorshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.datasrc.KhachHang;
import com.example.motorshop.datasrc.NhaCungCap;
import com.example.motorshop.datasrc.NhanVien;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final String TAG = "DBManager";

    public DBManager(Context context) {
        super(context, "dbMOTORSTORE.db", null, 2);
        Log.d(TAG,"Create DB: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ArrayList<String> createTables = new ArrayList<>();
        createTables.add("CREATE TABLE IF NOT EXISTS BOPHAN (MABP text PRIMARY KEY, TENBP text not null)");
        createTables.add("CREATE TABLE IF NOT EXISTS NHANVIEN(MANV text PRIMARY KEY, HOTEN text not null, SDT text not null, MABP text not null, CONSTRAINT FK_NHANVIEN_BOPHAN FOREIGN KEY (MABP) REFERENCES BOPHAN(MABP))");
        createTables.add("CREATE TABLE IF NOT EXISTS KHACHHANG(CMND text PRIMARY KEY, HOTENKH text not null,DIACHIKH text not null,SDTKH text null, PASSKH text not null)");
        //createTables.add("CREATE TABLE IF NOT EXISTS KHACHHANG (CMND text PRIMARY KEY, HOTENKH text not null, DIACHIKH text not null, SDTKH text null)");
        createTables.add("CREATE TABLE IF NOT EXISTS NHACUNGCAP (MANCC text PRIMARY KEY, TENNCC text not null, DIACHI text not null, SDT text not null, EMAIL text null, LOGO int not null)");
        createTables.add("CREATE TABLE IF NOT EXISTS XE (MAXE text PRIMARY KEY, TENXE text not null, SOLUONG int not null, DONGIA int not null, HANBAOHANH int not null, HINHANH int not null, MANCC text not null, CONSTRAINT FK_XE_NHACUNGCAP FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC))");
        createTables.add("CREATE TABLE IF NOT EXISTS PHUTUNG (MAPT text PRIMARY KEY, TENPT text not null, SOLUONG int not null, DONGIA int not null, HANBAOHANH int not null, HINHANH int not null, MANCC text not null, CONSTRAINT FK_PHUTUNG_NHACUNGCAP FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC))");
        createTables.add("CREATE TABLE IF NOT EXISTS THONGSOXE (MATS int PRIMARY KEY, TENTS text not null)");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETTHONGSOXE (MAXE text not null, MATS int not null, NOIDUNGTS text not null, CONSTRAINT FK_CHITIETTHONGSOXE_XE FOREIGN KEY (MAXE) REFERENCES XE(MAXE), CONSTRAINT FK_CHITIETTHONGSOXE_THONGSOXE FOREIGN KEY (MATS) REFERENCES THONGSOXE(MATS), PRIMARY KEY (MAXE, MATS))");
        createTables.add("CREATE TABLE IF NOT EXISTS THONGSOPHUTUNG (MAPT text not null, MAXE text not null, DONGIA int not null, CONSTRAINT FK_THONGSOPHUTUNG_PHUTUNG FOREIGN KEY (MAPT) REFERENCES PHUTUNG(MAPT), CONSTRAINT FK_THONGSOPHUTUNG_XE FOREIGN KEY (MAXE) REFERENCES XE(MAXE), PRIMARY KEY (MAPT, MAXE))");
        createTables.add("CREATE TABLE IF NOT EXISTS DONDATHANG (MADH text PRIMARY KEY, NGAYDAT text not null, CMND text not null, MANV text not null, CONSTRAINT FK_DONDATHANG_KHACHHANG FOREIGN KEY (CMND) REFERENCES KHACHHANG(CMND), CONSTRAINT FK_DONDATHANG_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV))");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETDONDATXE (MADH text not null, MAXE text not null, SOLUONG int not null, DONGIABAN int not null, CONSTRAINT FK_CHITIETDONDATXE_DONDATHANG FOREIGN KEY (MADH) REFERENCES DONDATHANG(MADH), CONSTRAINT FK_CHITIETDONDATXE_XE FOREIGN KEY (MAXE) REFERENCES XE(MAXE), PRIMARY KEY (MADH, MAXE))");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETDONDATPHUTUNG (MADH text not null, MAPT text not null, SOLUONG int not null, DONGIABAN int not null, CONSTRAINT FK_CHITIETDONDATPHUTUNG_DONDATHANG FOREIGN KEY (MADH) REFERENCES DONDATHANG(MADH), CONSTRAINT FK_CHITIETDONDATPHUTUNG_PHUTUNG FOREIGN KEY (MAPT) REFERENCES PHUTUNG(MAPT), PRIMARY KEY (MADH, MAPT))");
        createTables.add("CREATE TABLE IF NOT EXISTS BAOHANH (MABH text PRIMARY KEY, MADH text not null, NGAYBH text not null, MANV text not null, CONSTRAINT FK_BAOHANH_DONDATHANG FOREIGN KEY (MADH) REFERENCES DONDATHANG(MADH), CONSTRAINT FK_BAOHANH_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV))");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETBAOHANHXE (MABH text not null, MAXE text not null, NOIDUNGBH text not null, PHIBH int null, CONSTRAINT FK_CHITIETBAOHANHXE_BAOHANH FOREIGN KEY (MABH) REFERENCES BAOHANH(MABH), CONSTRAINT FK_CHITIETBAOHANHXE_XE FOREIGN KEY (MAXE) REFERENCES XE(MAXE), PRIMARY KEY (MABH, MAXE, NOIDUNGBH))");
        createTables.add("CREATE TABLE IF NOT EXISTS CHITIETBAOHANHPHUTUNG (MABH text not null, MAPT text not null, NOIDUNGBH text not null, PHIBH int null, CONSTRAINT FK_CHITIETBAOHANHPHUTUNG_BAOHANH FOREIGN KEY (MABH) REFERENCES BAOHANH(MABH), CONSTRAINT FK_CHITIETBAOHANHPHUTUNG_PHUTUNG FOREIGN KEY (MAPT) REFERENCES PHUTUNG(MAPT), PRIMARY KEY (MABH, MAPT, NOIDUNGBH))");

        for(String str : createTables){
            db.execSQL(str);
            Log.d(TAG,"onCreate DB: " + str);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"onUpgrade DB: ");
    }


    //USED IN COMMONS
    public boolean ifIDExist(String IDColumnName, String tableName, String condition){
        boolean exist = false;
        String query = "select " +IDColumnName+ " from " +tableName+ " where " +IDColumnName+ " = " +condition;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String id_tmp_check = cursor.getString(0);                              //
            cursor.close();
            db.close();
            Log.d(TAG,"Check MaBP ifIDExist: " + IDColumnName + "=" + id_tmp_check);
            return true;
        }else{
            cursor.close();
            db.close();
            Log.d(TAG,"Check MaBP ifIDExist: not exist");
            return false;
        }
    }


    //BO PHAN (DEPARTMENT)
    public void insertDP(BoPhan department){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MABP", department.getMaBP());
        values.put("TENBP", department.getTenBP());
        db.insert("BoPhan", null, values);
        db.close();
        Log.d(TAG,"Insert DEPARTMENT: ");
    }

    public void updateDP(BoPhan department){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Update  BOPHAN  set ";
        query += " TENBP  = '" + department.getTenBP() + "' ";
        query += " WHERE MABP  = '" + department.getMaBP() + "'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Update DEPARTMENT: ");
    }

  /*  public void deleteDP(BoPhan department){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM BOPHAN WHERE MABP = '" + department.getMaBP() + "'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Delete DEPARTMENT: ");
    }*/

    public void deleteDP(String departmentID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM BOPHAN WHERE MABP = '" + departmentID + "'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Delete DEPARTMENT with departmentID: ");
    }

    public void loadDPList(ArrayList<BoPhan> departmentList){
        departmentList.clear();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from BOPHAN";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                BoPhan department = new BoPhan();
                department.setMaBP(cursor.getString(0));
                department.setTenBP(cursor.getString(1));
                departmentList.add(department);
            } while (cursor.moveToNext());
        }
        Log.d(TAG,"Load DEPARTMENT LIST: ");
    }

    public ArrayList<BoPhan> loadAllDPList() {
        ArrayList<BoPhan> data = new ArrayList<>();
//        data.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from BOPHAN";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                BoPhan boPhan = new BoPhan();
                boPhan.setMaBP(cursor.getString(0));
                boPhan.setTenBP(cursor.getString(1));
                data.add(boPhan);
            }while (cursor.moveToNext());
        }
        Log.d(TAG,"Load DP LIST: ");
        return data;
    }
    public void createDP(){

        insertDP(new BoPhan("BP02", "Sua chua"));
        insertDP(new BoPhan("BP01", "Bao hanh"));
        Log.d(TAG,"Insert Nhan Vien thanh cong");
    }

    //NHAN VIEN (STAFF)
    public void insertST(NhanVien nhanVien){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MANV",nhanVien.getMaNV());
        values.put("HOTEN",nhanVien.getHoTen());
        values.put("SDT",nhanVien.getSdt());
        values.put("MABP",nhanVien.getMaBP());
        db.insert("NhanVien",null,values);
        db.close();
        Log.d(TAG,"Insert Nhan Vien");
    }
    public void create(){
        insertST(new NhanVien("NV02", "Nguyen Thinh", "0123456789", "PT122"));
        insertST(new NhanVien("NV01", "Nguyen Thinh Phat", "0877269539", "PT123"));
        insertST(new NhanVien("NV03", "Le Diem Quynh ", "0877269539", "PT021"));
        Log.d(TAG,"Insert Nhan Vien thanh cong");
    }
    public void updateST(NhanVien nhanVien) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Update NHANVIEN set ";
        sql += "HOTEN'"+nhanVien.getHoTen()+"', ";
        sql += "SDT'"+nhanVien.getSdt()+"', ";
        sql += "TENBP'"+nhanVien.getMaBP()+"', ";
        sql += "WHERE MANV = '"+nhanVien.getMaNV()+"'";
        db.execSQL(sql);
    }
    public void deleteST(String maNV) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM NHANVIEN WHERE MANV='" + maNV+"'";
        db.execSQL(query);
        db.close();
        Log.d("data","Delete Nhan Vien");
    }
    public void loadSTList(ArrayList<NhanVien> data) {
        data.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from NHANVIEN";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(cursor.getString(0));
                nhanVien.setHoTen(cursor.getString(1));
                nhanVien.setSdt(cursor.getString(2));
                nhanVien.setMaBP(cursor.getString(3));
                data.add(nhanVien);
            }while (cursor.moveToNext());
        }
        Log.d(TAG,"Load STAFF LIST: ");
    }

    public ArrayList<NhanVien> loadAllSTList() {
        ArrayList<NhanVien> data = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from NHANVIEN";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(cursor.getString(0));
                nhanVien.setHoTen(cursor.getString(1));
                nhanVien.setSdt(cursor.getString(2));
                nhanVien.setMaBP(cursor.getString(3));
                data.add(nhanVien);
            }while (cursor.moveToNext());
        }
        Log.d(TAG,"Load STAFF LIST: ");
        return data;
    }

    //KHACH HANG (CUSTOMER)

    public void createCTM(){
//        insertST(new NhanVien("NV01", "Nguyen Thinh Phat", "0877269539", "PT123"));
        insertCTM(new KhachHang("1212121", "Nguyen Thinh", "0123456789", "01212","phatphuphang"));
        insertCTM(new KhachHang("1324234", "Nguyen Thinh Phat", "0123456789", "12131","alo123"));
        Log.d(TAG,"Insert Khach hang thanh cong");
    }
    public void insertCTM(KhachHang khachHang) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CMND",khachHang.getCmnd());
        values.put("HOTENKH",khachHang.getHoTen());
        values.put("DIACHIKH",khachHang.getDiaChi());
        values.put("SDTKH",khachHang.getSdt());
        values.put("PASSKH",khachHang.getPassKH());
        db.insert("KhachHang",null,values);
        db.close();
        Log.d(TAG,"Insert Khach Hang");
    }
    public void updateCTM(KhachHang khachHang) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Update KHACHHANG set ";
        sql += "HOTENKH"+khachHang.getHoTen()+"', ";
        sql += "DIACHIKH"+khachHang.getDiaChi()+"', ";
        sql += "SDTKH"+khachHang.getSdt()+"', ";
        sql += "PASSKH"+khachHang.getPassKH()+"', ";
        sql += "WHERE CMND = '"+khachHang.getCmnd()+"'";
        db.execSQL(sql);
    }

    public void deleteCTM(String cmnd) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM KHACHHANG WHERE CMND='" + cmnd+"'";
        db.execSQL(query);
        db.close();
        Log.d("data","Delete Khach Hang");
    }

    public ArrayList<KhachHang> loadAllCTMList() {
        ArrayList<KhachHang> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from KHACHHANG";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                KhachHang khachHang = new KhachHang();
                khachHang.setCmnd(cursor.getString(0));
                khachHang.setHoTen(cursor.getString(1));
                khachHang.setDiaChi(cursor.getString(2));
                khachHang.setSdt(cursor.getString(3));
                khachHang.setPassKH(cursor.getString(4));
                data.add(khachHang);
            }while (cursor.moveToNext());
        }
        Log.d(TAG,"Load CTM LIST: ");
        return data;
    }


    //NHA CUNG CAP (BRAND)
    public void insertBR(NhaCungCap brand){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MANCC", brand.getMaNCC());
        values.put("TENNCC", brand.getTenNCC());
        values.put("DIACHI", brand.getDiaChi());
        values.put("SDT", brand.getSdt());
        values.put("EMAIL", brand.getEmail());
        values.put("LOGO", brand.getLogo());
        db.insert("NHACUNGCAP", null, values);
        db.close();
        Log.d(TAG,"Insert BRAND: ");
    }

    public void updateBR(NhaCungCap brand){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Update  NHACUNGCAP  set ";
        query += " TENNCC  = '"+ brand.getTenNCC()+"', ";
        query += " DIACHI  = '"+ brand.getDiaChi()+"', ";
        query += " SDT  = '"+ brand.getSdt()+"', ";
        query += " EMAIL  = '"+ brand.getEmail()+"', ";
        query += " LOGO  = '"+ brand.getLogo()+"' ";
        query += " WHERE MANCC  = '"+ brand.getMaNCC()+"'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Update BRAND: ");
    }

    public void deleteBR(NhaCungCap brand){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM NHACUNGCAP WHERE MANCC = '" + brand.getMaNCC()+"'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Delete BRAND: ");
    }

    public void deleteBR(String brandName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM NHACUNGCAP WHERE TENNCC = '" + brandName + "'";
        db.execSQL(query);
        db.close();
        Log.d(TAG,"Delete BRAND with brandID: ");
    }

    public void loadBRList(ArrayList<NhaCungCap> brandList){
        brandList.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM NHACUNGCAP";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                NhaCungCap brand = new NhaCungCap();
                brand.setMaNCC(cursor.getString(0));
                brand.setTenNCC(cursor.getString(1));
                brand.setDiaChi(cursor.getString(2));
                brand.setSdt(cursor.getString(3));
                brand.setEmail(cursor.getString(4));
                brand.setLogo(cursor.getInt(5));
                brandList.add(brand);
            } while (cursor.moveToNext());
        }
        Log.d(TAG,"Load BRAND: ");
    }


    //XE
    public void insertXe() { }
    public void updateXe() { }
    public void loadXe() { }
    public void deleteXe() { }


    //PHU TUNG
    public void insertPT() { }
    public void updatePT() { }
    public void loadPT() { }
    public void deletePT() { }


    //THONG SO XE
    public void insertTSX() { }
    public void updateTSX() { }
    public void loadTSX() { }
    public void deleteTSX() { }


    //CHI TIET THONG SO XE
    public void insertCTTSX() { }
    public void updateCTTSX() { }
    public void loadCTTSX() { }
    public void deleteCTTSX() { }


    //THONG SO PHU TUNG
    public void insertTSPT() { }
    public void updateTSPT() { }
    public void loadTSPT() { }
    public void deleteTSPT() { }


    //DON HANG & CHI TIET DON HANG
    //public void insertDH() { }      ->        public void insertCTDH() { }
    public void loadDH() { }


//CAC PHAN CON LAI TUONG TU

}
