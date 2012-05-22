package my.battery.ns;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryStatusActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent batteryIntent = this.getApplicationContext().registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if(level >=0 && scale > 0)
        {
        	float batteryPct = (level / (float)scale)*100;
//        	TextView tv = (TextView) findViewById(R.id.textView2); 
//        	//Toast.makeText(this,"Battery status:"+batteryPct+"%", Toast.LENGTH_LONG).show();
//        	tv.setText(" "+batteryPct+"% charged");
        	
        	
        	int battery = (int) batteryPct;
        	ImageView iv = (ImageView) findViewById(R.id.imageView);
        	TextView tv4 = (TextView) findViewById(R.id.textView4);
    		tv4.setText(battery+"%");
    		TextView tv1 = (TextView) findViewById(R.id.textView1);
            	
        	if(battery==100) {
        		iv.setImageResource(R.drawable.battery_100);
        		tv1.setText("Its fully charged. You can relax :-)");
        	}
        	else if (battery >=50 && battery<=100) {
        		iv.setImageResource(R.drawable.battery_75);
        		tv1.setText("It has enough charge! Don't worry");
        	}
        	else if (battery < 50) {
        		iv.setImageResource(R.drawable.battery_25);
        		tv1.setText("You may have to keep an eye on usage!");
        	}
        	else if(battery < 10) {
        		tv1.setText("Charge your phone soon!");
        	}
        }
        
    }
}