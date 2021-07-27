package com.happy.Reptile_Store.petsgrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class CartsAdapter extends RecyclerView.Adapter<CartsAdapter.CartsVH> {

    private static final String TAG = "CartsAdapter";
    List<Product> cartsList;
    Context context;

    private CartsAdapter.OnItemClickListener mListener;

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
    public void setOnItemClickListener(CartsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public CartsAdapter(Context context, List<Product> cartsList) {
        this.context = context;
        this.cartsList = cartsList;
    }

    @NonNull
    @Override
    public CartsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_cart, parent, false);
        return new CartsVH(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartsVH holder, int position) {

        Product cart = cartsList.get(position);
        holder.nameTextView.setText(cart.getName());
        holder.productImage.setImageResource(cart.getImgID());
        holder.quantityTextView.setText(cart.getQuantity());
        holder.priceTextView.setText(String.format("%s$", cart.getPrice()));
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setCartsList(List<Product> carts) {
        cartsList = carts;
        notifyDataSetChanged();
    }

    public List<Product> getCarts(){
        return cartsList;
    }

    public void clearData() {
        cartsList.clear(); // clear list
        notifyDataSetChanged(); // let your adapter know about the changes and reload view.
    }

    @Override
    public int getItemCount() {
        return cartsList.size();
    }

    class CartsVH extends RecyclerView.ViewHolder {

        private static final String TAG = "CartsVH";

        TextView nameTextView, quantityTextView, priceTextView;
        ImageView productImage;


        public CartsVH(@NonNull final View itemView, final CartsAdapter.OnItemClickListener listener) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.cart_name_rv);
            productImage = (ImageView) itemView.findViewById(R.id.img_cart_rv);
            quantityTextView = (TextView) itemView.findViewById(R.id.cart_quantity_rv);
            priceTextView = (TextView) itemView.findViewById(R.id.cart_price_rv);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    //gets position of clicked item
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
