package com.example.login2;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login2 extends Activity {
    EditText un,pw;
	TextView error;
    Button ok;
	final Context context = this;

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
            	if (un.getText().toString().length() == 0
						|| pw.getText().toString().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Please enter username and password",
							Toast.LENGTH_SHORT).show();
				} else {
//					final Dialog dialog = new Dialog(context);
//					dialog.setCanceledOnTouchOutside(false);
//					dialog.setContentView(R.layout.dialoglayout);
//					dialog.setTitle("Processing...");
//					Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//					// if button is clicked, close the custom dialog
//					dialogButton.setOnClickListener(new OnClickListener() {
//						@Override
//						public void onClick(View v) {
//							dialog.dismiss();
//						}
//					});
	                GetData task = new GetData();
	                task.execute();
				}
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
                response = CustomHttpClient.executeHttpPost("http://website.com/filename.php", postParameters);
                String res=response.toString();
                Log.d("response:", res);
                res = res.split("\n")[0];
                res= res.replaceAll("\\s+","");
                Log.d("response trimmed:", res);
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
