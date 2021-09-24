/*     */ package fr.byakko.foodplugin.commands;
/*     */ 
/*     */ import fr.byakko.foodplugin.utils.EffectsUtils;
/*     */ import fr.byakko.foodplugin.utils.MessagesUtils;
/*     */ import fr.byakko.foodplugin.utils.TypeUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Pattern;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FoodCommand
/*     */   implements CommandExecutor
/*     */ {
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
/*  26 */     if (!(sender instanceof Player)) {
/*  27 */       return false;
/*     */     }
/*  29 */     Player p = (Player)sender;
/*     */     
/*  31 */     if (cmd.getName().equalsIgnoreCase("food")) {
/*  32 */       if (!p.hasPermission("SaoFrFood.*")) {
/*     */         
/*  34 */         MessagesUtils.sendMessage(sender, MessagesUtils.getMessageFromConfig("permission"));
/*  35 */         return true;
/*     */       } 
/*     */ 
/*     */       
/*  39 */       if (args.length == 0) {
/*  40 */         return displayCommandHelp(sender);
/*     */       }
/*  42 */       if (args.length >= 1 && TypeUtils.isInteger(args[0])) {
/*     */         
/*  44 */         if (Integer.parseInt(args[0]) < 0) {
/*     */           
/*  46 */           MessagesUtils.sendMessage(sender, MessagesUtils.getMessageFromConfig("integer-less-0"));
/*  47 */           return true;
/*     */         } 
/*  49 */         if (args.length >= 2 && EffectsUtils.effectExist(args[1])) {
/*  50 */           ItemStack item = p.getInventory().getItemInHand();
/*  51 */           if (item != null) {
/*  52 */             String color = "§6";
/*  53 */             if (args.length >= 3 && TypeUtils.isFloat(args[2])) {
/*  54 */               if (!args[1].equalsIgnoreCase("HEALTH") && !args[1].equalsIgnoreCase("HUNGER")) {
/*  55 */                 if (Integer.parseInt(args[2]) < 0) {
/*     */                   
/*  57 */                   MessagesUtils.sendMessage(sender, MessagesUtils.getMessageFromConfig("integer-less-0"));
/*  58 */                   return true;
/*     */                 } 
/*  60 */                 if (Integer.parseInt(args[2]) > 10) {
/*     */                   
/*  62 */                   MessagesUtils.sendMessage(sender, MessagesUtils.getMessageFromConfig("integer-more-10"));
/*  63 */                   return true;
/*     */                 } 
/*     */                 
/*  66 */                 if (args.length >= 4 && !args[3].isEmpty()) {
/*  67 */                   Pattern pattern = Pattern.compile("^([0-9])+[shm]$");
/*  68 */                   if (!pattern.matcher(args[3]).matches()) {
/*  69 */                     MessagesUtils.sendMessage(sender, MessagesUtils.getMessageFromConfig("bad-time-format"));
/*  70 */                     return true;
/*     */                   } 
/*  72 */                   if (args.length >= 5 && args[4].contains("&"))
/*  73 */                     color = args[4].replaceAll("&", "§"); 
/*  74 */                   applyLore(Integer.parseInt(args[0]), args[1], args[2], args[3], color, item);
/*     */                 }
/*     */                 else {
/*     */                   
/*  78 */                   MessagesUtils.sendMessage(sender, MessagesUtils.getMessageFromConfig("empty-time"));
/*  79 */                   return true;
/*     */                 }
/*     */               
/*     */               } else {
/*     */                 
/*  84 */                 if (args.length >= 4 && args[3].contains("&"))
/*  85 */                   color = args[3].replaceAll("&", "§"); 
/*  86 */                 applyLore(Integer.parseInt(args[0]), args[1], args[2], "", color, item);
/*     */               } 
/*     */             } else {
/*     */               
/*  90 */               MessagesUtils.sendMessage(sender, MessagesUtils.getMessageFromConfig("not-value"));
/*  91 */               return true;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/*  96 */             MessagesUtils.sendMessage(sender, MessagesUtils.getMessageFromConfig("not-food"));
/*  97 */             return true;
/*     */           } 
/*     */         } else {
/*     */           
/* 101 */           EffectsUtils.displayEffectList(sender);
/*     */         } 
/*     */       } else {
/*     */         
/* 105 */         MessagesUtils.sendMessage(sender, MessagesUtils.getMessageFromConfig("not-integer"));
/* 106 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public boolean displayCommandHelp(CommandSender sender) {
/* 115 */     sender.sendMessage("§e---------§6 SaoFrFood §c1.0 §eDev : byakko §e---------");
/* 116 */     sender.sendMessage("");
/* 117 */     sender.sendMessage("  §e-/food <ligne> <Hunger/Health> <valeur> <couleur> §f- §6Personnalise la nourriture en fonction des arguments");
/* 118 */     sender.sendMessage("");
/* 119 */     sender.sendMessage("  §e-/food <ligne> <effet> <niv effet> <durée s/m/h> <couleur> §f- §6Personnalise la nourriture en fonction des arguments");
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   public void applyLore(int line, String effectName, String value1, String value2, String color, ItemStack it) {
/* 124 */     List<String> lore = null;
/* 125 */     ItemMeta meta = it.getItemMeta();
/*     */     
/* 127 */     String itemName = CraftItemStack.asNMSCopy(it).getName();
/*     */     
/* 129 */     itemName = String.valueOf(color) + itemName;
/*     */     
/* 131 */     meta.setDisplayName(itemName);
/*     */     
/* 133 */     String effect = String.valueOf(effectName.substring(0, 1).toUpperCase()) + effectName.substring(1).toLowerCase();
/*     */     
/* 135 */     if (it.getItemMeta().hasLore()) {
/* 136 */       lore = meta.getLore();
/*     */     } else {
/* 138 */       lore = new ArrayList<String>();
/*     */     } 
/* 140 */     if (lore.size() < line) {
/* 141 */       for (int i = 0; i < line; i++) {
/*     */         
/* 143 */         if (lore.size() != line) {
/* 144 */           lore.add("");
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 151 */     if (effectName.equalsIgnoreCase("HEALTH") || effectName.equalsIgnoreCase("HUNGER"))
/* 152 */     { if (effectName.equalsIgnoreCase("HUNGER")) {
/* 153 */         lore.set(line - 1, "§f> " + effect + ": " + color + "+" + value1 + "♨§f.");
/*     */       } else {
/* 155 */         lore.set(line - 1, "§f> " + effect + ": " + color + "+" + value1 + "❤§f.");
/*     */       }  }
/* 157 */     else { lore.set(line - 1, "§f> " + effect + " " + TypeUtils.ToRoman(Integer.parseInt(value1)) + ": " + color + value2 + "§f."); }
/*     */     
/* 159 */     if (lore.contains("§6SAOFOOD"))
/*     */     {
/* 161 */       lore.remove("§6SAOFOOD");
/*     */     }
/* 163 */     lore.add("§6SAOFOOD");
/*     */     
/* 165 */     meta.setLore(lore);
/* 166 */     it.setItemMeta(meta);
/*     */   }
/*     */ }


/* Location:              A:\Produit SAO\SaoFrFood.jar!\fr\byakko\foodplugin\commands\FoodCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.2
 */