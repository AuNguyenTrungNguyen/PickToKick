package picktokick.devfest.picktokick.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by banhtrung on 11/23/2017.
 */

public class Forecastday implements Parcelable
{

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_epoch")
    @Expose
    private Integer dateEpoch;
    @SerializedName("day")
    @Expose
    private Day day;
    @SerializedName("astro")
    @Expose
    private Astro astro;
    @SerializedName("hour")
    @Expose
    private List<Hour> hour = null;
    public final static Creator<Forecastday> CREATOR = new Creator<Forecastday>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Forecastday createFromParcel(Parcel in) {
            return new Forecastday(in);
        }

        public Forecastday[] newArray(int size) {
            return (new Forecastday[size]);
        }

    }
            ;

    protected Forecastday(Parcel in) {
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.dateEpoch = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.day = ((Day) in.readValue((Day.class.getClassLoader())));
        this.astro = ((Astro) in.readValue((Astro.class.getClassLoader())));
        in.readList(this.hour, (Hour.class.getClassLoader()));
    }

    public Forecastday() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDateEpoch() {
        return dateEpoch;
    }

    public void setDateEpoch(Integer dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public List<Hour> getHour() {
        return hour;
    }

    public void setHour(List<Hour> hour) {
        this.hour = hour;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(date);
        dest.writeValue(dateEpoch);
        dest.writeValue(day);
        dest.writeValue(astro);
        dest.writeList(hour);
    }

    public int describeContents() {
        return 0;
    }
}