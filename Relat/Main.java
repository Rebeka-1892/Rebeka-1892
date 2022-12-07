package main;

import Fonction.*;
import mot.*;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{

        Vocabulaire vocabulaire = new Vocabulaire();
        // CreerTable = "Create table TableName : ColumnName1,ColumnName2,...";
        // Insert = "Insert into TableName values Value1,Value2,...";
        // Projection = "Show */ColumnName1,ColumnName2,... of TableName";
        // Selection = "Show */ColumnName1,ColumnName2,... of TableName where Condition1=Value1 Condition2=Value2 ...";
        // Difference = "Show difference between TableName1 and TableName2";
        // Cartesien = "Show cartesian-product of TableName1 and TableName2";
        // Jointure = "Join TableName1 and TableName2 by CommonField";
        // Union = "Show union between TableName1 and TableName2";
        // Intersection = "Show intersection between TableName1 and TableName2";

        String sql= "Show union between Animal and Personne";
        Grammaire grammaire = new Grammaire();
        grammaire.ExecuteSql(sql);
    }
}