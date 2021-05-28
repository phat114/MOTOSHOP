package com.example.motorshop.activity.people;

import android.content.Context;
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
import com.example.motorshop.datasrc.KhachHang;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.db.DBManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QLKHAdapter extends RecyclerView.Adapter<QLKHAdapter.ViewHolder> {
    Context context;
    int resource;
    List<KhachHang> data;
    RecyclerView rvDataKH;
    DBManager db = new DBManager(context);

    public QLKHAdapter(Context context, int resource, List<KhachHang> data) {
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if (data != null && data.size() > 0) {
            KhachHang KH = data.get(position);
            holder.tvdtHoTenKH.setText(KH.getHoTen());
            holder.tvdtSDTKH.setText(KH.getSdt());
            holder.tvdtDC.setText(KH.getDiaChi());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvdtHoTenKH, tvdtSDTKH, tvdtDC;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvdtHoTenKH = itemView.findViewById(R.id.tvdtHoTenKH);
            tvdtSDTKH = itemView.findViewById(R.id.tvdtSDTKH);
            tvdtDC = itemView.findViewById(R.id.tvdtDC);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showPopUpKH();
                    return true;
                }
            });
        }

        private void showPopUpKH() {
            PopupMenu popupMenu = new PopupMenu(context, itemView);
            popupMenu.getMenuInflater().inflate(R.menu.menu_khachhang, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.mnThemKH:
                            Intent intentThemNV = new Intent(context,Them_KHActivity.class);
                            context.startActivity(intentThemNV);
                            break;
                        case R.id.mnSuaKH:
                            Intent intentSuaNV = new Intent(context,Sua_KHAcitivy.class);
                            context.startActivity(intentSuaNV);
                            break;
                        case R.id.mnXoaKH:

                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    }
}