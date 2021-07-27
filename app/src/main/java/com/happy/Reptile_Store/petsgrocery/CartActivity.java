package com.happy.Reptile_Store.petsgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView emptyText;
    CartsAdapter cartsAdapter;
    List<Product> cartsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setTitle("My Cart");  // provide compatibility to all the versions

        initRecycler();
    }

    private void initRecycler() {

        emptyText = (TextView) findViewById(R.id.empty_tv);
        recyclerView = findViewById(R.id.cart_recycler);
        cartsAdapter = new CartsAdapter(this, new ArrayList<Product>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartsAdapter);

        initProducts();
        cartsAdapter.setCartsList(cartsList);

        //holds listener for clicking the notification item
        //gets index of item pressed, then send it to its target
        cartsAdapter.setOnItemClickListener(this::viewItemDetails);

        checkVisibility();
        setupSwipe();
    }


    private void setupSwipe(){

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

                int position = viewHolder.getAdapterPosition();
                Constants.cartItems.remove(position);
                cartsAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Item is Deleted Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(CartActivity.this, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(CartActivity.this, R.color.red))
                        .addActionIcon(R.drawable.ic_delete)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);
    }


    private void checkVisibility() {
        if(cartsList.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            emptyText.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.GONE);
        }
    }

    private void initProducts() {
        cartsList = Constants.cartItems;
    }

    private void viewItemDetails(int position) {
        //Toast.makeText(this, String.format("Item is Clicked: %s", products.get(position).getName()), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("productObj", cartsList.get(position));
        startActivity(intent);
    }
}