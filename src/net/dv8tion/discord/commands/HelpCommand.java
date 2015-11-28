package net.dv8tion.discord.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class HelpCommand extends Command
{

    private ArrayList<Command> commands;

    public HelpCommand()
    {
        commands = new ArrayList<Command>();
    }

    @Override
    public void onChat(UserChatEvent e)
    {
        if (!containsCommand(e.getMsg()))
            return;

        String[] args = commandArgs(e.getMsg());
        if (args.length < 2)
        {
            StringBuilder s = new StringBuilder();
            for (Command c : commands)
            {
                s.append("**").append(c.getAliases().get(0)).append("** - ");
                s.append(c.getDescription()).append("\n");
            }
            //TODO: Replace with a PrivateMessage
            e.getGroup().sendMessage(new MessageBuilder()
                .addUserTag(e.getUser(), e.getGroup())
                .addString(": The following commands are supported by the bot\n")
                .addString(s.toString())
                .build());
        }
        else
        {

        }
    }

    public Command registerCommand(Command command)
    {
        commands.add(command);
        return command;
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(new String[] {".help"});
    }

    @Override
    public String getDescription()
    {
        return null;
    }

    @Override
    public String getUsageInstructions()
    {
        return null;
    }
}