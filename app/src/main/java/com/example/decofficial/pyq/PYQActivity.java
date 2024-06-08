package com.example.decofficial.pyq;

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
import com.example.decofficial.ebook.EbookActivity;
import com.example.decofficial.ebook.EbookData;
import com.example.decofficial.ebook.ebookAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PYQActivity extends AppCompatActivity {

    private RecyclerView pyqRecycler;
    private DatabaseReference reference;
    private List<pyqData> list;
    private pyqAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pyqactivity);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        pyqRecycler = findViewById(R.id.pyq_recycler);
        reference = FirebaseDatabase.getInstance().getReference().child("PYQ");

        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    pyqData data = dataSnapshot.getValue(pyqData.class);
                    list.add(data);
                }
                adapter = new pyqAdapter(PYQActivity.this, list);
                pyqRecycler.setLayoutManager(new LinearLayoutManager(PYQActivity.this));
                pyqRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PYQActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}