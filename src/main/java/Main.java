import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import textCommand.TextCommandDelegator;

import javax.security.auth.login.LoginException;
import java.io.InputStream;
import java.util.Scanner;

public class Main extends ListenerAdapter {
    public static void main(String[] args) {
        try {
            JDA api = new JDABuilder(getToken(Main.class.getResourceAsStream("secret.txt"))).build();
            api.addEventListener(new Main());
            api.awaitReady();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getToken(InputStream iS) {
        Scanner scan = new Scanner(iS);
        String line = scan.nextLine();
        line = line.substring(line.indexOf(':') + 1);
        return line.trim();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;
        Message message = e.getMessage();
        String content = message.getContentRaw();
        if (content.startsWith(";")) {
            content = content.substring(1);
            int index = content.indexOf(" ");
            if (index == -1) index = content.length();
            String command = content.substring(0, index);
            content = content.substring(index).trim();
            String[] args = content.split("\\s+");
            TextCommandDelegator.delegate(command, args, e);
        }
    }
}
