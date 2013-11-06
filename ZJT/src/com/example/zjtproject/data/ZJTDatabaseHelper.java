package com.example.zjtproject.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.Toast;

/**
 * SQLiteOpenHelper是一个辅助类，用来管理数据库的创建和版本他，它提供两个方面的功能
 * 第一，getReadableDatabase()、getWritableDatabase
 * ()可以获得SQLiteDatabase对象，通过该对象可以对数据库进行操作
 * 第二，提供了onCreate()、onUpgrade()两个回调函数，允许我们再创建和升级数据库时，进行自己的操作
 */
public class ZJTDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DBNAME = "ZJT.db";

    /**
     * 在SQLiteOpenHelper的子类当中，必须有该构造函数
     * 
     * @param context
     *            上下文对象
     * @param name
     *            数据库名称
     * @param factory
     * @param version
     *            当前数据库的版本，值必须是整数并且是递增的状态
     */
    public ZJTDatabaseHelper(Context context, String name,
            CursorFactory factory, int version) {
        // 必须通过super调用父类当中的构造函数
        super(context, DBNAME, factory, version);
    }

    public ZJTDatabaseHelper(Context context, String name, int version) {
        this(context, DBNAME, null, version);
    }

    public ZJTDatabaseHelper(Context context, String name) {
        this(context, DBNAME, VERSION);
    }

    // 该函数是在第一次创建的时候执行，实际上是第一次得到SQLiteDatabase对象的时候才会调用这个方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        System.out.println("create a database");
        // execSQL用于执行SQL语句
        db.execSQL("create table IF NOT EXISTS carinfo (CarRegion varchar(20),CarNo varchar(20),CarRegionNo varchar(30),EngineNo varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        System.out.println("upgrade a database");
    }
    
    static public void insertData(Context context, String CarRegion,
            String CarNo,String EngineNo) {
        List<String[]> allData =queryAllData(context);
        for(String[] data : allData) {
            if(data[1].equals(CarNo)) {
                Toast.makeText(context, "车牌号已经存在。", Toast.LENGTH_LONG).show();
                return;
            }
        }
     // 创建ContentValues对象   
        ContentValues values = new ContentValues();   
        // 向该对象中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致   
        values.put("CarRegion", CarRegion);   
        values.put("CarNo", CarNo);   
        values.put("EngineNo", EngineNo);  
        values.put("CarRegionNo", CarRegion+CarNo);  
        
        // 创建DatabaseHelper对象   
        ZJTDatabaseHelper dbHelper = new ZJTDatabaseHelper(context,   
                DBNAME, VERSION);   
        // 得到一个可写的SQLiteDatabase对象   
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();   
        // 调用insert方法，就可以将数据插入到数据库当中   
        // 第一个参数:表名称   
        // 第二个参数：SQl不允许一个空列，如果ContentValues是空的，那么这一列被明确的指明为NULL值   
        // 第三个参数：ContentValues对象   
        sqliteDatabase.insert("carinfo", null, values);   

    }
    static public void updateData(Context context,String CarNo,String EngineNo)
    {
     // 创建一个DatabaseHelper对象   
        ZJTDatabaseHelper dbHelper = new ZJTDatabaseHelper(context,   
                DBNAME, VERSION); 
        // 得到一个可写的SQLiteDatabase对象   
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();   
        // 创建一个ContentValues对象   
        ContentValues values = new ContentValues();   
        values.put("EngineNo", EngineNo);  
        // 调用update方法   
        // 第一个参数String：表名   
        // 第二个参数ContentValues：ContentValues对象   
        // 第三个参数String：where字句，相当于sql语句where后面的语句，？号是占位符   
        // 第四个参数String[]：占位符的值   
        sqliteDatabase.update("carinfo", values, "CarNo=?", new String[] { CarNo });  
//        values.put("CarRegionNo", CarRegion+CarNo);  
    }
    static public void queryData(Context context)
    {
        String id = null;   
        String name = null;   
        //创建DatabaseHelper对象   
        ZJTDatabaseHelper dbHelper = new ZJTDatabaseHelper(context,   
                DBNAME, VERSION);   
        // 得到一个只读的SQLiteDatabase对象   
        SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase();   
        // 调用SQLiteDatabase对象的query方法进行查询，返回一个Cursor对象：由数据库查询返回的结果集对象   
        // 第一个参数String：表名   
        // 第二个参数String[]:要查询的列名   
        // 第三个参数String：查询条件   
        // 第四个参数String[]：查询条件的参数   
        // 第五个参数String:对查询的结果进行分组   
        // 第六个参数String：对分组的结果进行限制   
        // 第七个参数String：对查询的结果进行排序   
        Cursor cursor = sqliteDatabase.query("carinfo", new String[] { "id",   
                "name" }, "id=?", new String[] { "1" }, null, null, null);   
//        Cursor cursor = sqliteDatabase.rawQuery("select * from carinfo;",
//                null);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false   
        while (cursor.moveToNext()) {   
            id = cursor.getString(cursor.getColumnIndex("id"));   
            name = cursor.getString(cursor.getColumnIndex("name"));   
        }   
        System.out.println("-------------select------------");   
        System.out.println("id: "+id);   
        System.out.println("name: "+name);   
    }
    
    static public List<String[]> queryAllData(Context context) {
        List<String[]> resu = new ArrayList<String[]>();
        // 创建DatabaseHelper对象
        ZJTDatabaseHelper dbHelper = new ZJTDatabaseHelper(context, DBNAME,
                VERSION);
        // 得到一个只读的SQLiteDatabase对象
        SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqliteDatabase.rawQuery("select * from carinfo;", null);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            String CarRegion = cursor.getString(cursor
                    .getColumnIndex("CarRegion"));
            String CarNo = cursor.getString(cursor.getColumnIndex("CarNo"));
            String EngineNo = cursor.getString(cursor
                    .getColumnIndex("EngineNo"));
            String[] aaaa = new String[] { CarRegion,CarNo,EngineNo
            };
            resu.add(aaaa);
        }
        return resu;
    }

    static public void deleteData(Context context, String CarNo) {
        // 创建DatabaseHelper对象
        ZJTDatabaseHelper dbHelper = new ZJTDatabaseHelper(context, DBNAME,
                VERSION);
        // 获得可写的SQLiteDatabase对象
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
        // 调用SQLiteDatabase对象的delete方法进行删除操作
        // 第一个参数String：表名
        // 第二个参数String：条件语句
        // 第三个参数String[]：条件值
        sqliteDatabase.delete("carinfo", "CarRegionNo=?", new String[] { CarNo });
    }
}
