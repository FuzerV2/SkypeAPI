package pl.tpacce.skypeapi.action;

import com.skype.ChatMessage;
import com.skype.SkypeException;
import pl.tpacce.skypeapi.main.Profile;

import java.util.Scanner;

/**
 * Created by Szymon on 2015-06-23.
 */
public class Away extends Action {

    public ChatMessage chat;
    public boolean fromskype;
    public Scanner scanner = new Scanner(System.in);

    public Away() { this.fromskype = false; }

    public Away(ChatMessage chat) {
        this.chat = chat;
        this.fromskype = true;
    }

    @Override
    public void dothing() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (fromskype) System.out.println("To stop away edit message to \"STOP\"");
                else System.out.println("To stop away write \"STOP\"");
                Profile profile = new Profile();
                while (true) {
                    try {
                        if (fromskype && chat.getContent().equalsIgnoreCase("STOP")) break;
                        if (!fromskype && scanner.hasNextLine() && scanner.nextLine().equalsIgnoreCase("STOP")) break;
                    } catch (SkypeException e) { }
                    try {
                        if (profile.getProfile().getStatus() == com.skype.Profile.Status.ONLINE)
                            profile.getProfile().setStatus(com.skype.Profile.Status.INVISIBLE);
                        else profile.getProfile().setStatus(com.skype.Profile.Status.ONLINE);
                        try { Thread.sleep(50); } catch (InterruptedException e) { }
                    } catch (SkypeException e) { }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setName("Away");
        thread.start();
    }
}
