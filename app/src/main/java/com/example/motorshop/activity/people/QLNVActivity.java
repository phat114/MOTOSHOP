package com.example.motorshop.activity.people;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.motorshop.activity.R;
import com.example.motorshop.activity.department.DepartmentActivity;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.db.DBManager;

import java.util.ArrayList;

public class QLNVActivity extends AppCompatActivity {
    NhanVien nhanVien;
    ArrayList<NhanVien> dataNV;
    RecyclerView rvDataNV;
    TextView tvdtHoTen, tvdtSDT, tvdtBP;
    MenuItem mnThemNV, mnSuaNV, mnXoaNV;
    ImageButton ibQLKH,ibQLNV,ibQLBP;
    QLNVAdapter qlnvAdapter;
    DBManager db = new DBManager(this);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_nhanvien);
        setControl();
        /*db.create();*/
        setEvent();
    }
    public void loadQLNV(){
        dataNV = db.loadAllSTList();
        qlnvAdapter = new QLNVAdapter(this, R.layout.item_nhanvien, dataNV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvDataNV.setLayoutManager(mLayoutManager);
        rvDataNV.setAdapter(qlnvAdapter);
        }

    public void deleteQLNV(NhanVien nhanVien){
        this.nhanVien = nhanVien;
        nhanVien.setMaNV(tvdtHoTen.getText().toString().trim());
        nhanVien.setHoTen(tvdtHoTen.getText().toString().trim());
        nhanVien.setSdt(tvdtSDT.getText().toString().trim());
        nhanVien.setMaBP(tvdtBP.getText().toString().trim());
        new DBManager(QLNVActivity.this).deleteST(nhanVien);
    }



    private void setEvent() {
        loadQLNV();
    }
    private void setControl() {
        tvdtHoTen = findViewById(R.id.tvdtHoTen);
        tvdtSDT = findViewById(R.id.tvdtSDT);
        tvdtBP = findViewById(R.id.tvdtBP);

        rvDataNV = findViewById(R.id.rvDataNV);
        mnThemNV = findViewById(R.id.mnThemNV);
        mnXoaNV = findViewById(R.id.mnXoaNV);
        mnSuaNV = findViewById(R.id.mnSuaNV);

        ibQLKH = findViewById(R.id.ibQLKH);
        ibQLNV = findViewById(R.id.ibQLNV);
        ibQLBP = findViewById(R.id.ibQLBP);

        ibQLKH.setOnClickListener(onClickListener);
        ibQLBP.setOnClickListener(onClickListener);
        ibQLNV.setOnClickListener(onClickListener);
    }
        ImageButton.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ibQLBP:
                        Intent intentQLBP = new Intent(QLNVActivity.this,QLBPActivity.class);
                        startActivity(intentQLBP);
                        break;
                    case R.id.ibQLKH:
                        Intent intentQLKH = new Intent(QLNVActivity.this,QLKHActivity.class);
                        startActivity(intentQLKH);
                        break;
                }
            }
        };


}



