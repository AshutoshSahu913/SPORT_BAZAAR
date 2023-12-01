package Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportbazaar.ProductDetails;
import com.example.sportbazaar.R;
import com.example.sportbazaar.databinding.ItemCartBinding;
import com.example.sportbazaar.databinding.QuantityDialogBinding;

import java.util.ArrayList;

import Model.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {


    Context context;
    ArrayList<Product> products;

    ImageView imageView;

    public CartAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = products.get(position);

        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.productImage);

        holder.binding.productName.setText(product.getName());
        holder.binding.productPrice.setText("INR " + product.getPrice());
        holder.binding.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductDetails.class);
                intent.putExtra("name", product.getName());
                intent.putExtra("image", product.getImage());

                intent.putExtra("price", product.getPrice());


                context.startActivity(intent);
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                QuantityDialogBinding quantityDialogBinding = QuantityDialogBinding.inflate(LayoutInflater.from(context));

                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setView(quantityDialogBinding.getRoot())
                        .create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));


                quantityDialogBinding.productName.setText(product.getName());
                quantityDialogBinding.productStock.setText("Stock " + product.getStock());

                int stock = product.getStock();


                quantityDialogBinding.plusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                quantityDialogBinding.minusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


                quantityDialogBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        ItemCartBinding binding;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCartBinding.bind(itemView);
        }
    }
}
