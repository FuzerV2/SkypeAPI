package pl.tpacce.skypeapi.action;

/**
 * Created by Szymon on 2015-07-07.
 */
public abstract class Action {

    public abstract void dothing() throws Exception;

    public enum Type {
        AUTOANSWER, AWAY, CALL, CHAT, DESC, MULTICHAT, SPAM, STATUS;
    }
}
