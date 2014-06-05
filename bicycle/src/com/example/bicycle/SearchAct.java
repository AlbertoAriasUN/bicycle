package com.example.bicycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SearchAct extends Activity {
	private List<HashMap<String, Object>> OrderData = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchact);
		
		Button searchBut = (Button)findViewById(R.id.SearchAct_Search);
		final TextView noInfoText = (TextView)findViewById(R.id.NoInfo);
		final ListView listview = (ListView)findViewById(R.id.SearchAct_Listview);
		
		// what should I sent to server?
		///////////////////////////////////////////////////////
		HashMap<String, Object> Map = new HashMap<String, Object>();
		Map.put("LocaA", "至善园");
		Map.put("LocaB", "图书馆");
		Map.put("Time", "6月22日，9:00");
		OrderData.add(Map);
		HashMap<String, Object> MapB = new HashMap<String, Object>();
		MapB.put("LocaA", "慎思园");
		MapB.put("LocaB", "公共\n教学楼");
		MapB.put("Time", "6月25日，9:00");
		OrderData.add(MapB);
		HashMap<String, Object> mapC = new HashMap<String, Object>();
		mapC.put("LocaA", "慎思园");
		mapC.put("LocaB", "北实\n验楼");
		mapC.put("Time", "6月30日，9:00");
		OrderData.add(mapC);
		///////////////////////////////////////////////////////
		
		adapter = new SimpleAdapter(this, OrderData, R.layout.orderitem,
				new String[]{"LocaA", "LocaB", "Time"}, new int[]{R.id.LocaA, R.id.LocaB, R.id.Time});
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchAct.this,AnOrderAct.class);  
			    intent.putExtra("OrderID", 2); // the orderId should be got from server
//			    intent.putExtra("charactar", "driver");
				startActivity(intent);
			}
		});
		
		final EditText searchText = (EditText)findViewById(R.id.SearchAct_Keyword);
		searchText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO 自动生成的方法存根
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO 自动生成的方法存根
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO 自动生成的方法存根
				if (arg0.toString().equals("")) {
					noInfoText.setVisibility(View.GONE);
					listview.setVisibility(View.VISIBLE);
					listview.setAdapter(adapter);
				}
			}
		});
		
		
		searchBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String searchInfo = searchText.getText().toString();
				if (!searchInfo.equals("")) {
					List<HashMap<String, Object>> OrderDataForSearch = new ArrayList<HashMap<String, Object>>();
					for (HashMap<String, Object> map : OrderData) {
						if (map.get("LocaA").toString().contains(searchInfo) || map.get("LocaB").toString().contains(searchInfo)) {
							OrderDataForSearch.add(map);
						}
					}
					if (OrderDataForSearch.size() != 0) {
						noInfoText.setVisibility(View.GONE);
						listview.setVisibility(View.VISIBLE);
						SimpleAdapter adapter2 = new SimpleAdapter(SearchAct.this, OrderDataForSearch, R.layout.orderitem,
								new String[]{"LocaA", "LocaB", "Time"}, new int[]{R.id.LocaA, R.id.LocaB, R.id.Time});
						listview.setAdapter(adapter2);
					} else {
						listview.setVisibility(View.GONE);
						noInfoText.setVisibility(View.VISIBLE);
					}
				}
			}
		});
		
	}

}
