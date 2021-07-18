package com.happy.Reptile_Store.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.happy.Reptile_Store.databinding.ShopSuppliesFragmentBinding;

public class ShopSuppliesFragment extends Fragment {
    private ShopSuppliesViewModel shopSuppliesViewModel;
    private ShopSuppliesFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopSuppliesViewModel = new ViewModelProvider(this).get(ShopSuppliesViewModel.class);

        binding = ShopSuppliesFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textShopSupplies;
        shopSuppliesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
