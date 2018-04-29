package com.gisela.pointofsales.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gisela.pointofsales.R;
import com.gisela.pointofsales.entity.Barang;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder>{

    private ArrayList<Barang> barangs;
    private BarangDataListener barangDataClickedListener;

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        final Barang barang = getBarangs().get(position);
        String id = barang.getIdBarang();
        String nama = barang.getNamaBarang();
        int hargaJual = barang.getHargaJual();
        int hargaBeli = barang.getHargaBeli();
        int stock = barang.getStock();

        holder.txt_brg_nama.setText(nama);
//        holder.txt_brg_harga_jual.setText(hargaJual);
//        holder.txt_brg_harga_beli.setText(hargaBeli);
//        holder.txt_brg_stock.setText(stock);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(barangDataClickedListener != null){
                    barangDataClickedListener.onBarangClicked(barang);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getBarangs().size();
    }

    public ArrayList<Barang> getBarangs() {
        if(barangs == null){
            barangs = new ArrayList<>();
        }
        return barangs;
    }

    public void setBarangs(ArrayList<Barang> barangs) {
        getBarangs().clear();
        getBarangs().addAll(barangs);
        notifyDataSetChanged();
    }

    public void setBarangDataClickedListener(BarangDataListener barangDataClickedListener) {
        this.barangDataClickedListener = barangDataClickedListener;
    }

    class BarangViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_brg_nama)
        TextView txt_brg_nama;
        @BindView(R.id.text_brg_harga_jual)
        TextView txt_brg_harga_jual;
        @BindView(R.id.text_brg_harga_beli)
        TextView txt_brg_harga_beli;
        @BindView(R.id.text_brg_stock)
        TextView txt_brg_stock;

        public BarangViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface BarangDataListener{
        void onBarangClicked(Barang barang);
    }
}
