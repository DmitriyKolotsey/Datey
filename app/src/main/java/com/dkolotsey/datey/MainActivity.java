package com.dkolotsey.datey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.dkolotsey.datey.Data.Adapter;
import com.dkolotsey.datey.Data.Contacts;
import com.dkolotsey.datey.Data.Database;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    public static Database database;
    DialogFragment addDataFragment = new AddDataFragment();
    DialogFragment editDataFragment = new EditDataFragment();
    DialogFragment deleteDataFragment = new DeleteDataFragment();

    RecyclerView recyclerView;

    List<Contacts> contactsListGetSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(getApplicationContext(), Database.class, "notification_list").allowMainThreadQueries().build();
        recyclerView = (RecyclerView) findViewById(R.id.rvContacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getData();
    }

    public void getData(){
        class GetData extends AsyncTask<Void, Void, List<Contacts>>{
            List<Contacts> contactsList;
            @Override
            protected List<Contacts> doInBackground(Void... voids) {
                contactsList = MainActivity.database.contactsDao().getContacts();
                contactsListGetSize = contactsList;
                return contactsList;
            }

            @Override
            protected void onPostExecute(List<Contacts> contacts) {
                Adapter adapter = new Adapter(getApplicationContext(), contacts, new Adapter.AdapterListener() {
                    @Override
                    public void editButtonOnClick(View v, int position) {
                        int id = contactsList.size();
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", id);
                        editDataFragment.setArguments(bundle);
                        editDataFragment.show(getSupportFragmentManager(), "addDataFragment");
                    }

                    @Override
                    public void deleteButtonOnClick(View v, int position) {
                        int id = contactsList.size();
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", id);
                        deleteDataFragment.setArguments(bundle);
                        deleteDataFragment.show(getSupportFragmentManager(), "addDataFragment");

                    }
                });
                recyclerView.setAdapter(adapter);
                super.onPostExecute(contacts);
            }
        }
        GetData getData = new GetData();
        getData.execute();
    }

    public void onAddDataFragment(View view) {
        int id = contactsListGetSize.size();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        deleteDataFragment.setArguments(bundle);
        addDataFragment.show(getSupportFragmentManager(), "addDataFragment");
    }
}