package picktokick.devfest.picktokick.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by banhtrung on 11/23/2017.
 */

public interface Api_interface {
    @GET("forecast.json?key=1559c291a09440a989d61816172311&q=10.0417618,105.7831367&days=7")
    Call<Weather> getApi();
}
