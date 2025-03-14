package BabyBaby.Command.commands.Owner;

import BabyBaby.Command.CommandContext;
import BabyBaby.Command.IOwnerCMD;
import BabyBaby.Command.StandardHelp;
import BabyBaby.data.Data;
import net.dv8tion.jda.api.entities.MessageEmbed;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class toMultipixelCMD implements IOwnerCMD{

    @Override
    public String getName() {
        return "tomulti";
    }

    @Override
    public void handleOwner(CommandContext ctx) {

        try {
            Scanner scanner = new Scanner(new File(Data.PLACE + ctx.getArgs().get(0) + ".txt"));
            
            ArrayList<String> list = new ArrayList<String>();
            PrintStream writer = null;
            while (scanner.hasNextLine())
                list.add(scanner.nextLine());
            
            int idx = 0;
            boolean first = true;
            for (int i = 0; i < list.size(); i++) {
                if (i % 3600 == 0) {
                    writer = new PrintStream(Data.PLACE + ctx.getArgs().get(0) + idx + ".txt");
                    idx++;
                    first = true;
                }
                if (!first) {
                    writer.print("|");
                }
                first = false;
                writer.print(list.get(i).substring(16));
            }
            writer.close();
        } catch (FileNotFoundException e1) {
            ctx.getMessage().addReaction(Data.xmark).queue();
            e1.printStackTrace();
            return;
        }
        
        ctx.getMessage().addReaction(Data.check).queue();


    }

    @Override
    public MessageEmbed getOwnerHelp(String prefix) {
        return StandardHelp.Help(prefix, getName(), "<FileName>", "Make a normal file into a multipixel file");
    }
    
}
