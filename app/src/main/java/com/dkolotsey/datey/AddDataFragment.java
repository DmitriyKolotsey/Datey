package com.dkolotsey.datey;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dkolotsey.datey.Data.Contacts;
import com.dkolotsey.datey.Data.Database;

public class AddDataFragment extends DialogFragment implements View.OnClickListener {
    EditText etName, etBirthdayDate;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        getDialog().setTitle("Add Data");
        View v = inflater.inflate(R.layout.fragment_add_data, null);
        etName = v.findViewById(R.id.etName);
        etBirthdayDate = v.findViewById(R.id.etBirthdayDate);

        v.findViewById(R.id.bAccept).setOnClickListener(this);
        v.findViewById(R.id.bCancel).setOnClickListener(this);
        v.findViewById(R.id.ibContactImage).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bAccept:
                Contacts contacts = new Contacts();

                //int id = ;
                //String path = ;
                String name = etName.getText().toString();
                String birthday_date = etBirthdayDate.getText().toString();

                //contacts.setId(id);
                //contacts.setImgPath(path);
                contacts.setName(name);
                contacts.setBirthdayDate(birthday_date);

                MainActivity.database.contactsDao().addData(contacts);
                break;
            case R.id.bCancel:
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                break;
            case R.id.ibContactImage:

                break;
        }
    }
}