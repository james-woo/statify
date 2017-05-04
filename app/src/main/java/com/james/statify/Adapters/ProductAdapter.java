package com.james.statify.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.james.statify.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
