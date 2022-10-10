package com.firstapp.room_db_fragment_task;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firstapp.room_db_fragment_task.database.AppDatabase;
import com.firstapp.room_db_fragment_task.database.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
* create an instance of this fragment.
 */
public class Fragment_B extends Fragment {

    public static RecyclerView recyclerView;
    UserListAdapter userListAdapter;

    public Fragment_B() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment__b, container, false);

        recyclerView =v.findViewById(R.id.recyclerid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        loadUserList();

        return v;
    }

    //load the data
    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getContext());
        List<User> userList =db.userDao().getAllUsers();
        userListAdapter=new UserListAdapter(getContext());
        userListAdapter.setUserList(userList);
        recyclerView.setAdapter(userListAdapter);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadUserList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
