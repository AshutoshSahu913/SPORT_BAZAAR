package com.example.sportbazaar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sportbazaar.databinding.ActivityProductDetailsBinding;

import java.util.ArrayList;

import Model.Product;

public class ProductDetails extends AppCompatActivity {
    Context context;
    ActivityProductDetailsBinding binding;
    ArrayList<Product> products;
    Button addToCart;
    TextView name;
    TextView price;
    TextView desc;
    TextView stock;
    TextView dis;
    ImageView img;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addToCart = findViewById(R.id.addToCart);

        img=findViewById(R.id.productDetailsImage);
        name=findViewById(R.id.productDetailsName);
        price=findViewById(R.id.productDetailsPrice);
        desc=findViewById(R.id.productDetailsDescription);
        stock=findViewById(R.id.productDetailsStock);
        dis=findViewById(R.id.productDetailsDiscount);

        String productName = getIntent().getStringExtra("productName");
        String productImage = getIntent().getStringExtra("image");
        String productDescription = getIntent().getStringExtra("productDescription");
        double productPrice = getIntent().getDoubleExtra("productPrice", 0.0);
        double productDiscount = getIntent().getDoubleExtra("productDiscount", 0.0);
        int productStock = getIntent().getIntExtra("productStock", 0);


        Glide.with(this)
                .load(productImage)
                .into(binding.productDetailsImage);

        getSupportActionBar().setTitle(productName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name.setText(productName);
        img.setImageResource(Integer.parseInt(productImage));
        desc.setText(productDescription);
        price.setText((int) productPrice);
        stock.setText((int) productStock);
        dis.setText((int)productDiscount);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addToCart.getVisibility() == View.INVISIBLE) {
                    addToCart.setVisibility(View.INVISIBLE);
                } else {
                    addToCart.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart) {
            startActivity(new Intent(this, Cart.class));
        }
        return super.onOptionsItemSelected(item);
    }

}