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

import com.gisela.pointofsales.Adapter.UserAdapter;
import com.gisela.pointofsales.entity.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
    @BindView(R.id.txt_user_re_password)
    EditText txt_user_re_password;
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
    @BindView(R.id.update_user)
    Button btn_update_user;
    @BindView(R.id.delete_user)
    Button btn_delete_user;
    @BindView(R.id.btn_add_user)
    Button btn_add_user;

    ArrayList<User> users;
    UserAdapter userAdapter;

    private DatabaseReference database;
    int id;

    final static String ARG_USER = "parcel_user";
    User selectedUser;

    public UserFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference();
        id = 0;
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        ButterKnife.bind(this,view);
        btn_update_user.setEnabled(false);
        btn_delete_user.setEnabled(false);
        btn_add_user.setEnabled(true);
        userAdapter = new UserAdapter();
        users = new ArrayList<>();
        selectedUser = new User();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getArguments()!=null && getArguments().containsKey(UserFragment.ARG_USER)){
            selectedUser = getArguments().getParcelable(UserFragment.ARG_USER);
            txt_user_address.setText(selectedUser.getAlamatUser());
            txt_user_name.setText(selectedUser.getNamaUser());
            txt_user_phone.setText(selectedUser.getNoTelpUser());
            txt_user_username.setText(selectedUser.getUsername());
            txt_user_password.setText(selectedUser.getPassword());
            txt_user_re_password.setText(selectedUser.getPassword());
            txt_user_username.setEnabled(false);
            txt_user_re_password.setEnabled(false);
            txt_user_password.setEnabled(false);
            btn_update_user.setEnabled(true);
            btn_delete_user.setEnabled(true);
            btn_add_user.setEnabled(false);

            if(selectedUser.getAdmin() == 0){
                rad_group_admin.check(rad_admin_00.getId());
            }else{
                rad_group_admin.check(rad_admin_01.getId());
            }
        }
    }

    private boolean isEmpty(EditText txt){
        return TextUtils.isEmpty(txt.getText().toString().trim());
    }

    private boolean passwordChecker (EditText a, EditText b){
        return a.getText().toString().equals(b.getText().toString());
    }

    @OnClick(R.id.btn_add_user)
    public void addUser(){
        id=0;
        if(isEmpty(txt_user_address) || isEmpty(txt_user_name) || isEmpty(txt_user_password)
                || isEmpty(txt_user_re_password) || isEmpty(txt_user_username) || isEmpty
                (txt_user_phone) || (rad_group_admin.getCheckedRadioButtonId() !=
                rad_admin_01.getId() && rad_group_admin.getCheckedRadioButtonId() !=
                rad_admin_00.getId())){
            Toast.makeText(getActivity(), "Please fill all field!", Toast.LENGTH_SHORT).show();
        }else{
            if(!passwordChecker(txt_user_password, txt_user_re_password)){
                Toast.makeText(getActivity(), "Password and Re-password didn't match!", Toast
                        .LENGTH_SHORT).show();
            }else{
                String idUser;
                users = userAdapter.getUsers();
                id = users.size();
                if(id<10){
                    idUser = "00"+String.valueOf(id);
                }else if(id<100){
                    idUser = "0"+String.valueOf(id);
                }else{
                    idUser = String.valueOf(id);
                }
                User user = new User();
                user.setIdUser("00"+1);
                if(rad_group_admin.getCheckedRadioButtonId() == rad_admin_01.getId()){
                    user.setAdmin(1);
                }else{
                    user.setAdmin(0);
                }
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
                        txt_user_re_password.setText("");
                        txt_user_phone.setText("");
                        txt_user_username.setText("");
                    }
                });
            }
        }
    }

    @OnClick(R.id.update_user)
    public void update_user() {
        User user = new User();
        user.setIdUser(selectedUser.getIdUser());
        if (rad_group_admin.getCheckedRadioButtonId() == rad_admin_01.getId()) {
            user.setAdmin(1);
        } else {
            user.setAdmin(0);
        }
        user.setAlamatUser(txt_user_address.getText().toString());
        user.setNamaUser(txt_user_name.getText().toString());
        user.setNoTelpUser(txt_user_phone.getText().toString());
        user.setUsername(txt_user_username.getText().toString());
        user.setPassword(txt_user_password.getText().toString());
        database.child("User").child(selectedUser.getKey()).setValue(user).addOnSuccessListener
                (getActivity(), new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                        txt_user_address.setText("");
                        txt_user_name.setText("");
                        txt_user_password.setText("");
                        txt_user_re_password.setText("");
                        txt_user_phone.setText("");
                        txt_user_username.setText("");
                    }
                });
    }

    @OnClick(R.id.delete_user)
    public void deleteUser(){
        database.child("User").child(selectedUser.getKey()).removeValue().addOnSuccessListener
                (getActivity(), new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT)
                                .show();
                        txt_user_address.setText("");
                        txt_user_name.setText("");
                        txt_user_password.setText("");
                        txt_user_re_password.setText("");
                        txt_user_phone.setText("");
                        txt_user_username.setText("");
                    }
                });
    }

    @OnClick(R.id.btn_reset_user)
    public void resetUser(){
        rad_group_admin.clearCheck();
        txt_user_address.setText("");
        txt_user_name.setText("");
        txt_user_phone.setText("");
        txt_user_username.setText("");
        txt_user_password.setText("");
        txt_user_re_password.setText("");
        txt_user_username.setEnabled(true);
        txt_user_re_password.setEnabled(true);
        txt_user_password.setEnabled(true);
        btn_update_user.setEnabled(false);
        btn_delete_user.setEnabled(false);
        btn_add_user.setEnabled(true);
    }
}
