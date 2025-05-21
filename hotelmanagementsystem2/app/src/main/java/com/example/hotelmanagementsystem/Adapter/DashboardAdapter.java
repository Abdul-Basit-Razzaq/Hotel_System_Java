package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagementsystem.Model.DashboardItem;
import com.example.hotelmanagementsystem.R;
import com.example.hotelmanagementsystem.modules.AdminPanelActivity;
import com.example.hotelmanagementsystem.modules.SearchHotelActivity;
import com.example.hotelmanagementsystem.modules.SearchName;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private List<DashboardItem> items;
    private Context context;

    public DashboardAdapter(Context context, List<DashboardItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dashboard_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DashboardItem item = items.get(position);
        holder.featureIcon.setImageResource(item.getIconResId());
        holder.featureTitle.setText(item.getTitle());

        // Set click listener to handle item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        // Navigate to Admin Panel Activity
                        Intent adminIntent = new Intent(context, AdminPanelActivity.class);
                        context.startActivity(adminIntent);
                        break;
                    case 1:
                        // Navigate to Search Hotel Activity
                        Intent searchIntent = new Intent(context, SearchHotelActivity.class);
                        context.startActivity(searchIntent);
                        break;
                    case 2:
                        // Navigate to Search Hotel Activity
                        Intent Intent = new Intent(context, SearchName.class);
                        context.startActivity(Intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView featureIcon;
        public TextView featureTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            featureIcon = itemView.findViewById(R.id.feature_icon);
            featureTitle = itemView.findViewById(R.id.feature_title);
        }
    }
}
