package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportbazaar.ProductDetails;
import com.example.sportbazaar.R;
import com.example.sportbazaar.databinding.ItemProductBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import Model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {
    Context context;
    ArrayList<Product> products;

    private final List<Product> listFul;


    @SuppressLint("NotifyDataSetChanged")
    public void setFilteredList(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
        listFul = new ArrayList<>(products);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = products.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.itemProductImage);
        holder.binding.itemProductTextView.setText(product.getName());
        holder.binding.price.setText("₹ " + product.getPrice());


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.putExtra("productName", product.getName());
            intent.putExtra("image", product.getImage());
            intent.putExtra("productDescription", product.getDescription());
            intent.putExtra("productPrice", product.getPrice());
            intent.putExtra("productDiscount", product.getDiscount());
            intent.putExtra("productStock", product.getStock());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return FilterUser;
    }

    private Filter FilterUser = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchtext = constraint.toString().toLowerCase(Locale.ROOT);
            List<Product> templist = new ArrayList<>();
            if (searchtext.length() == 0 || searchtext.isEmpty()) {
                templist.addAll(listFul);
            } else {
                for (Product item : listFul) {
                    if (item.getName().toLowerCase().contains(searchtext)) {
                        templist.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = templist;
            return filterResults;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll((Collection<? extends Product>) results.values);
            notifyDataSetChanged();
        }
    };


    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public ItemProductBinding binding;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemProductBinding.bind(itemView);
        }
    }
}
