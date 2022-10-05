package com.example.autoservice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class FrontPageWorker extends AppCompatActivity {

    ArrayList<String> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page_worker);

        database = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("name");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = (String) snapshot.getValue(String.class);
                Toast.makeText(FrontPageWorker.this,"Добро пожаловать, " + username + "!",Toast.LENGTH_LONG).show();
                setTitle(username);

            }
        });

        ArrayAdapter<String> adapter= new ArrayAdapter<>(FrontPageWorker.this, android.R.layout.simple_list_item_1, arr);

        ListView applications_list=findViewById(R.id.ListViewApplicationsWorker);

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
    }
}