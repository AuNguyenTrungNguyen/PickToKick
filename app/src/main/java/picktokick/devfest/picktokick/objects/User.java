package picktokick.devfest.picktokick.objects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by quocb14005xx on 11/24/2017.
 */

public class User implements Serializable {
    private String idUser;
    private String tenUser;
    private String linkAvataUser;
    private List<String> listFriends;

    public User() {
    }

    public User(String idUser, String tenUser, String linkAvataUser, List<String> listFriends) {
        this.idUser = idUser;
        this.tenUser = tenUser;
        this.linkAvataUser = linkAvataUser;
        this.listFriends = listFriends;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }

    public String getLinkAvataUser() {
        return linkAvataUser;
    }

    public void setLinkAvataUser(String linkAvataUser) {
        this.linkAvataUser = linkAvataUser;
    }

    public List<String> getListFriends() {
        return listFriends;
    }

    public void setListFriends(List<String> listFriends) {
        this.listFriends = listFriends;
    }
}
