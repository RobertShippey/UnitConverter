package net.robertshippey.unitconverter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UnitConvert extends Activity implements OnClickListener {
	private TextView fromText;
	private EditText fromUnit;
	private String convertFrom;

	private TextView toText;
	private EditText toUnit;
	private String convertTo;

	private Button done;
	private final int menuAbout = 0;
	private final int menuUnits = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unit_convert);

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
			convertFrom = "in";
			convertTo = "cm";
		}

		fromText.setText(getResources().getString(R.string.convertFrom)
				.toString() + " " + convertFrom + ":");
		toText.setText(getResources().getString(R.string.convertTo).toString()
				+ " " + convertTo + ":");

		toUnit.setText("0 " + convertTo);

		done.setOnClickListener(this);

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

	@Override
	public void onClick(View v) {
		try {
			toUnit.setText(getResources().getString(R.string.processing)
					.toString());
			float from = Float.parseFloat(fromUnit.getText().toString());
			float to = 0.0f;
			float factor = 0.0f;

			if (convertFrom.equals("in")) {
				if (convertTo.equals("cm")) {
					factor = 2.54f;
				} else if (convertTo.equals("ft")) {
					factor = 0.0833333f;
				} else if (convertTo.equals("mm")) {
					factor = 25.4f;
				}
			} else if (convertFrom.equals("ft")) {
				if (convertTo.equals("mm")) {
					factor = 304.8f;
				} else if (convertTo.equals("cm")) {
					factor = 30.48f;
				} else if (convertTo.equals("in")) {
					factor = 12.0f;
				}
			} else if (convertFrom.equals("cm")) {
				if (convertTo.equals("mm")) {
					factor = 10.0f;
				} else if (convertTo.equals("ft")) {
					factor = 0.032808399f;
				} else if (convertTo.equals("ft")) {
					factor = 0.32808399f;
				}
			} else if (convertFrom.equals("mm")) {
				if (convertTo.equals("cm")) {
					factor = 0.1f;
				} else if (convertTo.equals("ft")) {
					factor = 0.0032808399f;
				} else if (convertTo.equals("in")) {
					factor = 0.0393700787f;
				}
			}
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
