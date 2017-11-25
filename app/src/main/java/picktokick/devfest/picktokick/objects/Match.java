package picktokick.devfest.picktokick.objects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by quocb14005xx on 11/24/2017.
 */

public class Match implements Serializable{
    private String idMatch;
    private String addressMatch;
    private String idPoster;
    private String nameOfPoster;
    private String typeOfMatch;
    private String thoigian;
    private List<String> listMember;
    private String description;
    private String urlOfMatch;
    private double xMatch;
    private double yMatch;

    public Match() {
    }

    public Match(String idMatch, String addressMatch, String idPoster, String nameOfPoster, String typeOfMatch, String thoigian, List<String> listMember, String description, String urlOfMatch, double xMatch, double yMatch) {
        this.idMatch = idMatch;
        this.addressMatch = addressMatch;
        this.idPoster = idPoster;
        this.nameOfPoster = nameOfPoster;
        this.typeOfMatch = typeOfMatch;
        this.thoigian = thoigian;
        this.listMember = listMember;
        this.description = description;
        this.urlOfMatch = urlOfMatch;
        this.xMatch = xMatch;
        this.yMatch = yMatch;
    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public String getAddressMatch() {
        return addressMatch;
    }

    public void setAddressMatch(String addressMatch) {
        this.addressMatch = addressMatch;
    }

    public String getIdPoster() {
        return idPoster;
    }

    public void setIdPoster(String idPoster) {
        this.idPoster = idPoster;
    }

    public String getNameOfPoster() {
        return nameOfPoster;
    }

    public void setNameOfPoster(String nameOfPoster) {
        this.nameOfPoster = nameOfPoster;
    }

    public String getTypeOfMatch() {
        return typeOfMatch;
    }

    public void setTypeOfMatch(String typeOfMatch) {
        this.typeOfMatch = typeOfMatch;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public List<String> getListMember() {
        return listMember;
    }

    public void setListMember(List<String> listMember) {
        this.listMember = listMember;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlOfMatch() {
        return urlOfMatch;
    }

    public void setUrlOfMatch(String urlOfMatch) {
        this.urlOfMatch = urlOfMatch;
    }

    public double getxMatch() {
        return xMatch;
    }

    public void setxMatch(double xMatch) {
        this.xMatch = xMatch;
    }

    public double getyMatch() {
        return yMatch;
    }

    public void setyMatch(double yMatch) {
        this.yMatch = yMatch;
    }

   /* @Override
    public int compareTo(@NonNull Match match) {
        int compareQuantity = (int)Double.parseDouble((match).getThoigian());
        return (int)Double.parseDouble(this.getThoigian()) - compareQuantity;
    }*/
}
