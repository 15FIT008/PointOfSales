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

import com.gisela.pointofsales.Adapter.UserAdapter;
import com.gisela.pointofsales.entity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Gisela (1572008)
 */

public class UserListFragment extends Fragment {

    private UserAdapter userAdapter;
    @BindView(R.id.rv_list_user)
    RecyclerView recyclerUsers;
    private ArrayList<User> users;
    private DatabaseReference userRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration did = new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation());
        recyclerUsers = rootView.findViewById(R.id.rv_list_user);
        recyclerUsers.addItemDecoration(did);
        recyclerUsers.setLayoutManager(linearLayoutManager);
        recyclerUsers.setAdapter(getUserAdapter());
        populateUserData();
        return rootView;
    }

    public UserAdapter getUserAdapter() {
        if (userAdapter == null){
            userAdapter = new UserAdapter();
        }
        return userAdapter;
    }

    public void populateUserData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        users = new ArrayList<>();
        userRef = database.getReference();
        userRef.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    User user = new User(noteDataSnapshot.getValue(User.class));
                    user.setKey(noteDataSnapshot.getKey());
                    users.add(user);
                }
                getUserAdapter().setUsers(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
        getUserAdapter().setUsers(users);
    }
}
