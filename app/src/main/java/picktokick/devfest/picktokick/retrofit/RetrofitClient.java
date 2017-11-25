package picktokick.devfest.picktokick.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by banhtrung on 11/23/2017.
 */

public class RetrofitClient {
    static Retrofit retrofit = null ;

    public static Retrofit getClient(String base_url) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
