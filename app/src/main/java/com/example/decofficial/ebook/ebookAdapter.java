package com.example.decofficial.ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decofficial.R;

import java.util.List;

public class ebookAdapter extends RecyclerView.Adapter<ebookAdapter.ebookViewHolder> {

    private Context context;
    private List<EbookData> list;

    public ebookAdapter(Context context, List<EbookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ebookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout,parent, false);
        return new ebookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ebookViewHolder holder, int position) {

        holder.ebookTitle.setText(list.get(holder.getAdapterPosition()).getPdfTitle());
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PdfViewerActivity.class);
                intent.putExtra("pdfUrl",list.get(holder.getAdapterPosition()).getPdfUrl());
                context.startActivity(intent);
            }
        });

        holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
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

    public class ebookViewHolder extends RecyclerView.ViewHolder {

        private TextView ebookTitle;
        private ImageView ebookDownload;

        public ebookViewHolder(@NonNull View itemView) {
            super(itemView);

            ebookDownload = itemView.findViewById(R.id.ebook_download);
            ebookTitle = itemView.findViewById(R.id.ebook_title);
        }
    }

}
