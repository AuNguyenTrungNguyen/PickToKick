package picktokick.devfest.picktokick.objects;

import java.io.Serializable;

/**
 * Created by Au Nguyen on 11/25/2017.
 */

public class Member implements Serializable{
    private String idMember;
    private String nameOfMember;
    private String urlMember;

    public Member() {}

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getNameOfMember() {
        return nameOfMember;
    }

    public void setNameOfMember(String nameOfMember) {
        this.nameOfMember = nameOfMember;
    }

    public String getUrlMember() {
        return urlMember;
    }

    public void setUrlMember(String urlMember) {
        this.urlMember = urlMember;
    }
}
