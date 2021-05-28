package com.example.motorshop.activity.people;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.KhachHang;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.db.DBManager;

public class Them_KHActivity extends AppCompatActivity {

    KhachHang khachHang;
    DBManager db = new DBManager(this);
    EditText etTenKHThem,etCMNDThem,etSDTKH,etDiaChiThem;
    Button btnXacNhanThemKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_kh);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnXacNhanThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CMNDThem = etCMNDThem.getText().toString();
                String tenKHThem = etTenKHThem.getText().toString();
                String SDTKHThem = etSDTKH.getText().toString();
                String diaChiKHThem = etDiaChiThem.getText().toString();
                if(TextUtils.isEmpty(CMNDThem)){
                    Toast.makeText(Them_KHActivity.this,"CMND khách hàng không được rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
                InserKH(khachHang);
                Intent intent = new Intent(Them_KHActivity.this,QLKHActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        etCMNDThem = findViewById(R.id.etCMNDThem);
        etTenKHThem = findViewById(R.id.etTenKHThem);
        etSDTKH= findViewById(R.id.etSDTKHCT);
        etDiaChiThem= findViewById(R.id.etDCKHCanThem);
        btnXacNhanThemKH = findViewById(R.id.btnXacNhanThemKH);
    }

    public void InserKH(KhachHang khachHang){
        khachHang = new KhachHang();
        khachHang.setCmnd(etCMNDThem.getText().toString().trim());
        khachHang.setHoTen(etTenKHThem.getText().toString().trim());
        khachHang.setSdt(etSDTKH.getText().toString().trim());
        khachHang.setDiaChi(etDiaChiThem.getText().toString().trim());
        new DBManager(Them_KHActivity.this).insertCTM(khachHang);
    }
}