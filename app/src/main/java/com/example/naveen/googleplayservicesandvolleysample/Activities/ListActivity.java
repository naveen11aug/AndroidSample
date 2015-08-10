package com.example.naveen.googleplayservicesandvolleysample.Activities;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.naveen.googleplayservicesandvolleysample.Adapters.GenericRecyclerAdapter;
import com.example.naveen.googleplayservicesandvolleysample.Entities.BasePlace;
import com.example.naveen.googleplayservicesandvolleysample.R;
import com.example.naveen.googleplayservicesandvolleysample.Utils.CustomJsonRequest;
import com.example.naveen.googleplayservicesandvolleysample.Utils.CustomVolleyRequestQueue;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends Activity  {

	RecyclerView.Adapter adapter;
	private RecyclerView mRecyclerView;
	private ArrayList places = new ArrayList();
	public CustomVolleyRequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext());
		GetPlacesAndSetAdapter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the HomeActivity/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unchecked")
	public void setAdapter() {
			adapter = new GenericRecyclerAdapter(this, places);
			setContentView(R.layout.list_activity);
			// Initialize recycler view
			mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
			mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
			mRecyclerView.setAdapter(adapter);
	}

	private void GetPlacesAndSetAdapter()
	{
		final String requestUrl = "https://maps.googleapis.com/maps/api/place/search/json?location=12.937464,77.629406&sensor=false&radius=1000.000000&key=AIzaSyBE_FD6mNSdjLToter9Oc0ZkVEcGuSKIsk&types=food&rankBy=distance";

		CustomJsonRequest jsonRequest = new CustomJsonRequest(requestUrl,
				JsonElement.class, null, new Response.Listener<JsonElement>() {
			@Override
			public void onResponse(JsonElement response) {
				try {
					places = parseGenericPlaceJsonResponse(response.toString());
					setAdapter();
				} catch (JSONException e) {
					Log.e("HTML", "ExceptionJson: " + e.toString());
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
			}
		});
		mQueue.addToRequestQueue(jsonRequest);
	}

	public static ArrayList<BasePlace> parseGenericPlaceJsonResponse(String jsonResponse) throws JSONException
	{
		ArrayList<BasePlace> places = new ArrayList<>();
		JSONObject object = new JSONObject(jsonResponse);
		JSONArray jsonArray = object.getJSONArray("results");
		BasePlace tempResObject;

		for (int i = 0; i < jsonArray.length(); i++)
		{
			tempResObject = new BasePlace();
			try {
				tempResObject.setData((JSONObject) jsonArray.get(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			places.add(tempResObject);
		}
		return places;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
