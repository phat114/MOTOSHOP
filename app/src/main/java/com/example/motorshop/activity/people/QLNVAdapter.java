package com.example.motorshop.activity.people;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.PopupMenu;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.motorshop.activity.R;
import com.example.motorshop.activity.department.DepartmentActivity;
import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.db.DBManager;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

public class QLNVAdapter extends RecyclerView.Adapter<QLNVAdapter.ViewHolder> {
    Context context;
    int resource;
    ArrayList<NhanVien> data = new ArrayList<>();
    RecyclerView rvDataNV;
    DBManager db = new DBManager(context);
    NhanVien nhanVien;

    public QLNVAdapter(Context context, int resource, ArrayList<NhanVien> data) {
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nhanvien, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if (data != null && data.size() > 0) {
            NhanVien NV = data.get(position);
            holder.tvdtHoTen.setText(NV.getHoTen());
            holder.tvdtSDT.setText(NV.getSdt());
            holder.tvdtBP.setText(NV.getMaBP());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvdtHoTen, tvdtSDT, tvdtBP;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvdtHoTen = itemView.findViewById(R.id.tvdtHoTen);
            tvdtSDT = itemView.findViewById(R.id.tvdtSDT);
            tvdtBP = itemView.findViewById(R.id.tvdtBP);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showPopUpBP();
                    return true;
                }
            });
        }

        private void showPopUpBP() {
            PopupMenu popupMenu = new PopupMenu(context, itemView);
            popupMenu.getMenuInflater().inflate(R.menu.menu_nhanvien, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.mnThemNV:
                            Intent intentThemNV = new Intent(context, Them_NVActivity.class);
                            context.startActivity(intentThemNV);
                            break;
                        case R.id.mnSuaNV:
                            Intent intentSuaNV = new Intent(context, Sua_NVActivity.class);
                            context.startActivity(intentSuaNV);
                            break;
                        case R.id.mnXoaNV:
                            int pos = getAdapterPosition();
                            System.out.println("data size: " + data.size());
                            System.out.println("id: " + data.get(pos).getMaNV());
                            DBManager db = new DBManager(context.getApplicationContext());
                                    db.deleteST(data.get(pos).getMaNV());
                            System.out.println("data size 2nd: " + data.size());
//                            db.loadSTList(data);
                            db.loadAllSTList();
                            ((QLNVActivity)context).loadQLNV();
                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }

//        private void deleteNV(int pos) {
//            new AlertDialog.Builder((QLNVActivity)context)
//                    .setTitle("Xóa Nội Dung")
//                    .setMessage("Bạn có muốn xóa nhân viên này không?")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            data.get(pos).setHoTen(tvdtHoTen.getText().toString().trim());
//                            data.get(pos).setSdt(tvdtSDT.getText().toString().trim());
//                            data.get(pos).setMaBP(tvdtBP.getText().toString().trim());
//                            db.deleteST(pos, (ArrayList<NhanVien>) data);
//                            data.remove(pos);
//                            }
//                    });
//        }


    }


}
