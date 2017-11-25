package picktokick.devfest.picktokick.retrofit;

/**
 * Created by banhtrung on 11/23/2017.
 */

public class useApi {
    public static final String base_url = "http://api.apixu.com/v1/";

    public static Api_interface using () {
        return RetrofitClient.getClient(base_url).create(Api_interface.class) ;
    }
}
