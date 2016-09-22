package pl.tpacce.skypeapi.command;

import com.skype.ChatMessage;
import com.skype.SkypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Szymon on 2015-06-25.
 */
public class Emoticon extends Command {

    private List<String> emoticons = new ArrayList<String>();
    private ChatMessage chat;

    public Emoticon(ChatMessage chat) {
        emoticons.add(":-)");
        emoticons.add(":-(");
        emoticons.add(":-D");
        emoticons.add("8-)");
        emoticons.add(";-)");
        emoticons.add(":-o");
        emoticons.add(";-(");
        emoticons.add("(:|");
        emoticons.add(":-|");
        emoticons.add(":-*");
        emoticons.add(":-P");
        emoticons.add("(yn)");
        emoticons.add(":-$");
        emoticons.add(":^)");
        emoticons.add("|-)");
        emoticons.add("|-(");
        emoticons.add("]:)");
        emoticons.add(":-&");
        emoticons.add(":-@");
        emoticons.add(":-S");
        emoticons.add("8-|");
        emoticons.add(":-x");
        emoticons.add(":-?");

        this.chat = chat;
    }

    @Override
    public void dothing() throws SkypeException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String emoticon = emoticons.get(new Random().nextInt(emoticons.size()));
                try { chat.setContent(chat.getContent().replace("$emoticon", emoticon)); } catch (SkypeException e) { }
                while (true) {
                    String _emoticon = emoticons.get(new Random().nextInt(emoticons.size()));
                    try { chat.setContent(chat.getContent().replace(emoticon, _emoticon)); } catch (SkypeException e) { }
                    emoticon = _emoticon;
                    try { Thread.sleep(250); } catch (InterruptedException e) { }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setName("Emoticon");
        thread.start();
    }
}
