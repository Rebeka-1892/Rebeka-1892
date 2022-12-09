package mot;
import Fonction.Fonction;

import java.io.*;
import java.util.Vector;

public class Grammaire {
    Fonction fonction= new Fonction();

    public Object[][] ExecuteSql(String sql) throws Exception{
        String[] mots = sql.split(" ");
        String erreur = "Oups verifier votre requete";

        Reading read= new Reading();
        String[] names= read.LireNomFichhiers();
        if(mots[0].equalsIgnoreCase("show")){
            if(mots[1].equalsIgnoreCase("difference") && mots[2].equalsIgnoreCase("between")){
                for (int i = 0; i < names.length; i++) {
                    if(mots[3].equalsIgnoreCase(names[i]) && mots[4].equalsIgnoreCase("and")){
                        for (int j = 0; j < names.length; j++) {
                            if(mots[5].equalsIgnoreCase(names[j])){
                                File FT1 = new File("Database\\"+ mots[3] +".txt");
                                Object[][] T1=read.ReadFichier(FT1);
                                File FT1Col = new File("Database\\"+ mots[3] +"_Field.txt");
                                String[] T1Col=read.ColumName(FT1Col);
                                File FT2 = new File("Database\\"+ mots[5] +".txt");
                                Object[][] T2=read.ReadFichier(FT2);
                                File FT2Col = new File("Database\\"+ mots[5] +"_Field.txt");
                                String[] T2Col=read.ColumName(FT2Col);
                                return fonction.Difference(T1, T1Col, T2, T2Col);
                            }
                        }
                    }
                }
            }
            else if(mots[1].equalsIgnoreCase("cartesian-product") && mots[2].equalsIgnoreCase("of")){
                for (int i = 0; i < names.length; i++) {
                    if(mots[3].equalsIgnoreCase(names[i]) && mots[4].equalsIgnoreCase("and")){
                        for (int j = 0; j < names.length; j++) {
                            if(mots[5].equalsIgnoreCase(names[j])){
                                File FT1 = new File("Database\\"+ mots[3] +".txt");
                                Object[][] T1=read.ReadFichier(FT1);
                                File FT2 = new File("Database\\"+ mots[5] +".txt");
                                Object[][] T2=read.ReadFichier(FT2);
                                return fonction.Cartesien(T1, T2);
                            }
                        }
                    }
                }
            }
            else if(mots[1].equalsIgnoreCase("union") && mots[2].equalsIgnoreCase("between")){
                for (int i = 0; i < names.length; i++) {
                    if(mots[3].equalsIgnoreCase(names[i]) && mots[4].equalsIgnoreCase("and")){
                        for (int j = 0; j < names.length; j++) {
                            if(mots[5].equalsIgnoreCase(names[j])){
                                File FT1 = new File("Database\\"+ mots[3] +".txt");
                                Object[][] T1=read.ReadFichier(FT1);
                                File FT1Col = new File("Database\\"+ mots[3] +"_Field.txt");
                                String[] T1Col=read.ColumName(FT1Col);
                                File FT2 = new File("Database\\"+ mots[5] +".txt");
                                Object[][] T2=read.ReadFichier(FT2);
                                File FT2Col = new File("Database\\"+ mots[5] +"_Field.txt");
                                String[] T2Col=read.ColumName(FT2Col);
                                return fonction.Union(T1, T1Col, T2, T2Col);
                            }
                        }
                    }
                }
            }
            else if(mots[1].equalsIgnoreCase("intersection") && mots[2].equalsIgnoreCase("between")){
                for (int i = 0; i < names.length; i++) {
                    if(mots[3].equalsIgnoreCase(names[i]) && mots[4].equalsIgnoreCase("and")){
                        for (int j = 0; j < names.length; j++) {
                            if(mots[5].equalsIgnoreCase(names[j])){
                                File FT1 = new File("Database\\"+ mots[3] +".txt");
                                Object[][] T1=read.ReadFichier(FT1);
                                File FT1Col = new File("Database\\"+ mots[3] +"_Field.txt");
                                String[] T1Col=read.ColumName(FT1Col);
                                File FT2 = new File("Database\\"+ mots[5] +".txt");
                                Object[][] T2=read.ReadFichier(FT2);
                                File FT2Col = new File("Database\\"+ mots[5] +"_Field.txt");
                                String[] T2Col=read.ColumName(FT2Col);
                                return fonction.Intersection(T1, T1Col, T2, T2Col);
                            }
                        }
                    }
                }
            }
            else{                                   //division
                for (int i = 0; i < names.length; i++) {
                    if(mots[1].equalsIgnoreCase(names[i]) && mots[2].equalsIgnoreCase("expect")){
                        for (int j = 0; j < names.length; j++) {
                            if(mots[3].equalsIgnoreCase(names[j]) && mots[4].equalsIgnoreCase("by")){
                                File FT1 = new File("Database\\"+ mots[1] +".txt");
                                Object[][] T1=read.ReadFichier(FT1);
                                File FT1Col = new File("Database\\"+ mots[1] +"_Field.txt");
                                String[] T1Col=read.ColumName(FT1Col);
                                File FT2 = new File("Database\\"+ mots[3] +".txt");
                                Object[][] T2=read.ReadFichier(FT2);
                                File FT2Col = new File("Database\\"+ mots[3] +"_Field.txt");
                                String[] T2Col=read.ColumName(FT2Col);
                                return fonction.Division(T1, T1Col, T1Col, T2, T2Col, T2Col);
                            }
                        }
                    }
                }
                int indice = 1;
                Vector vect = new Vector();
                while (mots[indice].equalsIgnoreCase("of") == false) {
                    vect.add(mots[indice]);
                    indice++;
                }
                String[] list= new String[vect.size()];
                for (int j = 0; j < list.length; j++) {
                    list[j] = (String) vect.get(j);
                }
                for (int i = 0; i < names.length; i++) {
                    if(mots[indice].equalsIgnoreCase("of") && mots[indice+1].equalsIgnoreCase(names[i])){
                        File FT1 = new File("Database\\"+ mots[indice+1] +".txt");
                        Object[][] T1=read.ReadFichier(FT1);
                        File FT1Col = new File("Database\\"+ mots[indice+1] +"_Field.txt");
                        String[] T1Col=read.ColumName(FT1Col);
                        if(mots.length==indice+2){
                            return fonction.Projection(T1, T1Col, list);
                        }
                        else if(mots.length!=indice+2 && mots[indice+2].equalsIgnoreCase("where")){
                            Vector cond= new Vector();
                            Vector valcond= new Vector();
                            for (int j = indice+3 ; j < mots.length; j++) {
                                String[] getCondition= mots[j].split("=");
                                cond.add(getCondition[0]);
                                valcond.add(getCondition[1]);
                            }
                            String[] condStrings= new String[cond.size()];
                            String[] valcondStrings= new String[valcond.size()];
                            for (int j = 0; j < valcondStrings.length; j++) {
                                condStrings[j]= (String) cond.get(j);
                                valcondStrings[j]= (String) valcond.get(j);
                            }
                            return fonction.Selection(T1, T1Col, list, condStrings, valcondStrings);
                        }
                    }
                }
            }
        }
        else if(mots[0].equalsIgnoreCase("join")){
            for (int i = 0; i < names.length; i++) {
                if(mots[1].equalsIgnoreCase(names[i]) && mots[2].equalsIgnoreCase("and")){
                    for (int j = 0; j < names.length; j++) {
                        if(mots[3].equalsIgnoreCase(names[j]) && mots[4].equalsIgnoreCase("by")){
                            File FT1 = new File("Database\\"+ mots[1] +".txt");
                            Object[][] T1=read.ReadFichier(FT1);
                            File FT1Col = new File("Database\\"+ mots[1] +"_Field.txt");
                            String[] T1Col=read.ColumName(FT1Col);
                            File FT2 = new File("Database\\"+ mots[3] +".txt");
                            Object[][] T2=read.ReadFichier(FT2);
                            File FT2Col = new File("Database\\"+ mots[3] +"_Field.txt");
                            String[] T2Col=read.ColumName(FT2Col);

                            for (int k = 0; k < T1Col.length; k++) {
                                if(mots[5].equalsIgnoreCase(T1Col[k])){
                                    return fonction.Jointure(T1, T1Col, T2, T2Col, T1Col[k]);
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(mots[0].equalsIgnoreCase("create") && mots[1].equalsIgnoreCase("table") && mots[3].equalsIgnoreCase(":")){
            return fonction.CreateTable(mots[2], mots[4]);
        }
        else if(mots[0].equalsIgnoreCase("insert") && mots[1].equalsIgnoreCase("into") && mots[3].equalsIgnoreCase("values")){
            return fonction.Insert(mots[2], mots[4]);
        }
        else{
            System.out.println(erreur);
            return null;
        }
        return null;
    }
}
