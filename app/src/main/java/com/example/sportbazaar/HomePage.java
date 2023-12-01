package com.example.sportbazaar;

import static com.example.sportbazaar.R.id.navigationview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportbazaar.databinding.ActivityHomePageBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

import Adapter.CategoryAdapter;
import Adapter.ProductAdapter;
import LoginActivity.EnterMobileNumber;
import Model.Category;
import Model.Product;

public class HomePage extends AppCompatActivity  {

    //for Navigation drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ActivityHomePageBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;
    ProductAdapter productAdapter;
    ArrayList<Product> products;

    public ActionBarDrawerToggle toggle;

    RecyclerView recyclerView;
    RecyclerView recyclerView1;

    DatabaseReference databaseReference;

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        carousel=findViewById(R.id.carousel);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(navigationview);
        toolbar = findViewById(R.id.toolbar);

        recyclerView = findViewById(R.id.categoriesList);
        recyclerView1 = findViewById(R.id.productList);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(HomePage.this, HomePage.class));
                    overridePendingTransition(0, 0);
                    break;
                case R.id.nav_cart:
                    startActivity(new Intent(HomePage.this, Cart.class));
                    overridePendingTransition(0, 0);
                    break;
                case R.id.nav_setting:
                    startActivity(new Intent(HomePage.this, SettingsActivity.class));
                    overridePendingTransition(0, 0);
                    break;
                case R.id.nav_wishList:
                    startActivity(new Intent(HomePage.this, WishListActivity.class));
                    overridePendingTransition(0, 0);
                    break;
                case R.id.nav_logout:
                    startActivity(new Intent(HomePage.this, EnterMobileNumber.class));
                    overridePendingTransition(0, 0);
                    break;
            }
            return true;
        });

        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));


        setSupportActionBar(toolbar);
        initSlider();
        initCategories();
        initProducts();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



