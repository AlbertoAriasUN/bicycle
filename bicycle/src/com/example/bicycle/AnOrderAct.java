package com.example.bicycle;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AnOrderAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anorderact);		
		
		int OrderID = getIntent().getIntExtra("OrderID", 0);
		// use this orderId to ask server for detail information
		
		Button button = (Button)findViewById(R.id.AnorderAct_But);
//		if (getIntent().getStringExtra("charactar").equals("driver")) {
//			button.setText("申请订单");
//		}
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO apply the order
				
//				Toast.makeText(AnOrderAct.this, "已申请该订单，请等待对方确认", Toast.LENGTH_LONG).show();
			}
		});
		
		// what are these?
		ListView listview = (ListView)findViewById(R.id.AnorderAct_Listview);
		List<String> data = new ArrayList<String>();
        data.add("Lin");
        data.add("Wangyuan");
        data.add("hehe");
        
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data));
	}
}
