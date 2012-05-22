package my.namespace;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ProfileActivity extends Activity {
	  LinearLayout mLinearLayout;
	  TextView myTempTV = null; 
	  Context context;
	  ImageView myIV = null;

	  
	/** Called when the activity is first created. */
   
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        TextView textname = new TextView(this);
        String name = "Peter \nHöller";
        textname.setText(name);
        textname.setTextSize(40);
        TextView textAgeSex = new TextView(this);
        int age = 30;
        String sex = "Male";
        textAgeSex.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+sex+","+age);
        TextView textcountry = new TextView(this);
        String country = "Paderborn";
        textcountry.setText(country);
        TextView textfriends = new TextView(this);
        int friends = 300;
        textfriends.setText("Friends: "+friends);
        
         // Create a LinearLayout in which to add the ImageView and the TextViews
        mLinearLayout = new LinearLayout(this);

        // Instantiate an ImageView and define its properties
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.man_icon);
        i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
        i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
        LayoutParams.WRAP_CONTENT));

        mLinearLayout.addView(i);
        mLinearLayout.addView(textname);
        mLinearLayout.addView(textAgeSex);
        mLinearLayout.addView(textcountry);
        mLinearLayout.addView(textfriends);
        setContentView(mLinearLayout);
    	
    }
}