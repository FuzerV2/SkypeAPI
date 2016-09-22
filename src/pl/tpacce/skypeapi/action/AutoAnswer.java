package pl.tpacce.skypeapi.action;

import com.skype.*;
import pl.tpacce.botapi.ChatterBot;
import pl.tpacce.botapi.ChatterBotFactory;
import pl.tpacce.botapi.ChatterBotSession;
import pl.tpacce.botapi.ChatterBotType;

import java.util.Scanner;

/**
 * Created by Szymon on 2015-06-23.
 */
public class AutoAnswer extends Action {

    public Scanner scanner = new Scanner(System.in);

    public static ChatterBotFactory factory;
    public static ChatterBot bot;
    public static ChatterBotSession session;

    private ChatMessage chat;
    private boolean fromskype;
    public static boolean started;

    public AutoAnswer() { this.fromskype = false; }

    public AutoAnswer(ChatMessage chat) {
        this.chat = chat;
        this.fromskype = true;
    }

    @Override
    public void dothing() throws Exception {
        started = true;
        factory = new ChatterBotFactory();
        bot = factory.create(ChatterBotType.CLEVERBOT);
        session = bot.createSession();
        if (fromskype) System.out.println("To stop autoanswer edit message to \"STOP\"");
        else System.out.println("To stop autoanswer write \"STOP\"");
        waitToStop();
}

    public void waitToStop() throws SkypeException {
        while (true) {
            if (fromskype && chat.getContent().equalsIgnoreCase("STOP")) {
                started = false;
                break;
            }
            if (!fromskype && scanner.hasNextLine() && scanner.nextLine().equalsIgnoreCase("STOP")) {
                started = false;
                break;
            }
        }
    }

    public static void think(ChatMessage chat) throws Exception {
        if (started) chat.getSender().send(session.think(chat.getContent()));
    }
}
