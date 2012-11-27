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
		new Row("in", "cm", 2.54),
		new Row("in", "ft", 0.083333333),
		new Row("in", "mm", 25.4),	
		new Row("ft", "mm", 304.8),
		new Row("ft", "cm", 30.48),
		new Row("ft", "in", 12.0),
		new Row("cm", "mm", 10.0),
		new Row("cm", "ft", 0.032808399),
		new Row("cm", "in", 0.393700787),
		new Row("mm", "cm", 0.1),
		new Row("mm", "ft", 0.0032808399999),
		new Row("mm", "in", 0.0393700787)};
	
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
		public double factor;
		
		public Row (String f, String t, double fa){
			from = f;
			to = t;
			factor = fa;
		}
	}

}
