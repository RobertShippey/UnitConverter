package net.robertshippey.unitconverter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UnitDatabase {
	public static final String tableName = "Units";
	public static final String columnFrom = "from";
	public static final String columnTo = "to";
	public static final String columnFactor = "factor";
	
	private static final Row[] data = {	new Row("cm", "mm", 10.0f)};
		
	private UnitDatabase() {}
	
	private static final String SQLCreateTable = "CREATE TABLE IF NOT EXISTS " + tableName +
			" (" + columnFrom + " TEXT, " + 
			columnTo + " TEXT, " + 
			columnFactor + " REAL, PRIMARY KEY(" + columnFrom + ", " + columnTo + "));";
	
	public class DBHelper extends SQLiteOpenHelper {
		public static final int DATABASE_VERSION = 1;
		public static final String DATABASE_NAME = "Units.db";
		
		public DBHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		public void onCreate(SQLiteDatabase db){
			db.execSQL(SQLCreateTable);
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
