package com.example.hotelmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagementsystem.Adapter.DashboardAdapter;
import com.example.hotelmanagementsystem.Model.DashboardItem;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private List<DashboardItem> dashboardItems;
    private ImageView ivLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler_view);
        ivLogout = findViewById(R.id.ivLogout);

        mAuth = FirebaseAuth.getInstance();

        dashboardItems = new ArrayList<>();
        // Add your dashboard items here
        dashboardItems.add(new DashboardItem(R.drawable.gym, "Admin Panel"));
        dashboardItems.add(new DashboardItem(R.drawable.swimming, "Search Hotel"));
        dashboardItems.add(new DashboardItem(R.drawable.room, "Search Name"));

        adapter = new DashboardAdapter(this, dashboardItems);
        recyclerView.setAdapter(adapter);

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void signOut() {
        mAuth.signOut();
        Intent intent = new Intent(Home.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
