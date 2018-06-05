package com.example.ka.edu_sqlite_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtName, edtPhone, edtAdress, edtEmail;
    private TextView txtId;
    private Button btnSave, btnUpdate;
    private ListView lvListView;

    private Adapterr customAdapter;
    private List<Student> studentList;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        addControls();
        studentList = dbManager.getAllStudent();
        setAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = createStudent();
                if(student != null) {
                    dbManager.addStudent(student);
                }

                studentList.clear();
                studentList.addAll(dbManager.getAllStudent());
                setAdapter();


            }
        });


        addEvents();

    }

    private void addEvents() {
        lvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = studentList.get(position);
                txtId.setText(student.getId()+"");
                edtName.setText(student.getName());
                edtPhone.setText(student.getNumber());
                edtEmail.setText(student.getEmail());
                edtAdress.setText(student.getAddress());
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
            }
        });

        lvListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = studentList.get(position);
                int result = dbManager.deleteStudent(student.getId());
                if(result > 0)
                {
                    updateStudent();
                    Toast.makeText(MainActivity.this, "Delete successfully!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setId(Integer.parseInt(txtId.getText()+""));
                student.setName(edtName.getText()+"");
                student.setAddress(edtAdress.getText()+"");
                student.setNumber(edtPhone.getText()+"");
                student.setEmail(edtEmail.getText()+"");
                int result = dbManager.Update(student);
                if(result > 0){
                    updateStudent();
                }else
                    btnSave.setEnabled(true);
                    btnUpdate.setEnabled(false);


            }
        });
    }

    private void addControls() {
        txtId = findViewById(R.id.txtId);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtName = findViewById(R.id.edtName);
        edtAdress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        btnSave = findViewById(R.id.btnSave);
        lvListView = findViewById(R.id.lvListview);
    }

    private Student createStudent(){
        String name = edtName.getText().toString();
        String address= String.valueOf(edtAdress.getText());
        String phone = edtPhone.getText()+"";
        String email = edtEmail.getText()+"";

        Student student = new Student(name, address, phone, email);
        return student;
    }

    public void setAdapter(){
        if(customAdapter == null){
            customAdapter = new Adapterr(this, R.layout.item_lisview_student,studentList);
            lvListView.setAdapter(customAdapter);
        }else {
            customAdapter.notifyDataSetChanged();
            lvListView.setSelection(customAdapter.getCount() - 1);

        }

    }

    public void updateStudent(){
        studentList.clear();
        studentList.addAll(dbManager.getAllStudent());
        customAdapter.notifyDataSetChanged();

    }


}
