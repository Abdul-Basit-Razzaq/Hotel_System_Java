package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagementsystem.HotelDetailActivity;
import com.example.hotelmanagementsystem.R;
import com.example.hotelmanagementsystem.Model.Hotel;

import java.util.List;

public class HotelDetailAdapter extends RecyclerView.Adapter<HotelDetailAdapter.ViewHolder> {
    private Context context;
    private List<Hotel> hotels;

    public HotelDetailAdapter(Context context, List<Hotel> hotels) {
        this.context = context;
        this.hotels = hotels;
    }

    public HotelDetailAdapter(HotelDetailActivity context, List<com.example.hotelmanagementsystem.Hotel> hotelList) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);
        holder.bind(hotel);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView hotelName;
        private TextView hotelAddress;
        private TextView description;
        private TextView price;
        private TextView phoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotel_name);
            hotelAddress = itemView.findViewById(R.id.hotel_address);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            phoneNumber = itemView.findViewById(R.id.phone_number);
        }

        public void bind(Hotel hotel) {
            hotelName.setText(hotel.getHotelName());
            hotelAddress.setText(hotel.getHotelAddress());
            description.setText(hotel.getDescription());
            price.setText(String.valueOf(hotel.getPrice()));
            phoneNumber.setText(hotel.getPhoneNumber());
        }
    }
}
