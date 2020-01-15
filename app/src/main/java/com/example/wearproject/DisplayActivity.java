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
import com.android.volley.toolbox.JsonObjectRequest;
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

        WearableRecyclerView recyclerView = findViewById(R.id.recycler_launcher_view_msg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);
        CustomScrollingLayoutCallback customScrollingLayoutCallback =
                new CustomScrollingLayoutCallback();
        recyclerView.setLayoutManager(new WearableLinearLayoutManager(this, customScrollingLayoutCallback));
        recyclerView.setCircularScrollingGestureEnabled(true);
        recyclerView.setBezelFraction(0.5f);
        recyclerView.setScrollDegreesPerScreen(90);


        Log.d("success", "display_message");
        ArrayList<Message> msgs = getMessages();
        for (int i = 0; i < msgs.size(); i++)
            Log.d("msg", msgs.get(i).getStudent_message());
        recyclerView.setAdapter(new MessageAdapter(this, msgs, new MessageAdapter.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer menuPosition) {
            }
        }));

    }

        public ArrayList<com.example.wearapplication.Message> getMessages(){

        final ArrayList<com.example.wearapplication.Message> messages = new ArrayList<com.example.wearapplication.Message>();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constants.URL_GET,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray(" ");
                                if(jsonArray != null )
                                    Log.d("getting JSON Array","success");
                                else
                                    Log.d("getting JSON Array","failure");

                                for(int i=0;i < jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Log.d("student_message",jsonObject.getString("student_message"));
                                    HashMap coordinates = new HashMap();
                                    coordinates.put("gps_lat",jsonObject.getDouble("gps_lat"));
                                    coordinates.put("gps_long",jsonObject.getDouble("gps_long"));
                                    messages.add(new Message(
                                     jsonObject.getInt("id"),
                                     jsonObject.getInt("student_id"),
                                     jsonObject.getString("student_message"),
                                     coordinates
                                    ));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
            RequestHandler.getInstance(this).addToRequestQueue(request);
            return messages;
        }

}
