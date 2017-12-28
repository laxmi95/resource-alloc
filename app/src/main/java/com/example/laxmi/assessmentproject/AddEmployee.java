package com.example.laxmi.assessmentproject;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class AddEmployee extends AppCompatActivity {

    EditText name,emailid,unit,phone;
    Button save_emp;
    DBHelperEmp db2;

    Employee emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        db2 = new DBHelperEmp(AddEmployee.this);

        emp = new Employee();
        // id = (EditText)findViewById(R.id.empID);
        name = (EditText)findViewById(R.id.empName);
        emailid = (EditText)findViewById(R.id.emailid);
        unit= (EditText)findViewById(R.id.unit);
        phone= (EditText)findViewById(R.id.phoneNumber);
        save_emp = (Button)findViewById(R.id.save1);


        save_emp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                final String name1 = name.getText().toString();
                final String emailid1 = emailid.getText().toString();
                final String unit1= unit.getText().toString();
                final String phone1=phone.getText().toString();


                emp.setEmailID(emailid1);
                emp.setEmpName(name1);
                emp.setPhoneNumber(phone1);
                emp.setUnit(unit1);

                db2.createEmp(new Employee(name1,emailid1,unit1,phone1));
                Toast.makeText(AddEmployee.this,"Employee Name"+emp.getEmpName()+emp.getEmpID(), Toast.LENGTH_LONG).show();
                long d1 = db2.query1;
                Toast.makeText(AddEmployee.this,"Value"+d1,Toast.LENGTH_SHORT).show();
                if (d1 > 0){
                    Toast.makeText(AddEmployee.this,"SUCCESS", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddEmployee.this,"ERROR",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(AddEmployee.this,DashBoard.class);
                startActivity(intent);

            }
        });


    }
}
