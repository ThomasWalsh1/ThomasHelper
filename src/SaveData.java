import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class used as blueprint for SaveData object.
 * 
 * @author Thomas Walsh
 */
public class SaveData implements Serializable{
    //sets the serialVersionUID to a constant
    public static final long serialVersionUID = 1L;

    //array of tasks
    public ArrayList<TaskPiece> tasks = new ArrayList<TaskPiece>();
    //date to be used as the fileName
    public String date;

    public SaveData(ArrayList<TaskPiece> tasks, String date)
    {
        this.tasks = tasks;
        this.date = date;
    }

    public ArrayList<TaskPiece> getTasks()
    {
        return tasks;
    }

    

}
