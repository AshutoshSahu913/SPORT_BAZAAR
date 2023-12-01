package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportbazaar.CategoryActivity;
import com.example.sportbazaar.R;
import com.example.sportbazaar.databinding.ItemCategoriesBinding;

import java.util.ArrayList;

import Model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;

    private TextView categoryId;
    ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_categories, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        Glide.with(context)
                .load(category.getIcon())
                .into(holder.binding.imageItemCategories);
        holder.binding.labal.setText(category.getName());


        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context, CategoryActivity.class);
            intent.putExtra("CategoryName",category.getName());
            intent.putExtra("categoryIcon", category.getIcon());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(String ignoredIconUrl) {

    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ItemCategoriesBinding binding;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCategoriesBinding.bind(itemView);
            TextView categoryName = itemView.findViewById(R.id.labal);
            ImageView categoryIcon = itemView.findViewById(R.id.image_item_categories);
        }
    }
}
