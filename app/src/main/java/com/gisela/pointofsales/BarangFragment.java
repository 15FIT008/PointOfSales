package com.gisela.pointofsales;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gisela.pointofsales.entity.Barang;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Gisela (1572008)
 */

public class BarangFragment extends Fragment {


    @BindView(R.id.txt_brg_nama)
    EditText txt_brg_nama;
    @BindView(R.id.txt_brg_harga_jual)
    EditText txt_brg_harga_jual;
    @BindView(R.id.txt_brg_harga_beli)
    EditText txt_brg_harga_beli;
    @BindView(R.id.txt_brg_stock)
    EditText txt_brg_stock;

    public BarangFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barang,container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getArguments() != null && getArguments().containsKey(getResources().getString(R.string
                .parcel_barang))){
            Barang barang = getArguments().getParcelable(getResources().getString(R.string
                    .parcel_barang));
            txt_brg_nama.setText(barang.getNamaBarang());
            txt_brg_harga_jual.setText(barang.getHargaJual());
            txt_brg_harga_beli.setText(barang.getHargaBeli());
            txt_brg_stock.setText(barang.getStock());
        }
    }


}
