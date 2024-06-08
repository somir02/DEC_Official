package com.example.decofficial.routine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decofficial.R;
import com.example.decofficial.ebook.PdfViewerActivity;

import java.util.List;

public class routineAdapter extends RecyclerView.Adapter<routineAdapter.routineViewAdapter> {

    private List<routineData> list;
    private Context context;

    public routineAdapter(List<routineData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public routineAdapter.routineViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.routine_item_layout, parent,false);
        return new routineViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull routineAdapter.routineViewAdapter holder, int position) {

        final routineData data = list.get(position);
        holder.title.setText(data.getPdfTitle());
        holder.Branch.setText(data.getBranch());
        holder.Semester.setText(data.getSemester());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PdfViewerActivity.class);
                intent.putExtra("pdfUrl",list.get(holder.getAdapterPosition()).getPdfUrl());
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

    public class routineViewAdapter extends RecyclerView.ViewHolder {

        private TextView title, Branch, Semester;
        private ImageView download;
        public routineViewAdapter(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.routine_title);
            Branch = itemView.findViewById(R.id.routine_branch);
            Semester = itemView.findViewById(R.id.routine_semester);
            download = itemView.findViewById(R.id.routine_download);
        }
    }
}
