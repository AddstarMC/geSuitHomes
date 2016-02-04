package net.cubespace.geSuitHomes.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.cubespace.geSuitHomes.managers.HomesManager;

import javax.annotation.RegEx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetHomeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(args.length==0){
			HomesManager.setHome(sender, "home");
		}else{
			Pattern invalidChar = Pattern.compile("[^a-zA-Z0-9_äöüßÄÖÜ-]");
			Matcher reMatch = invalidChar.matcher(args[0]);

			StringBuffer bufstr = new StringBuffer();
			boolean flag = false;
            int count = 0;

			while (flag = reMatch.find()) {
				String rep = reMatch.group();
				reMatch.appendReplacement(bufstr, ChatColor.YELLOW + rep.replace("$","\\$") + ChatColor.RED);
                count++;
			}
			reMatch.appendTail(bufstr);

			if (count>0) {
                String result = (count) + " invalid character" + (count>1?"s":"") + " in new home name: " + bufstr.toString();
				sender.sendMessage(ChatColor.RED + result);
				return true;
			}

			HomesManager.setHome(sender, args[0]);
		}
		return true;
	}

}
