package com.example.fetchhw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/*
James Zhao
4-17-2021

Mobile application to parse JSON file through a URL using Volley to make a request
and display the list of items in a multi-column listview grouped by listId and sorted by name.
Filters out results where the name field is left blank and where the field says null.
*/

public class MainActivity extends AppCompatActivity {
//    private ListView mList;
    private RequestQueue mQueue;

    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    public static final String THIRD_COLUMN="Third";

    //array list of hash maps to hold json data
    private ArrayList<HashMap<String, String>> hList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        //clicking the button will execute function
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    public void jsonParse() {

        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

        hList = new ArrayList<HashMap<String, String>>();

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                try {
                    //JSON has no object name so you can parse array directly
                    JSONArray jArray = new JSONArray(string);

                    // iterate through the JSONArray to insert separate items to insert into hashmap
                    for(int i = 0; i < jArray.length(); ++i) {
                        JSONObject object = jArray.getJSONObject(i);

                        //if statement filters out names that are blank or are "null
                        if(!object.getString("name").isEmpty() && !object.getString("name").equals( "null")) {
                            HashMap<String, String> hashmap = new HashMap<String, String>();
                            hashmap.put(FIRST_COLUMN, object.getString("id"));
                            hashmap.put(SECOND_COLUMN, object.getString("listId"));
                            hashmap.put(THIRD_COLUMN, object.getString("name"));
                            hList.add(hashmap);
                        }
                    }

                    //sort JSON data by the id (sorting by name results in a sort where names are not in numerical order)
                    Collections.sort(hList, new IntComparator(FIRST_COLUMN));
                    //sort JSON data by listId to group all items with the same listId
                    Collections.sort(hList, new MapComparator(SECOND_COLUMN));

                    //connect hList to adapter to display multicolumn listview
                    ListView listView = findViewById(R.id.list_view);
                    ListViewAdapter adapter = new ListViewAdapter(MainActivity.this, hList);

                    if(listView != null) {
                        listView.setAdapter(adapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

}