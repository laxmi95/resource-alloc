package com.example.laxmi.assessmentproject;



        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

public class DBHelperEmp extends SQLiteOpenHelper {

    //Context ctx;



    public static String DB_NAME="assetmgmt2";
    public static int DB_VERSION=1;
    public static String TABLE_EMP="employee";
    public static String TABLE_ASSET = "asset";

    //emp columns
    public static  String EMP_ID="empID";
    public static String EMP_NAME="empName";
    public static String EMP_EMAIL = "emailID";
    public static String EMP_UNIT ="unit";
    public static String EMP_PHONE = "phoneNumber";

    // static long data;
    static  long query1;
    private final String CREATE_EMP_TABLE = "create table " + TABLE_EMP + "(" + EMP_ID + " integer primary key autoincrement,"
            + EMP_NAME + " text,"
            + EMP_EMAIL + " text,"
            + EMP_UNIT + " text,"
            + EMP_PHONE + " text);";


    SQLiteDatabase db1 ;

    public DBHelperEmp(Context ctx){
        super(ctx,DB_NAME,null,DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) { //gives ref to SQLite db to create a db (creates instance of db)

        db.execSQL(CREATE_EMP_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //whenever there is change in DB version
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMP);
        onCreate(db);
    }

    public void createEmp(Employee employee){

        db1 = this.getWritableDatabase();

        ContentValues vcl = new ContentValues(); //key value

        vcl.put(EMP_NAME,employee.getEmpName());
        vcl.put(EMP_EMAIL,employee.getEmailID());
        vcl.put(EMP_UNIT,employee.getUnit());
        vcl.put(EMP_PHONE, employee.getPhoneNumber());
        query1 = db1.insert(TABLE_EMP, null, vcl);
        db1.close();
    }

    public Cursor fetchMessage(String TABLE_EMP, String EMP_PHONE){
        return db1.rawQuery("select phoneNumber from " + TABLE_EMP + " where empName = "+EMP_NAME,null);
    }



}
