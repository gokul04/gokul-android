	package my.namespace;
	
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends Activity {
	    
	    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
	    
	    protected LocationManager locationManager;
	    
	    protected Button retrieveLocationButton;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);

	        retrieveLocationButton = (Button) findViewById(R.id.retrieve_location_button);
	        
	        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	        
	        locationManager.requestLocationUpdates(
	                LocationManager.GPS_PROVIDER, 
	                MINIMUM_TIME_BETWEEN_UPDATES, 
	                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
	                new MyLocationListener()
	        );
	        
	    retrieveLocationButton.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                showCurrentLocation();
	            }
	    });        
	        
	    }    

	    protected void showCurrentLocation() {

	        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

	        if (location != null) {
	            String message = String.format(
	                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
	                    location.getLongitude(), location.getLatitude()
	            );

	            //MapView mapview = new MapView;
	            //GeoPoint p = new mapview.getProjection().fromPixels((int) event.getX(),(int) event.getY());
	            Geocoder geoCoder = new Geocoder(
                        getBaseContext(), Locale.getDefault());
                
                try 
                {
                    List<Address> addresses = geoCoder.getFromLocation(
                        location.getLatitude(), 
                        location.getLongitude(), 1);
 
                    String add = "";
                    if (addresses.size() > 0) 
                    {
                        for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); 
                             i++)
                           add += addresses.get(0).getAddressLine(i) + "\n";
                    }
 
                    Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
                }
                catch (IOException e) {                
                    e.printStackTrace();
                }
	         //   Toast.makeText(HomeActivity.this, message, Toast.LENGTH_LONG).show();
	        }

	    }   

	    private class MyLocationListener implements LocationListener {

	        public void onLocationChanged(Location location) {
	            String message = String.format(
	                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
	                    location.getLongitude(), location.getLatitude()
	            );
	            Toast.makeText(HomeActivity.this, message, Toast.LENGTH_LONG).show();
	        }

	        public void onStatusChanged(String s, int i, Bundle b) {
	            Toast.makeText(HomeActivity.this, "Provider status changed",
	                    Toast.LENGTH_LONG).show();
	        }

	        public void onProviderDisabled(String s) {
	            Toast.makeText(HomeActivity.this,
	                    "Provider disabled by the user. GPS turned off",
	                    Toast.LENGTH_LONG).show();
	        }

	        public void onProviderEnabled(String s) {
	            Toast.makeText(HomeActivity.this,
	                    "Provider enabled by the user. GPS turned on",
	                    Toast.LENGTH_LONG).show();
	        }

	    }
	    
}
