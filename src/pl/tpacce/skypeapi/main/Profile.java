package pl.tpacce.skypeapi.main;

import com.skype.Skype;
import com.skype.SkypeException;

/**
 * Created by Szymon on 2015-06-23.
 */
public class Profile {

    private com.skype.Profile profile;

    public Profile() {
        this.profile = Skype.getProfile();
    }

    public void setStatus(com.skype.Profile.Status status) {
        try { getProfile().setStatus(status); } catch (SkypeException e) {}
    }

    public void setDesc(String desc) {
        try { getProfile().setMoodMessage(desc); } catch (SkypeException e) {}
    }

    public void setSex(com.skype.Profile.Sex sex) {
        try { getProfile().setSex(sex); } catch (SkypeException e) {}
    }

    public com.skype.Profile getProfile() {
        return this.profile;
    }
}
