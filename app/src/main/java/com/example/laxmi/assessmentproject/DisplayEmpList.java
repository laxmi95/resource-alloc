package com.example.laxmi.assessmentproject;


        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

public class DisplayEmpList extends AppCompatActivity {

    DBHelperEmp dbEmp;
    SQLiteDatabase dataEmp;
    ListView listView;
    List<String> enameList = new ArrayList<String>();

    DBHelper assetHelper;
    SQLiteDatabase sd ;

    int fID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_emp_list);

        listView = (ListView)findViewById(R.id.listView);
        Intent i1 = getIntent();
        fID = i1.getIntExtra("key",0);

        assetHelper= new DBHelper(this);
        sd=assetHelper.getWritableDatabase();

        dbEmp = new DBHelperEmp(this);
        dataEmp = dbEmp.getWritableDatabase();
        Cursor cursor = dataEmp.query(DBHelperEmp.TABLE_EMP,
                new String[]{DBHelperEmp.EMP_NAME}, null,null, null, null, null);//group by, having ,order by
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //Toast.makeText(this, "Emp Name is " + cus.getString(0) + " Emp Unit is  " + cus.getString(1), Toast.LENGTH_LONG).show();
            enameList.add(cursor.getString(0));

            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter<String> ename = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,enameList);
        listView.setAdapter(ename);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String empName=parent.getItemAtPosition(position).toString();

                ContentValues cvl1 = new ContentValues();

                cvl1.put(assetHelper.ASSET_ALLOC,empName);

                long d =  sd.update(assetHelper.TABLE_ASSET, cvl1, assetHelper._ID+"=" + fID,null);



                //Cursor cursor1 = dataEmp.query();

                Cursor phoneNo = dataEmp.rawQuery("select "+ DBHelperEmp.EMP_PHONE
                        + " from " + DBHelperEmp.TABLE_EMP
                        +" where " + DBHelperEmp.EMP_NAME + " =?", new String[]{empName});
                //int nameIndex = phoneNo.getColumnIndexOrThrow(DBHelperEmp.EMP_PHONE);
              //  String ph = phoneNo.getString(phoneNo.getColumnIndex("phoneNumber"));
               // Toast.makeText(DisplayEmpList.this,"Phone no:"+ph,Toast.LENGTH_LONG).show();
                //OR
              //  Cursor cursor1;
                //return dataEmp.rawQuery("select phoneNumber from " + DBHelperEmp.TABLE_EMP + " where empName = "+DBHelperEmp.EMP_NAME,null);

                Toast.makeText(DisplayEmpList.this,"Update opr" + d,Toast.LENGTH_SHORT).show();
                //sd.close();
                Intent i2 = new Intent(DisplayEmpList.this,DashBoard.class);
                startActivity(i2);



            }
        });


    }
}
