package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagementsystem.Model.Hotel;
import com.example.hotelmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.HotelViewHolder> implements Filterable {

    private Context context;
    private List<Hotel> hotelList;
    private List<Hotel> filteredList;

    public SearchAdapter(Context context, List<Hotel> hotelList) {
        this.context = context;
        this.hotelList = hotelList;
        this.filteredList = new ArrayList<>(hotelList); // Initialize filteredList with all items
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = filteredList.get(position); // Use filteredList instead of hotelList
        holder.bind(hotel);
    }

    @Override
    public int getItemCount() {
        return filteredList.size(); // Return filteredList size
    }

    // ViewHolder and other methods as before

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                if (filterPattern.isEmpty()) {
                    filteredList.clear();
                    filteredList.addAll(hotelList); // Show all items if filter is empty
                } else {
                    List<Hotel> filtered = new ArrayList<>();
                    for (Hotel hotel : hotelList) {
                        if (hotel.getHotelName().toLowerCase().contains(filterPattern)) {
                            filtered.add(hotel);
                        }
                    }
                    filteredList.clear();
                    filteredList.addAll(filtered); // Update filteredList with matching items
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged(); // Notify adapter of data change
            }
        };
    }

    // ViewHolder class and other methods as before

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        public HotelViewHolder(View view) {
            super(view);


        }

        public void bind(Hotel hotel) {
        }
        // ViewHolder implementation as before
    }
}
