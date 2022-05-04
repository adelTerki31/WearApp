package com.example.wearproject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wearapplication.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;

public class DisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        final WearableRecyclerView recyclerView = findViewById(R.id.recycler_launcher_view_msg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        CustomScrollingLayoutCallback customScrollingLayoutCallback =
                new CustomScrollingLayoutCallback();
        recyclerView.setLayoutManager(new WearableLinearLayoutManager(this, customScrollingLayoutCallback));
        recyclerView.setCircularScrollingGestureEnabled(true);
        recyclerView.setBezelFraction(0.5f);
        recyclerView.setScrollDegreesPerScreen(90);



        Log.d("success", "display_message");
        final ArrayList<Message> messages = new ArrayList<Message>();
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Constants.URL_GET,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject message =  response.getJSONObject(i);
                                Log.d("Response", message.getString("student_message"));
                                Log.d("Recieved msg", message.toString());
                                HashMap coordinates = new HashMap();
                                coordinates.put("gps_lat", message.getJSONObject("coordinates").get("gps_lat"));
                                coordinates.put("gps_long", message.getJSONObject("coordinates").get("gps_long"));
                                messages.add(new Message(
                                        message.getInt("id"),
                                        message.getInt("student_id"),
                                        message.getString("student_message"),
                                        coordinates
                                ));
                            }

                            Log.d("success",messages.toString());

                            recyclerView.setAdapter(new MessageAdapter(this , messages, new MessageAdapter.AdapterCallback() {
                                @Override
                                public void onItemClicked(final Integer menuPosition) {
                                    Log.d("Clicked","You click me!");
                                }
                            }));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                });
        requestQueue.add(request);
    }


}
