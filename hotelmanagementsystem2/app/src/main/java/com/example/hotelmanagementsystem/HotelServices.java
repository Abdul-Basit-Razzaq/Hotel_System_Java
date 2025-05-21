// HotelDetailActivity.java
package com.example.hotelmanagementsystem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmanagementsystem.Model.Hotel;
import com.example.hotelmanagementsystem.R;

public class HotelServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_services);

        // Retrieve hotel details from intent
        Hotel hotel = getIntent().getParcelableExtra("hotel");

        // Initialize TextViews to display hotel details
        TextView hotelNameTextView = findViewById(R.id.et_hotel_name);
        TextView hotelAddressTextView = findViewById(R.id.et_hotel_address);
        TextView descriptionTextView = findViewById(R.id.et_description);
        TextView phoneNumberTextView = findViewById(R.id.et_phone_number);
        TextView priceTextView = findViewById(R.id.et_phone_number);

        // Set text to TextViews
        hotelNameTextView.setText(hotel.getHotelName());
        hotelAddressTextView.setText(hotel.getHotelAddress());
        descriptionTextView.setText(hotel.getDescription());
        phoneNumberTextView.setText(hotel.getPhoneNumber());
        priceTextView.setText(String.valueOf(hotel.getPrice()));
    }
}