/*
//    public void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
*/

    //Slider
    void initSlider() {
//        // slider data
        binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-vector/discount-sale-abstract-dark-maroon-gradient-geometric-triangle-pattern-multipurpose-background-banner_1340-17229.jpg", "hello"));
        binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-vector/special-offer-big-sale-background_1361-2651.jpg", ""));
        binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-vector/modern-cyber-monday-black-banner-with-brush-stroke_1361-2982.jpg", ""));

//        databaseReference = FirebaseDatabase.getInstance().getReference("Categories");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        categories = new ArrayList<>();
//
//        categoryAdapter = new CategoryAdapter(this, categories);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Category category = dataSnapshot.getValue(Category.class);
//                    categories.add(category);
//                }
//                categoryAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//


    }

    // Categories
    private void initCategories() {
//
        databaseReference = FirebaseDatabase.getInstance().getReference("Categories");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categories = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(this, categories);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Category category = dataSnapshot.getValue(Category.class);
                    categories.add(category);
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        //sample data

////
        categories.add(new Category("E-Sports", "https://w7.pngwing.com/pngs/758/250/png-transparent-megaport-pc-gamer-amd-fx-6100-gaming-computer-desktop-computers-computer-cases-housings-computer-electronics-computer-laptop-thumbnail.png"));
        categories.add(new Category("Cricket", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4046e711jpg?alt=media&token=3ae9f22f-e1a2-41f9-a2d8-3727614e2d37"));
        categories.add(new Category("Weight Lifting", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40a8f0d28jpg?alt=media&token=627ede70-193e-4a61-be75-58d8e92d2df9"));
        categories.add(new Category("Football", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40fd995a3jpg?alt=media&token=342a7630-076d-46ff-8fad-abc3a6a746fe"));
        categories.add(new Category("Badminton", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40d6ad56fjpg?alt=media&token=514a74e3-3317-4042-8941-4ae539050fbd"));
        categories.add(new Category("Hockey", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4052f5c71jpg?alt=media&token=b9e4651a-1cd1-4c48-b599-564c6bb5e6fd"));
        categories.add(new Category("Indoor Games", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40726c91fjpg?alt=media&token=7692ec07-1819-489a-9687-58e76ec26a9e"));
        categories.add(new Category("Mobile games", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40f0fde75jpg?alt=media&token=4ff10ec1-3312-4418-ac74-a58be7454cdd"));
//

        categoryAdapter = new CategoryAdapter(this, categories);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);

    }

    //Product
    private void initProducts() {
        products = new ArrayList<>();
        //Sample data

//        Intent intent;
//        intent=new Intent(HomePage.this,ProductDetails.class);
//        intent.putExtra()


        products.add(new Product("Leather Ball", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%407077dc3jpg?alt=media&token=ccd5187c-9a0b-450a-ab97-48faa218adb1", "sd", 999, 12, 1));
        products.add(new Product("Helmet", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%409e8a256jpg?alt=media&token=f1b2e6f4-7d5b-4ff5-bcaf-af8a37a4afcc", "sd", 999, 12, 1));
        products.add(new Product("Football", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%403bf6848jpg?alt=media&token=4b266dea-8783-4c41-afde-72af4b35bb1c", "sd", 1200, 12, 11));
        products.add(new Product("Football ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%40808a84ajpg?alt=media&token=31cb4e21-e418-4524-9775-2f96db48074a", "", 999, 12, 4));
        products.add(new Product("Keeper Gloves", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40870c634jpg?alt=media&token=305eb3ff-343c-4ed3-b591-57b4a2223b96", "sd", 999, 12, 1));
        products.add(new Product("Cricket Pads", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%404957ac6jpg?alt=media&token=223ec099-d027-4a08-90dc-663a2c06f205", "", 999, 12, 4));
        products.add(new Product("Cricket Shoes", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40de6036jpg?alt=media&token=e106f9e0-3ee3-4f2a-9eec-beaea912c837", "sd", 999, 12, 1));
        products.add(new Product("Football Gloves", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%407b335a7jpg?alt=media&token=97ea0f40-fa69-483f-89b7-823d0e11341c", "sd", 999, 12, 1));
        products.add(new Product("Hockey stick", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40c7e374ajpg?alt=media&token=2fd846d1-764b-4b61-a54e-024f6ca1e2ab", "sd", 999, 12, 1));
        products.add(new Product("Cricket Shirt", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%404c09aaajpg?alt=media&token=88df6138-5e3b-4ccc-91dc-59f54cbab3c2", "sd", 999, 12, 1));
        products.add(new Product("Football Helmet", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4091c3466jpg?alt=media&token=41aa242e-bf5f-400b-b038-59b563093c9f", "sd", 999, 12, 1));
        products.add(new Product("Hockey gloves ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4027d3b9ejpg?alt=media&token=5fa045c5-22f7-43b0-87af-d4cce61d068e", "sd", 999, 12, 1));
        products.add(new Product("Football boot", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40c58a87fjpg?alt=media&token=dc1a72a6-54e0-4d76-acfa-5cd65c8d2976", "sd", 999, 12, 1));
        products.add(new Product("weight lifting Dumbles ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%408fe41bbjpg?alt=media&token=24b260a9-d73f-48ad-b14b-1e7a7f35ddfd", "sd", 999, 12, 1));
        products.add(new Product("Football Soccer Ball", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40459dc90jpg?alt=media&token=c5c401eb-0110-43cc-bc81-9478b88991d7", "sd", 999, 12, 1));
        products.add(new Product("weight lifting Cable machine", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40267ed4bjpg?alt=media&token=ae193073-9501-4642-bf63-976064e0a1ca", "sd", 999, 12, 1));
        products.add(new Product("Badminton Shuttlecock", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4049c3ab4jpg?alt=media&token=618c99b5-846e-4c92-a95f-436adb935337", "sd", 999, 12, 2));
        products.add(new Product(" Weightlifting Belt", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40ed73003jpg?alt=media&token=368df829-49c6-41b0-b7cd-2ea89d9b18fc", "sd", 999, 12, 3));
        products.add(new Product("Badminton Racket", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40b6cc44ajpg?alt=media&token=f0844338-9a86-41cd-aa05-b18b810671a0", "sd", 999, 12, 6));
        products.add(new Product("Chess Board ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40fee936ejpg?alt=media&token=68f687dc-bec9-4bc9-9fb2-8d25f553373b", "sd", 999, 12, 4));
        products.add(new Product("Tennis racket", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40d73830jpg?alt=media&token=d6f28d92-0f68-409b-8643-fa1447c52e14", "sd", 999, 12, 11));
        products.add(new Product("Carom Board ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40806d20djpg?alt=media&token=d190d5e6-7b66-4034-a511-b4d3556d350b", "sd", 999, 12, 31));
        products.add(new Product("Tennis Ball", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40b4b4e78jpg?alt=media&token=7fb00fdc-a53d-4f10-a43e-2d2b97e4f418", "sd", 999, 12, 11));
        products.add(new Product("Video Game Controller ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40318cdd3jpg?alt=media&token=a5347507-82d9-48b7-96d6-9cffadeb569f", "sd", 999, 12, 1));
        products.add(new Product("Volleyball ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%404c40bdajpg?alt=media&token=5c93bf10-8feb-49d1-9450-d26338ad3c11", "sd", 999, 12, 1));
        products.add(new Product("Video Game Microsoft Xbox 360", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40e4aea47jpg?alt=media&token=c0d25f84-815c-4f42-9235-4c8e6d2ba344", "sd", 999, 12, 1));
        products.add(new Product("Volleyball Knee pad", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4090dabf3jpg?alt=media&token=1df0bdec-52da-4471-83e4-b04ef42ffcb0", "sd", 999, 12, 1));

//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/category/1677932600939.png", "s", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/category/1677932600939.png", "s", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "", "s", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/category/1677932600939.png", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/category/1677932600939.png", "s", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/category/1677932600939.png", "s", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/category/1677932600939.png", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 1, 1));

        products.add(new Product("Football ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40fd995a3jpg?alt=media&token=342a7630-076d-46ff-8fad-abc3a6a746fe", "", 999.00, 12.5, 4));
        products.add(new Product("Leather Ball", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%407077dc3jpg?alt=media&token=ccd5187c-9a0b-450a-ab97-48faa218adb1", "", 999.00, 12.5, 11));
        products.add(new Product("Helmet", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%409e8a256jpg?alt=media&token=f1b2e6f4-7d5b-4ff5-bcaf-af8a37a4afcc", "sd", 999.00, 1.2, 1));
        products.add(new Product("Football", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%403bf6848jpg?alt=media&token=4b266dea-8783-4c41-afde-72af4b35bb1c", "sd", 1200.00, 12.3,  1));
        products.add(new Product("Football ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%40808a84ajpg?alt=media&token=31cb4e21-e418-4524-9775-2f96db48074a", "", 999, 12.8, 4));
        products.add(new Product("Keeper Gloves", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40870c634jpg?alt=media&token=305eb3ff-343c-4ed3-b591-57b4a2223b96", "sd", 999, 12, 11));
        products.add(new Product("Cricket Pads", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%404957ac6jpg?alt=media&token=223ec099-d027-4a08-90dc-663a2c06f205", "", 999, 1.52, 43));
        products.add(new Product("Cricket Shoes", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40de6036jpg?alt=media&token=e106f9e0-3ee3-4f2a-9eec-beaea912c837", "sd", 999, 76.12, 31));
        products.add(new Product("Football Gloves", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%407b335a7jpg?alt=media&token=97ea0f40-fa69-483f-89b7-823d0e11341c", "sd", 999, 8.2, 41));
        products.add(new Product("Hockey stick", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40c7e374ajpg?alt=media&token=2fd846d1-764b-4b61-a54e-024f6ca1e2ab", "sd", 999, 50.8, 54));
        products.add(new Product("Cricket Shirt", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%404c09aaajpg?alt=media&token=88df6138-5e3b-4ccc-91dc-59f54cbab3c2", "sd", 999, 12, 15));
        products.add(new Product("Football Helmet", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4091c3466jpg?alt=media&token=41aa242e-bf5f-400b-b038-59b563093c9f", "sd", 999, 12, 11));
        products.add(new Product("Hockey gloves ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4027d3b9ejpg?alt=media&token=5fa045c5-22f7-43b0-87af-d4cce61d068e", "sd", 999, 12, 11));
        products.add(new Product("Football boot", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40c58a87fjpg?alt=media&token=dc1a72a6-54e0-4d76-acfa-5cd65c8d2976", "sd", 999, 12, 11));
        products.add(new Product("weight lifting Dumbles ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%408fe41bbjpg?alt=media&token=24b260a9-d73f-48ad-b14b-1e7a7f35ddfd", "sd", 999, 12,1));
        products.add(new Product("Football Soccer Ball", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40459dc90jpg?alt=media&token=c5c401eb-0110-43cc-bc81-9478b88991d7", "sd", 999, 12,  1));
        products.add(new Product("weight lifting Cable machine", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40267ed4bjpg?alt=media&token=ae193073-9501-4642-bf63-976064e0a1ca", "sd", 999, 12,  1));
        products.add(new Product("Badminton Shuttlecock", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4049c3ab4jpg?alt=media&token=618c99b5-846e-4c92-a95f-436adb935337", "sd", 999, 12,  1));
        products.add(new Product(" Weightlifting Belt", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40ed73003jpg?alt=media&token=368df829-49c6-41b0-b7cd-2ea89d9b18fc", "sd", 999, 12,  1));
        products.add(new Product("Badminton Racket", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40b6cc44ajpg?alt=media&token=f0844338-9a86-41cd-aa05-b18b810671a0", "sd", 999, 12,  1));
        products.add(new Product("Chess Board ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40fee936ejpg?alt=media&token=68f687dc-bec9-4bc9-9fb2-8d25f553373b", "sd", 999, 12,  1));
        products.add(new Product("Tennis racket", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40d73830jpg?alt=media&token=d6f28d92-0f68-409b-8643-fa1447c52e14", "sd", 999, 12,  1));
        products.add(new Product("Carom Board ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40806d20djpg?alt=media&token=d190d5e6-7b66-4034-a511-b4d3556d350b", "sd", 999, 12,  1));
        products.add(new Product("Tennis Ball", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40b4b4e78jpg?alt=media&token=7fb00fdc-a53d-4f10-a43e-2d2b97e4f418", "sd", 999, 12,  1));
        products.add(new Product("Video Game Controller ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40318cdd3jpg?alt=media&token=a5347507-82d9-48b7-96d6-9cffadeb569f", "sd", 999, 12,  1));
        products.add(new Product("Volleyball ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%404c40bdajpg?alt=media&token=5c93bf10-8feb-49d1-9450-d26338ad3c11", "sd", 999, 12, 1));
        products.add(new Product("Video Game Microsoft Xbox 360", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40e4aea47jpg?alt=media&token=c0d25f84-815c-4f42-9235-4c8e6d2ba344", "sd", 999, 12,  1));
        products.add(new Product("Volleyball Knee pad", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4090dabf3jpg?alt=media&token=1df0bdec-52da-4471-83e4-b04ef42ffcb0", "sd", 999, 12,  1));
//
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 11));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/product/1678003366265.jpg", "sd", 1200, 12, 16));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/category/1677932600939.png", "s", 1200, 12, 14));
//        products.add(new Product("Korean Short Cowboy Outwear", "https://tutorials.mianasad.com/ecommerce/uploads/category/1677932600939.png", "s", 1200, 12, 21));

//
//        products.add(new Product("Football ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40fd995a3jpg?alt=media&token=342a7630-076d-46ff-8fad-abc3a6a746fe", "", 999, 12, 4, 1));
//        products.add(new Product("Leather Ball", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%407077dc3jpg?alt=media&token=ccd5187c-9a0b-450a-ab97-48faa218adb1", "sd", 999, 12, 1, 1));
//        products.add(new Product("Helmet", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%409e8a256jpg?alt=media&token=f1b2e6f4-7d5b-4ff5-bcaf-af8a37a4afcc", "sd", 999, 12, 1, 1));
//        products.add(new Product("Football", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%403bf6848jpg?alt=media&token=4b266dea-8783-4c41-afde-72af4b35bb1c", "sd", 1200, 12, 1, 1));
//        products.add(new Product("Football ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Product%2F%5BB%40808a84ajpg?alt=media&token=31cb4e21-e418-4524-9775-2f96db48074a", "", 999, 12, 4, 1));
//        products.add(new Product("Keeper Gloves", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40870c634jpg?alt=media&token=305eb3ff-343c-4ed3-b591-57b4a2223b96", "sd", 999, 12, 1, 1));
//        products.add(new Product("Cricket Pads", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%404957ac6jpg?alt=media&token=223ec099-d027-4a08-90dc-663a2c06f205", "", 999, 12, 4, 1));
//        products.add(new Product("Cricket Shoes", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40de6036jpg?alt=media&token=e106f9e0-3ee3-4f2a-9eec-beaea912c837", "sd", 999, 12, 1, 1));
//        products.add(new Product("Football Gloves", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%407b335a7jpg?alt=media&token=97ea0f40-fa69-483f-89b7-823d0e11341c", "sd", 999, 12, 1, 1));
//        products.add(new Product("Hockey stick", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40c7e374ajpg?alt=media&token=2fd846d1-764b-4b61-a54e-024f6ca1e2ab", "sd", 999, 12, 1, 1));
//        products.add(new Product("Cricket Shirt", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%404c09aaajpg?alt=media&token=88df6138-5e3b-4ccc-91dc-59f54cbab3c2", "sd", 999, 12, 1, 1));
//        products.add(new Product("Football Helmet", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4091c3466jpg?alt=media&token=41aa242e-bf5f-400b-b038-59b563093c9f", "sd", 999, 12, 1, 1));
//        products.add(new Product("Hockey gloves ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4027d3b9ejpg?alt=media&token=5fa045c5-22f7-43b0-87af-d4cce61d068e", "sd", 999, 12, 1, 1));
//        products.add(new Product("Football boot", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40c58a87fjpg?alt=media&token=dc1a72a6-54e0-4d76-acfa-5cd65c8d2976", "sd", 999, 12, 1, 1));
//        products.add(new Product("weight lifting Dumbles ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%408fe41bbjpg?alt=media&token=24b260a9-d73f-48ad-b14b-1e7a7f35ddfd", "sd", 999, 12, 1, 1));
//        products.add(new Product("Football Soccer Ball", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40459dc90jpg?alt=media&token=c5c401eb-0110-43cc-bc81-9478b88991d7", "sd", 999, 12, 1, 1));
//        products.add(new Product("weight lifting Cable machine", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40267ed4bjpg?alt=media&token=ae193073-9501-4642-bf63-976064e0a1ca", "sd", 999, 12, 1, 1));
//        products.add(new Product("Badminton Shuttlecock", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4049c3ab4jpg?alt=media&token=618c99b5-846e-4c92-a95f-436adb935337", "sd", 999, 12, 1, 1));
//        products.add(new Product(" Weightlifting Belt", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40ed73003jpg?alt=media&token=368df829-49c6-41b0-b7cd-2ea89d9b18fc", "sd", 999, 12, 1, 1));
//        products.add(new Product("Badminton Racket", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40b6cc44ajpg?alt=media&token=f0844338-9a86-41cd-aa05-b18b810671a0", "sd", 999, 12, 1, 1));
//        products.add(new Product("Chess Board ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40fee936ejpg?alt=media&token=68f687dc-bec9-4bc9-9fb2-8d25f553373b", "sd", 999, 12, 1, 1));
//        products.add(new Product("Tennis racket", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40d73830jpg?alt=media&token=d6f28d92-0f68-409b-8643-fa1447c52e14", "sd", 999, 12, 1, 1));
//        products.add(new Product("Carom Board ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40806d20djpg?alt=media&token=d190d5e6-7b66-4034-a511-b4d3556d350b", "sd", 999, 12, 1, 1));
//        products.add(new Product("Tennis Ball", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40b4b4e78jpg?alt=media&token=7fb00fdc-a53d-4f10-a43e-2d2b97e4f418", "sd", 999, 12, 1, 1));
//        products.add(new Product("Video Game Controller ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40318cdd3jpg?alt=media&token=a5347507-82d9-48b7-96d6-9cffadeb569f", "sd", 999, 12, 1, 1));
//        products.add(new Product("Volleyball ", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%404c40bdajpg?alt=media&token=5c93bf10-8feb-49d1-9450-d26338ad3c11", "sd", 999, 12, 1, 1));
//        products.add(new Product("Video Game Microsoft Xbox 360", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%40e4aea47jpg?alt=media&token=c0d25f84-815c-4f42-9235-4c8e6d2ba344", "sd", 999, 12, 1, 1));
//        products.add(new Product("Volleyball Knee pad", "https://firebasestorage.googleapis.com/v0/b/sport-bazaar-80ef1.appspot.com/o/Categories%2F%5BB%4090dabf3jpg?alt=media&token=1df0bdec-52da-4471-83e4-b04ef42ffcb0", "sd", 999, 12, 1, 1));

//*/
//        databaseReference = FirebaseDatabase.getInstance().getReference("Product");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        products= new ArrayList<>();
//
//        productAdapter = new ProductAdapter(this, products);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Product product = dataSnapshot.getValue(Product.class);
//                    products.add(product);
//                }
//                productAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//

        productAdapter = new ProductAdapter(this, products);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
    }

}