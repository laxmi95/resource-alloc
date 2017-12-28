package com.example.laxmi.assessmentproject;


        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.Menu;
        import android.view.MenuItem;

        import java.util.ArrayList;
        import java.util.Arrays;

public class DashBoard extends AppCompatActivity {


    //Cursor cus = sqldb.query(DBHelper.TABLE_ASSET, new String[]{DBHelper.EMP_NAME, DBHelper.UNIT_NAME}, DBHelper.EMP_NAME + "=?", new String[]{t1.getText().toString()}, null, null, null);//group by, having ,order by
    //      cus.moveToFirst();
    ArrayList<String> data1 = new ArrayList<String>(Arrays.asList("Data"));
    ArrayList<String> data3 = new ArrayList<String>(Arrays.asList("Data"));
    ArrayList<String> data5 = new ArrayList<String>(Arrays.asList("Data"));
    ArrayList<Integer> data2 = new ArrayList<Integer>(Arrays.asList(R.drawable.newimg,R.drawable.newimg));
    //ArrayList<Button> data4 = new ArrayList<Button>(Arrays.asList(allocate));

    DBHelper helper;
    SQLiteDatabase sqldb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        helper= new DBHelper(this);
        sqldb = helper.getWritableDatabase();

        RecyclerView rv = (RecyclerView)findViewById(R.id.recView);

        //to display the CardViews dynamically
        Cursor cus = sqldb.query(DBHelper.TABLE_ASSET,
                new String[]{DBHelper.ASSET_MAKE, DBHelper._ID,DBHelper.ASSET_ALLOC}, null,null, null, null, null);//group by, having ,order by
        cus.moveToFirst();
        while (!cus.isAfterLast()) {
            //Toast.makeText(this, "Emp Name is " + cus.getString(0) + " Emp Unit is  " + cus.getString(1), Toast.LENGTH_LONG).show();
                data1.add(cus.getString(0));
           data3.add(cus.getString(1));
           data5.add(cus.getString(2));
            data2.add(R.drawable.newimg);
            cus.moveToNext();
        }
        cus.close();



        RecyclerAdapter ra = new RecyclerAdapter(data1,data2,data3,data5,this);
        //rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(ra);
    }



//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();

//        inflater.inflate(R.menu.layout_menus, menu);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(final MenuItem item){
        switch (item.getItemId()){
            case R.id.add_asset:
                Intent intent = new Intent(DashBoard.this,AddAssets.class);
                startActivity(intent);
                break;
            case R.id.add_employee:
                Intent intent1 = new Intent(DashBoard.this,AddEmployee.class);
                startActivity(intent1);
                break;
            case R.id.remove_asset:
                //sqldb.execSQL("delete from " + DBHelper.TABLE_ASSET + " where" + DBHelper.ASSET_MAKE + " ='Apple';");
                Intent intent2 = new Intent(DashBoard.this,RemoveAssert.class);
                startActivity(intent2);
                break;

            case R.id.update_pin:
                Intent intent3 = new Intent(DashBoard.this,UpdatePin.class);
                startActivity(intent3);
                break;
        }

        return true;
    }
}
