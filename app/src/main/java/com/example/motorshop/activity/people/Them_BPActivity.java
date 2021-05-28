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
import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.db.DBManager;

public class Them_BPActivity extends AppCompatActivity {
    BoPhan boPhan;
    DBManager db = new DBManager(this);
    EditText etMaBPThem,etTenBPThem;
    Button btnXacNhanThemBP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_bp);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnXacNhanThemBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maBPThem = etMaBPThem.getText().toString();
                String tenBPThem = etTenBPThem.getText().toString();
                if(TextUtils.isEmpty(maBPThem)){
                    Toast.makeText(Them_BPActivity.this,"Mã Bộ Phận không được rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
                InserBP(boPhan);
                Intent intent = new Intent(Them_BPActivity.this,QLBPActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        etMaBPThem = findViewById(R.id.etMaBPThem);
        etTenBPThem = findViewById(R.id.etTenBPThem);
        btnXacNhanThemBP = findViewById(R.id.btnXacNhanThemBP);
    }

    public void InserBP(BoPhan boPhan){
        boPhan = new BoPhan();
        boPhan.setMaBP(etMaBPThem.getText().toString().trim());
        boPhan.setTenBP(etTenBPThem.getText().toString().trim());

        new DBManager(Them_BPActivity.this).insertDP(boPhan);
    }
}
