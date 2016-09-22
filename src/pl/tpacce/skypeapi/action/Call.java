package pl.tpacce.skypeapi.action;

import com.skype.Skype;
import com.skype.SkypeException;

/**
 * Created by Szymon on 2015-06-23.
 */
public class Call extends Action {

    private String user;

    public Call(String user) {
        this.user = user;
    }

    @Override
    public void dothing() throws SkypeException {
        Skype.call(this.user);
    }
}
