package com.example.decofficial.ui.notices;

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

import com.example.decofficial.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NoticesViewModel extends RecyclerView.Adapter<NoticesViewModel.NoticesModelAdapter> {

    private final MutableLiveData<String> mText;

    private Context context;
    private ArrayList<NoticesData> list;

    public NoticesViewModel(Context context, ArrayList<NoticesData> list) {
        mText = new MutableLiveData<>();
        mText.setValue("This is notices fragment");

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticesModelAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item, parent, false);
        return new NoticesModelAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticesModelAdapter holder, int position) {

        final NoticesData currentItem = list.get(position);

        holder.noticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());


        try {
            if (currentItem.getImage() != null)
                Picasso.get().load(currentItem.getImage()).into(holder.noticeImage);
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

    public class NoticesModelAdapter extends RecyclerView.ViewHolder {

        private TextView noticeTitle, date, time;
        private ImageView noticeImage;

        public NoticesModelAdapter(@NonNull View itemView) {
            super(itemView);
            noticeTitle = itemView.findViewById(R.id.notice_title);
            noticeImage = itemView.findViewById(R.id.notice_image);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}