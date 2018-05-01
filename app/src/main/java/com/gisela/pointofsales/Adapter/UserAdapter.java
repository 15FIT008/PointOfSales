package com.gisela.pointofsales.Adapter;

import android.content.Context;
import android.icu.lang.UScript;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gisela.pointofsales.R;
import com.gisela.pointofsales.entity.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> users;
    private UserDataListener userDataClickedListener;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final User user = getUsers().get(position);
//        String id = user.getIdUser();
        String name = user.getNamaUser();
        String username = user.getUsername();
        String alamat = user.getAlamatUser();
        String phone = user.getNoTelpUser();
        int admin = user.getAdmin();

        String role;
        if(admin == 1){
            role = "Admin";
        }else{
            role = "Kasir";
        }

//        holder.txt_user_id.setText(id);
        holder.txt_user_name.setText(name);
        holder.txt_user_address.setText(alamat);
        holder.txt_user_username.setText(username);
        holder.txt_user_role.setText(role);
        holder.txt_user_phone.setText(phone);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userDataClickedListener != null){
                        userDataClickedListener.onUserClicked(user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getUsers().size();
    }

    public ArrayList<User> getUsers() {
        if(users == null){
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        getUsers().clear();
        getUsers().addAll(users);
        notifyDataSetChanged();
    }

    public void setUserDataClickedListener(UserDataListener userDataClickedListener) {
        this.userDataClickedListener = userDataClickedListener;
    }


    class UserViewHolder extends RecyclerView.ViewHolder{

//        @BindView(R.id.text_user_id)
//        TextView txt_user_id;
        @BindView(R.id.text_user_name)
        TextView txt_user_name;
        @BindView(R.id.text_user_role)
        TextView txt_user_role;
        @BindView(R.id.text_user_address)
        TextView txt_user_address;
        @BindView(R.id.text_user_username)
        TextView txt_user_username;
        @BindView(R.id.text_user_phone)
        TextView txt_user_phone;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface UserDataListener {
        void onUserClicked(User user);
    }

}
