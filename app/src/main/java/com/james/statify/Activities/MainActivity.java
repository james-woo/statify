package com.james.statify.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.james.statify.Adapters.ProductAdapter;
import com.james.statify.Models.Product;
import com.james.statify.R;
import com.james.statify.Utils.JsonUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> products;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private TextView totalRevenue;
    private AutoCompleteTextView searchProduct;

    private TextView quantityTV;
    private TextView productTV;
    private TextView revenueTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_products);
        totalRevenue = (TextView) findViewById(R.id.total_revenue);
        searchProduct = (AutoCompleteTextView) findViewById(R.id.search_products);

        quantityTV = (TextView) findViewById(R.id.quantity_title);
        quantityTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByQuantity();
            }
        });
        productTV = (TextView) findViewById(R.id.product_title);
        productTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByProduct();
            }
        });
        revenueTV = (TextView) findViewById(R.id.revenue_title);
        revenueTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortByRevenue();
            }
        });

        new GetProducts().execute();
    }

    private void sortByQuantity() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product lhs, Product rhs) {
                return Product.compare(lhs, rhs, 0);
            }
        });
        refreshRecyclerView();
    }

    private void sortByProduct() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product lhs, Product rhs) {
                return Product.compare(lhs, rhs, 1);
            }
        });
        refreshRecyclerView();
    }

    private void sortByRevenue() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product lhs, Product rhs) {
                return Product.compare(lhs, rhs, 2);
            }
        });
        refreshRecyclerView();
    }

    private void refreshRecyclerView() {
        adapter = new ProductAdapter(products, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private class GetProducts extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            products = JsonUtil.getProducts();
            return null;
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onPostExecute(Void result) {
            refreshRecyclerView();

            ArrayList<String> productNames = new ArrayList<>();
            double total = 0;
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                total += p.getRevenue();
                productNames.add(p.getName());
            }

            totalRevenue.setText(String.format(Locale.getDefault(), "$%.2f", total));

            searchProduct.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                                                        R.layout.search_row_layout,
                                                        R.id.search_row,
                                                        productNames));
            searchProduct.setThreshold(1);

            searchProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchProduct.setText("");
                }
            });

            searchProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selection = (String)parent.getItemAtPosition(position);
                    adapter.setSelected(selection);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
