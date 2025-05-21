package com.example.hotelmanagementsystem.modules;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagementsystem.Adapter.HotelAdapter;
import com.example.hotelmanagementsystem.Model.Hotel;
import com.example.hotelmanagementsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchName extends AppCompatActivity {

    private EditText etSearchHotel;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;
    private List<Hotel> filteredList; // To store filtered hotels
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);

        etSearchHotel = findViewById(R.id.et_search_hotel);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_hotels);

        hotelList = new ArrayList<>();
        filteredList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(this, filteredList); // Initialize with filteredList
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(hotelAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("hotels");

        etSearchHotel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString().toLowerCase().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        retrieveHotels();
    }

    private void retrieveHotels() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hotelList.clear();
                filteredList.clear(); // Clear existing data in filteredList
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Hotel hotel = snapshot.getValue(Hotel.class);
                    if (hotel != null) {
                        hotelList.add(hotel);
                        filteredList.add(hotel); // Add to filteredList as well initially
                    }
                }
                hotelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SearchName.this, "Failed to retrieve hotels", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filter(String searchText) {
        filteredList.clear();
        if (searchText.isEmpty()) {
            filteredList.addAll(hotelList); // Add all hotels if search query is empty
        } else {
            searchText = searchText.toLowerCase();
            for (Hotel hotel : hotelList) {
                if (hotel.getHotelName().toLowerCase().contains(searchText)) {
                    filteredList.add(hotel);
                }
            }
        }
        hotelAdapter.notifyDataSetChanged();
    }
}
