package picktokick.devfest.picktokick.objects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by phamh on 11/25/2017.
 */

public class GroupChatObj implements Serializable{
    private List<MessageObj> messageObjList;

    public GroupChatObj(List<MessageObj> messageObjList) {
        this.messageObjList = messageObjList;
    }

    public GroupChatObj() {
    }

    public List<MessageObj> getMessageObjList() {
        return messageObjList;
    }

    public void setMessageObjList(List<MessageObj> messageObjList) {
        this.messageObjList = messageObjList;
    }
}
