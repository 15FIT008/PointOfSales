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
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisela.pointofsales.Adapter.UserAdapter;
import com.gisela.pointofsales.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Gisela (1572008)
 */

public class UserFragment extends Fragment {

    @BindView(R.id.text_user_id)
    TextView txt_user_id;
    @BindView(R.id.text_user_name)
    TextView txt_user_name;
    @BindView(R.id.text_user_role)
    TextView txt_user_role;
    @BindView(R.id.text_user_address)
    TextView txt_user_address;
    @BindView(R.id.text_user_username)
    TextView txt_user_username;

    public UserFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getArguments()!=null && getArguments().containsKey(getResources().getString(R.string.parcel_user))){
            User user = getArguments().getParcelable(getResources().getString(R.string.parcel_user));
            txt_user_address.setText(user.getAdmin());
            txt_user_id.setText(user.getIdUser());
            txt_user_name.setText(user.getNamaUser());
            txt_user_role.setText(user.getAdmin());
            txt_user_username.setText(user.getUsername());
        }
    }
}
