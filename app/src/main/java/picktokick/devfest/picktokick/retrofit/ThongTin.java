package picktokick.devfest.picktokick.retrofit;

/**
 * Created by banhtrung on 11/23/2017.
 */

public class ThongTin {
    private String Date ;
    private double MaxTem ;
    private double MinTem ;
    private String url ;
    private String state ;

    public ThongTin (String Date , String state , String url , double Max , double Min) {
        this.Date =Date ;
        this.url = url ;
        this.state = state ;
        this.MaxTem = Max ;
        this.MinTem = Min ;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getMaxTem() {
        return MaxTem;
    }

    public void setMaxTem(double maxTem) {
        MaxTem = maxTem;
    }

    public double getMinTem() {
        return MinTem;
    }

    public void setMinTem(double minTem) {
        MinTem = minTem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
