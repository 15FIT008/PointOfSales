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
import android.widget.EditText;
import android.widget.RadioButton;
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
import butterknife.OnClick;

/**
 * @author Gisela (1572008)
 */

public class UserFragment extends Fragment {

    @BindView(R.id.txt_user_name)
    EditText txt_user_name;
    @BindView(R.id.txt_user_username)
    EditText txt_user_username;
    @BindView(R.id.txt_user_password)
    EditText txt_user_password;
    @BindView(R.id.txt_user_phone)
    EditText txt_user_phone;
    @BindView(R.id.txt_user_address)
    EditText txt_user_address;
    @BindView(R.id.rad_admin_00)
    RadioButton rad_admin_00;
    @BindView(R.id.rad_admin_01)
    RadioButton rad_admin_01;

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
            txt_user_password.setText(user.getPassword());
            txt_user_name.setText(user.getNamaUser());
            txt_user_phone.setText(user.getNoTelpUser());
            txt_user_username.setText(user.getUsername());
        }
    }

    @OnClick(R.id.btn_add_user)
    public void addUser(){
        System.out.println( rad_admin_00.isSelected() + " - " + rad_admin_01.isSelected());
    }
}
