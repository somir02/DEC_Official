package com.example.decofficial.syllabus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decofficial.R;
import com.example.decofficial.ebook.PdfViewerActivity;

import java.util.List;

public class syllabusAdapter extends RecyclerView.Adapter<syllabusAdapter.syllabusViewAdapter> {

    private List<syllabusData> list;
    private Context context;

    public syllabusAdapter(List<syllabusData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public syllabusAdapter.syllabusViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.syllabus_item_layout, parent, false);
        return new syllabusViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull syllabusAdapter.syllabusViewAdapter holder, int position) {

        final syllabusData data = list.get(position);
        holder.title.setText(data.getPdfTitle());
        holder.branch.setText(data.getBranch());
        holder.semester.setText(data.getSemester());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PdfViewerActivity.class);
                intent.putExtra("pdfUrl", list.get(holder.getAdapterPosition()).getPdfUrl());
                context.startActivity(intent);
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(holder.getAdapterPosition()).getPdfUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class syllabusViewAdapter extends RecyclerView.ViewHolder {

        private TextView title, branch, semester;
        private ImageView download;
        public syllabusViewAdapter(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.syllabus_title);
            branch = itemView.findViewById(R.id.syllabus_branch);
            semester = itemView.findViewById(R.id.syllabus_semester);
            download = itemView.findViewById(R.id.syllabus_download);
        }
    }
}
