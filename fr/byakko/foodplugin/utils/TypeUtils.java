/*    */ package fr.karmaowner.foodplugin.utils;
/*    */ 
/*    */ public class TypeUtils
/*    */ {
/*    */   public static boolean isInteger(String value) {
/*    */     try {
/*  7 */       Integer.parseInt(value);
/*  8 */     } catch (NumberFormatException e) {
/*  9 */       return false;
/* 10 */     } catch (NullPointerException e) {
/* 11 */       return false;
/*    */     } 
/* 13 */     return true;
/*    */   }
/*    */   
/*    */   public static boolean isFloat(String value) {
/*    */     try {
/* 18 */       Float.parseFloat(value);
/* 19 */     } catch (NumberFormatException e) {
/* 20 */       return false;
/* 21 */     } catch (NullPointerException e) {
/* 22 */       return false;
/*    */     } 
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   public static String ToRoman(int number) {
/* 28 */     if (number < 1 || number > 3999)
/* 29 */       return ""; 
/* 30 */     if (number >= 1000) return "M" + ToRoman(number - 1000); 
/* 31 */     if (number >= 900) return "CM" + ToRoman(number - 900); 
/* 32 */     if (number >= 500) return "D" + ToRoman(number - 500); 
/* 33 */     if (number >= 400) return "CD" + ToRoman(number - 400); 
/* 34 */     if (number >= 100) return "C" + ToRoman(number - 100); 
/* 35 */     if (number >= 90) return "XC" + ToRoman(number - 90); 
/* 36 */     if (number >= 50) return "L" + ToRoman(number - 50); 
/* 37 */     if (number >= 40) return "XL" + ToRoman(number - 40); 
/* 38 */     if (number >= 10) return "X" + ToRoman(number - 10); 
/* 39 */     if (number >= 9) return "IX" + ToRoman(number - 9); 
/* 40 */     if (number >= 5) return "V" + ToRoman(number - 5); 
/* 41 */     if (number >= 4) return "IV" + ToRoman(number - 4); 
/* 42 */     if (number >= 1) return "I" + ToRoman(number - 1); 
/* 43 */     return "INVALID";
/*    */   }
/*    */ 
/*    */   
/*    */   public static int fromRomanToInteger(String r) {
/* 48 */     int result = 0;
/* 49 */     int[] decimal = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
/* 50 */     String[] roman = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
/*    */     
/* 52 */     for (int i = 0; i < decimal.length; i++) {
/* 53 */       while (r.indexOf(roman[i]) == 0) {
/* 54 */         result += decimal[i];
/* 55 */         r = r.substring(roman[i].length());
/*    */       } 
/*    */     } 
/* 58 */     return result;
/*    */   }
/*    */ }


/* Location:              A:\Produit SAO\SaoFrFood.jar!\fr\karmaowner\foodplugi\\utils\TypeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.2
 */