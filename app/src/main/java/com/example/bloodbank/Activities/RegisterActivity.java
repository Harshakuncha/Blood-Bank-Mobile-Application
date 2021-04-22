package com.example.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.bloodbank.Activities.Utils.VolleySingleton;
import com.example.bloodbank.R;

import com.android.volley.Request.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
     private EditText namee,citye,bloodgroupe,contacte,passworde;
     private Button submitb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        namee=findViewById(R.id.name);
        citye=findViewById(R.id.city);
        bloodgroupe=findViewById(R.id.bloodgroup);
        contacte=findViewById(R.id.contact);
        passworde=findViewById(R.id.password);
        submitb=findViewById(R.id.donarb);
        submitb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na,ci,bg,ct,pw;
                na=namee.getText().toString();
                ci=citye.getText().toString();
                bg=bloodgroupe.getText().toString();
                ct=contacte.getText().toString();
                pw=passworde.getText().toString();
                if(isvalid(na,ci,bg,pw,ct))
                {
                    register(na,ci,bg,pw,ct);
                }


            }
        });
    }
    private void register(final String name,final String city,final String blood_group,final String password,final String mobile)
    {
        StringRequest stringRequest=new StringRequest(Method.POST, "Endpoints.register_url", new Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Success"))
                {
                     Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                     RegisterActivity.this.finish();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,"somethingwent wrong :(",Toast.LENGTH_SHORT).show();;
                Log.d("VOLLEY",error.getMessage());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("name",name);
                params.put("city",city);
                params.put("blood_group",blood_group);
                params.put("password",password);
                params.put("number",mobile);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }



    private boolean isvalid(String name,String city,String blood_group,String password,String mobile)
    {
        List<String> ok=new ArrayList<>();
        ok.add("A+");
        ok.add("A-");
        ok.add("B+");
        ok.add("B-");
        ok.add("AB+");
        ok.add("AB-");
        ok.add("O+");
        ok.add("O-");

        if(name.isEmpty())
        {
            showMessage("name is empty");
            return false;
        } else if (city.isEmpty()) {
            showMessage("city is empty");
            return false;
        }
        else if(!ok.contains(blood_group))
        {
            showMessage("blood group invalid choose form"+ok);
            return false;
        }
        else if(mobile.isEmpty() || mobile.length()!=10)
        {
            showMessage("Invalid mobile number ");
        }

        return true;

    }

    private void showMessage(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}