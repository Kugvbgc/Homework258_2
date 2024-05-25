package com.khair.homework2582;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {


    TextView textView,textView2,textView3,textView4,textView5,textView6,textView7;

    ImageView imageView,imageView1,imageView2,images_back;

    public  static String profile_image="";
    public  static String BloodGroup="";
    public  static String Bron_in="";
    public  static String Weight="";
    public  static String phone="";
    public  static String gender="";
    public  static String image_name="";
    public  static String email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView=findViewById(R.id.profile_image);
        imageView2=findViewById(R.id.images_mail);
        imageView1=findViewById(R.id.image_call);
        textView=findViewById(R.id.BloodGroup);
        textView2=findViewById(R.id.Bron_in);
        textView3=findViewById(R.id.Weight);
        textView4=findViewById(R.id.phone);
        textView5=findViewById(R.id.gender);
        textView6=findViewById(R.id.image_name);
        textView7=findViewById(R.id.email);
        images_back=findViewById(R.id.images_back);

        Picasso.get()
                .load(profile_image)
                .into(imageView);

        textView.append(" "+BloodGroup);
        textView3.append(" "+Bron_in);
        textView3.append(" "+Weight);
        textView4.append(" "+phone);
        textView5.append(" "+gender);
        textView6.append(" "+image_name);
        textView7.append(" "+email);



        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);

            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""+textView7});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email");
                intent.putExtra(Intent.EXTRA_TEXT, "Body of the email");

                try {
                    startActivity(Intent.createChooser(intent, "Send email using..."));
                } catch (ActivityNotFoundException ex) {
                    // Handle case where no email clients are installed
                    ex.printStackTrace();
                }

            }
        });


        images_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }

        });



    }
}