package net.robertshippey.unitconverter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeUnits extends Activity {
	
	private Spinner from;
	private Spinner to;
	
	private TextView fromText;
	private TextView toText;
	
	private Button done;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_units);
        
        from = (Spinner) findViewById(R.id.ChangeFromSpinner);
        to = (Spinner) findViewById(R.id.ChangeToSpinner);
        
        fromText = (TextView) findViewById(R.id.ChangeFromText);
        toText = (TextView) findViewById(R.id.ChangeToText);
        
        fromText.setText(getResources().getString(R.string.convertFrom).toString() + ":");
        toText.setText(getResources().getString(R.string.convertTo).toString() + ":");
        
        done = (Button) findViewById(R.id.ChangeDone);

		ArrayAdapter<CharSequence> fromAdapter = ArrayAdapter.createFromResource(
				this, R.array.units, android.R.layout.simple_spinner_item);
		fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		from.setAdapter(fromAdapter);
		
		ArrayAdapter<CharSequence> toAdapter = ArrayAdapter.createFromResource(
				this, R.array.units, android.R.layout.simple_spinner_item);
		toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		to.setAdapter(toAdapter);
		
		done.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				
				String selectedFrom = from.getSelectedItem().toString();
				String selectedTo = to.getSelectedItem().toString();
				
				if(selectedFrom.equals(selectedTo)){
					Toast.makeText(ChangeUnits.this, getResources().getString(R.string.changeUnitsSilly).toString(), Toast.LENGTH_LONG).show();
				} else{
					Intent converter = new Intent(ChangeUnits.this, UnitConvert.class);
				
					converter.putExtra("from", selectedFrom);
					converter.putExtra("to", selectedTo);
				
					startActivity(converter);
					ChangeUnits.this.finish();
				}
			}});
    }
}
