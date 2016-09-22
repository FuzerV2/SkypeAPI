package pl.tpacce.skypeapi.action;

import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;

/**
 * Created by Szymon on 2015-06-23.
 */
public class MultiChat extends Action {

    private String message;

    public MultiChat(String message) {
        this.message = message;
    }

    @Override
    public void dothing() throws SkypeException {
        for (Friend friend : Skype.getContactList().getAllFriends())
            Skype.chat(friend.getId()).send(message);
    }
}
