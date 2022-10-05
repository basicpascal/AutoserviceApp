package com.example.autoservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText name = findViewById(R.id.TextName);
        EditText email = findViewById(R.id.TextEmail);
        EditText password = findViewById(R.id.TextPassword);
        Button register_btn = findViewById(R.id.RegisterButton);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String value_name = name.getText().toString();
                            database.child("Users").child(mAuth.getCurrentUser().getUid()).child("name").setValue(value_name);
                            database.child("Users").child(mAuth.getCurrentUser().getUid()).child("email").setValue(email.getText().toString());
                            database.child("Users").child(mAuth.getCurrentUser().getUid()).child("password").setValue(password.getText().toString());
                            database.child("Users").child(mAuth.getCurrentUser().getUid()).child("status").setValue("Worker");

                            Intent intent = new Intent(Registration.this, Authorization.class);
                            startActivity(intent);
                            Toast.makeText(Registration.this,"Успешная регистрация",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(Registration.this,"Ошибка регистрации",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}