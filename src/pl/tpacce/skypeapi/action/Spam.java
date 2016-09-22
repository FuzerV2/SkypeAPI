package pl.tpacce.skypeapi.action;

import com.skype.Skype;
import com.skype.SkypeException;

/**
 * Created by Szymon on 2015-06-24.
 */
public class Spam extends Action {

    private String user;
    private String message;
    private int count;

    public Spam(String user, String message, int count) {
        this.user = user;
        this.message = message;
        this.count = count;
    }

    @Override
    public void dothing() {
        for (int i = 1; i <= this.count; i++) {
            try {
                Skype.chat(this.user).send(this.message
                .replace("$int", Integer.toString(this.count))
                .replace("$+int", Integer.toString(i))
                .replace("$-int", Integer.toString(this.count - i)));
            } catch (SkypeException e) { }
        }
    }
}
