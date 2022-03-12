package a5.djorgensen.weatherapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIBridge {
    private String apiKey, geocodingURL, weatherURL, weatherIconURL;
    private WeatherModel weatherModel = new WeatherModel();
    private UIBind uiBind;
    private RequestQueue queue;

    public APIBridge(UIBind uiBind, Context context) {
        this.uiBind = uiBind;
        this.queue = Volley.newRequestQueue(context);
        String apiJson = null;
        try {
            InputStream is = context.getAssets().open("api.json");
            int size = is.available();
            byte[] buff = new byte[size];
            is.read(buff);
            is.close();
            apiJson = new String(buff, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(apiJson);
            this.apiKey = jsonObject.getString("apiKey");
            this.geocodingURL = jsonObject.getString("geocodingURL");
            this.weatherURL = jsonObject.getString("weatherURL");
            this.weatherIconURL = jsonObject.getString("weatherIconURL");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void GenerateWeatherModel(String loc) {
        String url = null;
        try {
            url = String.format(this.geocodingURL, URLEncoder.encode(loc, String.valueOf(StandardCharsets.UTF_8)), this.apiKey);
            Log.i("REST", url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.i("REST", response);
                    try {
                        JSONArray tmp = new JSONArray(response);
                        weatherModel.setLat(tmp.getJSONObject(0).getDouble("lat"));
                        weatherModel.setLon(tmp.getJSONObject(0).getDouble("lon"));
                        getWeather(String.valueOf(weatherModel.getLat()), String.valueOf(weatherModel.getLon()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.e("REST", error.getMessage()));
        queue.add(stringRequest);
    }

    private void getWeather(String lat, String lon) {
        String url = String.format(this.weatherURL, lat, lon, this.apiKey);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.i("REST", response);
                    try {
                        JSONObject tmp = new JSONObject(response);
                        JSONObject wind = tmp.getJSONObject("wind");
                        weatherModel.setWindSpeed(wind.getDouble("speed"));
                        weatherModel.setWindDirection(wind.getInt("deg"));

                        JSONObject weather = tmp.getJSONArray("weather").getJSONObject(0);
                        weatherModel.setWeatherIcon(String.format(weatherIconURL, weather.getString("icon")));
                        weatherModel.setWeatherDescription(weather.getString("description"));

                        JSONObject main = tmp.getJSONObject("main");
                        weatherModel.setTemp(main.getDouble("temp"));
                        weatherModel.setTempMin(main.getDouble("temp_min"));
                        weatherModel.setHumidity(main.getInt("humidity"));
                        weatherModel.setPressure(main.getInt("pressure"));
                        weatherModel.setFeelsLike(main.getDouble("feels_like"));
                        weatherModel.setTempMax(main.getDouble("temp_max"));
                        uiBind.mapUI(weatherModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.e("REST", error.getMessage()));
        queue.add(stringRequest);
    }
}
