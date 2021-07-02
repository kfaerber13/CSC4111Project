package com.happy.Reptile_Store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.happy.Reptile_Store.R;
import com.happy.Reptile_Store.models.User;

import java.util.List;

/*** Created by Rahat Shovo on 5/3/2021 
 */
public class userListAdapter extends RecyclerView.Adapter<userListAdapter.viewholder> {

    private final Context context;
    private List<User> items;
    private ItemClickListener itemClickListener;

    public userListAdapter(List<User> items, Context context, ItemClickListener itemClickListener) {
        this.items = items;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent,
                                  int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        User item = items.get(position);

        holder.phn.setText(item.getPh());
        holder.address.setText(item.getAddress());
        holder.itemView.setOnClickListener(v -> {
            itemClickListener.onItemClick(item);
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user").child(item.getId());
                databaseReference.removeValue() ;
            }
        });

    }


    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class viewholder extends RecyclerView.ViewHolder {

        public  TextView  name , phn  ,address ;
        public  Button deleteButton ;



        viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.username) ;
            phn = itemView.findViewById(R.id.userphone) ;
            address = itemView.findViewById(R.id.userlocation) ;
            deleteButton = itemView.findViewById(R.id.deleteBtn) ;
        }


    }

    public interface ItemClickListener {
        void onItemClick(User model);
    }


}