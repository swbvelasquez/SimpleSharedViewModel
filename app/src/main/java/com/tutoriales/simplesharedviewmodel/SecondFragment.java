package com.tutoriales.simplesharedviewmodel;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutoriales.simplesharedviewmodel.databinding.FragmentFirstBinding;
import com.tutoriales.simplesharedviewmodel.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private FragmentSecondBinding binding;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        //Para personalizar las acciones del back press, sin embargo genera una llamada adicional, revisar en que casos usar
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                sharedViewModel.setCountry(binding.editTextCountry.getText().toString());
                setEnabled(false);
            }
        };

        //si usas viewLifeOwner se va a estar llamando a cada rato, por eso solo usamos this para indicar que
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);
        */
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater,container,false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getCountry().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textViewCountry.setText(s);
            }
        });

        binding.editTextCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    sharedViewModel.setCountry(s.toString());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
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