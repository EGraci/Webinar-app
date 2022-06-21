package com.example.uasrpl.Config;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uasrpl.Dto.EventDto;
import com.example.uasrpl.R;
import com.example.uasrpl.deskripsi;

import java.util.ArrayList;
import java.util.List;

public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.EventSet>{
    private List<EventDto> ls_event = new ArrayList<>();
    private Context ct;
    private String id, nama;
    public AdapterEvent(List<EventDto> events, Context tx, String kd, String nm){
        this.ls_event = events;
        this.ct = tx;
        this.id = kd;
        this.nama = nm;
    }
    @NonNull
    @Override
    public EventSet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(ct);
        View view= layoutInflater.inflate(R.layout.card_event, parent,  false);
        return new EventSet(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventSet holder, int position) {
        Glide.with(ct).load(ls_event.get(position).getGambar()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pindah ke class sampean mau
                Intent intent = new Intent(ct, deskripsi.class);
                intent.putExtra("id_peserta", ls_event.get(position).getIdEvent());
                intent.putExtra("id_event", ls_event.get(position).getIdEvent());
                intent.putExtra("gambar", ls_event.get(position).getGambar());
                intent.putExtra("judul", ls_event.get(position).getJudul());
                intent.putExtra("Deskripsi", ls_event.get(position).getDeskripsi());
                intent.putExtra("pembicara", ls_event.get(position).getPembicara());
                intent.putExtra("jammulai", ls_event.get(position).getJamMulai());
                intent.putExtra("selesai", ls_event.get(position).getJamSelesai());
                ct.startActivity(intent);
//                Toast.makeText(holder.view.getContext(), nama+" "+id, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls_event.size();
    }

    public class EventSet extends RecyclerView.ViewHolder{
        ImageView imageView;
        CardView cardView;
        View view;
        public EventSet(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageEvent);
            cardView = itemView.findViewById(R.id.row_konten);
            view = itemView;
        }

    }
}