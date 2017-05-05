package com.james.statify.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.james.statify.Models.Product;
import com.james.statify.R;

import java.util.ArrayList;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private ArrayList<Product> products;
    private Context context;
    private String selected = "";

    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setSelected(String selection) {
        selected = selection;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View productView = inflater.inflate(R.layout.item_product, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Product product = products.get(position);

        // Set item views based on product data model
        TextView sold = viewHolder.soldTextView;
        sold.setText(String.valueOf(product.getQuantity()));

        TextView name = viewHolder.nameTextView;
        name.setText(product.getName());

        TextView revenue = viewHolder.revenueTextView;
        revenue.setText(String.format(Locale.getDefault(), "%.2f", product.getRevenue()));

        if (products.get(position).getName() == selected) {
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            sold.setTextColor(Color.WHITE);
            name.setTextColor(Color.WHITE);
            revenue.setTextColor(Color.WHITE);
        } else {
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
            sold.setTextColor(Color.BLACK);
            name.setTextColor(Color.BLACK);
            revenue.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView soldTextView;
        public TextView nameTextView;
        public TextView revenueTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            soldTextView = (TextView) itemView.findViewById(R.id.product_sold);
            nameTextView = (TextView) itemView.findViewById(R.id.product_name);
            revenueTextView = (TextView) itemView.findViewById(R.id.product_revenue);
        }
    }
}
