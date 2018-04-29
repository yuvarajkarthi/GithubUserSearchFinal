package com.android.githubuser.controller;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.githubuser.ItemAdapter;
import com.android.githubuser.R;
import com.android.githubuser.api.Client;
import com.android.githubuser.api.Service;
import com.android.githubuser.model.Item;
import com.android.githubuser.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView Disconnected;
    private Item item;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         initViews();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadJSON();
                Toast.makeText(MainActivity.this, "Github Users Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Users...");
        pd.setCancelable(false);
        pd.show();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

       recyclerView.smoothScrollToPosition(5);
        loadJSON();
    }
    private void loadJSON(){
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try{
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            String s="location:india";
            Call<ItemResponse> call = apiService.getItems(s);
            Log.d("info",Client.toString());
            Toast.makeText(MainActivity.this, call.request().url().toString(), Toast.LENGTH_LONG).show();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItems();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                  recyclerView.smoothScrollToPosition(5);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_LONG).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void loadjsonwithname(View v){
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try{
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            EditText e=(EditText) findViewById(R.id.editText);
            String username=e.getText().toString();
            e.setText("");
            if(username.isEmpty())
            {
                Toast.makeText(this, "username feild not filled", Toast.LENGTH_LONG).show();
            }
            else {
                String s = username + " in:login";
                Call<ItemResponse> call = apiService.getItems(s);
                Log.d("info", Client.toString());
                Toast.makeText(MainActivity.this, call.request().url().toString(), Toast.LENGTH_LONG).show();
                call.enqueue(new Callback<ItemResponse>() {
                    @Override
                    public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                        List<Item> items = response.body().getItems();
                        recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                        recyclerView.smoothScrollToPosition(5);
                        swipeContainer.setRefreshing(false);
                        pd.hide();
                    }

                    @Override
                    public void onFailure(Call<ItemResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());
                        Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_LONG).show();
                        Disconnected.setVisibility(View.VISIBLE);
                        pd.hide();

                    }
                });
            }
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

}
