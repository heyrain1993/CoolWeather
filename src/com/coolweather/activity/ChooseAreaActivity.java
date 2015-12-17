package com.coolweather.activity;


import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.R;
import com.coolweather.db.CoolWeatherDB;
import com.coolweather.model.City;
import com.coolweather.model.Country;
import com.coolweather.model.Province;
import com.coolweather.util.CallbackListener;
import com.coolweather.util.HttpUtil;
import com.coolweather.util.Utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseAreaActivity extends Activity{
	
	public static final int LEVEL_PROVINCE=0;
	public static final int LEVEL_CITY=1;
	public static final int LEVEL_COUNTRY=2;
	
	
	private int currentLevel;
	
	private ProgressDialog progressDialog;
	private TextView titleText;
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private CoolWeatherDB coolWeatherDB;
	private ArrayList<String> dataList=new ArrayList<String>();//ListView��������Դ
	
	private List<Province> provinceList;
	private List<City> cityList;
	private List<Country> countryList;
	
	
	private Province selectedProvince;
	private City selectedCity;
	
	private boolean isFromWeatherActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isFromWeatherActivity=getIntent().getBooleanExtra("from_weather_activity", false);
		SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);
		if(prefs.getBoolean("city_selected", false)&&!isFromWeatherActivity){
			Intent intent=new Intent(this,WeatherActivity.class);
			startActivity(intent);
			finish();
		}
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);
		listView=(ListView)findViewById(R.id.list_view);
		titleText=(TextView)findViewById(R.id.title_text);
		//ΪLissView����������
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);
		coolWeatherDB=CoolWeatherDB.getInstance(this);//�������ݿ�
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int index,
					long arg3) {
				if(currentLevel==LEVEL_PROVINCE){
					selectedProvince=provinceList.get(index);
					queryCity();
				}else if(currentLevel==LEVEL_CITY){
					selectedCity=cityList.get(index);
					queryCountry();
				}else if(currentLevel==LEVEL_COUNTRY){
					String countryCode=countryList.get(index).getCountryCode();
					Intent intent=new Intent(ChooseAreaActivity.this,WeatherActivity.class);
					intent.putExtra("country_code", countryCode);
					startActivity(intent);
					finish();
				}
				
				
			}
		
		});
		queryProvince();
		
	}

	private void queryProvince() {
		provinceList=coolWeatherDB.loadProvince();
		if(provinceList.size()>0){
			dataList.clear();
			for(Province province:provinceList){
				dataList.add(province.getProvinceName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText("�й�");
			currentLevel=LEVEL_PROVINCE;
		}else{
			queryFromServer(null,"province");
		}
	}
	
	private void queryCity(){
		cityList=coolWeatherDB.loadCity(selectedProvince.getId());
		if(cityList.size()>0){
			dataList.clear();
			for(City city:cityList){
				dataList.add(city.getCityName());
				
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedProvince.getProvinceName());
			currentLevel=LEVEL_CITY;
			
		}else{
			queryFromServer(selectedProvince.getProvinceCode(), "city");
		}
	}
	
	private void queryCountry(){
		countryList=coolWeatherDB.loadCountry(selectedCity.getId());
		if(countryList.size()>0){
			dataList.clear();
			for(Country country:countryList){
				dataList.add(country.getCountryName());
				
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedCity.getCityName());
			currentLevel=LEVEL_COUNTRY;
			
		}else{
			queryFromServer(selectedCity.getCityCode(), "country");
		}
	}
	
	

	private void queryFromServer(final String code, final String type) {
		String address;
		if("province".equals(type)){
			address="http://www.weather.com.cn/data/list3/city.xml";
			
		}else if("city".equals(type)){
			address="http://www.weather.com.cn/data/list3/city"+code+".xml";
		}else{
			address="http://www.weather.com.cn/data/list3/city"+code+".xml";
		}
		showProgressDialog();
		HttpUtil.sendHttpRequest(address, new CallbackListener(){
			
			/**
			 * ����ѯ���صĳ��ַ������뵽onFinish������
			 */
			@Override
			public void onFinish(String response) {
				boolean result=false;
				if("province".equals(type)){
					result=Utility.handleProvinceResponse(coolWeatherDB, response);
				}else if("city".equals(type)){
					result=Utility.handleCityResponse(coolWeatherDB, response, selectedProvince.getId());
				}else if("country".equals(type)){
					result=Utility.handleCountryResponse(coolWeatherDB, response,selectedCity.getId());
				}
				
				if(result){
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							closeProgressDialog();
							if("province".equals(type)){
								queryProvince();
							}else if("city".equals(type)){
								queryCity();
							}else if("country".equals(type)){
								queryCountry();
							}
						}

						
					});
				}
				
			}

			@Override
			public void onError(Exception e) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						closeProgressDialog();
						Toast.makeText(ChooseAreaActivity.this, "����ʧ��",Toast.LENGTH_SHORT).show();
						
					}
				});
			}
		});
	}

	private void showProgressDialog() {
		if(progressDialog==null){
			progressDialog=new ProgressDialog(this);
			progressDialog.setMessage("���ڼ���...");
			progressDialog.setCanceledOnTouchOutside(false);
		}
		progressDialog.show();
	}
	
	private void closeProgressDialog() {
		if(progressDialog!=null){
			progressDialog.dismiss();
		}
		
	}

	@Override
	public void onBackPressed() {
		if(currentLevel==LEVEL_CITY){
			queryProvince();
		}else if(currentLevel==LEVEL_COUNTRY){
			queryCity();
		}else {
			if(isFromWeatherActivity){
				Intent intent=new Intent(this,WeatherActivity.class);
				startActivity(intent);
			}
			finish();
		}
	}
	
	
}
