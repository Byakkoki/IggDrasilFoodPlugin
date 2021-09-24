/*    */ package fr.byakko.foodplugin.utils;
/*    */ 
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectsUtils
/*    */ {
/*    */   public static boolean effectExist(String name) {
/* 12 */     if (PotionEffectType.getByName(name) != null)
/* 13 */       return true; 
/* 14 */     if (name.equalsIgnoreCase("health") || name.equalsIgnoreCase("hunger"))
/* 15 */       return true; 
/* 16 */     return false; } public static boolean containEffects(String str) {
/*    */     byte b;
/*    */     int i;
/*    */     PotionEffectType[] arrayOfPotionEffectType;
/* 20 */     for (i = arrayOfPotionEffectType = PotionEffectType.values().length, b = 0; b < i; ) { PotionEffectType effect = arrayOfPotionEffectType[b];
/* 21 */       if (effect != null && 
/* 22 */         str.toLowerCase().contains(effect.getName().toLowerCase())) {
/* 23 */         return true;
/*    */       }
/*    */       b++; }
/*    */     
/* 27 */     if (str.contains("HEALTH") || str.contains("HUNGER")) {
/* 28 */       return true;
/*    */     }
/* 30 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean displayEffectList(CommandSender sender) {
/* 34 */     sender.sendMessage("§e---------§6 Liste des effets §e---------");
/* 35 */     String effectsList = "";
/* 36 */     for (int i = 0; i < PotionEffectType.values().length; i++) {
/*    */       
/* 38 */       if (PotionEffectType.values()[i] != null)
/* 39 */         effectsList = String.valueOf(effectsList) + PotionEffectType.values()[i].getName() + " "; 
/*    */     } 
/* 41 */     sender.sendMessage("§a" + effectsList);
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   public static Float toSeconds(String format) {
/* 46 */     System.out.println("format = " + format);
/* 47 */     if (format.contains("h"))
/* 48 */       return Float.valueOf(3600.0F * Float.parseFloat(format.split("h")[0])); 
/* 49 */     if (format.contains("m"))
/* 50 */       return Float.valueOf(60.0F * Float.parseFloat(format.split("m")[0])); 
/* 51 */     if (format.contains("s"))
/* 52 */       return Float.valueOf(Float.parseFloat(format.split("s")[0])); 
/* 53 */     return Float.valueOf(0.0F);
/*    */   }
/*    */ }


/* Location:              A:\Produit SAO\SaoFrFood.jar!\fr\byakko\foodplugi\\utils\EffectsUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.2
 */