package com.example.motorshop.activity.people;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.motorshop.activity.R;
import com.example.motorshop.datasrc.KhachHang;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.db.DBManager;

import java.util.ArrayList;

public class QLKHActivity extends AppCompatActivity {

    KhachHang khachHang;
    ArrayList<KhachHang> dataKH;
    RecyclerView rvDataKH;
    TextView tvdtHoTen, tvdtSDT, tvdtDC;
    MenuItem mnThemKH, mnSuaKH, mnXoaKH;
    ImageButton ibQLKH2,ibQLNV2,ibQLBP2;
    QLKHAdapter qlkhAdapter;
    DBManager dbKH;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khachhang);
        setControl();
        setEvent();
        loadQLNV();
    }

    public void loadQLNV() {
        new DBManager(QLKHActivity.this).loadCTMList(dataKH);
    }

    private void setEvent() {
        dataKH = new ArrayList<>();
        qlkhAdapter = new QLKHAdapter(this, R.layout.item_khachhang, dataKH);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvDataKH.setLayoutManager(mLayoutManager);
        rvDataKH.setAdapter(qlkhAdapter);
}


    private void setControl() {
        tvdtHoTen = findViewById(R.id.tvdtHoTenKH);
        tvdtSDT = findViewById(R.id.tvdtSDTKH);
        tvdtDC = findViewById(R.id.tvdtDC);
        rvDataKH = findViewById(R.id.rvDataKH);

        mnThemKH = findViewById(R.id.mnThemKH);
        mnXoaKH = findViewById(R.id.mnXoaKH);
        mnSuaKH = findViewById(R.id.mnSuaKH);

        ibQLKH2 = findViewById(R.id.ibQLKH2);
        ibQLNV2 = findViewById(R.id.ibQLNV2);
        ibQLBP2 = findViewById(R.id.ibQLBP2);

        ibQLKH2.setOnClickListener(onClickListener);
        ibQLBP2.setOnClickListener(onClickListener);
        ibQLNV2.setOnClickListener(onClickListener);
    }
        ImageButton.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ibQLNV2:
                        Intent intentQLNV = new Intent(QLKHActivity.this,QLNVActivity.class);
                        startActivity(intentQLNV);
                        break;
                    case R.id.ibQLBP2:
                        Intent intentQLBP = new Intent(QLKHActivity.this,QLBPActivity.class);
                        startActivity(intentQLBP);
                        break;
                }
            }
        };


    }

