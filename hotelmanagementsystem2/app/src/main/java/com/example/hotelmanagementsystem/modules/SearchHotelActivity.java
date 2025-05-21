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

import com.example.hotelmanagementsystem.Adapter.HotelSearchAdapter;
import com.example.hotelmanagementsystem.Model.Hotel;
import com.example.hotelmanagementsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchHotelActivity extends AppCompatActivity {

    private EditText etSearchHotel, etMinPrice, etMaxPrice;
    private HotelSearchAdapter hotelAdapter;
    private List<Hotel> hotelList;
    private List<Hotel> filteredList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        etSearchHotel = findViewById(R.id.et_search_hotel);
        
        etMinPrice = findViewById(R.id.et_search_price_min);
        etMaxPrice = findViewById(R.id.et_search_price_max);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_hotels);

        hotelList = new ArrayList<>();
        filteredList = new ArrayList<>();
        hotelAdapter = new HotelSearchAdapter(this, filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(hotelAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("hotels");

        // TextWatcher for hotel name search
        etSearchHotel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase().trim();
                filter(searchText);
            }

            private void filter(String searchText) {
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // TextWatcher for minimum price filter
        etMinPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // TextWatcher for maximum price filter
        etMaxPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        retrieveHotels();
    }

    // Retrieve hotels from Firebase
    private void retrieveHotels() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hotelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Hotel hotel = snapshot.getValue(Hotel.class);
                    if (hotel != null) {
                        hotelList.add(hotel);
                    }
                }
                filter(); // Apply initial filtering
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SearchHotelActivity.this, "Failed to retrieve hotels", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Filter hotels based on search text and price range
    private void filter() {
        filteredList.clear();
        String searchText = etSearchHotel.getText().toString().toLowerCase().trim();
        double minPrice = Double.MIN_VALUE;
        double maxPrice = Double.MAX_VALUE;

        try {
            if (!etMinPrice.getText().toString().isEmpty()) {
                minPrice = Double.parseDouble(etMinPrice.getText().toString());
            }
            if (!etMaxPrice.getText().toString().isEmpty()) {
                maxPrice = Double.parseDouble(etMaxPrice.getText().toString());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        for (Hotel hotel : hotelList) {
            if ((searchText.isEmpty() || hotel.getHotelName().toLowerCase().contains(searchText))
                    && hotel.getPrice() >= minPrice && hotel.getPrice() <= maxPrice) {
                filteredList.add(hotel);
            }
        }

        // Sort filtered list by hotel name
        Collections.sort(filteredList, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel hotel1, Hotel hotel2) {
                return hotel1.getHotelName().compareToIgnoreCase(hotel2.getHotelName());
            }
        });

        hotelAdapter.notifyDataSetChanged();
    }
}
