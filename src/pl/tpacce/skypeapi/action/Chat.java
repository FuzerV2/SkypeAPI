package pl.tpacce.skypeapi.action;

import com.skype.Skype;
import com.skype.SkypeException;

/**
 * Created by Szymon on 2015-06-23.
 */
public class Chat extends Action {

    private String user;
    private String message;

    public Chat(String user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public void dothing() throws SkypeException {
        Skype.chat(this.user).send(this.message);
    }
}
