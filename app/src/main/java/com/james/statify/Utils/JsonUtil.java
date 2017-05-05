package com.james.statify.Utils;

import com.james.statify.Models.Product;
import com.james.statify.Models.Sale;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonUtil {
    private static final String SHOPICRUIT_BASE_URL = "https://shopicruit.myshopify.com/admin/orders.json?";
    private static final String SHOPICRUIT_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";

    public static ArrayList<Product> getProducts() {
        OkHttpClient client = new OkHttpClient();
        // Store name of product as key, a list of sales as values
        // price of awesome products could change at any point
        HashMap<String, ArrayList<Sale>> orders = new HashMap<>();

        // Only looking at page 1 for now
        Request request = new Request.Builder()
                .url(SHOPICRUIT_BASE_URL + "page=1&access_token=" + SHOPICRUIT_TOKEN)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            String jsonData = response.body().string();
            JSONObject jObject = new JSONObject(jsonData);
            JSONArray ordersArray = jObject.getJSONArray("orders");

            for (int i = 0; i < ordersArray.length(); i++) {
                JSONArray orderLineItems = ordersArray.getJSONObject(i).getJSONArray("line_items");
                for (int j = 0; j < orderLineItems.length(); j++) {
                    JSONObject lineItem = orderLineItems.getJSONObject(j);
                    String name = lineItem.getString("title");
                    int quantity = lineItem.getInt("quantity");
                    double price = lineItem.getDouble("price");
                    if (orders.containsKey(name)) {
                        orders.get(name).add(new Sale(quantity, price));
                    } else {ArrayList<Sale> newSales = new ArrayList<>();
                        newSales.add(new Sale(quantity, price));
                        orders.put(name, newSales);
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ArrayList<Product> products = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Sale>> entry : orders.entrySet()) {
            String product = entry.getKey();
            ArrayList<Sale> sales = entry.getValue();

            int quantity = 0;
            double totalPrice = 0;
            for (Sale sale : sales) {
                quantity += sale.getQuantity();
                totalPrice += sale.getPrice() * sale.getQuantity();
            }

            products.add(new Product(quantity, product, totalPrice));
        }

        return products;
    }
}
