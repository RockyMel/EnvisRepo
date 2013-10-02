package com.envisprototype.view;

import java.util.List;
import com.envisprototype.R;
import com.envisprototype.model.set.SetInterface;
import com.envisprototype.model.set.SetListModel;
import com.envisprototype.view.model.SetListAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class SetListActivity extends Activity {
	public List<SetInterface> sets;
	public static SetListAdapter sla;
	public static ListView lv;
	private Spinner spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_list);
		
		Button addsensor = (Button)findViewById(R.id.Add);
		addsensor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(view.getContext(),InputIDActivity.class);
				intent.putExtra("mode", "set");
				view.getContext().startActivity(intent); 
			}
		});
		
		sets = SetListModel.getSingletonInstance().getSetList();
		spinner = (Spinner) findViewById(R.id.spinner1);
		//addListenerOnSpinnerItemSelection();
		//Collections.sort(sets,new SortBySetAlph());
		sla = new SetListAdapter(this,0,sets);
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(sla);
		
	}

	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_list, menu);
		return true;
	}

}
