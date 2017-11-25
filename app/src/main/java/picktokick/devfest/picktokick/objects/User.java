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
    private List<Friend> listFriends;
    private List<Friend> listWait;
    private double x;
    private double y;

    public User() {
    }

    public User(String idUser, String tenUser, String linkAvataUser, List<Friend> listFriends, List<Friend> listWait, double x, double y) {
        this.idUser = idUser;
        this.tenUser = tenUser;
        this.linkAvataUser = linkAvataUser;
        this.listFriends = listFriends;
        this.listWait = listWait;
        this.x = x;
        this.y = y;
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

    public List<Friend> getListFriends() {
        return listFriends;
    }

    public void setListFriends(List<Friend> listFriends) {
        this.listFriends = listFriends;
    }

    public List<Friend> getListWait() {
        return listWait;
    }

    public void setListWait(List<Friend> listWait) {
        this.listWait = listWait;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
