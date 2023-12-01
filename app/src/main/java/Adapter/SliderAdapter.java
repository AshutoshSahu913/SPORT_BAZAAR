package Adapter;

import android.content.Context;

import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class SliderAdapter {
    Context context;
    ArrayList<Slider> sliders;

    public SliderAdapter(Context context, ArrayList<Slider> sliders) {
        this.context = context;
        this.sliders = sliders;
    }
/*
    @NonNull
    @Override
    public SliderAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderAdapter.CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_categories, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
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
        return sliders.size();
    }

    public void setCategories(String ignoredIconUrl) {

    }

    public class SilderViewHolder extends RecyclerView.ViewHolder {
        ItemCategoriesBinding binding;
        public SilderViewHolderViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCategoriesBinding.bind(itemView);
            TextView categoryName = itemView.findViewById(R.id.labal);
            ImageView categoryIcon = itemView.findViewById(R.id.image_item_categories);

        }
    }*/
}
