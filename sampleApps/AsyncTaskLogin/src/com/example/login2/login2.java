package com.example.login2;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login2 extends Activity {
    EditText un,pw;
	TextView error;
    Button ok;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        un=(EditText)findViewById(R.id.et_un);
        pw=(EditText)findViewById(R.id.et_pw);
        ok=(Button)findViewById(R.id.btn_login);
        error=(TextView)findViewById(R.id.tv_error);

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	
                GetData task = new GetData();
                task.execute();
            }
        });
    }
    
    private class GetData extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... urls) {
            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("username", un.getText().toString()));
            postParameters.add(new BasicNameValuePair("password", pw.getText().toString()));
            //String valid = "1";
            String response = null;
            try {
                response = CustomHttpClient.executeHttpPost("http://10.0.2.2/check.php", postParameters);
                String res=response.toString();
                res= res.replaceAll("\\s+","");                              
                
               if(res.equals("1"))
            	   return true;
                else
                	return false;
               
            } catch (Exception e) {
                un.setText(e.toString());
            }
            return false;
        }
        
        @Override
        protected void onPostExecute(Boolean res) {
          if(res)
              error.setText("Correct Username or Password");
          else
              error.setText("Sorry!! Incorrect Username or Password");
        }
        
    }

}
