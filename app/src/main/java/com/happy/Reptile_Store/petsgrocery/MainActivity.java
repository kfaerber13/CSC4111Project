package com.happy.Reptile_Store.petsgrocery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;
    List<Product> products;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.products_recycler);
        productsAdapter = new ProductsAdapter(this, new ArrayList<Product>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productsAdapter);
        
        initProducts();
        productsAdapter.setProductsList(products);

        //holds listener for clicking the notification item
        productsAdapter.setOnItemClickListener(position -> {
            //gets index of item pressed, then send it to its target
            viewItemDetails(position);
        });

        Constants.cartItems = new ArrayList<>();
    }

    private void initProducts() {
        List<Integer> pedigree = new ArrayList<>();
        pedigree.add(R.drawable.dog_food1);
        pedigree.add(R.drawable.dog_food11);
        pedigree.add(R.drawable.dog_food12);
        pedigree.add(R.drawable.dog_food13);

        List<Integer> nulo = new ArrayList<>();
        nulo.add(R.drawable.dog_food2);
        nulo.add(R.drawable.dog_food21);
        nulo.add(R.drawable.dog_food22);

        List<Integer> royal = new ArrayList<>();
        royal.add(R.drawable.cat_food);
        royal.add(R.drawable.cat_food11);

        List<Integer> alpha = new ArrayList<>();
        alpha.add(R.drawable.dog_food3);
        alpha.add(R.drawable.dog_food31);
        alpha.add(R.drawable.dog_food32);

        List<Integer> artu = new ArrayList<>();
        artu.add(R.drawable.dog_food4);

        List<Integer> care = new ArrayList<>();
        care.add(R.drawable.pets_care);
        care.add(R.drawable.pets_care11);

        products = new ArrayList<>();
        products.add(new Product("Pedigree", "Pedigree Adult Dry Dog Food, Chicken & Vegetables, 3kg Pack", 20.99, R.drawable.dog_food1, pedigree, "3 Kg"));
        products.add(new Product("nulo", "Nulo Adult Dog Food: Grain Free, All Natural Dry Pet Kibble for Large and Small Breed Dogs - Lamb, Salmon, or Turkey Recipe", 12.49, R.drawable.dog_food2, nulo, "500 g"));
        products.add(new Product("Royal Canin", "Royal Canin Indoor dry cat food is targeted nutrition made for those lovably lazy housecats to live a long, magnificent life. Moderate calorie levels help maintain a healthy weight. Optimal fibers control hairballs", 50.99, R.drawable.cat_food, royal, "1 Unit"));
        products.add(new Product("Alpha", "Alpha Sporting Puppy is a complete hypoallergenic 'wheat gluten free' food, nutritionally formulated to provide puppies of sporting, working and racing breeds with the essential ingredients for the best possible start in life.", 70.49, R.drawable.dog_food3, alpha, "2 Kg"));
        products.add(new Product("ARTU", "Artù is a range of dry and wet food that Monge developed to assure your four-legged friends a daily diet that fits in with the family routine. The superb quality is guaranteed by the company's long experience in Italy producing the full Artù range of tasty, easy-to-digest recipes that are colouring and additive-free.", 120.24, R.drawable.dog_food4, artu, "4 Kg"));
        products.add(new Product("Pets Care", "Brushes, Combs, Clippers & Nail Care\n" +
                "Whether your dog's hair is short, long, wiry or smooth, every dog benefits from regular grooming. After a thorough wash with a good shampoo, the right brushes and combs can make the grooming faster and easier for both you and your pet. Find the best tools for your dog's needs.", 200.99, R.drawable.pets_care, care, "1 Package"));
    }

    private void viewItemDetails(int position) {
        //Toast.makeText(this, String.format("Item is Clicked: %s", products.get(position).getName()), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("productObj", products.get(position));
        startActivity(intent);
    }

    public void openCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}