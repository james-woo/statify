package com.james.statify.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.james.statify.Adapters.ProductAdapter;
import com.james.statify.Models.Product;
import com.james.statify.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_products);
        products.add(new Product(1, "Keyboard", 12.2F));
        products.add(new Product(2, "Couch", 13.2F));
        products.add(new Product(3, "Knife", 14.2F));

        ProductAdapter adapter = new ProductAdapter(products, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
