package com.happy.Reptile_Store.petsgrocery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    Product product;
    RecyclerView recyclerView;
    ImagesAdapter imagesAdapter;
    List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        product = (Product) getIntent().getSerializableExtra("productObj");
        getSupportActionBar().setTitle(product.getName());  // provide compatibility to all the versions

        images = product.getImages();

        initRecycler();
        initViews();
    }

    private void initViews() {
        TextView product_name = findViewById(R.id.product_desc);
        product_name.setText(product.getDescription());

        TextView product_quantity = findViewById(R.id.product_quantity);
        product_quantity.setText(product.getQuantity());

        TextView product_price = findViewById(R.id.product_price);
        product_price.setText(String.format("%s$", product.getPrice()));
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.rv_images);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());
        imagesAdapter = new ImagesAdapter(this, new ArrayList<Integer>());
        //object of layoutManager that controls recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.findFirstVisibleItemPosition();

        recyclerView.setLayoutManager(layoutManager);
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(imagesAdapter);

        initImages();

        imagesAdapter.setImagesList(images);
    }

    private void initImages() {
        images = product.getImages();
    }

    public void addToCart(View view) {
        Constants.cartItems.add(product);
        Toast.makeText(this, "Added Successfully!", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}