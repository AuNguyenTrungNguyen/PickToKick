package picktokick.devfest.picktokick.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by banhtrung on 11/23/2017.
 */

public class Astro implements Parcelable
{

    @SerializedName("sunrise")
    @Expose
    private String sunrise;
    @SerializedName("sunset")
    @Expose
    private String sunset;
    @SerializedName("moonrise")
    @Expose
    private String moonrise;
    @SerializedName("moonset")
    @Expose
    private String moonset;
    public final static Creator<Astro> CREATOR = new Creator<Astro>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Astro createFromParcel(Parcel in) {
            return new Astro(in);
        }

        public Astro[] newArray(int size) {
            return (new Astro[size]);
        }

    }
            ;

    protected Astro(Parcel in) {
        this.sunrise = ((String) in.readValue((String.class.getClassLoader())));
        this.sunset = ((String) in.readValue((String.class.getClassLoader())));
        this.moonrise = ((String) in.readValue((String.class.getClassLoader())));
        this.moonset = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Astro() {
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sunrise);
        dest.writeValue(sunset);
        dest.writeValue(moonrise);
        dest.writeValue(moonset);
    }

    public int describeContents() {
        return 0;
    }

}