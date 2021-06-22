package com.tutoriales.simplesharedviewmodel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutoriales.simplesharedviewmodel.databinding.FragmentFirstBinding;


public class FirstFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private FragmentFirstBinding binding;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater,container,false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedViewModel.setCountry(binding.editTextCountry.getText().toString());
                Navigation.findNavController(v).navigate(R.id.action_nav_firstFragment_to_nav_secondFragment);
            }
        });

        sharedViewModel.getCountry().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textViewCountry.setText(s);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}