package Fonction;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.util.*;
import java.io.*;

import objet.Objet;

public class Fonction {

    public void Afficher(Object[][] rep){
        for (Object[] objects : rep) {
            for (Object object : objects) {
                System.out.print("| "+object+" |");
            }
            System.out.println();
          }
    }

    public Object[][] Projection(Object[][] obj, String[] fields, String[] col) {
        Object[][] rep = null;
        if (col.length == 1 && col[0].equalsIgnoreCase("*")) {
            rep = new Object[obj.length][fields.length];
            for (int i = 0; i < obj.length; i++) {
                for (int j = 0; j < fields.length; j++) {
                    try {
                        rep[i][j] = obj[i][j];
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        } else {
            rep = new Object[obj.length][col.length];
            for (int i = 0; i < obj.length; i++) {
                for (int j = 0; j < col.length; j++) {
                    for (int k = 0; k < fields.length; k++) {
                        if (fields[k].equalsIgnoreCase(col[j])) {
                            try {
                                rep[i][j]=obj[i][k];
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
            }
        }
        Afficher(rep);
        return rep;
    }

    public Object[][] Selection(Object[][] obj, String[] fields, String[] col, String[] condition, String[] ValCond) {
        Object[][] rep;
        if (condition.length == ValCond.length) {
            Vector indice = new Vector();
            for (int k = 0; k < obj.length; k++) {
                int manisa = 0;
                for (int i = 0; i < condition.length; i++) {
                    for (int j = 0; j < fields.length; j++) {
                        if (condition[i].equalsIgnoreCase(fields[j])) {
                            try {
                                if (ValCond[i].equalsIgnoreCase(obj[k][j].toString())) {
                                    manisa++;
                                    System.out.println(obj[k][j]);
                                }
                                if (manisa == condition.length) {
                                    indice.add(k);
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
            }
            Object[][] tab = new Object[indice.size()][fields.length];
            for (int i = 0; i < tab.length; i++) {
                for(int j=0; j<tab[i].length; j++){
                    tab[i][j] = obj[(int) indice.get(i)][j];
                }
            }
            rep = Projection(tab, fields, col);
        } else {
            rep = null;
            System.out.println("Verifier votre condition");
        }
        return rep;
    }

    public Object[][] Difference(Object[][] T1, String[] fields, Object[][] T2, String[] fields2) {
        String[] valT1 = new String[T1.length];
        for (int i = 0; i < T1.length; i++) {
            valT1[i] = "";
            for (int j = 0; j < fields.length; j++) {
                try {
                    valT1[i] = valT1[i] + T1[i][j].toString() + " ";
                } catch (Exception e) {
                    System.out.println("Tsy mety");
                }
            }
        }
        String[] valT2 = new String[T2.length];
        for (int i = 0; i < T2.length; i++) {
            valT2[i] = "";
            for (int j = 0; j < fields2.length; j++) {
                try {
                    valT2[i] = valT2[i] + T2[i][j].toString() + " ";
                } catch (Exception e) {
                    System.out.println("Tsy mety");
                }
            }
        }
        Vector indice = new Vector();
        for (int i = 0; i < valT1.length; i++) {
            int manisa = 0;
            for (int j = 0; j < valT2.length; j++) {
                if (!valT1[i].equalsIgnoreCase(valT2[j])) {
                    manisa++;
                    if (manisa == valT2.length) {
                        indice.add(i);
                    }
                }
            }
        }
        Object[][] rep = new Object[indice.size()][fields.length];
        for (int i = 0; i < rep.length; i++) {
            String[] strings = valT1[(int) indice.get(i)].split(" ");
            for (int j = 0; j < strings.length; j++) {
                rep[i][j] = strings[j];
            }
        }
        Afficher(rep);
        return rep;
    }

    public Object[][] Cartesien(Object[][] data1, Object[][] data2) throws Exception {
        Object[][] infos1 = data1;
        Object[][] infos2 = data2;

        int nbLignes = data1.length * infos2.length;
        int nbCol = infos1[0].length + infos2[0].length;

        Object[][] rep = new Object[nbLignes][nbCol];

        int k2 = 0;
        int indLigneInfo1 = 0;
        int indLigneInfo2 = 0;

        for (int i = 0; i < nbLignes; i++) {
            if (indLigneInfo2 >= infos2.length) {
                indLigneInfo2 = 0;
                indLigneInfo1++;
            }
            for (int k = 0; k < infos1[0].length; k++) {
                rep[i][k] = infos1[indLigneInfo1][k];
                k2 = k;
            }
            int k3 = k2 + 1;
            for (int l = 0; l < infos2[0].length; l++) {
                rep[i][k3 + l] = infos2[indLigneInfo2][l];
            }
            if (indLigneInfo2 < infos2.length) {
                indLigneInfo2++;
            }
        }
        Afficher(rep);
        return rep;
    }

    public Object[][] Division(Object[][] R1, String[] f1, String[] c1, Object[][] R2, String[] f2, String[] c2) throws Exception{
        System.out.println("____Projection____");
        System.out.println("|"+c1[0]+"|");
        Object[][] R3= Projection(R1, f1, c1);
        System.out.println("|"+c2[0]+"|");
        Object[][] R4= Projection(R2, f2, c2);
        System.out.println("____Cartesien____");
        System.out.println("|"+c2[0]+"||"+c1[0]+"|");
        Object[][] R5=Cartesien(R4, R3);
        System.out.println("____Difference____");
        System.out.println("|"+c2[0]+"||"+c1[0]+"|");
        Object[][] R6=Difference(R5, f1, R2, f2);
        System.out.println("____Projection____");
        System.out.println("|"+c2[0]+"|");
        Object[][] R7=Projection(R6, f2, c2);
        System.out.println("____Difference____");
        System.out.println("|"+c2[0]+"|");
        Object[][] R8=Difference(R4, c2, R7, c2);
        return R8;
    }

    public Object[][] Jointure(Object[][] T1, String[] F1, Object[][] T2, String[] F2, String FCommune){
        int iT1=0;
        for (int i = 0; i < F1.length; i++) {
            if(F1[i].equalsIgnoreCase(FCommune)){
                iT1=i;
            }
        }
        int iT2=0;
        for (int i = 0; i < F2.length; i++) {
            if(F2[i].equalsIgnoreCase(FCommune)){
                iT2=i;
            }
        }
        Vector v1= new Vector();
        Vector v2= new Vector();
        for (int i = 0; i < T1.length; i++) {
            for (int j = 0; j < T2.length; j++) {
                if(T1[i][iT1].toString().equalsIgnoreCase(T2[j][iT2].toString())){
                    v1.add(i);
                    v2.add(j);
                }
            }
        }
        Object[][] rep= new Object[v1.size()][F1.length+F2.length-1];
        for (int i = 0; i < rep.length; i++) {
            for (int j = 0; j < F1.length; j++) {
                rep[i][j]=T1[(int) v1.get(i)][j];
            }
            for (int j = 0; j < F2.length; j++) {
                if(j != iT2){
                    for (int k = F1.length; k < rep[i].length; k++) {
                        rep[i][k]= T2[(int) v2.get(i)][j];
                    }
                }
            }
        }
        Afficher(rep);
        return rep;
    }   

    public Object[][] Union(Object[][] T1, String[] F1, Object[][] T2, String[] F2){
        Object[][] rep= new Object[T1.length+T2.length][F1.length];
        if(F1.length==F2.length){
            Vector v= new Vector();
            int manisa=0;
            for (int i = 0; i < F1.length; i++) {
                for (int j = 0; j < F2.length; j++) {
                    if(F1[i].equalsIgnoreCase(F2[j])){
                        manisa++;
                        v.add(j);
                    }
                }
            }
            if(manisa==F1.length){
                for (int i = 0; i < T1.length; i++) {
                    for (int j = 0; j < rep[i].length; j++) {
                        rep[i][j]= T1[i][j];
                    }
                }
                int t=0;
                for (int i = T1.length; i < rep.length; i++) {
                    for (int j = 0; j < rep[i].length; j++) {
                        rep[i][j]=T2[t][(int) v.get(j)];
                    }
                    t++;
                }
            }
            Afficher(rep);
        }
        return rep;
    }

    public void Intersection(Object[][] T1, String[] F1, Object[][] T2, String[] F2){
        if(F1.length==F2.length){
            Vector v= new Vector();
            for (int i = 0; i < F1.length; i++) {
                for (int j = 0; j < F2.length; j++) {
                    if(F1[i].equalsIgnoreCase(F2[j])){
                        v.add(j);
                    }
                }
            }
            Vector v2= new Vector();
            for (int i = 0; i <T1.length; i++) {
                int manisa=0;
                for (int j = 0; j < T2.length; j++) {
                    for (int k = 0; k < F2.length; k++) {
                        if(T1[i][k].equals(T2[j][(int)v.get(k)])){
                            manisa++;

                            if(manisa==F2.length){
                                v2.add(i);
                            }
                        }
                    }
                }
            }
            Object[][] rep= new Object[v2.size()][F1.length];
            for (int i = 0; i < rep.length; i++) {
                for (int j = 0; j < rep[i].length; j++) {
                    rep[i][j]= T1[(int)v2.get(i)][j];
                }
            }
            Afficher(rep);
        }
    }

    public void CreateTable(String NomTable, String NomColonnes){
        try {
            String[] transform= NomColonnes.split(" ");
            String content = "";
            for (int i = 0; i < transform.length; i++) {
                if(i == 0){
                    content=transform[i];
                }
                if(i > 0){
                    content= content + "%%" + transform[i];
                }
            }
            File file = new File(NomTable+".txt");
            if (!file.exists()) {
            file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            System.out.println("Nom des colonnes: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Insert(String NomTable, String NomColonnes){
        try {
            String[] transform= NomColonnes.split(" ");
            String content = transform[0];
            if(transform.length==1){
                content= content + ";";
            }
            else{
                for (int i = 1; i < transform.length; i++) {
                    if(i == transform.length-1){
                        content=content + "%%" + transform[i] +";";
                    }
                    else{
                        content=content + "%%" + transform[i];
                    }
                }
            }
            File file = new File(NomTable+"Values.txt");
           
            if (!file.exists()) {
            file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.newLine();
            bw.close();
            System.out.println("Nom des colonnes: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}