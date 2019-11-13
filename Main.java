
package Ejemplo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;



public class Main {
 public static void main( String [] args) throws Exception {

        JSONObject object = new JSONObject(read());
        String name = (String) object.get("name");
        System.out.println("Name: " + name);

        JSONArray array = object.getJSONArray("calificaciones");

        JSONWriter jsonWriter = new JSONStringer();
        jsonWriter.object();

        for (Object materia: array) {
            JSONObject jsonOb = (JSONObject) materia;
            System.out.println("Materia: " + jsonOb.get("materia") + " Calificacion: "+ jsonOb.get("calificacion"));
            JSONObject mat = new JSONObject();
            mat.put((String) jsonOb.get("materia"), jsonOb.get("calificacion"));

            jsonWriter.key((String) jsonOb.get("materia"));
            jsonWriter.value(jsonOb.get("calificacion"));
        }
            jsonWriter.endObject();
            writeFile(jsonWriter);
    }

    private static String read() throws Exception {
        BufferedReader reader = null;
        FileReader fileReader = null;
        String text = "", completeText= "";
        
        try {
            File file = new File( "C:\\Users\\Nancy\\Documents\\ejemplo-json.json");
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
           
            while((text = reader.readLine())!=null) {
                 completeText = completeText + text;
            }
           
        } finally {
            if (reader != null)
                reader.close();
        }
        return completeText;
    }

    private static boolean writeFile(JSONWriter writer)
    {
        try {
            FileWriter file = new FileWriter("C:\\Users\\Nancy\\Documents\\prueba.json");
            file.write(writer.toString());
            file.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
    

