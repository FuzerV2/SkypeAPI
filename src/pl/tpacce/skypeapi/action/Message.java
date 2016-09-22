package pl.tpacce.skypeapi.action;

import com.skype.*;
import pl.tpacce.skypeapi.command.Count;
import pl.tpacce.skypeapi.command.Emoticon;
import pl.tpacce.skypeapi.command.Percent;
import pl.tpacce.skypeapi.main.Date;

import javax.script.ScriptException;

/**
 * Created by Szymon on 2015-06-24.
 */
public class Message {

    private ChatMessageListener listener;
    private ChatMessageEditListener editlistener;

    public void start() {
        Skype.setDaemon(false);
        listener = new ChatMessageListener() {
            public void chatMessageReceived(ChatMessage received) {
                try { AutoAnswer.think(received); } catch (Exception e) { }
            }
            public void chatMessageSent(ChatMessage sended) {
                try {
                    String message = sended.getContent();
                    sended.setContent("!" + message);
                    sended.setContent(message);
                } catch (SkypeException e) { }
            }
        };
        editlistener = new ChatMessageEditListener() {
            public void chatMessageEdited(ChatMessage edited, java.util.Date date, User user) {
                try { replace(edited); } catch (SkypeException e) { } catch (ScriptException e) { } catch (Exception e) { }
            }
        };
        try {
            Skype.addChatMessageListener(listener);
            Skype.addChatMessageEditListener(editlistener);
        } catch (SkypeException e) { }
    }

    public void replace(final ChatMessage chat) throws Exception {
        replace$(chat);
        replaceCmd(chat);
        if (chat.getContent().contains("$count")) new Count(chat).dothing();
        if (chat.getContent().contains("$math")) new pl.tpacce.skypeapi.command.Math(chat).dothing();
        if (chat.getContent().contains("$percent")) new Percent(chat).dothing();
        if (chat.getContent().contains("$emoticon")) new Emoticon(chat).dothing();
    }

    public void replace$(final ChatMessage chat) throws SkypeException {
        String message = chat.getContent();
        if (message.contains("$nick") || message.contains("$mynick") || message.contains("$date") || message.contains("$time")) {
            message = message
                    .replace("$nick", Skype.getUser(chat.getChat().getId().split("/\\$")[1].split(";")[0]).getDisplayName())
                    .replace("$mynick", chat.getSenderDisplayName())
                    .replace("$date", Date.getDate())
                    .replace("$time", Date.getTime());
            chat.setContent(message);
        }
    }

    public void replaceCmd(final ChatMessage chat) throws Exception {
        if (chat.getContent().contains("$chat")) {
            String user = chat.getContent()
                    .replaceFirst("\\$chat:", "")
                    .split(":")[0];
            String msg = chat.getContent()
                    .replaceFirst("\\$chat:", "")
                    .replaceFirst(user + ":", "");
            chat.setContent("Command performed. (Chat)");
            new Chat(user, msg).dothing();
        }
        if (chat.getContent().contains("$call")) {
            String user = chat.getContent()
                    .replaceFirst("\\$call:", "")
                    .split(":")[0];
            chat.setContent("Command performed. (Call)");
            new Call(user).dothing();
        }
        if (chat.getContent().contains("$multichat")) {
            String msg = chat.getContent()
                    .replaceFirst("\\$multichat:", "");
            chat.setContent("Command performed. (MultiChat)");
            new MultiChat(msg).dothing();
        }
        if (chat.getContent().contains("$desc")) {
            String desc = chat.getContent()
                    .replaceFirst("\\$desc:", "");
            chat.setContent("Command performed. (Desc)");
            new Desc(desc).dothing();
        }
        if (chat.getContent().contains("$spam")) {
            String user = chat.getContent()
                    .replaceFirst("\\$spam:", "")
                    .split(":")[0];
            int count = Integer.parseInt(chat.getContent()
                        .replaceFirst("\\$spam:", "")
                        .replaceFirst(user + ":", "").split(":")[0]);
            String msg = chat.getContent()
                    .replaceFirst("\\$spam:", "")
                    .replaceFirst(user + ":", "")
                    .replaceFirst(count + ":", "");
            chat.setContent("Command performed. (Spam)");
            new Spam(user, msg, count).dothing();
        }
        if (chat.getContent().contains("$status")) {
            String status = chat.getContent()
                    .replaceFirst("\\$status:", "");
            chat.setContent("Command performed. (Status)");
            new Status(status).dothing();
        }
        if (chat.getContent().contains("$away")) {
            chat.setContent("Command performed. (Away)");
            new Away(chat).dothing();
        }
        if (chat.getContent().contains("$autoanswer")) {
            chat.setContent("Command performed. (AutoAnswer)");
            new AutoAnswer(chat).dothing();
        }
    }

    public void stop() throws SkypeException {
        Skype.removeChatMessageListener(listener);
        Skype.removeChatMessageEditListener(editlistener);
    }
}
