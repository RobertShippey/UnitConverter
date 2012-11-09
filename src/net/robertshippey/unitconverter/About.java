package net.robertshippey.unitconverter;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class About extends Activity {

    private final int menuConverter = 0;
	private final int menuUnits = 1;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        
        ((Button)findViewById(R.id.email)).setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent email = new Intent(Intent.ACTION_SEND);
        		email.putExtra(Intent.EXTRA_EMAIL, new String[]{getResources().getString(R.string.DevEmail).toString().toString()});
        		email.putExtra(Intent.EXTRA_SUBJECT, "Regarding Unit Converter");
        		email.setType("text/html");
        		startActivity(Intent.createChooser(email, "Send Email..."));
        	}
        });
        
        ((Button)findViewById(R.id.website)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent site = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.DevWebsite)));
				startActivity(site);
			}});
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.activity_unit_convert, menu);
		menu.add(0, menuConverter, 1, getResources().getString(R.string.converter).toString());
		menu.add(0, menuUnits, 2, getResources().getString(R.string.changeUnits).toString());
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		Intent page;
		switch (item.getItemId()) {
		case menuConverter:
			page = new Intent(About.this, UnitConvert.class);
			break;
		case menuUnits:
			page = new Intent(About.this, ChangeUnits.class);
			break;
		default:
			return false;
		}
		this.finish();
		startActivity(page);
		return true;
	}
}
