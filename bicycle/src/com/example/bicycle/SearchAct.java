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

public class SearchAct extends Activity {

	Button searchBut;
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchact);		
		
		searchBut = (Button)findViewById(R.id.SearchAct_Search);
		searchBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		listview = (ListView)findViewById(R.id.SearchAct_Listview);
		List<HashMap<String,Object>> OrderData = new ArrayList<HashMap<String,Object>>();
		
		///////////////////////////////////////////////////////
		HashMap<String,Object> Map = new HashMap<String,Object>();
		Map.put("LocaA", "三饭");
		Map.put("LocaB", "图书馆");
		Map.put("Time", "5月22日，9:00");
		OrderData.add(Map);
		HashMap<String,Object> MapB = new HashMap<String,Object>();
		MapB.put("LocaA", "慎6");
		MapB.put("LocaB", "实验楼");
		MapB.put("Time", "5月30日，9:00");
		OrderData.add(MapB);
		///////////////////////////////////////////////////////
		SimpleAdapter adapter = new SimpleAdapter(this, OrderData, R.layout.orderitem, new String[]{"LocaA","LocaB","Time"}, new int[]{R.id.LocaA,R.id.LocaB,R.id.Time});
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchAct.this,AnOrderAct.class);  
			    intent.putExtra("OrderID", 3);
				startActivity(intent);
			}});
		
	}

}
