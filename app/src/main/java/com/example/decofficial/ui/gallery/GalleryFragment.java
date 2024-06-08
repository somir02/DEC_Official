package com.example.decofficial.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decofficial.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GalleryFragment extends Fragment{

    RecyclerView placement_recycler, seminar_recycler, id_recycler, others_recycler, college_week_recycler;
    GalleryViewModel viewModel;

    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        placement_recycler = view.findViewById(R.id.placement_recycler);
        seminar_recycler = view.findViewById(R.id.seminar_recycler);
        id_recycler = view.findViewById(R.id.id_recycler);
        others_recycler = view.findViewById(R.id.other_event_recycler);
        college_week_recycler = view.findViewById(R.id.college_week_recycler);

        reference = FirebaseDatabase.getInstance().getReference().child("gallery");
        
        getSeminarImage();
        
        getPlacementImage();
        
        getidImage();
        
        getOtherEventImage();

        getCollegeWeekImage();
        
        return view;
    }

    private void getCollegeWeekImage() {

        reference.child("College Week").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imageList.add(data);
                }

                viewModel = new GalleryViewModel(getContext(), imageList);
                others_recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                others_recycler.setAdapter(viewModel);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getOtherEventImage() {
        reference.child("Other event").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imageList.add(data);
                }

                viewModel = new GalleryViewModel(getContext(), imageList);
                others_recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                others_recycler.setAdapter(viewModel);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getidImage() {
        reference.child("Independence Day").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imageList.add(data);
                }
                viewModel = new GalleryViewModel(getContext(), imageList);
                id_recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                id_recycler.setAdapter(viewModel);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
    }

    private void getPlacementImage() {

        reference.child("Placement").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imageList.add(data);
                }
                viewModel = new GalleryViewModel(getContext(), imageList);
                id_recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                id_recycler.setAdapter(viewModel);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
    }

    private void getSeminarImage() {
        reference.child("Seminar").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                    imageList.add(data);
                }
                viewModel = new GalleryViewModel(getContext(), imageList);
                id_recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                id_recycler.setAdapter(viewModel);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
