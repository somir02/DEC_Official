package com.example.decofficial.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.decofficial.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class GalleryViewModel extends RecyclerView.Adapter<GalleryViewModel.GalleryAdapter> {

    private Context context;
    private List<String> images;

    public GalleryViewModel(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public GalleryAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_layout, parent, false);
        return new GalleryAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter holder, int position) {

//        Picasso.get().load(URL).into(imageView);
        Glide.with(context).load(images.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class GalleryAdapter extends RecyclerView.ViewHolder {

        ImageView imageView;

        public GalleryAdapter(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.g_image);
        }
    }
}
