package com.dkolotsey.datey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.dkolotsey.datey.Data.Database;

public class MainActivity extends AppCompatActivity {
    public static Database database;
    DialogFragment addDataFragment = new AddDataFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(getApplicationContext(), Database.class, "notification_list").allowMainThreadQueries().build();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvContacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onAddDataFragment(View view) {
        addDataFragment.show(getSupportFragmentManager(), "addDataFragment");
    }
}