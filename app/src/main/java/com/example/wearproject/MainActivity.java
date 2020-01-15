package com.example.wearproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);


        WearableRecyclerView recyclerView = findViewById(R.id.recycler_launcher_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        CustomScrollingLayoutCallback customScrollingLayoutCallback =
                new CustomScrollingLayoutCallback();
        recyclerView.setLayoutManager(new WearableLinearLayoutManager(this, customScrollingLayoutCallback));
        recyclerView.setCircularScrollingGestureEnabled(true);
        recyclerView.setBezelFraction(0.5f);

        recyclerView.setScrollDegreesPerScreen(90);

        final ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.drawable.ic_send, "Send"));
        menuItems.add(new MenuItem(R.drawable.ic_message, "Read"));


       /* recyclerView.setAdapter(new MainMenuAdapter(this, menuItems, new MainMenuAdapter.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer menuPosition) {
                switch (menuPosition){
                    case 0:  Log.d("action_1","success"); break;
                    case 1:  Log.d("action_2","success"); break;
                    default : Log.d("default","success");
                }
            }
        }));

        */

        recyclerView.setAdapter(new MainMenuAdapter(this, menuItems, new MainMenuAdapter.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer menuPosition) {
                switch (menuPosition){
                    case 0:  {
                        Intent editActivity = new Intent(MainActivity.this, EditActivity.class);
                        startActivity(editActivity);
                    }; break;
                    case 1:  {
                        Intent displayActivity = new Intent(MainActivity.this, DisplayActivity.class);
                        startActivity(displayActivity);
                    }; break;
                    default : Log.d("default","success");
                }
            }
        }));


    }
}
