package com.example.login2;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

public class loginTask extends AsyncTask<Array, Void, Boolean> {

	@Override
	protected Boolean doInBackground(Array... params) {
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("username", params[0].toString()));
        //String valid = "1";
        String response = null;
        try {
            response = CustomHttpClient.executeHttpPost("http://gokul-android.comli.com/check.php", postParameters);
            String res=response.toString();
          
            res= res.replaceAll("\\s+","");                              

           if(res.equals("1"))
                return true;
            else
            	return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;		
	}
	
	protected void onPostExecute() {
		
	}

}
