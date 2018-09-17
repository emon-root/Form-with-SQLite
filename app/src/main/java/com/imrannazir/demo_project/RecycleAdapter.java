package com.imrannazir.demo_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AFF02 on 23-Aug-17.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    List<PersonData> dataModelList;

    public RecycleAdapter(List<PersonData> dataModelList) {
        this.dataModelList = dataModelList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtID, txtName,txtEmail, txtPhone;
        public ViewHolder(View itemView) {
            super(itemView);

           // txtID = (TextView)itemView.findViewById(R.id.id_row);
            txtName = (TextView)itemView.findViewById(R.id.name_row);
            txtEmail = (TextView)itemView.findViewById(R.id.email_row);
            txtPhone = (TextView)itemView.findViewById(R.id.phone_row);
        }
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {

        PersonData model = dataModelList.get(position);
     //   holder.txtID.setText(position+1);
        holder.txtName.setText(model.getName());
        holder.txtEmail.setText(model.getEmail());
        holder.txtEmail.setSelected(true);
        holder.txtName.setText(model.getPhone());
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }
}