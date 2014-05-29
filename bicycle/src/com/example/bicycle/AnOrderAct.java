package com.example.bicycle;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AnOrderAct extends Activity {

	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anorderact);		
		
		int OrderID = getIntent().getIntExtra("OrderID",0);
		String a = "";
		a += OrderID;
		Toast.makeText(AnOrderAct.this, a, Toast.LENGTH_SHORT).show();
		
		listview = (ListView)findViewById(R.id.AnorderAct_Listview);
		List<String> data = new ArrayList<String>();
        data.add("Lin");
        data.add("Wangyuan");
        data.add("hehe");
        
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data));
	}
}
