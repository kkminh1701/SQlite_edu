package com.example.ka.edu_sqlite_demo;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Adapterr extends ArrayAdapter<Student>{

    private Context context;
    private int resource;
    private List<Student> listStudent;

    public Adapterr(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listStudent = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Viewholder viewholder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lisview_student, parent,false);
            viewholder = new Viewholder();
            viewholder.txtId = convertView.findViewById(R.id.txtId);
            viewholder.txtName = convertView.findViewById(R.id.txtName);
            viewholder.txtAddress = convertView.findViewById(R.id.txtAddress);
            viewholder.txtEmail = convertView.findViewById(R.id.txtEmail);
            viewholder.txtPhone = convertView.findViewById(R.id.txtPhone);

            convertView.setTag(viewholder);

        }else {
            viewholder = (Viewholder) convertView.getTag();

        }
        Student student = listStudent.get(position);
        viewholder.txtId.setText(String.valueOf(student.getId()));
        viewholder.txtAddress.setText(student.getAddress());
        viewholder.txtName.setText(student.getName());
        viewholder.txtEmail.setText(student.getEmail());
        viewholder.txtPhone.setText(student.getNumber());

        return convertView;
    }

    public class Viewholder{
        private TextView txtPhone;
        private TextView txtId;
        private TextView txtName;
        private TextView txtAddress;
        private TextView txtEmail;

    }
}
