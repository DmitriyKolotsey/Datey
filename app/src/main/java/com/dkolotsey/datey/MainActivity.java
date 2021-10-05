package com.dkolotsey.datey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.dkolotsey.datey.Data.Adapter;
import com.dkolotsey.datey.Data.Contacts;
import com.dkolotsey.datey.Data.Database;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static Database database;
    DialogFragment addDataFragment = new AddDataFragment();
    DialogFragment editDataFragment = new EditDataFragment();
    DialogFragment deleteDataFragment = new DeleteDataFragment();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(getApplicationContext(), Database.class, "notification_list").allowMainThreadQueries().build();
        recyclerView = (RecyclerView) findViewById(R.id.rvContacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.ibEdit).setOnClickListener(this);
        findViewById(R.id.ibDelete).setOnClickListener(this);

        getData();
    }

    public void getData(){
        class GetData extends AsyncTask<Void, Void, List<Contacts>>{
            @Override
            protected List<Contacts> doInBackground(Void... voids) {
                List<Contacts> contactsList = MainActivity.database.contactsDao().getContacts();
                return contactsList;
            }

            @Override
            protected void onPostExecute(List<Contacts> contacts) {
                Adapter adapter = new Adapter(getApplicationContext(), contacts);
                recyclerView.setAdapter(adapter);
                super.onPostExecute(contacts);
            }
        }
        GetData getData = new GetData();
        getData.execute();
    }

    public void onAddDataFragment(View view) {
        addDataFragment.show(getSupportFragmentManager(), "addDataFragment");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ibEdit:
                editDataFragment.show(getSupportFragmentManager(), "editDataFragment");
                break;
            case R.id.ibDelete:
                deleteDataFragment.show(getSupportFragmentManager(), "deleteDataFragment");
                break;
        }
    }
}