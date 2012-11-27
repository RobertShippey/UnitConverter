package net.robertshippey.unitconverter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UnitConvert extends Activity implements OnClickListener {
	
	public static final String Prefs_FileName = "UnitConverterPreferences";
	
	private TextView fromText;
	private EditText fromUnit;
	private String convertFrom;

	private TextView toText;
	private EditText toUnit;
	private String convertTo;

	private Button done;
	private final int menuAbout = 0;
	private final int menuUnits = 1;

	private SQLiteDatabase db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unit_convert);
		
		db = (new UnitDatabase.DBHelper(this)).getWritableDatabase();

		fromText = (TextView) findViewById(R.id.unitConverterFromTextView);
		fromUnit = (EditText) findViewById(R.id.UnitConverterFromEditText);

		toText = (TextView) findViewById(R.id.UnitConverterToTextView);
		toUnit = (EditText) findViewById(R.id.UnitConverterToEditText);

		done = (Button) findViewById(R.id.UnitConverterDoneButton);

		try {
			Bundle intentExtras = getIntent().getExtras();

			convertFrom = intentExtras.getString("from");
			convertTo = intentExtras.getString("to");

		} catch (NullPointerException npe) {
			SharedPreferences savedPrefs = getSharedPreferences(UnitConvert.Prefs_FileName, 0);
			convertFrom = savedPrefs.getString("from", "in");
			convertTo = savedPrefs.getString("to", "cm");
		}

		fromText.setText(getResources().getString(R.string.convertFrom)
				.toString() + " " + convertFrom + ":");
		toText.setText(getResources().getString(R.string.convertTo).toString()
				+ " " + convertTo + ":");

		toUnit.setText("0 " + convertTo);

		done.setOnClickListener(this);

	}
	
	public void onStop(){
		db.close();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.activity_unit_convert, menu);
		menu.add(0, menuUnits, 1, getResources()
				.getString(R.string.changeUnits).toString());
		menu.add(0, menuAbout, 2, getResources().getString(R.string.about)
				.toString());
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		Intent page;
		switch (item.getItemId()) {
		case menuAbout:
			page = new Intent(UnitConvert.this, About.class);
			break;
		case menuUnits:
			page = new Intent(UnitConvert.this, ChangeUnits.class);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		startActivity(page);
		return true;
	}

	public void onClick(View v) {
		try {
			toUnit.setText(getResources().getString(R.string.processing)
					.toString());
			double from = Double.parseDouble(fromUnit.getText().toString());
			double to = 0.0;
			double factor = 0.0;
			
			Cursor c = db.query(
				    UnitDatabase.tableName, 
				    new String[]{UnitDatabase.columnFactor},
				    UnitDatabase.columnFrom + " = ? AND " + UnitDatabase.columnTo + " = ?",
				    new String[]{convertFrom, convertTo},
				    null, null, null);
			
			c.moveToFirst();
			factor = c.getDouble(0);
			c.close();

			to = from * factor;
			if (to == 0) {
				toUnit.setText(getResources().getString(R.string.error)
						.toString());
			} else {
				toUnit.setText(String.valueOf(to + " " + convertTo));
			}
		} catch (NumberFormatException nfe) {
			Toast.makeText(UnitConvert.this,
					getResources().getString(R.string.notanumber).toString(),
					Toast.LENGTH_LONG).show();
			toUnit.setText("--");
		} finally {
			fromUnit.requestFocus();
		}

	}
}
