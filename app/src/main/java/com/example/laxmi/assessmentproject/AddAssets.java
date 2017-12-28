package com.example.laxmi.assessmentproject;


        import android.annotation.TargetApi;
        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.os.Build;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

public class AddAssets extends AppCompatActivity {

    EditText make,yom,allocTo,allocTill,id;
    Button save1;
    DBHelper db1;
    static int count = 0;


    Assets assets;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assets);

        final List<String> enameList = new ArrayList<String>();
        assets = new Assets();
        db1= new DBHelper(this);
        Log.d("Created","Helper");
        // id = (EditText)findViewById(R.id.assetID);
        make = (EditText)findViewById(R.id.assetMake);
        yom = (EditText)findViewById(R.id.yearOfMaking);
        allocTo = (EditText)findViewById(R.id.assetAllocTo);
        allocTill = (EditText)findViewById(R.id.assetAllocTill);


        allocTill.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);

                int mMonth = c.get(Calendar.MONTH);

                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AddAssets.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        allocTill.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
                     datePickerDialog.show();

            //    c.add(Calendar.MONTH,6);
            }
        });


        allocTo.setOnClickListener(new View.OnClickListener() {
            DBHelperEmp dbEmp;
            SQLiteDatabase sqlOpen;

            @Override
            public void onClick(View v) {
                dbEmp = new DBHelperEmp(AddAssets.this);
                sqlOpen = dbEmp.getWritableDatabase();

                final Dialog dialog = new Dialog(AddAssets.this);

                dialog.setContentView(R.layout.emp_list);

                dialog.setTitle("select from given emplyees");

                ListView listView = (ListView) dialog.findViewById(R.id.emp_list1);

                //  String[] values = new String[]{"qwe", "ert", "ytr"};
                //to fetch and display EMP list from DB
                Cursor cursor = sqlOpen.query(DBHelperEmp.TABLE_EMP,
                        new String[]{DBHelperEmp.EMP_NAME}, null,null, null, null, null);//group by, having ,order by
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    //Toast.makeText(this, "Emp Name is " + cus.getString(0) + " Emp Unit is  " + cus.getString(1), Toast.LENGTH_LONG).show();

                    enameList.add(cursor.getString(0));
                    cursor.moveToNext();
                }
                cursor.close();

                dialog.show();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAssets.this,android.R.layout.simple_list_item_1,android.R.id.text1,enameList);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String empName = parent.getItemAtPosition(position).toString();
                        allocTo.setText(empName);

//                        Intent i2 = new Intent(DisplayEmpList.this, DashBoard.class);
//                        startActivity(i2);


                    }
                });

            }
        });


           // save asset details to TABLE_ASSET
        save1 = (Button)findViewById(R.id.save);
        save1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                final String make1 = make.getText().toString();
                final String yom1 = yom.getText().toString();
                final String allocTo1 = allocTo.getText().toString();
                final String allocTill1 = allocTill.getText().toString();


                assets.setAssetMake(make1);
                //final String m= assets.getAssetMake();
                assets.setYearOfMaking(yom1);
                assets.setAllocatedTo(allocTo1);
                assets.setAllocatedTill(allocTill1);

                //  assets.setAssetId(++count);
                Calendar c = Calendar.getInstance();
                int mY = c.get(Calendar.YEAR);
                int mMnth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                c.add(Calendar.MONTH,6);
                Toast.makeText(AddAssets.this,"Date after allocation of asset"+mY+"/"+mMnth+"/"+ mDay,Toast.LENGTH_SHORT).show();


                db1.createAssetTable(new Assets(make1,yom1,allocTo1,allocTill1));
                // SQLiteDatabase db1 = helper.getWritableDatabase();

                Toast.makeText(AddAssets.this,assets.getAllocatedTo(), Toast.LENGTH_LONG).show();
                long d = db1.data;

                if (d > 0){
                    Toast.makeText(AddAssets.this,"SUCCESS", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddAssets.this,"ERROR",Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(AddAssets.this,DashBoard.class);
                startActivity(intent);
            }
        });
    }


}
