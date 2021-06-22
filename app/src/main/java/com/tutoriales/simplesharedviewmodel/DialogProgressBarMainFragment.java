package com.tutoriales.simplesharedviewmodel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tutoriales.simplesharedviewmodel.databinding.FragmentDialogProgressBarMainBinding;

public class DialogProgressBarMainFragment extends DialogFragment {
    public static final String tag= "DialogProgressFragmentTag";
    private FragmentDialogProgressBarMainBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        binding = FragmentDialogProgressBarMainBinding.inflate(inflater);
        builder.setView(binding.getRoot());
        builder.setCancelable(false);
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
