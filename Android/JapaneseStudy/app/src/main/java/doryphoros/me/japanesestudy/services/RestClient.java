package doryphoros.me.japanesestudy.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import doryphoros.me.japanesestudy.activities.MainActivity;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Link on 2/5/2016.
 */

public class RestClient
{
    private JapaneseService apiService;

    public RestClient(Context context)
    {
        final SharedPreferences prefs = context.getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.1.111:3000")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        apiService = restAdapter.create(JapaneseService.class);
    }

    public JapaneseService getApiService()
    {
        return apiService;
    }
}