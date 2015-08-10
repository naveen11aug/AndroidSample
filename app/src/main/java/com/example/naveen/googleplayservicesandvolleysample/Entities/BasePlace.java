package com.example.naveen.googleplayservicesandvolleysample.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class BasePlace implements Serializable {
	    private String id;
	    private String icon;
	    private String name;
	    private String vicinity;

	    public BasePlace()
	    {
	    	this.id	= null;
	    	this.icon = null;
	    	this.name = null;
	    	this.vicinity = null;
	    }
	    
	    public String getId() {
	        return id;
	    }
	    public void setId(String id) {
	        this.id = id;
	    }
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public String getVicinity() {
	        return vicinity;
	    }
	    public void setVicinity(String vicinity) {
	        this.vicinity = vicinity;
	    }

	    public  void setData(JSONObject placeData)throws JSONException
	    {
			if (placeData == null) {
				return;
			}			
			JSONObject geometry = placeData.optJSONObject("geometry");
			JSONObject location = geometry.optJSONObject("location");
			this.setName(placeData.optString("name"));
			this.setVicinity(placeData.optString("vicinity"));
			this.setId(placeData.optString("place_id"));
	   }
	    
}
