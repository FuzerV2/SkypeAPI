package pl.tpacce.skypeapi.action;

import pl.tpacce.skypeapi.main.Profile;

/**
 * Created by Szymon on 2015-07-07.
 */
public class Desc extends Action {

    private String desc;

    public Desc(String desc) {
        this.desc = desc;
    }

    @Override
    public void dothing() {
        Profile profile = new Profile();
        profile.setDesc(this.desc);
    }


}
