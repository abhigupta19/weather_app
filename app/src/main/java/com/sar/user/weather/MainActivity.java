package com.sar.user.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String a = null;
    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         textView=findViewById(R.id.textView);
         editText=findViewById(R.id.editText);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=editText.getText().toString();
                weather(a);
            }

        });
    }

    private void weather(String ab) {

        String Base_url="https://openweathermap.org/data/2.5/weather?q="+ ab +"&appid=b6907d289e10d714a6e88b30761fae22";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest( Request.Method.GET,Base_url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject  jsonObject=response.getJSONObject("main");
                    JSONArray jsonArray=response.getJSONArray("weather");
                    JSONObject jsonObject1=jsonArray.getJSONObject(0);
                    String au=String.valueOf(jsonObject.getDouble("temp"));
                    String am=String.valueOf(jsonObject1.getString("description"));
                    textView.setText(au+"  "+am);
                    editText.setText(null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"no city found",Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

}
}
