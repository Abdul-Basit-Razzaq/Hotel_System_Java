package com.example.hotelmanagementsystem;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagementsystem.Adapter.HotelAdapter;
import com.example.hotelmanagementsystem.Model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        recyclerView = findViewById(R.id.Recycler_view_hotel_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize hotel list
        hotelList = new ArrayList<>();
        // Populate hotel list with data
        populateHotelList();

        hotelAdapter = new HotelAdapter(this, hotelList);
        recyclerView.setAdapter(hotelAdapter);

        hotelAdapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Hotel hotel) {

            }

            @Override
            public void onItemClick(int position) {

            }
        });
    }

    private void populateHotelList() {
        hotelList.add(new Hotel("1", "Hotel One", "Address One", "Description One", 100.0, "1234567890"));
        hotelList.add(new Hotel("2", "Hotel Two", "Address Two", "Description Two", 150.0, "0987654321"));

    }
}
