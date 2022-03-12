package a5.djorgensen.weatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements UIBind{
    private Spinner spinnerLocations;
    private APIBridge apiBridge;
    private TextView txtLatitude, txtLongitude, txtLow, txtCurrent,
            txtPressure, txtHigh, txtFeelsLike, txtHumidity, txtWeatherDescription, txtWindSpeed;
    NetworkImageView imgWeatherIcon;
    ImageView imgWindDir;
    RequestQueue requestQueue;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.apiBridge = new APIBridge(this, getApplicationContext());
        this.txtLatitude = findViewById(R.id.txtLatitude);
        this.txtLongitude = findViewById(R.id.txtLongitude);
        this.txtLow = findViewById(R.id.txtLow);
        this.txtCurrent = findViewById(R.id.txtCurrent);
        this.txtPressure = findViewById(R.id.txtPressure);
        this.txtHigh = findViewById(R.id.txtHigh);
        this.txtFeelsLike = findViewById(R.id.txtFeelsLike);
        this.txtHumidity = findViewById(R.id.txtHumidity);
        this.txtWeatherDescription = findViewById(R.id.txtWeatherDescription);
        this.txtWindSpeed = findViewById(R.id.txtWindSpeed);
        this.imgWeatherIcon = findViewById(R.id.imgWeatherIcon);
        this.imgWindDir = findViewById(R.id.imgWindDir);
        this.requestQueue = Volley.newRequestQueue(this);
        this.imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<>(10);
            @Nullable
            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
        });
        this.spinnerLocations = findViewById(R.id.spinLocations);

        spinnerLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                apiBridge.GenerateWeatherModel(spinnerLocations.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void mapUI(WeatherModel weatherModel) {
        txtLatitude.setText(String.valueOf(weatherModel.getLat()));
        txtLongitude.setText(String.valueOf(weatherModel.getLon()));
        txtLow.setText(String.format("%sF", weatherModel.getTempMin()));
        txtCurrent.setText(String.format("%sF", weatherModel.getTemp()));
        txtPressure.setText(String.format("%sF", weatherModel.getPressure()));
        txtHigh.setText(String.format("%sF", weatherModel.getTempMax()));
        txtFeelsLike.setText(String.format("%shPa", weatherModel.getFeelsLike()));
        txtHumidity.setText(String.format("%s%%", weatherModel.getHumidity()));
        imgWeatherIcon.setImageUrl(weatherModel.getWeatherIcon(), this.imageLoader);
        txtWeatherDescription.setText(weatherModel.getWeatherDescription());
        txtWindSpeed.setText(String.format("%sMPH", weatherModel.getWindSpeed()));
        imgWindDir.setRotation(weatherModel.getWindDirection());
    }
}