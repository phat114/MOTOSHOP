package com.example.motorshop.activity.people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.department.DepartmentActivity;
import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.db.DBManager;

import java.util.ArrayList;

public class Them_NVActivity extends AppCompatActivity {
    NhanVien nhanVien;
    DBManager db = new DBManager(this);
    EditText etTenNVThem,etMaNVThem,etSDT,etPBNVThem;
    Button btnXacNhanThemNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_nv);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnXacNhanThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNVThem = etMaNVThem.getText().toString();
                String tenNVThem = etTenNVThem.getText().toString();
                String SDTNVThem = etSDT.getText().toString();
                String PBNVThem = etPBNVThem.getText().toString();
                if(TextUtils.isEmpty(maNVThem)){
                    Toast.makeText(Them_NVActivity.this,"Mã nhân viên không được rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
                InserNV(nhanVien);
                Intent intent = new Intent(Them_NVActivity.this,QLNVActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        etMaNVThem = findViewById(R.id.etMaNVThem);
        etTenNVThem = findViewById(R.id.etTenNVThem);
        etSDT= findViewById(R.id.etSDT);
        etPBNVThem = findViewById(R.id.etPBNVCanThem);
        btnXacNhanThemNV = findViewById(R.id.btnXacNhanThemNV);
    }

    public void InserNV(NhanVien nhanVien){
        nhanVien = new NhanVien();
        nhanVien.setMaNV( etMaNVThem.getText().toString().trim());
        nhanVien.setHoTen(etTenNVThem.getText().toString().trim());
        nhanVien.setSdt(etSDT.getText().toString().trim());
        nhanVien.setMaBP(etPBNVThem.getText().toString().trim());
        new DBManager(Them_NVActivity.this).insertST(nhanVien);
    }
}