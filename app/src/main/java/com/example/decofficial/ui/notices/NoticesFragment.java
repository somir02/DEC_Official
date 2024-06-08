package com.example.decofficial.ui.notices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decofficial.R;
import com.example.decofficial.databinding.FragmentNoticesBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NoticesFragment extends Fragment {


    private FragmentNoticesBinding binding;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<NoticesData> list;
    private NoticesViewModel noticesViewModel;
    private Query databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        NoticesViewModel noticesViewModel =
//                new ViewModelProvider(this).get(NoticesViewModel.class);

//        binding = FragmentNoticesBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.text;
//        noticesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;

        View view = inflater.inflate(R.layout.fragment_notices, container, false);

        recyclerView = view.findViewById(R.id.notice_recycler);
        progressBar = view.findViewById(R.id.progress_bar);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notice").orderByChild("time");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        
        getNotice();
        return view;
    }

    private void getNotice() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    NoticesData data = dataSnapshot.getValue(NoticesData.class);
                    list.add(data);
                }

                noticesViewModel = new NoticesViewModel(getContext(),list);
                noticesViewModel.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(noticesViewModel);
            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}