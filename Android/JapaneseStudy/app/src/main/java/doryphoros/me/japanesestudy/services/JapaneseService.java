package doryphoros.me.japanesestudy.services;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Sara on 8/3/2015.
 */
public interface JapaneseService {
    @GET(("/card/findCards"))
    void getCards(Callback<JsonElement> cb);
}
