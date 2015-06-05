package com.example.dwijpr.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dwijpr on 24/05/15.
 */
public class MarkerJSONParser {

    public List<HashMap<String, String>> parse(JSONObject jObject){
        JSONArray jMarkers = new JSONArray();

        if(jObject != null){
            try{
                jMarkers = jObject.getJSONArray("markers");
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        return getMarkers(jMarkers);
    }

    private List<HashMap<String, String>> getMarkers(JSONArray jMarkers){
        int markersCount = jMarkers.length();
        List<HashMap<String, String>> markersList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> marker = null;

        for(int i = 0; i < markersCount;i++){
            try{
                marker = getMarker((JSONObject)jMarkers.get(i));
                markersList.add(marker);
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return markersList;
    }

    private HashMap<String, String> getMarker(JSONObject jMarker){
        HashMap<String, String> marker = new HashMap<String, String>();
        String lat = "-NA-";
        String lng = "-NA-";

        try{
            if(!jMarker.isNull("lat")){
                lat = jMarker.getString("lat");
            }

            if(!jMarker.isNull("lng")){
                lng = jMarker.getString("lng");
            }

            marker.put("lat", lat);
            marker.put("lng", lng);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return marker;
    }
}
