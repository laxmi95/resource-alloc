package com.example.laxmi.assessmentproject;



        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper; //Readable or Writable db
        import android.content.Context;
        import android.util.Log;
        import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    //Context ctx;



    public static String DB_NAME="assetmgmt";
    public static int DB_VERSION=1;
    public static String TABLE_EMP="employee";
    public static String TABLE_ASSET = "asset";

    //emp columns
//    public static  String EMP_ID="empID";
//    public static String EMP_NAME="empName";
//    public static String EMP_EMAIL = "emailID";
//    public static String EMP_UNIT ="unit";
//    public static String EMP_PHONE = "phoneNumber";

    //asset columns
    public static  String _ID="assetID";
    public static  String ASSET_MAKE="assetMake";
    public static  String ASSET_YEAR="yearOfMaking";
    public static  String ASSET_ALLOC="assetAllocTo";
    public static  String ASSET_ALLOC_TILL="assetAllocTill";

    static long data;
    static  long query1;
//     private final String CREATE_EMP_TABLE = "create table " + TABLE_EMP + "(" + EMP_ID + " integer primary key autoincrement,"
//                                                                    + EMP_NAME + " text,"
//                                                                    + EMP_EMAIL + " text,"
//                                                                    + EMP_UNIT + " text,"
//                                                                     + EMP_PHONE + " text);";

    private final String CREATE_ASSET_TABLE = "create table " + TABLE_ASSET + "(" + _ID + " integer primary key autoincrement,"
            + ASSET_MAKE + " text,"
            + ASSET_YEAR + " text,"
            + ASSET_ALLOC + " text default \"NA\","
            + ASSET_ALLOC_TILL + " text);";


    SQLiteDatabase db1 ;

    public DBHelper(Context ctx){
        super(ctx,DB_NAME,null,DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) { //gives ref to SQLite db to create a db (creates instance of db)
        //String q1="create table " +DBHelper.TABLE_EMP + "(" +DBHelper.EMP_NAME + "  text,"+ DBHelper.UNIT_NAME + " text);";
//        String CREATE_ASSET_TABLE = "create table " + TABLE_ASSET + "(" + _ID + " integer primary key autoincrement,"
//                + ASSET_MAKE + " text,"
//                + ASSET_YEAR + " text,"
//                + ASSET_ALLOC + " text default 'NA',"
//                + ASSET_ALLOC_TILL + " text);";
        Log.d("Query:",CREATE_ASSET_TABLE);
        db.execSQL(CREATE_ASSET_TABLE);
        // db.execSQL(CREATE_EMP_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //whenever there is change in DB version
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMP);
        onCreate(db);
    }

    public void createAssetTable(Assets asset){
        db1 = this.getWritableDatabase();

        ContentValues vcl1 = new ContentValues(); //key value
        //vcl.put(ASSET_ID,asset.getAssetId());
        vcl1.put(ASSET_MAKE,asset.getAssetMake());
        vcl1.put(ASSET_YEAR,asset.getYearOfMaking());
        vcl1.put(ASSET_ALLOC,asset.getAllocatedTo());
        vcl1.put(ASSET_ALLOC_TILL,asset.getAllocatedTill());
        data = db1.insert(TABLE_ASSET, null, vcl1);
        db1.close();
    }

    public void deleteAssetTableID(int assetID){
        SQLiteDatabase data2;
        data2 = this.getWritableDatabase();
        data2.delete(TABLE_ASSET, _ID+"=" + assetID,null);
        //data2.close();
    }

//    public void deleteAssetTableID(){
//        db1 = this.getWritableDatabase();
//        db1.execSQL("delete * from "+ TABLE_ASSET+ " ;");
//        //return db1.delete(TABLE_ASSET, _ID + "="+ assetID + " ;",null) > 0;
//        db1.close();
//
//    }
}
