package com.example.loginanddatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewActivity extends AppCompatActivity {
    ListView searchBar;
    ArrayAdapter<String>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        searchBar =(ListView) findViewById(R.id.searchBar);
        ArrayList<String> arrayUser = new ArrayList<>();
        arrayUser.addAll(Arrays.asList(getResources().getStringArray(R.array.my_users)));

        adapter = new ArrayAdapter<String>(
                ViewActivity.this,
                android.R.layout.simple_list_item_1,
                arrayUser
        );

        searchBar.setAdapter(adapter);
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_user, menu);
        MenuItem item = menu.findItem(R.id.searchBar);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
}
