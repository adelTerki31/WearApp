package com.example.wearproject;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.RecyclerViewHolder> {

    private ArrayList<com.example.wearapplication.Message> dataSource = new ArrayList<com.example.wearapplication.Message>();
    public interface AdapterCallback{
        void onItemClicked(Integer menuPosition);
    }
    private AdapterCallback callback;

    private String drawableIcon;
    private Context context;


    public MessageAdapter(Context context, ArrayList<com.example.wearapplication.Message> dataArgs, AdapterCallback callback){
        this.context = context;
        this.dataSource = dataArgs;
        this.callback = callback;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout msgContainer;
        TextView id_msg,id_student,msg_content,lat,lng;

        public RecyclerViewHolder(View view) {
            super(view);
            msgContainer = view.findViewById(R.id.msg_container);
            id_msg = view.findViewById(R.id.id_msg);
            id_student = view.findViewById(R.id.id_student);
            msg_content= view.findViewById(R.id.msg_content);
            lat = view.findViewById(R.id.lat);
            lng = view.findViewById(R.id.lng);
          }

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        com.example.wearapplication.Message data_provider = dataSource.get(position);

        holder.id_student.setText(data_provider.getId());
        holder.id_student.setText(data_provider.getStudent_id());
        holder.msg_content.setText(data_provider.getStudent_message());
        holder.lng.setText(data_provider.getCoordinates().get("gps_long").toString());
        holder.lat.setText(data_provider.getCoordinates().get("gps_lat").toString());
        //holder.menuItem2.setText(data_provider.getText());
        //holder.menuIcon2.setImageResource(data_provider.getImage());
        holder.msgContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if(callback != null) {
                    callback.onItemClicked(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}

