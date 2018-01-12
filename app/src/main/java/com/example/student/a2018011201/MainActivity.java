package com.example.student.a2018011201;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click1(View v){
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);

        //此StringRequest是要抓字串，所以是String開頭，期三個參數為：1.網址，2.回應監聽成功了該麼做，3.回應監聽失敗(抓不到資料)該怎麼做
        final StringRequest request=new StringRequest("http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("NET", response);
                try {

                    JSONArray array = new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject obj = array.getJSONObject(i);
                        String str = obj.getString("district");
                        String str2 = obj.getString("address");
                        Log.d("NET", str);
                        Log.d("NET", str2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
        queue.start();

    }

}
