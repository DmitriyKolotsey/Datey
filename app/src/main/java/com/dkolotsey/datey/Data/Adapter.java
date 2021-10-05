package com.dkolotsey.datey.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dkolotsey.datey.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Contacts> contactsList;

    public Adapter(Context context, List<Contacts> contactsList) {
        this.inflater = LayoutInflater.from(context);
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Contacts contacts = contactsList.get(i);

        viewHolder.tvName.setText(contacts.getName());
        viewHolder.tvBirthdayDate.setText(contacts.getBirthdayDate());
        //viewHolder.tvDaysBeforeBirthday.setText(contacts.getBirthdayDate() - currentDate);
        Picasso.get().load(contacts.getImgPath()).into(viewHolder.ivContactImage);
        //viewHolder.ivContactImage.setImageURI(contacts.getImgPath());

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tvName, tvBirthdayDate, tvDaysBeforeBirthday;
            private ImageView ivContactImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvBirthdayDate = (TextView) itemView.findViewById(R.id.tvBirthdayDate);
            tvDaysBeforeBirthday = (TextView) itemView.findViewById(R.id.tvDaysBeforeBirthday);
            ivContactImage = (ImageView) itemView.findViewById(R.id.ivContactImage);
        }
    }
}
