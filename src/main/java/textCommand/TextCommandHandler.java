package textCommand;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class TextCommandHandler {
    /**
     * Called to handle a text command passed in
     * @param args arguments passed into command
     * @param e event in question
     */
    public abstract void onCommand(String[] args, MessageReceivedEvent e);
}
