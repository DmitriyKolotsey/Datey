package com.dkolotsey.datey.Data;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

    public AdapterListener onClickListener;

    public Adapter(Context context, List<Contacts> contactsList, AdapterListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.contactsList = contactsList;
        this.onClickListener = listener;
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
        if (Uri.parse(contacts.getImgPath()) != null){
            viewHolder.ivContactImage.setImageURI(Uri.parse(contacts.getImgPath()));
        } else {
            //viewHolder.ivContactImage.setImageDrawable(R.drawable.);
        }

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView tvName, tvBirthdayDate, tvDaysBeforeBirthday;
            private ImageView ivContactImage;
            private ImageButton ibEdit, ibDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvBirthdayDate = (TextView) itemView.findViewById(R.id.tvBirthdayDate);
            tvDaysBeforeBirthday = (TextView) itemView.findViewById(R.id.tvDaysBeforeBirthday);
            ivContactImage = (ImageView) itemView.findViewById(R.id.ivContactImage);
            ibEdit = (ImageButton) itemView.findViewById(R.id.ibEdit);
            ibDelete = (ImageButton) itemView.findViewById(R.id.ibDelete);

            ibEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.editButtonOnClick(v, getAdapterPosition());
                }
            });

            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.deleteButtonOnClick(v, getAdapterPosition());
                }
            });

        }
    }

    public interface AdapterListener{
        void editButtonOnClick(View v, int position);
        void deleteButtonOnClick(View v, int position);
    }
}
