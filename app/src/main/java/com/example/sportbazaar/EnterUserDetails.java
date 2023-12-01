package com.example.sportbazaar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import LoginActivity.EnterMobileNumber;

public class EnterUserDetails extends Activity {

    EditText editText1,editText2;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editText1=findViewById(R.id.enterName);
        editText2 = findViewById(R.id.enterAge);
        button=findViewById(R.id.sendDetailsBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(EnterUserDetails.this, EnterMobileNumber.class);
                startActivity(intent);

            }
        });
    }
}
