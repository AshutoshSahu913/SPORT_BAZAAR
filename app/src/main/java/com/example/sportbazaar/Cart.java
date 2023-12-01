package com.example.sportbazaar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sportbazaar.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.Objects;

import Adapter.CartAdapter;
import Model.Product;

public class Cart extends AppCompatActivity {

    ActivityCartBinding binding;
    CartAdapter adapter;
    ArrayList<Product> products;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        button = findViewById(R.id.Continuebtn);

        products = new ArrayList<>();

        //define quantity

        products.add(new Product("Football ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%40808a84ajpg?alt=media&token=31cb4e21-e418-4524-9775-2f96db48074a", "Shiped", 999, 12, 4));
        products.add(new Product("Football boot", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40c58a87fjpg?alt=media&token=dc1a72a6-54e0-4d76-acfa-5cd65c8d2976", "sd", 999, 12,  1));
        products.add(new Product("Football Helmet", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4091c3466jpg?alt=media&token=41aa242e-bf5f-400b-b038-59b563093c9f", "sd", 999, 12,  1));
        products.add(new Product("Football Gloves", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%407b335a7jpg?alt=media&token=97ea0f40-fa69-483f-89b7-823d0e11341c", "sd", 999, 12,  1));

        adapter = new CartAdapter(this, products);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());

        binding.CartList.setLayoutManager(new LinearLayoutManager(this));
        binding.CartList.addItemDecoration(itemDecoration);
        binding.CartList.setAdapter(adapter);

//        button.setOnClickListener(view -> startActivity(new Intent(Cart.this, CheckOut.class)));
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                if(button.getVisibility() == View.VISIBLE){
//                    button.setVisibility(View.GONE);
                    startActivity(new Intent(Cart.this, CheckOut.class));
//                }else{
//                    button.setVisibility(View.VISIBLE);
//                }

            }
        });


        (Objects.requireNonNull(getSupportActionBar())).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
