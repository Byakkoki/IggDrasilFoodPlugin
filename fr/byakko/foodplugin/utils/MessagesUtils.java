/*    */ package fr.karmaowner.foodplugin.utils;
/*    */ 
/*    */ import fr.karmaowner.foodplugin.Main;
/*    */ import java.util.regex.Pattern;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MessagesUtils
/*    */ {
/* 11 */   private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-OR]");
/*    */ 
/*    */   
/*    */   public static String getMessageFromConfig(String path) {
/* 15 */     String msg = Main.INSTANCE.getConfig().getString("messages." + path);
/* 16 */     if (msg != null)
/* 17 */       msg = msg.replaceAll("&", "§"); 
/* 18 */     return msg;
/*    */   }
/*    */ 
/*    */   
/* 22 */   public static void sendMessage(CommandSender sender, String msg) { sender.sendMessage(String.valueOf(Main.prefix) + " " + msg); }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public static String stripColor(String input) { return (input == null) ? null : STRIP_COLOR_PATTERN.matcher(input).replaceAll(""); }
/*    */ }


/* Location:              A:\Produit SAO\SaoFrFood.jar!\fr\karmaowner\foodplugi\\utils\MessagesUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.2
 */