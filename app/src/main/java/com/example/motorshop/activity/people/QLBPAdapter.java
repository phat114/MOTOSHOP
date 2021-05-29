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
import com.example.motorshop.datasrc.KhachHang;
import com.example.motorshop.datasrc.NhanVien;
import com.example.motorshop.db.DBManager;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QLBPAdapter extends RecyclerView.Adapter<QLBPAdapter.ViewHolder> {
    Context context;
    int resource;
    ArrayList<BoPhan> data;
    RecyclerView rvDataBP;
    DBManager db = new DBManager(context);

    public QLBPAdapter(Context context, int resource, ArrayList<BoPhan> data) {
        this.context = context;
        this.resource = resource;
        this.data = data;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bophan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if (data != null && data.size() > 0) {
            BoPhan BP = data.get(position);
            holder.tvdtMaBP.setText(BP.getMaBP());
            holder.tvdtTenBP.setText(BP.getTenBP());
        }
        BoPhan BP = this.data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvdtMaBP, tvdtTenBP;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvdtMaBP = itemView.findViewById(R.id.tvdtMaBP);
            tvdtTenBP = itemView.findViewById(R.id.tvdtTenBP);
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
            popupMenu.getMenuInflater().inflate(R.menu.menu_bophan, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.mnThemBP:
                            Intent intentThemBP = new Intent(context,Them_BPActivity.class);
                            context.startActivity(intentThemBP);
                            break;
                        case R.id.mnSuaBP:
                            Intent intentSuaBP = new Intent(context,Sua_BPAcitivy.class);
                            context.startActivity(intentSuaBP);
                            break;
                        case R.id.mnXoaBP:
                            int pos = getAdapterPosition();
                            System.out.println("data size: " + data.size());
                            System.out.println("id: " + data.get(pos).getMaBP());
                            DBManager db = new DBManager(context.getApplicationContext());
                            db.deleteDP(data.get(pos).getMaBP());
                            System.out.println("data size 2nd: " + data.size());
//                            db.loadSTList(data);
                            db.loadAllDPList();
                            ((QLBPActivity)context).loadQLBP();
                            break;

                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    }
}
