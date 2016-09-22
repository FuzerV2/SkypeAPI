package pl.tpacce.skypeapi.command;

import com.skype.ChatMessage;
import com.skype.SkypeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szymon on 2015-06-25.
 */
public class Percent extends Command {

    private List<String> percents = new ArrayList<String>();
    private ChatMessage chat;

    public Percent(ChatMessage chat) {
        percents.add("-");
        percents.add("\\");
        percents.add("|");
        percents.add("/");

        this.chat = chat;
    }

    @Override
    public void dothing() throws SkypeException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String percent = percents.get(0);
                try { chat.setContent(chat.getContent().replace("$percent", percent + " " + 0 + "%")); } catch (SkypeException e) { }
                int c = 0;
                for (int i = 1; i <= 100; i++) {
                    if (c > 3) c = 0;
                    String _percent = percents.get(c);
                    try { chat.setContent(chat.getContent().replace(percent + " " + (i - 1) + "%", _percent + " " + i + "%")); } catch (SkypeException e) { }
                    percent = _percent;
                    c++;
                    try { Thread.sleep(250); } catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setName("Percent");
        thread.start();
    }
}
