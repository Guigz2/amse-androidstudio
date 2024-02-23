package com.example.tpleboncoin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AdListViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_ad);

        DBManager dbManager = DBManager.getDBManager(this);
        dbManager.open();
        if (dbManager.fetch().getCount() == 0) {
            dbManager.init();
        }
        Cursor cursor = dbManager.fetch();
        CursorAdapter adapter = new DbAdAdapter(this, cursor, R.layout.item_listview_ad);
        adapter.notifyDataSetChanged();

        ListView listView = (ListView) findViewById(R.id.DisplayListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdModel selectedAd = (AdModel) dbManager.getById((int) id);

                Intent intent = new Intent(AdListViewActivity.this, AdViewActivity.class);
                intent.putExtra("title", selectedAd.getTitle());
                intent.putExtra("address", selectedAd.getAddress());
                intent.putExtra("AdId",id);
                startActivity(intent);
            }
        });
    }
}

