package com.example.sportbazaar;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.sportbazaar.databinding.ActivityCategoryBinding;

import java.util.ArrayList;
import java.util.Objects;

import Adapter.ProductAdapter;
import Model.Product;

public class CategoryActivity extends AppCompatActivity {

    ActivityCategoryBinding binding;

    ProductAdapter productAdapter;
    ArrayList<Product> products;
        TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCategoryBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        textView = findViewById(R.id.labal);
        String categoryName=getIntent().getStringExtra("CategoryName");
        Objects.requireNonNull(getSupportActionBar()).setTitle(categoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initProducts();

/*

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.products1.setLayoutManager(layoutManager);
        binding.products1.setAdapter(productAdapter);
*/


/*

        productAdapter = new ProductAdapter(this, products);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
*/

    }

    private void initProducts() {

        products = new ArrayList<>();

        products.add(new Product("Football", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%403bf6848jpg?alt=media&token=4b266dea-8783-4c41-afde-72af4b35bb1c", "\n" +
                "Price INR 999.00\nBrand\tNivia\n" +
                "Material\tRubber\n" +
                "Colour\tWhite\n" +
                "About this item\n" +
                "Sole: Rubber\n" +
                "Closure: Lace-Up\n" +
                "Shoe Width: Medium\n" +
                " Sole Material: Eva rubber, TPU sank", 1200, 12, 11));

        products.add(new Product("Football ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%40808a84ajpg?alt=media&token=31cb4e21-e418-4524-9775-2f96db48074a", " \n"+"Brand\\tNivia\\n\" +\n" +
                "Price INR 999.00\nBrand\tNivia\n" +
                "Material\tRubber\n" +
                "Colour\tWhite\n" +
                "About this item\n" +
                "Sole: Rubber\n" +
                "Closure: Lace-Up\n" +
                "Shoe Width: Medium\n" , 999, 12, 41));
        products.add(new Product("Football boot", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40c58a87fjpg?alt=media&token=dc1a72a6-54e0-4d76-acfa-5cd65c8d2976", " \n"+"Brand\\tNivia\\n\" +\n" +
                "Price INR 999.00\nBrand\tNivia\n" +
                "Material\tRubber\n" +
                "Colour\tWhite\n" +
                "About this item\n" +
                "Sole: Rubber\n" +
                "Closure: Lace-Up\n" +
                "Shoe Width: Medium\n" , 999, 12, 1));
        products.add(new Product("Football Helmet", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4091c3466jpg?alt=media&token=41aa242e-bf5f-400b-b038-59b563093c9f"," \n"+"Brand\\tNivia\\n\" +\n" +
                "Brand\tNivia\n" +
                "Material\tRubber\n" +
                "Colour\tWhite\n" +
                "About this item\n" +
                "Sole: Rubber\n" +
                "Closure: Lace-Up\n" +
                "Shoe Width: Medium\n" , 999, 12, 1));
        products.add(new Product("Football Gloves", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%407b335a7jpg?alt=media&token=97ea0f40-fa69-483f-89b7-823d0e11341c"," \n"+"Brand\\tNivia\\n\" +\n" +
                "Brand\tNivia\n" +
                "Material\tRubber\n" +
                "Colour\tWhite\n" +
                "About this item\n" +
                "Sole: Rubber\n" +
                "Closure: Lace-Up\n" +
                "Shoe Width: Medium\n" , 999, 12, 21));


        productAdapter = new ProductAdapter(this, products);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
//        products.add(new Product("Football", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%403bf6848jpg?alt=media&token=4b266dea-8783-4c41-afde-72af4b35bb1c", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Football ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%40808a84ajpg?alt=media&token=31cb4e21-e418-4524-9775-2f96db48074a", "", 999, 12, 4, 1));
//        products.add(new Product("Football boot", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40c58a87fjpg?alt=media&token=dc1a72a6-54e0-4d76-acfa-5cd65c8d2976", "sd", 999, 12, 1, 1));
//        products.add(new Product("Football Helmet", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4091c3466jpg?alt=media&token=41aa242e-bf5f-400b-b038-59b563093c9f", "sd", 999, 12, 1, 1));
//        products.add(new Product("Football Gloves", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%407b335a7jpg?alt=media&token=97ea0f40-fa69-483f-89b7-823d0e11341c", "sd", 999, 12, 1, 1));


//        productAdapter = new ProductAdapter(this, products);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        binding.productList.setLayoutManager(layoutManager);
//        binding.productList.setAdapter(productAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}