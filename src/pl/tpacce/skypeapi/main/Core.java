package pl.tpacce.skypeapi.main;

import pl.tpacce.skypeapi.action.*;

import java.util.Scanner;

/**
 * Created by Szymon on 2015-06-23.
 */
public class Core {

    public Scanner scanner;

    public static Core core;

    public static void main(String[] args) throws Exception {
        core = new Core();
        core.start();
    }

    public void start() throws Exception {
        scanner = new Scanner(System.in);
        new Message().start();
        System.out.println("Action?");
        System.out.println("SPAM, AWAY, STATUS, DESC, AUTOANSWER, CHAT, MULTICHAT, CALL");
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Action.Type action = Action.Type.valueOf(line.toUpperCase());
            switch (action) {
                case SPAM:
                    System.out.println("User?");
                    String user = scanner.nextLine();
                    System.out.println("Message?");
                    String msg = scanner.nextLine();
                    System.out.println("Count?");
                    int count = Integer.parseInt(scanner.nextLine());
                    new Spam(user, msg, count).dothing();
                case AWAY:
                    new Away().dothing();
                case STATUS:
                    System.out.println("Status?");
                    String status = scanner.nextLine();
                    new Status(status).dothing();
                case DESC:
                    System.out.println("Desc?");
                    String desc = scanner.nextLine();
                    new Desc(desc).dothing();
                case AUTOANSWER:
                    new AutoAnswer().dothing();
                case CHAT:
                    System.out.println("User?");
                    user = scanner.nextLine();
                    System.out.println("Message?");
                    msg = scanner.nextLine();
                    new Chat(user, msg).dothing();
                case MULTICHAT:
                    System.out.println("Message?");
                    msg = scanner.nextLine();
                    new MultiChat(msg).dothing();
                case CALL:
                    System.out.println("User?");
                    user = scanner.nextLine();
                    new Call(user).dothing();
                default:
                    System.out.println("This action not exists");
            }
        }
    }
}
