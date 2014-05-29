package com.example.bicycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HistoryAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historyact);		
		
		Button orderBut = (Button)findViewById(R.id.HistoryAct_order);
		orderBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HistoryAct.this, OrderAct.class);  
			    startActivity(intent); 
			}
		});
		
		ListView listview = (ListView)findViewById(R.id.HistoryAct_Listview);
		List<HashMap<String,Object>> OrderData = new ArrayList<HashMap<String,Object>>();
		
		///////////////////////////////////////////////////////
		HashMap<String,Object> Map = new HashMap<String,Object>();
		Map.put("LocaA", "三饭");
		Map.put("LocaB", "图书馆");
		Map.put("Time", "5月22日，9:00");
		OrderData.add(Map);
		///////////////////////////////////////////////////////
		SimpleAdapter adapter = new SimpleAdapter(this, OrderData, R.layout.orderitem, new String[]{"LocaA","LocaB","Time"}, new int[]{R.id.LocaA,R.id.LocaB,R.id.Time});
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HistoryAct.this,AnOrderAct.class);  
			    intent.putExtra("OrderID", 2);
				startActivity(intent);
			}});
		
	}
}
