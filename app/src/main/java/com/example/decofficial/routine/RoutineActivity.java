package com.example.decofficial.routine;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decofficial.R;
import com.example.decofficial.syllabus.SyllabusActivity;
import com.example.decofficial.syllabus.syllabusAdapter;
import com.example.decofficial.syllabus.syllabusData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoutineActivity extends AppCompatActivity {

    private RecyclerView routineRecycler;
    private DatabaseReference reference;
    private List<routineData> list;
    private routineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_routine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        routineRecycler = findViewById(R.id.routine_recycler);
        reference = FirebaseDatabase.getInstance().getReference().child("Routine");

        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    routineData data = dataSnapshot.getValue(routineData.class);
                    list.add(data);
                }
                adapter = new routineAdapter(list, RoutineActivity.this);
                routineRecycler.setLayoutManager(new LinearLayoutManager(RoutineActivity.this));
                routineRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(RoutineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}