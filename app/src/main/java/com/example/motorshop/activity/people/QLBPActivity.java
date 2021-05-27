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
import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.datasrc.KhachHang;
import com.example.motorshop.db.DBManager;

import java.util.ArrayList;

public class QLBPActivity extends AppCompatActivity {

    BoPhan boPhan;
    ArrayList<BoPhan> dataBP;
    RecyclerView rvDataBP;
    TextView tvdtMaBP, tvdtTenBP;
    MenuItem mnThemBP, mnSuaBP, mnXoaBP;
    ImageButton ibQLKH1,ibQLNV1,ibQLBP1;
    QLBPAdapter qlbpAdapter;
    DBManager dbBP;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bophan);
        setControl();
        setEvent();
        loadQLBP();
    }

    public void loadQLBP() {
        new DBManager(QLBPActivity.this).loadDPList(dataBP);
    }

    private void setEvent() {
        dataBP = new ArrayList<>();
        qlbpAdapter = new QLBPAdapter(this, R.layout.item_bophan, dataBP);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvDataBP.setLayoutManager(mLayoutManager);
        rvDataBP.setAdapter(qlbpAdapter);
    }


    private void setControl() {
        tvdtMaBP = findViewById(R.id.tvdtMaBP);
        tvdtTenBP = findViewById(R.id.tvdtTenBP);
        rvDataBP = findViewById(R.id.rvDataBP);
        mnThemBP = findViewById(R.id.mnThemBP);
        mnXoaBP = findViewById(R.id.mnXoaBP);
        mnSuaBP = findViewById(R.id.mnSuaBP);
        ibQLKH1 = findViewById(R.id.ibQLKH1);
        ibQLNV1 = findViewById(R.id.ibQLNV1);
        ibQLBP1 = findViewById(R.id.ibQLBP1);

        ibQLKH1.setOnClickListener(onClickListener);
        ibQLBP1.setOnClickListener(onClickListener);
        ibQLNV1.setOnClickListener(onClickListener);
    }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ibQLNV1:
                        Intent intentQLNV = new Intent(QLBPActivity.this,QLNVActivity.class);
                        startActivity(intentQLNV);
                        break;
                    case R.id.ibQLKH1:
                        Intent intentQLKH = new Intent(QLBPActivity.this,QLKHActivity.class);
                        startActivity(intentQLKH);
                        break;
                }
            }
        };

}