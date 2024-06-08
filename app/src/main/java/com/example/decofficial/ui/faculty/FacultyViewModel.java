package com.example.decofficial.ui.faculty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.decofficial.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FacultyViewModel extends RecyclerView.Adapter<FacultyViewModel.FacultyViewAdapter> {

    private final MutableLiveData<String> mText;

    private List<FacultyData> list;
    private Context context;

    public FacultyViewModel(List<FacultyData> list, Context context) {
        mText = new MutableLiveData<>();
        mText.setValue("This is faculty fragment");

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FacultyViewModel.FacultyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout, parent, false);
        return new FacultyViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewModel.FacultyViewAdapter holder, int position) {
        final FacultyData data = list.get(position);
        holder.name.setText(data.getName());
        holder.email.setText(data.getEmail());
        holder.post.setText(data.getPost());

        try {
            Glide.with(context).load(data.getImage()).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public class FacultyViewAdapter extends RecyclerView.ViewHolder {

        private TextView name, email, post;
        private ImageView imageView;

        public FacultyViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.faculty_name);
            email = itemView.findViewById(R.id.faculty_email);
            post = itemView.findViewById(R.id.faculty_post);
            imageView = itemView.findViewById(R.id.faculty_image);
        }
    }
}