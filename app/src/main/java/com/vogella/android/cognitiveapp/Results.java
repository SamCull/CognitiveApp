package com.vogella.android.cognitiveapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Results extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        listView=(ListView)findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Geography, medium, 10, Date 12/03/2022");
        arrayList.add("History medium, 8, Date 04/04/2022");
        arrayList.add("History easy, 1, Date 14/04/2022");
        arrayList.add("Maths medium, 11, Date 19/04/2022");
        arrayList.add("Geography hard, 17, Date 08/05/2022");
        arrayList.add("Maths easy, 10, Date 12/05/2022");
        arrayList.add("Maths hard, 8, Date 22/05/2022");
        arrayList.add("History medium, 1, Date 02/06/2022");
        arrayList.add("Geography easy, 11, Date 10/06/2022");
        arrayList.add("Maths hard, 17, Date 18/06/2022");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }
}
