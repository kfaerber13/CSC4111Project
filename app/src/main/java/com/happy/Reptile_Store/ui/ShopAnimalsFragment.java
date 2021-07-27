package com.happy.Reptile_Store.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.happy.Reptile_Store.databinding.ShopAnimalsFragmentBinding;

public class ShopAnimalsFragment extends  Fragment {
    private ShopAnimalsViewModel shopAnimalsViewModel;
    private ShopAnimalsFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopAnimalsViewModel = new ViewModelProvider(this).get(ShopAnimalsViewModel.class);

        binding = ShopAnimalsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textShopAnimals;
        shopAnimalsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
