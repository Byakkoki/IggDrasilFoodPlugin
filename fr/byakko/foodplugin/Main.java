/*     */ package fr.byakko.foodplugin;
/*     */ 
/*     */ import fr.byakko.foodplugin.commands.FoodCommand;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ public class Main
/*     */   extends JavaPlugin
/*     */ {
/*  18 */   public static String prefix = "§6[§eSaoFrFood§6]";
/*     */   
/*     */   public static final String version = "1.0";
/*     */   
/*     */   public static final String name = "SaoFrFood";
/*     */   public static Main INSTANCE;
/*  24 */   public static String licenceKey = "";
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  29 */     INSTANCE = this;
/*  30 */     saveDefaultConfig();
/*  31 */     System.out.println(licenceKey);
/*  32 */     registerCommands();
/*  33 */     registerEvents();
/*  34 */     prefix = getConfig().getString("prefix").replaceAll("&", "§");
/*  35 */     licenceKey = getConfig().getString("key-licence");
/*  36 */     if (keyIsCorrect(licenceKey, "https://www.atonha.fr/api/check.php").contains("false")) {
/*     */       
/*  38 */       System.out.println("§cVous devez payer une licence pour utiliser ce plugin ou un serveur utilise déjà ce mod merci de prévenir Atonha");
/*  39 */       INSTANCE.getServer().getPluginManager().disablePlugin(INSTANCE);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  45 */   public void onDisable() { System.out.println("disabled SaoFrFood"); }
/*     */ 
/*     */ 
/*     */   
/*  49 */   private void registerCommands() { getCommand("food").setExecutor(new FoodCommand()); }
/*     */ 
/*     */ 
/*     */   
/*  53 */   private void registerEvents() { getServer().getPluginManager().registerEvents(new EventsListener(), this); }
/*     */ 
/*     */   
/*     */   public static String keyIsCorrect(String licencekey, String url) {
/*  57 */     out = null;
/*  58 */     in = null;
/*  59 */     String result = "";
/*  60 */     String postData = "";
/*  61 */     String ip = "";
/*     */     try {
/*  63 */       ip = getIp();
/*  64 */     } catch (IOException e2) {
/*  65 */       e2.printStackTrace();
/*     */     } 
/*     */     try {
/*  68 */       postData = String.valueOf(URLEncoder.encode("licencekey", "UTF-8")) + "=" + URLEncoder.encode(licencekey, "UTF-8");
/*  69 */       postData = String.valueOf(postData) + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode("abcdadminpassworddiffucltelevated", "UTF-8");
/*  70 */       postData = String.valueOf(postData) + "&" + URLEncoder.encode("ip", "UTF-8") + "=" + URLEncoder.encode(ip, "UTF-8");
/*  71 */     } catch (UnsupportedEncodingException e1) {
/*  72 */       e1.printStackTrace();
/*     */     } 
/*     */     try {
/*  75 */       URL realUrl = new URL(url);
/*     */       
/*  77 */       URLConnection conn = realUrl.openConnection();
/*     */       
/*  79 */       conn.setRequestProperty("accept", "*/*");
/*  80 */       conn.setRequestProperty("connection", "Keep-Alive");
/*  81 */       conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
/*     */       
/*  83 */       conn.setDoOutput(true);
/*  84 */       conn.setDoInput(true);
/*  85 */       out = new PrintWriter(conn.getOutputStream());
/*     */       
/*  87 */       out.print(postData);
/*  88 */       out.flush();
/*  89 */       in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
/*     */       String line;
/*  91 */       while ((line = in.readLine()) != null) {
/*  92 */         result = String.valueOf(result) + line;
/*     */       }
/*  94 */     } catch (Exception e) {
/*  95 */       e.printStackTrace();
/*     */     } finally {
/*     */       
/*     */       try {
/*  99 */         if (out != null) {
/* 100 */           out.close();
/*     */         }
/* 102 */         if (in != null) {
/* 103 */           in.close();
/*     */         }
/* 105 */       } catch (IOException ex) {
/* 106 */         ex.printStackTrace();
/*     */       } 
/*     */     } 
/* 109 */     System.out.println(result);
/* 110 */     return result;
/*     */   }
/*     */   
/*     */   public static String getIp() throws IOException {
/* 114 */     whatismyip = new URL("http://checkip.amazonaws.com");
/* 115 */     BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
/*     */     
/* 117 */     return in.readLine();
/*     */   }
/*     */ }


/* Location:              A:\Produit SAO\SaoFrFood.jar!\fr\byakko\foodplugin\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.2
 */