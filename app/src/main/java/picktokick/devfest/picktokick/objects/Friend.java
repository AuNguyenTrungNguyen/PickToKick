package picktokick.devfest.picktokick.objects;

import java.io.Serializable;

/**
 * Created by quocb14005xx on 11/25/2017.
 */

public class Friend implements Serializable {
    private String id;
    private String nameFr;
    private String link;
    private double x;
    private double y;

    public Friend() {
    }

    public Friend(String ID, String nameFr, String link, double x, double y) {
        this.id = ID;
        this.nameFr = nameFr;
        this.link = link;
        this.x = x;
        this.y = y;
    }

    public Friend(String ID, String link, String nameFr) {
        this.id = ID;
        this.nameFr = nameFr;
        this.link = link;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getNameFr() {
        return nameFr;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
