// package mot;

// import Fonction.Fonction;
// import java.util.*;
// import java.util.concurrent.locks.Condition;
// import java.lang.Class.*;
// import java.lang.reflect.*;

// import javax.management.ValueExp;

// public class Vocabulaire {
//     Fonction fonction = new Fonction();

//     public void Projection(String phrase, Object[] obj) {
//         String[] mots = phrase.split(" ");
//         String error = "Oups verifier votre requete";
//         Field[] fields = obj[0].getClass().getDeclaredFields();
//         if ("select".equalsIgnoreCase(mots[0])) {
//             if (mots[1].equalsIgnoreCase("*")) {
//                 String[] Cols= {"*"};
//                 if ("from".equalsIgnoreCase(mots[2])) {
//                     if (obj[0].getClass().getSimpleName().equalsIgnoreCase(mots[3])) {
//                         fonction.Projection(obj, Cols);
//                     }
//                 }
//             }
//             int i = 1;
//             Vector vect = new Vector();
//             while (mots[i].equalsIgnoreCase("from") == false) {
//                 for (int j = 0; j < fields.length; j++) {
//                     if (fields[j].getName().equalsIgnoreCase(mots[i])) {
//                         vect.add(j);
//                     }
//                 }
//                 i++;
//             }
//             String[] Cols = new String[vect.size()];
//             for (int j = 0; j < Cols.length; j++) {
//                 Cols[j] = fields[(int) vect.get(j)].getName();
//             }
//             int manaraka = Cols.length + 1;
//             if ("from".equalsIgnoreCase(mots[manaraka])) {
//                 if (obj[0].getClass().getSimpleName().equalsIgnoreCase(mots[manaraka + 1])) {
//                     fonction.Projection(obj, Cols);
//                 }
//             }
//         } else {
//             System.out.println(error);
//         }
//     }

//     public void Selection(String phrase, Object[] obj) {
//         String[] mots = phrase.split(" ");
//         String error = "Oups verifier votre requete";
//         Field[] fields = obj[0].getClass().getDeclaredFields();
//         Vector condVector= new Vector();
//         Vector ValCond= new Vector();
//         if ("select".equalsIgnoreCase(mots[0])) {
//             if (mots[1].equalsIgnoreCase("*")) {
//                 if ("from".equalsIgnoreCase(mots[2])) {
//                     if (obj[0].getClass().getSimpleName().equalsIgnoreCase(mots[3])) {
//                         if ("where".equalsIgnoreCase(mots[4])) {
//                             for (int i = 5; i < mots.length; i++) {
//                                 if(i%2==0){
//                                     try{
//                                         if(mots[i].equalsIgnoreCase("and")){
//                                             System.out.println("and");
//                                         }
//                                     }
//                                     catch(Exception e){
//                                         System.out.println(error);
//                                     }
//                                 }
//                                 else{
//                                     String[] conditions = mots[i].split("=");
//                                     try{
//                                         for (int j = 0; j < fields.length; j++) {
//                                             if(fields[j].getName().equalsIgnoreCase(conditions[0])){
//                                                 condVector.add(conditions[0]);
//                                                 System.out.println("Cond "+conditions[0]);
//                                                 try {
//                                                     Field f = obj[0].getClass().getDeclaredField(conditions[0]);
//                                                     f.setAccessible(true);
//                                                     for (int k = 0; k < fields.length; k++) {
//                                                         try{
//                                                             if (f.get(obj[k]).toString().equalsIgnoreCase(conditions[1])) {
//                                                                 ValCond.add(conditions[1]);
//                                                                 if(ValCond.get(ValCond.size()-1).toString().equalsIgnoreCase(conditions[1])){
//                                                                     // ValCond.remove(ValCond.size()-1);
//                                                                     // ValCond.add(conditions[1]);
//                                                                     System.out.println((int) ValCond.size());
//                                                                 }
//                                                             }
//                                                         }
//                                                         catch(Exception e){
//                                                             System.out.println(error);
//                                                         }
//                                                     }
//                                                 } catch (Exception e) {
//                                                     System.out.println(e);
//                                                 }
//                                             }
//                                         }
//                                     }
//                                     catch(Exception e){
//                                         System.out.println(error);
//                                     }
//                                 }                             
//                             }
//                         }
//                     }
//                 }
//             }
//         } else {
//             System.out.println(error);
//         }
//     }

//     public void Difference(String phrase, Object[] T1, Object[] T2){
//         String[] mots = phrase.split(" ");
//         String error = "Oups verifier votre requete";
//         if ("select".equalsIgnoreCase(mots[0])) {
//             if("difference".equalsIgnoreCase(mots[1])){
//                 if ("between".equalsIgnoreCase(mots[2])) {
//                     if(T1[0].getClass().getSimpleName().toString().equalsIgnoreCase(mots[3])){
//                         if("and".equalsIgnoreCase(mots[4])){
//                             if(T2[0].getClass().getSimpleName().toString().equalsIgnoreCase(mots[5])){
//                                 System.out.println("Difference effectue:");
//                                 fonction.Difference(T1, T2);
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//         else{
//             System.out.println(error);
//         }
//     }
// }