/*     */ package fr.byakko.foodplugin;
/*     */ 
/*     */ import fr.byakko.foodplugin.utils.EffectsUtils;
/*     */ import fr.byakko.foodplugin.utils.MessagesUtils;
/*     */ import fr.byakko.foodplugin.utils.TypeUtils;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventsListener
/*     */   implements Listener
/*     */ {
/*     */   public int task;
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGHEST)
/*     */   public void onInteract(PlayerInteractEvent e) {
/*  30 */     final Player p = e.getPlayer();
/*  31 */     ItemStack it = e.getItem();
/*     */ 
/*     */     
/*  34 */     if (it == null) {
/*     */       return;
/*     */     }
/*  37 */     ItemMeta im = it.getItemMeta();
/*     */ 
/*     */     
/*  40 */     if (p.getFoodLevel() >= 20) {
/*  41 */       if (!im.hasLore()) {
/*     */         return;
/*     */       }
/*  44 */       p.setFoodLevel(p.getFoodLevel() - 1);
/*     */       
/*  46 */       this.task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.INSTANCE, new Runnable() {
/*     */             public void run() {
/*  48 */               p.setFoodLevel(Math.min(20, p.getFoodLevel() + 1));
/*  49 */               Bukkit.getScheduler().cancelTask(EventsListener.this.task);
/*     */             }
/*  51 */           }5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGHEST)
/*     */   public void onEat(PlayerItemConsumeEvent e) {
/*  58 */     Player p = e.getPlayer();
/*  59 */     ItemStack it = e.getItem();
/*  60 */     ItemMeta im = it.getItemMeta();
/*  61 */     List<String> lore = im.getLore();
/*     */     
/*  63 */     if (!im.hasLore()) {
/*     */       return;
/*     */     }
/*     */     
/*  67 */     for (String ilore : lore) {
/*  68 */       if (ilore.contains("Health") || ilore.contains("Hunger")) {
/*     */         
/*  70 */         ilore = MessagesUtils.stripColor(ilore);
/*  71 */         Player player = p;
/*  72 */         String[] splitData = ilore.split(":");
/*  73 */         String effect = splitData[0].replaceAll("> ", "");
/*  74 */         String value = splitData[1].replaceAll(" ", "").replaceAll("❤.", "").replaceAll("♨.", "");
/*  75 */         value = value.replaceAll("\\+", "");
/*  76 */         if (effect.equalsIgnoreCase("Health")) {
/*  77 */           p.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + Float.parseFloat(value))); continue;
/*     */         } 
/*  79 */         if (effect.equalsIgnoreCase("Hunger"))
/*  80 */           p.setFoodLevel(Math.min(20, p.getFoodLevel() + (int)Float.parseFloat(value))); 
/*     */         continue;
/*     */       } 
/*  83 */       if (EffectsUtils.containEffects(ilore)) {
/*  84 */         String effect = "";
/*  85 */         float duration = 0.0F;
/*  86 */         int lvl = 0;
/*     */         
/*  88 */         ilore = MessagesUtils.stripColor(ilore);
/*  89 */         ilore = ilore.replaceAll("> ", "");
/*  90 */         String[] splitData = ilore.split(":");
/*  91 */         effect = splitData[0].split(" ")[0];
/*  92 */         lvl = TypeUtils.fromRomanToInteger(splitData[0].split(" ")[1]);
/*  93 */         String format = splitData[1].replaceAll(" ", "").replaceAll("//.", "");
/*  94 */         duration = EffectsUtils.toSeconds(format).floatValue();
/*  95 */         for (PotionEffect potion : p.getActivePotionEffects()) {
/*  96 */           if (potion.getType().getName().equalsIgnoreCase(effect)) {
/*  97 */             p.removePotionEffect(PotionEffectType.getByName(effect));
/*     */             break;
/*     */           } 
/*     */         } 
/* 101 */         p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect), 20 * (int)duration, lvl - 1), false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              A:\Produit SAO\SaoFrFood.jar!\fr\byakko\foodplugin\EventsListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.2
 */