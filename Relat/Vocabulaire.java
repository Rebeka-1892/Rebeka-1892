package mot;

import java.lang.reflect.*;

public class Vocabulaire{
    String CreerTable = "Create table TableName : ColumnName1,ColumnName2,...";
    String Insert = "Insert into TableName values Value1,Value2,...";
    String Projection = "Show */ColumnName1,ColumnName2,... of TableName";
    String Selection = "Show */ColumnName1,ColumnName2,... of TableName where Condition1=Value1 Condition2=Value2 ...";
    String Difference = "Show difference between TableName1 and TableName2";
    String Cartesien = "Show cartesian-product of TableName1 and TableName2";
    String Jointure = "Join TableName1 and TableName2 by CommonField";
    String Union = "Show union between TableName1 and TableName2";
    String Intersection = "Show intersection between TableName1 and TableName2";

    public Vocabulaire() throws Exception{
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            System.out.println(f.getName() + " : " + f.get(this));
        }
        System.out.println();
    }
}