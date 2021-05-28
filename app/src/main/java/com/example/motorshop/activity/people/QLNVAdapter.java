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
import com.example.motorshop.datasrc.BoPhan;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.db.DBManager;

import org.jetbrains.annotations.NotNull;


import java.util.List;

public class QLNVAdapter extends RecyclerView.Adapter<QLNVAdapter.ViewHolder> {
    Context context;
    int resource;
    List<NhanVien> data;
    RecyclerView rvDataNV;
    DBManager db = new DBManager(context);

    public QLNVAdapter(Context context, int resource, List<NhanVien> data) {
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
        NhanVien NV = this.data.get(position);
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

                            break;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }


    }


}
