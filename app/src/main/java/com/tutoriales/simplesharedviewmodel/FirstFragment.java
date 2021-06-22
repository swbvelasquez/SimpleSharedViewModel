package com.tutoriales.simplesharedviewmodel;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.navigation.NavigationView;
import com.tutoriales.simplesharedviewmodel.databinding.FragmentFirstBinding;

import java.util.Objects;


public class FirstFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private FragmentFirstBinding binding;
    private DialogProgressBarMainFragment progressDialog;
    private NavController navController;

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

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedViewModel.setCountry(binding.editTextCountry.getText().toString());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //navController = Navigation.findNavController(view);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getCountry().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textViewCountry.setText(s);
            }
        });

        sharedViewModel.getTaskStatus().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    showProgressDialog();
                }else{
                    hideProgressDialog();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }

    private void showProgressDialog(){
        try{
            FragmentManager manager = requireActivity().getSupportFragmentManager();
            progressDialog = (DialogProgressBarMainFragment) manager.findFragmentByTag(DialogProgressBarMainFragment.tag);
            if(progressDialog == null){
                progressDialog = new DialogProgressBarMainFragment();
            }
            progressDialog.show(manager,DialogProgressBarMainFragment.tag);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void hideProgressDialog(){
        if(progressDialog!=null && Objects.requireNonNull(progressDialog.getDialog()).isShowing()){
            progressDialog.dismiss();
        }
    }
}