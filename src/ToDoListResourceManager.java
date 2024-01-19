import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class to manage the loading and saving of ToDoList data
 * 
 * @author Thomas Walsh
 */
public class ToDoListResourceManager{
    
    /**
     * Saves the data of ToDoList
     * @param data SaveData object associated with ToDoList
     * @param fileName String used to name the ToDoList
     * @throws Exception
     */
    public static void save(Serializable data, String fileName) throws Exception
    {
        File dir = new File("src//saves");
        if(!dir.exists())
        {
            dir.mkdir();
        }

        FileOutputStream fileOut = new FileOutputStream(dir+"//"+fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data);
        out.close();
        fileOut.close();
        System.out.println("Saved successfully!");
    }
    /**
     * Loads the SaveData by the name of the file
     * @param fileName The name of the file
     * @return SaveData object for the ToDoList associated with fileName
     * @throws Exception
     */
    public static SaveData load(String fileName) throws Exception
    {
        FileInputStream fileIn = new FileInputStream("src//saves//"+fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        SaveData data = (SaveData) in.readObject();
        fileIn.close();
        in.close();
        return data;
    }
}
