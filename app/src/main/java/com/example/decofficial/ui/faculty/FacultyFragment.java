package com.example.decofficial.ui.faculty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.decofficial.databinding.FragmentDashboardBinding;
import com.example.decofficial.R;
import com.example.decofficial.databinding.FragmentNoticesBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

//    private FragmentNoticesBinding binding;

    private RecyclerView csDepartment, meDepartment, ceDepartment, mathsDepartment, humanitiesDepartment;
    private LinearLayout csNoData, meNoData, ceNoData, mathsNoData, humanitiesNoData;
    private List<FacultyData> list1, list2, list3, list4, list5;
    private FacultyViewModel facultyViewModel;
//    private ProgressBar progressBar;
    private DatabaseReference databaseReference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_faculty, container, false);
//        FacultyViewModel facultyViewModel =
//                new ViewModelProvider(this).get(FacultyViewModel.class);

//        binding = FragmentDashboardBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
//        facultyViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;


        csDepartment = view.findViewById(R.id.cs_department);
        meDepartment = view.findViewById(R.id.me_department);
        ceDepartment = view.findViewById(R.id.ce_department);
        mathsDepartment = view.findViewById(R.id.maths_department);
        humanitiesDepartment = view.findViewById(R.id.humanities_department);

        csNoData = view.findViewById(R.id.cs_no_data);
        meNoData = view.findViewById(R.id.me_no_data);
        ceNoData = view.findViewById(R.id.ce_no_data);
        mathsNoData = view.findViewById(R.id.maths_no_data);
        humanitiesNoData = view.findViewById(R.id.humanities_no_data);

//        progressBar = view.findViewById(R.id.progress_bar);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("teacher");

        csDepartment();
        meDepartment();
        ceDepartment();
        mathsDepartment();
        humanitiesDepartment();


        return view;
    }

    private void humanitiesDepartment()  {
        databaseReference = databaseReference.child("Humanities");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list5 = new ArrayList<>();
                if (!snapshot.exists()){
                    humanitiesDepartment.setVisibility(View.VISIBLE);
                    humanitiesNoData.setVisibility(View.GONE);
                }
                else {
                    humanitiesNoData.setVisibility(View.GONE);
                    humanitiesDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list5.add(data);
                    }
                    humanitiesDepartment.setHasFixedSize(true);
                    humanitiesDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyViewModel = new FacultyViewModel(list5, getContext());
                    humanitiesDepartment.setAdapter(facultyViewModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mathsDepartment() {

        databaseReference = databaseReference.child("Mathematics");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if (!snapshot.exists()){
                    mathsDepartment.setVisibility(View.VISIBLE);
                    mathsNoData.setVisibility(View.GONE);
                }
                else {
                    mathsNoData.setVisibility(View.GONE);
                    mathsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list4.add(data);
                    }
                    mathsDepartment.setHasFixedSize(true);
                    mathsDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyViewModel = new FacultyViewModel(list4, getContext());
                    mathsDepartment.setAdapter(facultyViewModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ceDepartment() {

        databaseReference = databaseReference.child("Civil Engineering");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if (!snapshot.exists()){
                    ceDepartment.setVisibility(View.VISIBLE);
                    ceNoData.setVisibility(View.GONE);
                }
                else {
                    ceNoData.setVisibility(View.GONE);
                    ceDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list3.add(data);
                    }
                    ceDepartment.setHasFixedSize(true);
                    ceDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyViewModel = new FacultyViewModel(list3, getContext());
                    ceDepartment.setAdapter(facultyViewModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void meDepartment() {

        databaseReference = databaseReference.child("Mechanical Engineering");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if (!snapshot.exists()){
                    meDepartment.setVisibility(View.VISIBLE);
                    meNoData.setVisibility(View.GONE);
                }
                else {
                    meNoData.setVisibility(View.GONE);
                    meDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list2.add(data);
                    }
                    meDepartment.setHasFixedSize(true);
                    meDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyViewModel = new FacultyViewModel(list2, getContext());
                    meDepartment.setAdapter(facultyViewModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void csDepartment() {

        databaseReference = databaseReference.child("Computer Science and Engineering");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();
                if (!snapshot.exists()){
                    csDepartment.setVisibility(View.VISIBLE);
                    csNoData.setVisibility(View.GONE);
                }
                else {
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list1.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    facultyViewModel = new FacultyViewModel(list1, getContext());
                    csDepartment.setAdapter(facultyViewModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}