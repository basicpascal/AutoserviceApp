package com.example.autoservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Objects;

public class Authorization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        Button to_register_btn = findViewById(R.id.ToRegisterButton);

        Button to_auth_btn = findViewById(R.id.AuthButton);

        EditText login = findViewById(R.id.TextEmailAuth);
        EditText password = findViewById(R.id.TextPasswordAuth);

        to_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Authorization.this, Registration.class);
                startActivity(intent);
            }
        });

        to_auth_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*mAuth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            database = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("status");
                            database.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String user_status = ((snapshot.getValue(String.class)));
                                    if (Objects.equals(user_status, "Admin")) {
                                        Intent intent = new Intent(Authorization.this, FrontPageAdmin.class);
                                        startActivity(intent);
                                    }
                                    if (Objects.equals(user_status, "Worker")){
                                        Intent intent2 = new Intent(Authorization.this, FrontPageWorker.class);
                                        startActivity(intent2);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                });*/
            }
        });
    }
}