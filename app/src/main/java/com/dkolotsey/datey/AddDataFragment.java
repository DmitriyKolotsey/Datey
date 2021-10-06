package com.dkolotsey.datey;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dkolotsey.datey.Data.Contacts;
import com.squareup.picasso.Picasso;

public class AddDataFragment extends DialogFragment implements View.OnClickListener {
    EditText etName, etBirthdayDate;
    ImageButton ibContactImage;

    private static final int RESULT_OK = 1;
    private static final int RESULT_LOAD_IMAGE = 1;
    Uri imgUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Add Data");
        View v = inflater.inflate(R.layout.fragment_add_data, null);
        etName = v.findViewById(R.id.etName);
        etBirthdayDate = v.findViewById(R.id.etBirthdayDate);
        ibContactImage = v.findViewById(R.id.ibContactImage);
        ibContactImage.setClickable(true);

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

                int id = this.getArguments().getInt("id");

                if ( id == 0){
                     id = 0;
                } else id = id + 1;

                String path = imgUri.toString();
                String name = etName.getText().toString();
                String birthday_date = etBirthdayDate.getText().toString();

                contacts.setId(id);
                contacts.setImgPath(path);
                contacts.setName(name);
                contacts.setBirthdayDate(birthday_date);

                MainActivity.database.contactsDao().addData(contacts);
                Toast.makeText(getContext(), "Data Add", Toast.LENGTH_LONG).show();
                break;
            case R.id.bCancel:
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                break;
            case R.id.ibContactImage:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == RESULT_LOAD_IMAGE){
                if (data != null)
                        imgUri = data.getData();
                        Picasso.get().load(imgUri).into(ibContactImage);
                        ibContactImage.setClickable(false);
            }
        }
    }
}