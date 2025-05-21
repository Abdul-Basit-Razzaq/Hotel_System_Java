package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagementsystem.Model.Hotel;
import com.example.hotelmanagementsystem.R;

import java.util.List;

public class HotelSearchAdapter extends RecyclerView.Adapter<HotelSearchAdapter.HotelViewHolder> {

    private Context context;
    private List<Hotel> hotelList;

    public HotelSearchAdapter(Context context, List<Hotel> hotelList) {
        this.context = context;
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.bind(hotel);
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHotelName, tvHotelAddress, tvDescription, tvPhoneNumber, tvPrice;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHotelName = itemView.findViewById(R.id.hotel_name);
            tvHotelAddress = itemView.findViewById(R.id.hotel_address);
            tvDescription = itemView.findViewById(R.id.description);
            tvPhoneNumber = itemView.findViewById(R.id.phone_number);
            tvPrice = itemView.findViewById(R.id.price);
        }

        public void bind(Hotel hotel) {
            tvHotelName.setText(hotel.getHotelName());
            tvHotelAddress.setText(hotel.getHotelAddress());
            tvDescription.setText(hotel.getDescription());
            tvPhoneNumber.setText(hotel.getPhoneNumber());
            tvPrice.setText(String.valueOf(hotel.getPrice()));
        }
    }
}
