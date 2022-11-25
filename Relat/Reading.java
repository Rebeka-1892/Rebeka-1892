package mot;
import java.io.*;
public class Reading
{
    public Reading(){

    }

  public Object[][] ReadFichier(File file){
    try
    {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String ligne;
        String mamaky="";
        while((ligne=br.readLine()) != null){
            mamaky=mamaky+ligne;
        }
        fr.close();
        String[] valobj=mamaky.split(";");
        String[] col=valobj[0].split("%%");
        Object[][] rep=new Object[valobj.length][col.length];
        for(int i=0; i<rep.length; i++){
            String[] column=valobj[i].split("%%");
            for(int j=0; j<rep[i].length; j++){
                rep[i][j]=column[j];
            }
        }
        return rep;
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  public String[] ColumName(File file){
    try {
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String ligne=br.readLine();
      String[] col=ligne.split("%%");
      return col;
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }
}