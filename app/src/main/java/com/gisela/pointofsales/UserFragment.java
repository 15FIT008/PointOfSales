package com.gisela.pointofsales;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisela.pointofsales.Adapter.UserAdapter;
import com.gisela.pointofsales.entity.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    @BindView(R.id.rad_group_admin)
    RadioGroup rad_group_admin;
    private DatabaseReference database;
    boolean addData;
    int id;
    private MainActivity mainActivity;

    public UserFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference();
        addData = false;
        id = 0;
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
        id=0;
        if(!isEmpty(txt_user_address) && !isEmpty(txt_user_name) && !isEmpty(txt_user_password)
                && !isEmpty(txt_user_username) && !isEmpty(txt_user_phone)){

            database.child("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        int cekId = Integer.valueOf(noteDataSnapshot.getValue(User.class).getIdUser()
                                .substring(2,3));
                        id = cekId;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                }
            });

            User user = new User();
            user.setIdUser("00"+String.valueOf((id+1)));
            user.setAdmin(1);
            user.setAlamatUser(txt_user_address.getText().toString());
            user.setNamaUser(txt_user_name.getText().toString());
            user.setNoTelpUser(txt_user_phone.getText().toString());
            user.setUsername(txt_user_username.getText().toString());
            user.setPassword(txt_user_password.getText().toString());
            database.child("User").push().setValue(user).addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    txt_user_address.setText("");
                    txt_user_name.setText("");
                    txt_user_password.setText("");
                    txt_user_phone.setText("");
                    txt_user_username.setText("");
                    addData = true;
                    Toast.makeText(getActivity(), "Data User berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private boolean isEmpty(EditText txt){
        return TextUtils.isEmpty(txt.getText().toString().trim());
    }
}
