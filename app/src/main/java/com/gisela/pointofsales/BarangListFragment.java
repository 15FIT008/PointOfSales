package com.gisela.pointofsales;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gisela.pointofsales.Adapter.BarangAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Gisela (1572008)
 */

public class BarangListFragment extends Fragment {

    private BarangAdapter barangAdapter;
    @BindView(R.id.rv_list_barang)
    RecyclerView recyclerBarangs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_barang_list, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration did = new DividerItemDecoration(getActivity(), linearLayoutManager
                .getOrientation());
        recyclerBarangs = rootView.findViewById(R.id.rv_list_barang);
        recyclerBarangs.addItemDecoration(did);
        recyclerBarangs.setLayoutManager(linearLayoutManager);
        recyclerBarangs.setAdapter(getBarangAdapter());
        return rootView;
    }

    public BarangAdapter getBarangAdapter() {
        if(barangAdapter == null){
            barangAdapter = new BarangAdapter();
        }
        return barangAdapter;
    }
}
