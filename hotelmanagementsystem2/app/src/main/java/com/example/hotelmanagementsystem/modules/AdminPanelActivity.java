package com.example.hotelmanagementsystem.modules;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class AdminPanelActivity extends AppCompatActivity {
    private EditText etHotelName, etHotelAddress, etDescription, etPrice, etPhoneNumber;
    private Button btnAddHotel, btnUpdateHotel, btnDeleteHotel;
    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;
    private Hotel selectedHotel;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        etHotelName = findViewById(R.id.et_hotel_name);
        etHotelAddress = findViewById(R.id.et_hotel_address);
        etDescription = findViewById(R.id.et_description);
        etPrice = findViewById(R.id.et_price);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        btnAddHotel = findViewById(R.id.btn_add_hotel);
        btnUpdateHotel = findViewById(R.id.btn_update_hotel);
        btnDeleteHotel = findViewById(R.id.btn_delete_hotel);
        recyclerView = findViewById(R.id.recycler_view_hotels);

        databaseReference = FirebaseDatabase.getInstance().getReference("hotels");

        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(this, hotelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(hotelAdapter);

        // Retrieve hotels from Firebase
        retrieveHotels();

        btnAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHotel();
            }
        });

        btnUpdateHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHotel();
            }
        });

        btnDeleteHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHotel();
            }
        });

        // Handle item click listener for updating hotels
        hotelAdapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Hotel hotel) {
                selectedHotel = hotel;
                etHotelName.setText(hotel.getHotelName());
                etHotelAddress.setText(hotel.getHotelAddress());
                etDescription.setText(hotel.getDescription());
                etPrice.setText(String.valueOf(hotel.getPrice()));
                etPhoneNumber.setText(hotel.getPhoneNumber());
            }

            @Override
            public void onItemClick(int position) {

            }
        });
    }

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
                hotelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AdminPanelActivity.this, "Failed to retrieve hotels", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addHotel() {
        String hotelName = etHotelName.getText().toString().trim();
        String hotelAddress = etHotelAddress.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        double price = Double.parseDouble(etPrice.getText().toString().trim());
        String phoneNumber = etPhoneNumber.getText().toString().trim();

        if (hotelName.isEmpty() || hotelAddress.isEmpty() || description.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = databaseReference.push().getKey();
        Hotel hotel = new Hotel(id, hotelName, hotelAddress, description, price, phoneNumber);

        if (id != null) {
            databaseReference.child(id).setValue(hotel);
            clearFields();
            Toast.makeText(this, "Hotel added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateHotel() {
        if (selectedHotel == null) {
            Toast.makeText(this, "Please select a hotel to update", Toast.LENGTH_SHORT).show();
            return;
        }

        String hotelName = etHotelName.getText().toString().trim();
        String hotelAddress = etHotelAddress.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        double price = Double.parseDouble(etPrice.getText().toString().trim());
        String phoneNumber = etPhoneNumber.getText().toString().trim();

        if (hotelName.isEmpty() || hotelAddress.isEmpty() || description.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Hotel hotel = new Hotel(selectedHotel.getId(), hotelName, hotelAddress, description, price, phoneNumber);
        databaseReference.child(hotel.getId()).setValue(hotel);

        clearFields();
        selectedHotel = null;
        Toast.makeText(this, "Hotel updated successfully", Toast.LENGTH_SHORT).show();
    }

    private void deleteHotel() {
        if (selectedHotel == null) {
            Toast.makeText(this, "Please select a hotel to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseReference.child(selectedHotel.getId()).removeValue();
        clearFields();
        selectedHotel = null;
        Toast.makeText(this, "Hotel deleted successfully", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        etHotelName.setText("");
        etHotelAddress.setText("");
        etDescription.setText("");
        etPrice.setText("");
        etPhoneNumber.setText("");
    }
}
