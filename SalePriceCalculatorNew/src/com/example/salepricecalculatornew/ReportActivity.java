package com.example.salepricecalculatornew;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class ReportActivity extends ListActivity {
	
		// ListView items and adapter to handle the list
	   private ArrayList<String> listItems = new ArrayList<String>();
	   private ArrayAdapter<String> listAdapter;
	   private static final String PREFS_NAME = "TestData";
	   private Button  clearButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
			// Parent method
	       super.onCreate(savedInstanceState);
	       Log.i(getClass().getSimpleName(), "onCreate");
	 
	       // Set layout view
	       setContentView(R.layout.activity_report);
	       
	       // Create listView adapter
	       listAdapter = new ArrayAdapter<String>(this, R.layout.report_list_item,listItems);
	       setListAdapter(listAdapter);
	     
	       clearButton = (Button)findViewById(R.id.button1);
	       clearButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// Clear data in SharedPreferences
		              SharedPreferences prefState = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
		              SharedPreferences.Editor editor = prefState.edit();
		              editor.clear();
		              editor.commit();
		              
		              // Clear data in ListView
		              listAdapter.clear();
				}
			});
	       
	 	   // Get test results from SharedPreferences
	       SharedPreferences prefState = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
	       int numQuestions = prefState.getInt(getPackageName() + ".numDiscounts", 0);
	       Log.i(getClass().getSimpleName(), "Num Discounts: " + numQuestions);
	       for (int i = 0; i < numQuestions; ++i) {

	    	   double origPrice = prefState.getInt(getPackageName()+ ".originalPrice" + i, 0);
		       int firstDisc = prefState.getInt(getPackageName()+ ".firstDiscount" + i, 0);
		       int secDisc = prefState.getInt(getPackageName()+ ".secondDiscount" + i, 0);
		     //  double tx = prefState.getInt(getPackageName()+ ".taxAmount" + i, 0);
		       double fprice = prefState.getInt(getPackageName()+".finalPrice" +i,0); 
		       double sprice = prefState.getInt(getPackageName()+".savedPrice" +i,0); 


	          // Add to list
	           StringBuilder str = new StringBuilder();
	           str.append("Discount Page #"+ (i+1)+'\n');
			   str.append("Original Price: $" + origPrice +'\n');
			   str.append("First Discount: " + firstDisc +"%" +'\n');
			   str.append("Second Discount: " + secDisc +"%" +'\n');
			  // str.append("Tax Amount: " + tx+'\n');
			   str.append("Final Price: $" + fprice+'\n');
			   str.append("Saved Amount: $" + sprice);
	           listItems.add(str.toString());
	       }   
	       // Get SharedPreferences name from XML string resource
	    //   String prefsName = getResources().getString(R.string.questionPrefs);

	       /*Bundle bundle =getIntent().getBundleExtra(getPackageName()+".discountBundle");
	       
		   //list results
		   int numDiscounts = bundle.getInt(getPackageName()+".numDiscounts", 0);
		   Log.i(getClass().getSimpleName(),"Num Discount Pages: " + numDiscounts);
		   for(int i = 0; i < numDiscounts; i++)
		   {
			   double origPrice = bundle.getDouble(getPackageName()+".originalPrice", 0);
			   double firstDisc = bundle.getDouble(getPackageName()+".firstDiscount", 0);
			   double secDisc = bundle.getDouble(getPackageName()+".secondDiscount", 0);
			   double tx = bundle.getDouble(getPackageName()+".taxAmount", 0);
			   Log.i(getClass().getSimpleName(),"Original Price: " + origPrice);
			   Log.i(getClass().getSimpleName(),"First Discount: " + firstDisc); 
			   Log.i(getClass().getSimpleName(),"Second Discount: " + secDisc);
			   Log.i(getClass().getSimpleName(),"Tax Amount: " + tx);
			   
			   //add to list
			   StringBuilder str = new StringBuilder();
			   str.append("Discount Page #"+ (i+1)+'\n');
			   str.append("Original Price: " + origPrice +'\n');
			   str.append("First Discount: " + firstDisc +'\n');
			   str.append("Second Discount: " + secDisc +'\n');
			   str.append("Tax Amount: " + tx);
			   //str.toString();			   
			   listItems.add(str.toString());			   		   
		   }*/		    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			Intent intent = new Intent(getApplicationContext(),ReportActivity.class);
			startActivity(intent);						
			return true;
		}
		else if(id == R.id.menuCalculator)
		{
			Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			   startActivity(intent);
			   return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	   protected void onPause() {
	       super.onPause();
	       Log.i(getClass().getSimpleName(), "onPause");
	   }	 

	   @Override
	   protected void onResume() {
	       super.onResume();
	       Log.i(getClass().getSimpleName(), "onResume");
	   }
	   
	 	   @Override
	   protected void onDestroy() {
	       super.onDestroy();
	       Log.i(getClass().getSimpleName(), "onDestroy");
	   }	 

	   @Override
	   protected void onRestart() {
	       super.onRestart();
	       Log.i(getClass().getSimpleName(), "onRestart");
	   }	 

	   @Override
	   protected void onStart() {
	       super.onStart();
	       Log.i(getClass().getSimpleName(), "onStart");
	   }	 

	   @Override
	   protected void onStop() {
	       super.onStop();
	       Log.i(getClass().getSimpleName(), "onStop");
	   }

}


