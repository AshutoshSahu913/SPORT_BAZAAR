package com.example.sportbazaar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import Adapter.CartAdapter;
import Adapter.ProductAdapter;
import Model.Product;

public class CheckOut extends AppCompatActivity implements PaymentResultListener {

    ProductAdapter productAdapter;
    ArrayList<Product> products;
    Button btn;
    CartAdapter adapter;
    EditText editText1,editText2,editText3,editText4;
    TextView PayStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        editText1=findViewById(R.id.UserName);
        editText2=findViewById(R.id.EmailAddress);
        editText3=findViewById(R.id.PhoneNumber);
        editText4=findViewById(R.id.Address);
        products = new ArrayList<>();
        productAdapter = new ProductAdapter(this, products);
        (Objects.requireNonNull(getSupportActionBar())).setDisplayHomeAsUpEnabled(true);
//
        btn = findViewById(R.id.checkoutBtn);
        Checkout.preload(getApplicationContext());
        products = new ArrayList<>();


        //define quantity

        products.add(new Product("Product 1", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40726c91fjpg?alt=media&token=7692ec07-1819-489a-9687-58e76ec26a9e", "Running", 100, 25, 2));
        products.add(new Product("Product 2", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40726c91fjpg?alt=media&token=7692ec07-1819-489a-9687-58e76ec26a9e", "Playing", 100, 25, 23));

        adapter = new CartAdapter(this, products);

//        int catId = getIntent().getIntExtra("catId", 0);
//        String categoryName = getIntent().getStringExtra("categoryName");
//
//        Objects.requireNonNull(getSupportActionBar()).setTitle(categoryName);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getProducts(catId);

//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        binding.productList.setLayoutManager(layoutManager);
//        binding.productList.setAdapter(productAdapter);
        ProgressBar progressBar = findViewById(R.id.progressbar_ProcessCheckOut);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.length()==0){
                    editText1.setError("Please enter name");
                } else if (editText2.length()==0){
                    editText2.setError("Please enter Email ");
                }else if (editText3.length()==0){
                    editText3.setError("Please enter Phone number");
                } else if (editText4.length() == 0) {
                    editText4.setError("Please enter Address");
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    PaymentNow("4091.90");
                }
//   {
//   Intent intent =new Intent(CheckOut.this,PaymentActivity.class);
//                startActivity(intent);


            }


        });
    }

    public void PaymentNow(String price) {

        final Activity activity = this;
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_01KuhxrWiYIoWG");
        checkout.setImage(R.drawable.deliveryman);
        double finalAmount = Float.parseFloat(price) * 100;

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Ashutosh ");
            options.put("description", "Reference  No #12345");
            options.put("image", "");
            options.put("Theme.color", "@drawable/app_color");
            options.put("currency", "INR");
            options.put("amount", finalAmount);
            options.put("prefill.email", "SportBazar.com");

            options.put("prefill.contect", "9516895742");
            checkout.open(activity, options);

        } catch (JSONException e) {
            Log.e("TAG", "Error in starting RazorPay Checkout", e);
        }
    }

    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }

    void getProducts(int catId) {

    }

    @Override
    public void onPaymentSuccess(String s) {
        Intent intent = new Intent(CheckOut.this, HomePage.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Payment Success", Toast.LENGTH_SHORT).show();

        PayStatus.setText(s);
    }

    @Override
    public void onPaymentError(int i, String s) {

        Intent intent = new Intent(CheckOut.this, HomePage.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Payment Failed", Toast.LENGTH_SHORT).show();
        PayStatus.setText(s);
    }
}