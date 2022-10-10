package com.firstapp.room_db_fragment_task;

import static com.firstapp.room_db_fragment_task.Fragment_B.recyclerView;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
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
public class Fragment_A extends Fragment {

    private UserListAdapter userListAdapter;

    public Fragment_A() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment__a, container, false);

        final EditText firstNameInput = v.findViewById(R.id.firstNameInput);
        final EditText lastNameInput =v.findViewById(R.id.lastNameInput);
        Button saveButton =v.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firtnameStr,lastnameStr;

                firtnameStr=firstNameInput.getText().toString();
                lastnameStr=lastNameInput.getText().toString();

                if (TextUtils.isEmpty(firtnameStr)){
                    firstNameInput.setError("Enter Firstname");
                }
                if (TextUtils.isEmpty(lastnameStr)){
                    lastNameInput.setError("Enter Lastname");
                }
                else {
                    saveNewUser(firstNameInput.getText().toString(), lastNameInput.getText().toString());
                }
            }
        });

        return v;
    }

    private void saveNewUser(String firstName, String lastName) {
        AppDatabase db  = AppDatabase.getDbInstance(this.getContext());


        User user = new User();
        user.firstName =firstName;
        user.lastName =lastName;
        db.userDao().insertUser(user);

        List<User> userList=db.userDao().getAllUsers();
        userListAdapter=new UserListAdapter(getContext());
        userListAdapter.setUserList(userList);
        recyclerView.setAdapter(userListAdapter);


    }
}