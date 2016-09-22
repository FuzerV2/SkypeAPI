package pl.tpacce.skypeapi.action;

import pl.tpacce.skypeapi.main.Profile;

/**
 * Created by Szymon on 2015-07-07.
 */
public class Status extends Action {

    private com.skype.Profile.Status status;

    public Status(String status) {
        this.status = com.skype.Profile.Status.valueOf(status.toUpperCase());
    }

    public Status(com.skype.Profile.Status status) {
        this.status = status;
    }

    @Override
    public void dothing() {
        Profile profile = new Profile();
        profile.setStatus(this.status);
    }
}
