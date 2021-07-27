package com.happy.Reptile_Store.petsgrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductVH> {

    private static final String TAG = "ProductsAdapter";
    List<Product> productsList;
    Context context;

    private ProductsAdapter.OnItemClickListener mListener;

    /**
     * interface that handles clicking on items
     */
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    /**
     * handles item clicking
     * @param listener object of listener
     */
    public void setOnItemClickListener(ProductsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public ProductsAdapter(Context context, List<Product> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_product, parent, false);
        return new ProductVH(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {

        Product product = productsList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.productImage.setImageResource(product.getImgID());
        holder.quantityTextView.setText(product.getQuantity());
        holder.priceTextView.setText(String.format("%s$", product.getPrice()));
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setProductsList(List<Product> products) {
        productsList = products;
        notifyDataSetChanged();
    }

    public List<Product> getProducts(){
        return productsList;
    }

    public void clearData() {
        productsList.clear(); // clear list
        notifyDataSetChanged(); // let your adapter know about the changes and reload view.
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class ProductVH extends RecyclerView.ViewHolder {

        private static final String TAG = "ProductsVH";

        TextView nameTextView, quantityTextView, priceTextView;
        ImageView productImage;
        AppCompatButton addBtn;

        public ProductVH(@NonNull final View itemView, final ProductsAdapter.OnItemClickListener listener) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.product_name_rv);
            productImage = (ImageView) itemView.findViewById(R.id.img_product_rv);
            quantityTextView = (TextView) itemView.findViewById(R.id.product_quantity_rv);
            priceTextView = (TextView) itemView.findViewById(R.id.product_price_rv);
            addBtn = (AppCompatButton) itemView.findViewById(R.id.add_cart_rv);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    //gets position of clicked item
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

            addBtn.setOnClickListener(v -> {
                int i = getAdapterPosition();
                Constants.cartItems.add(getProducts().get(i));
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
