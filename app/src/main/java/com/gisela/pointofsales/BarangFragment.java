package com.gisela.pointofsales;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gisela.pointofsales.entity.Barang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.delete_brg)
    Button btn_delete_brg;
    @BindView(R.id.update_brg)
    Button btn_update_brg;
    @BindView(R.id.btn_add_brg)
    Button btn_add_brg;

    private DatabaseReference database;

    final static String ARG_BARANG = "parcel_barang";
    Barang selectedBarang;

    public BarangFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference();
        View view = inflater.inflate(R.layout.fragment_barang,container, false);
        ButterKnife.bind(this, view);
        btn_delete_brg.setEnabled(false);
        btn_update_brg.setEnabled(false);
        selectedBarang = new Barang();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getArguments() != null && getArguments().containsKey(BarangFragment.ARG_BARANG)){
            selectedBarang = getArguments().getParcelable(BarangFragment.ARG_BARANG);
            txt_brg_nama.setText(selectedBarang.getNamaBarang());
            txt_brg_harga_jual.setText(String.valueOf(selectedBarang.getHargaJual()));
            txt_brg_harga_beli.setText(String.valueOf(selectedBarang.getHargaBeli()));
            txt_brg_stock.setText(String.valueOf(selectedBarang.getStock()));
            btn_add_brg.setEnabled(false);
            btn_delete_brg.setEnabled(true);
            btn_update_brg.setEnabled(true);

        }
    }

    @OnClick(R.id.btn_add_brg)
    public void addBrg(){
        if(isEmpty(txt_brg_nama) || isEmpty(txt_brg_harga_jual) || isEmpty(txt_brg_harga_beli) ||
                isEmpty(txt_brg_stock)){
            Toast.makeText(getActivity(), "Please fill all field!", Toast.LENGTH_SHORT).show();
        }else{
            Barang brg = new Barang();
            brg.setIdBarang("00"+1);
            brg.setNamaBarang(txt_brg_nama.getText().toString());
            brg.setHargaBeli(Integer.valueOf(txt_brg_harga_beli.getText().toString()));
            brg.setHargaJual(Integer.valueOf(txt_brg_harga_jual.getText().toString()));
            brg.setStock(Integer.valueOf(txt_brg_stock.getText().toString()));
            database.child("Barang").push().setValue(brg).addOnSuccessListener(getActivity(), new
                    OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            txt_brg_harga_beli.setText("");
                            txt_brg_harga_jual.setText("");
                            txt_brg_nama.setText("");
                            txt_brg_stock.setText("");
                        }
                    });
        }
    }

    @OnClick(R.id.update_brg)
    public void updateBrg() {
        Barang brg = new Barang();
        brg.setIdBarang(selectedBarang.getIdBarang());
        brg.setNamaBarang(txt_brg_nama.getText().toString());
        brg.setHargaBeli(Integer.valueOf(txt_brg_harga_beli.getText().toString()));
        brg.setHargaJual(Integer.valueOf(txt_brg_harga_jual.getText().toString()));
        brg.setStock(Integer.valueOf(txt_brg_stock.getText().toString()));
        database.child("Barang").child(selectedBarang.getKey()).setValue(brg)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                        txt_brg_harga_beli.setText("");
                        txt_brg_harga_jual.setText("");
                        txt_brg_nama.setText("");
                        txt_brg_stock.setText("");
                    }
                });
    }

    @OnClick(R.id.delete_brg)
    public void deleteBrg(){
        database.child("Barang").child(selectedBarang.getKey()).removeValue().addOnSuccessListener
                (getActivity(), new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT)
                                .show();
                        txt_brg_harga_beli.setText("");
                        txt_brg_harga_jual.setText("");
                        txt_brg_nama.setText("");
                        txt_brg_stock.setText("");
                    }
                });

    }

    @OnClick(R.id.btn_reset_brg)
    public void resetBrg(){
        txt_brg_nama.setText("");
        txt_brg_harga_jual.setText("");
        txt_brg_harga_beli.setText("");
        txt_brg_stock.setText("");
        btn_delete_brg.setEnabled(false);
        btn_update_brg.setEnabled(false);
        btn_add_brg.setEnabled(true);
    }

    private boolean isEmpty(EditText txt){
        return TextUtils.isEmpty(txt.getText().toString().trim());
    }
}
