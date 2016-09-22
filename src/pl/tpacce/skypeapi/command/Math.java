package pl.tpacce.skypeapi.command;

import com.skype.ChatMessage;
import com.skype.SkypeException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Szymon on 2015-06-25.
 */
public class Math extends Command {

    private ScriptEngineManager mgr = new ScriptEngineManager();
    private ScriptEngine engine = mgr.getEngineByName("JavaScript");
    private ChatMessage chat;

    public Math(ChatMessage chat) {
        this.chat = chat;
    }

    @Override
    public void dothing() throws SkypeException, ScriptException {
        Pattern pattern = Pattern.compile("\\$math\\([0-9-+-/-\\)\\(-\\*--]*\\)");
        Matcher matcher = pattern.matcher(chat.getContent());
        while (matcher.find()) {
            String equation = matcher.group(matcher.groupCount())
                    .replaceFirst("\\$math\\(", "");
            math(chat, new StringBuilder(equation).replace(equation.lastIndexOf(')'), equation.lastIndexOf(')') + 1, "" ).toString());
        }
    }

    public void math(ChatMessage chat, String equation) throws SkypeException, ScriptException {
        Float f = Float.parseFloat(engine.eval(equation).toString());
        chat.setContent(chat.getContent().replace("$math(" + equation + ")", Float.toString(f)));
    }
}
