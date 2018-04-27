package com.gisela.pointofsales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.gisela.pointofsales.entity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txt_password)
    EditText txt_password;
    @BindView(R.id.txt_username)
    EditText txt_username;

    private DatabaseReference userRef;
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        if(users == null){
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        userRef = database.getReference();
    }

    @OnClick(R.id.btn_login)
    void login(){
        if(!txt_username.getText().toString().trim().isEmpty() && !txt_password.getText().toString().trim().isEmpty()){
            userRef.child("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        if(noteDataSnapshot.getValue(User.class).getUsername().equals(txt_username.getText().toString().trim()) &&
                                noteDataSnapshot.getValue(User.class).getPassword().equals(txt_password.getText().toString().trim())){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            LoginActivity.this.finish();
                            LoginActivity.this.startActivity(intent);
                            System.out.println(noteDataSnapshot.getValue(User.class).toString());
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                }
            });
        }
    }
}
