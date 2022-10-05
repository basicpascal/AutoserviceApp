package com.example.autoservice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;

public class FrontPageAdmin extends AppCompatActivity {

    ArrayList<String> arr = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page_admin);
        setTitle("Admin");

        database = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("name");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = (String) snapshot.getValue(String.class);
                Toast.makeText(FrontPageAdmin.this,"Добро пожаловать, " + username + "!",Toast.LENGTH_LONG).show();


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter<String> adapter= new ArrayAdapter<>(FrontPageAdmin.this, android.R.layout.simple_list_item_1, arr);

        ListView applications_list=findViewById(R.id.ListViewApplicationsAdmin);

        applications_list.setAdapter(adapter);

        applications_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        database = FirebaseDatabase.getInstance().getReference("Applications");

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value;

                value=((snapshot.getKey()));
                arr.add(value);
                adapter.notifyDataSetChanged();
            }
        });

    Button add_application = findViewById(R.id.AddApplicationButton);

        add_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FrontPageAdmin.this, MakeApplication.class);
                startActivity(intent);
            }
        });
    }
}