/*package com.example.salepricecalculator;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class ReportActivity extends ListActivity {
	
		// ListView items and adapter to handle the list
	   private ArrayList<String> listItems = new ArrayList<String>();
	   private ArrayAdapter<String> listAdapter;
	   private static final String PREFS_NAME = "TestData";
	   private Button  clearButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
			// Parent method
	       super.onCreate(savedInstanceState);
	       Log.i(getClass().getSimpleName(), "onCreate");
	 
	       // Set layout view
	       setContentView(R.layout.activity_report);
	       
	       // Create listView adapter
	       listAdapter = new ArrayAdapter<String>(this, R.layout.report_list_item,listItems);
	       setListAdapter(listAdapter);
	     
	       clearButton = (Button)findViewById(R.id.button1);
	       clearButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// Clear data in SharedPreferences
		              SharedPreferences prefState = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
		              SharedPreferences.Editor editor = prefState.edit();
		              editor.clear();
		              editor.commit();
		              
		              // Clear data in ListView
		              listAdapter.clear();
				}
			});
	       
	 	   // Get test results from SharedPreferences
	       SharedPreferences prefState = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
	       int numDiscounts = prefState.getInt(getPackageName() + ".numDiscounts", 0);
	       Log.i(getClass().getSimpleName(), "Num Discounts: " + numDiscounts);
	       for (int i = 0; i < numDiscounts; ++i) {

	    	   double origPrice = prefState.getInt(getPackageName()+ ".originalPrice" + i, 0);
	    	   int firstDisc = prefState.getInt(getPackageName()+ ".firstDiscount" + i, 0);
	    	   int secDisc = prefState.getInt(getPackageName()+ ".secondDiscount" + i, 0);
	    	  // int tx = prefState.getInt(getPackageName()+ ".taxAmount" + i, 0);
	    	   double fprice = prefState.getInt(getPackageName()+".finalPrice" +i,0); 
	    	   double sprice = prefState.getInt(getPackageName()+".savedPrice" +i,0); 

	          // Add to list
	           StringBuilder str = new StringBuilder();
	           str.append("Discount Page #" + (i+1) + '\n');
			   str.append("Original Price: $" + origPrice + '\n');
			   str.append("First Discount: " + firstDisc + "%" + '\n');
			   str.append("Second Discount: " + secDisc + "%" + '\n');
			  // str.append("Tax Amount: " + tx+'\n');
			   str.append("Final Price: $" + fprice + '\n');
			   str.append("Saved Price: $" + sprice);
	           listItems.add(str.toString());
	       }   
	       // Get SharedPreferences name from XML string resource
	    //   String prefsName = getResources().getString(R.string.questionPrefs);

	       Bundle bundle =getIntent().getBundleExtra(getPackageName()+".discountBundle");
	       
		   //list results
		   int numDiscounts = bundle.getInt(getPackageName()+".numDiscounts", 0);
		   Log.i(getClass().getSimpleName(),"Num Discount Pages: " + numDiscounts);
		   for(int i = 0; i < numDiscounts; i++)
		   {
			   double origPrice = bundle.getDouble(getPackageName()+".originalPrice", 0);
			   double firstDisc = bundle.getDouble(getPackageName()+".firstDiscount", 0);
			   double secDisc = bundle.getDouble(getPackageName()+".secondDiscount", 0);
			   double tx = bundle.getDouble(getPackageName()+".taxAmount", 0);
			   Log.i(getClass().getSimpleName(),"Original Price: " + origPrice);
			   Log.i(getClass().getSimpleName(),"First Discount: " + firstDisc); 
			   Log.i(getClass().getSimpleName(),"Second Discount: " + secDisc);
			   Log.i(getClass().getSimpleName(),"Tax Amount: " + tx);
			   
			   //add to list
			   StringBuilder str = new StringBuilder();
			   str.append("Discount Page #"+ (i+1)+'\n');
			   str.append("Original Price: " + origPrice +'\n');
			   str.append("First Discount: " + firstDisc +'\n');
			   str.append("Second Discount: " + secDisc +'\n');
			   str.append("Tax Amount: " + tx);
			   //str.toString();			   
			   listItems.add(str.toString());			   		   
		   }		    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id)
		{
			case R.id.menuCalculator:	
				startActivity(new Intent(getApplicationContext(),MainActivity.class));	
				return true;
				
			case R.id.menuReport:
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	@Override
	   protected void onPause() {
	       super.onPause();
	       Log.i(getClass().getSimpleName(), "onPause");
	   }	 

	   @Override
	   protected void onResume() {
	       super.onResume();
	       Log.i(getClass().getSimpleName(), "onResume");
	   }
	   
	 	   @Override
	   protected void onDestroy() {
	       super.onDestroy();
	       Log.i(getClass().getSimpleName(), "onDestroy");
	   }	 

	   @Override
	   protected void onRestart() {
	       super.onRestart();
	       Log.i(getClass().getSimpleName(), "onRestart");
	   }	 

	   @Override
	   protected void onStart() {
	       super.onStart();
	       Log.i(getClass().getSimpleName(), "onStart");
	   }	 

	   @Override
	   protected void onStop() {
	       super.onStop();
	       Log.i(getClass().getSimpleName(), "onStop");
	   }

}
*/