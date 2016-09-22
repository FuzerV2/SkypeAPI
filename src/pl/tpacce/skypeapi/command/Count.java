package pl.tpacce.skypeapi.command;

import com.skype.ChatMessage;
import com.skype.SkypeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Szymon on 2015-06-25.
 */
public class Count extends Command {

    private ChatMessage chat;

    public Count(ChatMessage chat) {
        this.chat = chat;
    }

    @Override
    public void dothing() throws SkypeException {
        Pattern pattern = Pattern.compile("\\$count\\([0-9]*\\)");
        Matcher matcher = pattern.matcher(chat.getContent());
        while (matcher.find()) {
            count(chat, Integer.parseInt(matcher.group(matcher.groupCount())
                    .replaceFirst("\\$count\\(", "")
                    .replaceFirst("\\)", "")));
        }
    }

    public void count(final ChatMessage sended, final int c) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try { sended.setContent(sended.getContent().replaceFirst("\\$count\\(" + c + "\\)", "0")); } catch (SkypeException e) { }
                for (int i = 1; i <= c; i++) {
                    try { sended.setContent(sended.getContent().replaceFirst(Integer.toString(i - 1), Integer.toString(i))); } catch (SkypeException e) { }
                    try { Thread.sleep(250); } catch (InterruptedException e) { }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setName("Count");
        thread.start();
    }
}
