package picktokick.devfest.picktokick.objects;

/**
 * Created by phamh on 11/24/2017.
 */

public class MessageObj {
    private String userName;
    private String dateSent;
    private String urlPicture;
    private String bodyMessage;
    private String idMember;
    private boolean isCheckSend = false;

    public MessageObj(String userName, String dateSent, String urlPicture, String bodyMessage, String idMember, boolean isCheckSend) {
        this.userName = userName;
        this.dateSent = dateSent;
        this.urlPicture = urlPicture;
        this.bodyMessage = bodyMessage;
        this.idMember = idMember;
        this.isCheckSend = isCheckSend;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public String getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(String bodyMessage) {
        this.bodyMessage = bodyMessage;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public boolean isCheckSend() {
        return isCheckSend;
    }

    public void setCheckSend(boolean checkSend) {
        isCheckSend = checkSend;
    }
}
