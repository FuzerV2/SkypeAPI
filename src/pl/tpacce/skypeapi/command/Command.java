package pl.tpacce.skypeapi.command;

import com.skype.SkypeException;

import javax.script.ScriptException;

/**
 * Created by Szymon on 2015-06-25.
 */
public abstract class Command {

    public abstract void dothing() throws SkypeException, ScriptException, InterruptedException;

    public enum Type {
        COUNT, MATH, PERCENT, EMOTICON;
    }
}
