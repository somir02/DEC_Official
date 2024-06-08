package com.example.decofficial.pyq;

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
import com.example.decofficial.ebook.EbookData;
import com.example.decofficial.ebook.PdfViewerActivity;
import com.example.decofficial.ebook.ebookAdapter;

import java.util.List;

public class pyqAdapter extends RecyclerView.Adapter<pyqAdapter.pyqViewHolder>{
    private Context context;
    private List<pyqData> list;

    public pyqAdapter(Context context, List<pyqData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public pyqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pyq_item_layout,parent, false);
        return new pyqAdapter.pyqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pyqViewHolder holder, int position) {

//        holder.pyqName.setText(list.get(holder.getAdapterPosition()).getPdfTitle());

        pyqData item = list.get(position);
        holder.pyqName.setText(item.getPdfTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PdfViewerActivity.class);
                intent.putExtra("pdfUrl",list.get(holder.getAdapterPosition()).getPdfUrl());
                context.startActivity(intent);
            }
        });

        holder.pyqDownload.setOnClickListener(new View.OnClickListener() {
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

    public class pyqViewHolder extends RecyclerView.ViewHolder {

        final private TextView pyqName;
        private ImageView pyqDownload;

        public pyqViewHolder(@NonNull View itemView) {
            super(itemView);

            pyqDownload = itemView.findViewById(R.id.pyq_download);
            pyqName = itemView.findViewById(R.id.pyq_name);
        }
    }

}

