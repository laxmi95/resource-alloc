package com.example.laxmi.assessmentproject;


        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class RemoveAssert extends AppCompatActivity {

    DBHelper helper;
    //SQLiteDatabase sqLiteDatabase;

    EditText a_id;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_assert);
        helper = new DBHelper(RemoveAssert.this);


        a_id= (EditText) findViewById(R.id.asset_id);
        delete = (Button) findViewById(R.id.delete);

        //final int ass_id = Integer.parseInt(a_id.getText().toString());

        delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final int ass_id = Integer.parseInt(a_id.getText().toString());
                helper.deleteAssetTableID(ass_id);
                Toast.makeText(RemoveAssert.this,"Data delete",Toast.LENGTH_SHORT).show();
                Intent in1 = new Intent(RemoveAssert.this,DashBoard.class);
                //Toast.makeText(RemoveAssert.this,"Data after delete",Toast.LENGTH_SHORT).show();
                startActivity(in1);

            }
        });

    }
}
