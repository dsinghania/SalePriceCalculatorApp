package com.example.salepricecalculatornew;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity 
{
			//fields
			private EditText original_price_message;
			private EditText final_price_message;
			private EditText first_discount_message;
			private EditText second_discount_message;
			private EditText tax_message;
			private EditText saved_message;
			private Button   restartButton;
			private Button savePage,compare;
			private Toast myToast;
			private double originalValue = 0.0,  discount1 = 0.0, discount2 = 0.0, finalValue = 0.0, tax = 0.0, result = 0.0, saved = 0.0;
			
			//current discount page
			private Discount d ;
			
			//stores multiple discount page values
			private ArrayList<Discount> discounts = new ArrayList<Discount>();
			
			// the name of the SharedPreferences file
			private static final String PREFS_NAME = "TestData";
	
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			Log.i(getClass().getSimpleName(), "onCreate");
			
			setContentView(R.layout.activity_main);
			
			//assign the external interface components
			
			original_price_message = (EditText) findViewById(R.id.editText1);
			first_discount_message = (EditText) findViewById(R.id.editText2);
			second_discount_message = (EditText) findViewById(R.id.editText3);
			tax_message = (EditText) findViewById(R.id.editText4);
			final_price_message = (EditText) findViewById(R.id.editText5);
			saved_message = (EditText) findViewById(R.id.editText6);
			restartButton = (Button)findViewById(R.id.button7);
			savePage = (Button)findViewById(R.id.button8);
			compare = (Button)findViewById(R.id.button9);
			myToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
			/*original_price_message.setText("0");
			discount_message.setText("0");
			second_discount_message.setText("0");
			tax_message.setText("0");
			final_price_message.setText("0");
			saved_message.setText("0");*/
			
			//clear discounts collection
			discounts.clear();
			
			// Restore questions from SharedPreferences
		       getDiscountsPreferences();
			
			//set the listener for the restart button		
			restartButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(getApplicationContext(),MainActivity.class);
					startActivity(intent);
				}			
			});
			
			original_price_message.addTextChangedListener(new TextWatcher()
			{
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,	int after)
				{				
				}
				@Override
				public void onTextChanged(CharSequence s, int start, int before,int count)
				{
					final_price_message.setText(s);
				}
				@Override
				public void afterTextChanged(Editable s)
				{
					String fdisc = first_discount_message.getText().toString();
					if(fdisc != "")
					first_discount_message.setText("");
					String secdisc = second_discount_message.getText().toString();
					if(secdisc != "")
						second_discount_message.setText("");
					String tx = tax_message.getText().toString();
					if(tx != "")
						tax_message.setText("");
					DecimalFormat fmt = new DecimalFormat();
					fmt.applyPattern("0.##");
					//saved = 0.0;
					saved_message.setText(fmt.format(saved));				
				}			
			});
					
			first_discount_message.addTextChangedListener(new TextWatcher()
			{
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,	int after)
				{
					
				}
				@Override
				public void onTextChanged(CharSequence s, int start, int before,int count)
				{				
				}
				@Override
				public void afterTextChanged(Editable s) 
				{	
					DecimalFormat fmt = new DecimalFormat();
					fmt.applyPattern("0.##");
					Editable editOrigValue = null, editDiscValue = null;
					String str = original_price_message.getText().toString();
					String secdisc = second_discount_message.getText().toString();
					if(secdisc != "")
						second_discount_message.setText("");
					String tx = tax_message.getText().toString();
					if(tx != "")
						tax_message.setText("");
					//Toast t= null;
					if(str.equals(""))
					{
						myToast.setText("Please enter the original price first");
						myToast.show();
					}
					else
					{
						editDiscValue = first_discount_message.getText();
						//t.cancel();
					}
					
					if(editDiscValue != null)
					{
						if(!s.toString().equals(""))
						{		
							editOrigValue = original_price_message.getText();
							editDiscValue = first_discount_message.getText();
							
							if(editOrigValue != null)
								originalValue = Double.parseDouble(editOrigValue.toString());
							
							if(editDiscValue != null)
								discount1 = Double.parseDouble(editDiscValue.toString());
							
							result = originalValue * (( 100 - discount1) / 100.0);
							saved = originalValue - result;
							final_price_message.setText(fmt.format(result));
							saved_message.setText(fmt.format(saved));
						}
						else 
						{
							//Toast.makeText(getApplicationContext(),"Please enter the first discount value",Toast.LENGTH_SHORT).show();
							/*final_price_message.setText(fmt.format(originalValue));
							saved_message.setText(fmt.format("0.0"));*/
						}	
					}
				}			 
			});
			
			second_discount_message.addTextChangedListener(new TextWatcher()
			{
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,	int after) 
				{
					Toast.makeText(getApplicationContext(),"Please enter a 0 if you don't need further discount or tax",Toast.LENGTH_SHORT).show();
				}
				@Override
				public void onTextChanged(CharSequence s, int start, int before,int count) 
				{
					DecimalFormat fmt = new DecimalFormat();
					fmt.applyPattern("0.##");
					Editable editOrigValue = null, editDiscValue = null, editSecDiscValue = null;
					String str = original_price_message.getText().toString();
					String fdisc = first_discount_message.getText().toString();
					String tx = tax_message.getText().toString();
					if(tx != "")
						tax_message.setText("");
					
					if(str.equals("") || fdisc.equals(""))
					{
						myToast.setText("Please enter the original & first discount values first");
						myToast.show();
					}
					else
						editSecDiscValue = second_discount_message.getText();
					
					if(editSecDiscValue != null)
					{
						if(!s.toString().equals(""))
						{
							editOrigValue = original_price_message.getText();
							editDiscValue = first_discount_message.getText();
							//editSecDiscValue = second_discount_message.getText();
							
							if(editOrigValue != null)
								originalValue = Double.parseDouble(editOrigValue.toString());
							if(editDiscValue != null)
								discount1 = Double.parseDouble(editDiscValue.toString());
							if(editSecDiscValue !=null)
								discount2 = Double.parseDouble(editSecDiscValue.toString());
							
							result =  (originalValue * (( 100 - discount1) / 100.0)) * ((100 - discount2) / 100.0);
							saved = originalValue - result;
							
							final_price_message.setText(fmt.format(result));
							saved_message.setText(fmt.format(saved));
						}
						else
						{
							editOrigValue = original_price_message.getText();
							editDiscValue = first_discount_message.getText();
							
							if(editOrigValue != null)
								originalValue = Double.parseDouble(editOrigValue.toString());
							if(editDiscValue != null)
								discount1 = Double.parseDouble(editDiscValue.toString());
							
							result = originalValue * (( 100 - discount1) / 100.0);
							saved = originalValue - result;
							
							final_price_message.setText(fmt.format(result));
							saved_message.setText(fmt.format(saved));
						}
					}
				}
				@Override
				public void afterTextChanged(Editable s) 
				{
				}			
			});
			
			tax_message.addTextChangedListener(new TextWatcher()
			{
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,	int after)
				{
				}
				@Override
				public void onTextChanged(CharSequence s, int start, int before,int count)
				{
					/*DecimalFormat fmt = new DecimalFormat();
					fmt.applyPattern("0.##");
					if(!s.toString().equals(""))
					{
						Editable editFinalvalue = final_price_message.getText();
						Editable editTax = tax_message.getText();					
						
						if(editFinalvalue != null)
							finalValue = Double.parseDouble(editFinalvalue.toString());
						if(editTax != null)
							tax = Double.parseDouble(editTax.toString());
						
						result = finalValue * ((100.0 + tax)/100);
						final_price_message.setText(fmt.format(result));
					}
					else
					{
						Editable editOrigValue = original_price_message.getText();
						Editable editDiscValue = first_discount_message.getText();
						Editable editSecDiscValue = second_discount_message.getText();
						
						if(editOrigValue != null)
							originalValue = Double.parseDouble(editOrigValue.toString());
						if(editDiscValue != null)
							discount1 = Double.parseDouble(editDiscValue.toString());
						if(editSecDiscValue != null)
							discount2 = Double.parseDouble(editSecDiscValue.toString());
						else
							discount2 = 0;
						
						result =  (originalValue * (( 100 - discount1) / 100.0)) * ((100 - discount2) / 100.0);
						saved = originalValue - result;
						final_price_message.setText(fmt.format(result));
					}*/
				}
				@Override
				public void afterTextChanged(Editable s)
				{
					DecimalFormat fmt = new DecimalFormat();
					fmt.applyPattern("0.##");
					Editable editOrigValue = null, editDiscValue = null, editSecDiscValue = null, editFinalvalue = null, editTax = null;
					String str = original_price_message.getText().toString();
					String fdisc = first_discount_message.getText().toString();
					String sdisc = second_discount_message.getText().toString();
					
					if(str.equals("") || fdisc.equals("") || sdisc.equals(""))
					{
						myToast.setText("Please enter the original & discount values first");
						myToast.show();
					}
						//Toast.makeText(getApplicationContext(),"Please enter the original & discount values first",Toast.LENGTH_SHORT).show();
					else
						editFinalvalue = final_price_message.getText();
						//editTax = tax_message.getText();
						String tx = tax_message.getText().toString();
					if(!str.equals("") && !fdisc.equals("") && !sdisc.equals(""))
					{
						if(!s.toString().equals(""))
						{					
							if(editFinalvalue != null)
								finalValue = Double.parseDouble(editFinalvalue.toString());
							
							tax = Double.parseDouble(tx);						
							result = finalValue * ((100.0 + tax)/100);
							final_price_message.setText(fmt.format(result));
						}
						else
						{
							editOrigValue = original_price_message.getText();
							editDiscValue = first_discount_message.getText();
							editSecDiscValue = second_discount_message.getText();
							
							if(editOrigValue != null)
								originalValue = Double.parseDouble(editOrigValue.toString());
							if(editDiscValue != null)
								discount1 = Double.parseDouble(editDiscValue.toString());
							if(editSecDiscValue != null)
								discount2 = Double.parseDouble(editSecDiscValue.toString());
							else
								discount2 = 0;
							
							result =  (originalValue * (( 100 - discount1) / 100.0)) * ((100 - discount2) / 100.0);
							saved = originalValue - result;
							final_price_message.setText(fmt.format(result));	
						}
					}
				}			
							
			});		
				//set the save page button
			   savePage.setOnClickListener(new OnClickListener()
			   {
				   public void onClick(View v)		   
				   {
					    d = new Discount();
					    String sd = "0", tx ="0",op="0", fd="0", fp="0", sp="0";
					    Editable origValue = null, discValue = null,secDiscValue = null, finalValue = null, tax  = null, save = null;
					    origValue = original_price_message.getText();
						discValue = first_discount_message.getText();
						secDiscValue = second_discount_message.getText();
						finalValue = final_price_message.getText();
						tax = tax_message.getText();
						save = saved_message.getText();
						
						if(origValue != null)
							 op = origValue.toString();
						if(discValue != null)
							fd = discValue.toString();
						if(secDiscValue != null)
							sd = secDiscValue.toString();
						if(tax != null)
							tx = tax.toString();
						if(finalValue != null)
							fp = finalValue.toString(); 
						if(save != null)
							sp = save.toString();
				      
				       Log.i(getClass().getSimpleName(),"Original Price entered: " + op);
				       Log.i(getClass().getSimpleName(),"First discount entered: " + fd);	
				       Log.i(getClass().getSimpleName(),"Second discount entered: " + sd);
				       Log.i(getClass().getSimpleName(),"Tax Amount entered: " + tx);
				       Log.i(getClass().getSimpleName(), "Final Price is: " + fp);
				       Log.i(getClass().getSimpleName(), "Saved Price is: " + sp);
				       
				       // If any edit box is empty
				       if( op.equals("") || fd.equals("") || sd.equals("") || tx.equals(""))
				       {
				          // Short duration dialog window to notify user
				    	   myToast.setText("Please enter all values");
							myToast.show();
				        //  Toast.makeText(getApplicationContext(), "Please enter all values",Toast.LENGTH_SHORT).show();			        
				       }
				       else
				       {
				    	   // Store response
				    	   d.setOriginalPrice(Double.parseDouble(op));
				    	   d.setFirst_discount(Double.parseDouble(fd));
				    	   d.setSecond_discount(Double.parseDouble(sd));
				    	   d.setTax(Double.parseDouble(tx));
				    	   d.setFinalPrice(Double.parseDouble(fp));
				    	   d.setSavedPrice(Double.parseDouble(sp));
				    	   discounts.add(d);
				    	   Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();	
				    	   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
						   startActivity(intent);			          
				       } 		  
				   }			   		   
			   });
			   compare.setOnClickListener(new OnClickListener()
			   {
				   public void onClick(View v)		   
				   {
					    Intent intent = new Intent(getApplicationContext(),ReportActivity.class);
						   startActivity(intent);
							
						   /*//load up bundle with the pages
						   Bundle bundle = new Bundle();
						   putDiscounts(bundle);
						   
						   //send bundle with Intent
						   intent.putExtra(getPackageName()+".discountBundle", bundle);*/
				   }		   
			   });	 
		}
		 private void putDiscountsPreferences()
		 {		     
			 // Get a SharedPreferences file
		       SharedPreferences state = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);	 

		       // Get a SharedPreferences editor
		       SharedPreferences.Editor editor = state.edit();	 

		       // Load the editor with the discount results
		       editor.putInt(getPackageName() + ".numDiscounts", discounts.size());
		       int i = 0;
		       for (Iterator<Discount> it = discounts.iterator(); it.hasNext();) {
		    	   Discount tmp = it.next();
		           editor.putInt(getPackageName() + ".originalPrice" + i,(int)tmp.getOriginalPrice());
		           editor.putInt(getPackageName() + ".firstDiscount" + i,(int)tmp.getFirst_discount());
		           editor.putInt(getPackageName() + ".secondDiscount" + i,(int)tmp.getSecond_discount());
		           editor.putInt(getPackageName() + ".taxAmount" + i,(int)tmp.getTax());
		           editor.putInt(getPackageName()+".finalPrice"+i, (int)tmp.getFinalPrice());
		           editor.putInt(getPackageName()+".savedPrice"+i, (int)tmp.getSavedPrice());
		          ++i;
		       }
		       // Commit the editor additions
		       editor.commit();
		   }
		
		 	// Get discounts from a SharedPreferences
		   private void getDiscountsPreferences() 
		   {
		       // Clear discount collection
			   discounts.clear(); 

		       // Get SharedPreferences
		       SharedPreferences prefState = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);	 

		       // Get discounts from SharedPreferences
		       int numDiscounts = prefState.getInt(getPackageName() + ".numDiscounts",0);
		       for (int i = 0; i < numDiscounts; ++i) {
		          // Create discount
		          d = new Discount();
		 
		          // Get discount data
		          double origPrice = prefState.getInt(getPackageName()+ ".originalPrice" + i, 0);
		          double firstDisc = prefState.getInt(getPackageName()+ ".firstDiscount" + i, 0);
		          double secDisc = prefState.getInt(getPackageName()+ ".secondDiscount" + i, 0);
		          double tx = prefState.getInt(getPackageName()+ ".taxAmount" + i, 0);
		          double fprice = prefState.getInt(getPackageName()+".finalPrice" +i,0);
		          double sprice = prefState.getInt(getPackageName()+".savedPrice" +i,0);
		           //set discounts data
				   d.setOriginalPrice(origPrice);
				   d.setFirst_discount(firstDisc);
				   d.setSecond_discount(secDisc);
				   d.setTax(tx);
				   d.setFinalPrice(fprice);
				   d.setSavedPrice(sprice);
				   
				   //add discounts to collection
				   discounts.add(d);	
		       }
		   } 
		   @Override
			public boolean onCreateOptionsMenu(Menu menu)
			{
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}

			@Override
			public boolean onOptionsItemSelected(MenuItem item)
			{
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
				else
				return super.onOptionsItemSelected(item);
				/*switch (id)
				{
					case R.id.action_settings:		
						return true;
						
					case R.id.menuReport:
						startActivity(new Intent(getApplicationContext(),ReportActivity.class));
						return true;
						
					default:
						return super.onOptionsItemSelected(item);
				}*/
			}
			@Override

			   protected void onPause() { 
			       // Parent method
			       super.onPause();
			       Log.i(getClass().getSimpleName(), "onPause");	       

			       // Put questions in SharedPreferences
			       putDiscountsPreferences();
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
		@Override
		   protected void onSaveInstanceState(Bundle outState)
			{
		       super.onSaveInstanceState(outState);
		       Log.i(getClass().getSimpleName(), "onSaveInstanceState");
		       
		      /* // Put discounts in bundle
		       putDiscounts(outState);	       

		       // Save parent state
		       super.onSaveInstanceState(outState);*/
		   }	   

		   @Override
		   protected void onRestoreInstanceState(Bundle savedInstanceState)
		   {
		       super.onRestoreInstanceState(savedInstanceState);
		       Log.i(getClass().getSimpleName(), "onRestoreInstanceState");
		       
		    /*// Restore parent state
		       super.onRestoreInstanceState(savedInstanceState);	       

		       // If bundle is not null
		       if(savedInstanceState!=null)
		       {
		          // Get discounts from bundle
		          getDiscounts(savedInstanceState);
		       }*/
		   }
	}

	
