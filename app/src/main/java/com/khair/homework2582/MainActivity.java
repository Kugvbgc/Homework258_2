package com.khair.homework2582;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    TextView textView;

    ListView listView;

    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();

    HashMap<String,String>hashMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar=findViewById(R.id.progress);
        textView=findViewById(R.id.textView);
        listView=findViewById(R.id.listview);

        String url="https://dummyjson.com/users";

        progressBar.setVisibility(View.VISIBLE);

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("users");
                    for (int x=0;x<jsonArray.length();x++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(x);
                        String firstName=jsonObject1.getString("firstName");
                        String lastName=jsonObject1.getString("lastName");
                        String age=jsonObject1.getString("age");
                        String gender=jsonObject1.getString("gender");
                        String email=jsonObject1.getString("email");
                        String phone=jsonObject1.getString("phone");
                        String image=jsonObject1.getString("image");
                        String bloodGroup=jsonObject1.getString("bloodGroup");
                        String weight=jsonObject1.getString("weight");
                        String birthDate=jsonObject1.getString("birthDate");
//*******************************************************************************************************
                        JSONObject object2=jsonObject1.getJSONObject("address");
                        String address=object2.getString("address");
                        String city=object2.getString("city");

                        hashMap=new HashMap<>();
                        hashMap.put("firstName",firstName);
                        hashMap.put("lastName",lastName);
                        hashMap.put("age",age);
                        hashMap.put("gender",gender);
                        hashMap.put("email",email);
                        hashMap.put("phone",phone);
                        hashMap.put("image",image);
                        hashMap.put("bloodGroup",bloodGroup);
                        hashMap.put("weight",weight);
                        hashMap.put("birthDate",birthDate);
                        hashMap.put("address",address);
                        hashMap.put("city",city);
                        arrayList.add(hashMap);

                    }


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                MyAdapter myAdapter=new MyAdapter();
                listView.setAdapter(myAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.GONE);


            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(objectRequest);




    }
    //================================================================================================
public class MyAdapter extends BaseAdapter {



        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView=layoutInflater.inflate(R.layout.item_layout,parent,false);

            ImageView imageView=myView.findViewById(R.id.profile_image);
            TextView Gander=myView.findViewById(R.id.tvGander);
            TextView Name=myView.findViewById(R.id.tvName);
            TextView Birthday=myView.findViewById(R.id.tvBirthday);
            TextView Phone=myView.findViewById(R.id.tvPhone);
            TextView Blood=myView.findViewById(R.id.tvBlood);
            TextView Weight=myView.findViewById(R.id.tvWeight);
            CardView main=myView.findViewById(R.id.center);


            Animation   myAnimation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.right_to_left);           main.startAnimation(myAnimation);


            HashMap<String,String>hashMap=arrayList.get(position);
           String images=hashMap.get("image");
            String firstName=hashMap.get("firstName");
            String lastName=hashMap.get("lastName");
            String age=hashMap.get("age");
            String gender=hashMap.get("gender");
            String email=hashMap.get("email");
            String phone=hashMap.get("phone");
            String bloodGroup=hashMap.get("bloodGroup");
            String weight=hashMap.get("weight");
            String address=hashMap.get("address");
            String city=hashMap.get("city");
            String birthDate=hashMap.get("birthDate");




            Picasso.get()
                    .load(images)
                    .into(imageView);

            Phone.setText(phone);
            Gander.setText(gender);
            Name.setText(firstName+" "+lastName);
            Birthday.setText("age : "+age);
            Blood.setText(bloodGroup);
            Weight.setText(weight);

            main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity2.profile_image=images;
                    MainActivity2.phone=phone;
                    MainActivity2.email=email;
                    MainActivity2.gender=gender;
                    MainActivity2.Weight=weight;
                    MainActivity2.BloodGroup=bloodGroup;
                    MainActivity2.Bron_in=birthDate;
                    MainActivity2.image_name=firstName+" "+lastName;

                    startActivity(new Intent(MainActivity.this,MainActivity2.class));
                }
            });



            return myView;
        }
    }


 //====================================================================================================
}