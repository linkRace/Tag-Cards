package doryphoros.me.japanesestudy.utils;

/**
 * Created by Link on 2/5/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import doryphoros.me.japanesestudy.activities.MainActivity;
import doryphoros.me.japanesestudy.constants.JPConstants;
import doryphoros.me.japanesestudy.services.JapaneseService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public class JPUtils {

    static final int READ_BLOCK_SIZE = 100;
    static JSONObject jsonObject = new JSONObject();
    public final static String TAG = "JPUtils";

    public static void storeJsonToFile(Context context, JsonElement jsonElement, String filename) {
        String string = jsonElement.toString();
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

            OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream);
            outputWriter.write(string);

            outputWriter.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JsonElement getJsonFromFile(Context context, String filename) {
        FileInputStream inputStream;
        JsonElement jsonElement = null;

        try {
            inputStream = context.openFileInput(filename);

            InputStreamReader inputReader = new InputStreamReader(inputStream);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;
            while ((charRead = inputReader.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            jsonElement = new JsonParser().parse(s);

            inputReader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonElement;
    }


    public interface DataLoaderCallback {
        public void onDataLoaded();
    }

    public static void getDataFromApi(final Context context, final DataLoaderCallback cb) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://doryphoros.me:3000")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        JapaneseService service = restAdapter.create(JapaneseService.class);
        System.out.println("calling getCards");
        service.getCards(new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                Log.d(TAG, "success!");
                Log.d(TAG, "from api: " + jsonElement.toString());
                JPUtils.storeJsonToFile(context, jsonElement, JPConstants.CARD_DATA_FILENAME);
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://doryphoros.me:3000")
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build();
                JapaneseService service = restAdapter.create(JapaneseService.class);
                System.out.println("calling getKanji");
                service.getKanji(new Callback<JsonElement>() {
                    @Override
                    public void success(JsonElement jsonElement, Response response) {
                        Log.d(TAG, "success!");
                        Log.d(TAG, "from api: " + jsonElement.toString());
                        Toast.makeText(context, "Data downloaded successfully", Toast.LENGTH_SHORT).show();
                        JPUtils.storeJsonToFile(context, jsonElement, JPConstants.KANJI_DATA_FILENAME);
                /*
                AuthResponse authResponse = new Gson().fromJson(jsonElement, AuthResponse.class);
                UserData.instance = authResponse.user;
                */
                        cb.onDataLoaded();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d(TAG, "error");
                        Toast.makeText(context, "Failure! Data was not downloaded", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "error");
                Toast.makeText(context, "Failure! Data was not downloaded", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
    }

    /**
     * Method extracts cookie string from headers
     * @param response with headers
     * @return cookie string if present or null
     */
    private static String getCookieString(Response response) {
        for (Header header : response.getHeaders()) {
            if (null!= header.getName() && header.getName().equals("set-cookie")) {
                return header.getValue().substring(0, header.getValue().indexOf(';'));
            }
        }
        return null;
    }
}
