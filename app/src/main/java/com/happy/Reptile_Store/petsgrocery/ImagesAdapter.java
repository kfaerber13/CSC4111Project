package com.happy.Reptile_Store.petsgrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesVH> {

    private static final String TAG = "ImagesAdapter";
    List<Integer> imagesList;
    Context context;

    public ImagesAdapter(Context context, List<Integer> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public ImagesAdapter.ImagesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_image, parent, false);
        return new ImagesAdapter.ImagesVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.ImagesVH holder, int position) {

        int imageCurrent = imagesList.get(position);
        holder.productImage.setImageResource(imageCurrent);
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setImagesList(List<Integer> images) {
        imagesList = images;
        notifyDataSetChanged();
    }

    public List<Integer> getImages(){
        return imagesList;
    }

    public void clearData() {
        imagesList.clear(); // clear list
        notifyDataSetChanged(); // let your adapter know about the changes and reload view.
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    class ImagesVH extends RecyclerView.ViewHolder {

        private static final String TAG = "ImagesVH";

        ImageView productImage;

        public ImagesVH(@NonNull final View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.image_id);
        }
    }
}

