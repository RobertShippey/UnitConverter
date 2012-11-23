package net.robertshippey.unitconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class UnitDatabase implements BaseColumns {
	public static final String tableName = "UnitsTBL";
	public static final String columnFrom = "unitFrom";
	public static final String columnTo = "unitTo";
	public static final String columnFactor = "factor";
	
	private static Row[] data = {
		new Row("in", "cm", 2.54f),
		new Row("in", "ft", 0.0833333f),
		new Row("in", "mm", 25.4f),	
		new Row("ft", "mm", 304.8f),
		new Row("ft", "cm", 30.48f),
		new Row("ft", "in", 12.0f),
		new Row("cm", "mm", 10.0f),
		new Row("cm", "ft", 0.032808399f),
		new Row("cm", "in", 0.32808399f),
		new Row("mm", "cm", 0.1f),
		new Row("mm", "ft", 0.0032808399f),
		new Row("mm", "in", 0.0393700787f)};
	
	private UnitDatabase() {}
	
	private static final String SQLCreateTable = "CREATE TABLE IF NOT EXISTS " + tableName +
			" (" + _ID + " INTEGER PRIMARY KEY," +
			columnFrom + " TEXT, " + 
			columnTo + " TEXT, " + 
			columnFactor + " REAL, UNIQUE(" + columnFrom + ", " + columnTo + "));";
	
	public static class DBHelper extends SQLiteOpenHelper {
		public static final int DATABASE_VERSION = 1;
		public static final String DATABASE_NAME = "Units.db";
		
		public DBHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		public void onCreate(SQLiteDatabase db){
			db.execSQL(SQLCreateTable);
			
			for(int x=0; x<data.length;x++){
				ContentValues values = new ContentValues();
				values.put(columnFrom, data[x].from);
				values.put(columnTo, data[x].to);
				values.put(columnFactor, data[x].factor);
				
				db.insert(tableName, null, values);
			}
			data = null;
		}
		
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
			//TODO
		}
		
		public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}
		
		
	}
	
	private static class Row {
		public String from;
		public String to;
		public float factor;
		
		public Row (String f, String t, float fa){
			from = f;
			to = t;
			factor = fa;
		}
	}

}
