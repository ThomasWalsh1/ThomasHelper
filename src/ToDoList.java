import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import com.formdev.flatlaf.ui.FlatUIUtils;

/**
 * Class that creates the ToDoList application.
 * ToDoList extends JFrame and implements ActionListener for interactive elements.
 * 
 * @author Thomas Walsh
 */
public class ToDoList extends JFrame implements ActionListener{

    //components
    public JPanel taskComponentPanel = new JPanel();
    public JPanel taskPanel;
    public JLabel searchLabel;
    public JTextField searchTextField;
    private ArrayList<TaskPiece> tasks = new ArrayList<TaskPiece>();
    private JTextField date;

    /**
     * Constructor for ToDoList JFrame
     */
    public ToDoList()
    {
        this.setDefaultCloseOperation(3);
        this.setPreferredSize(Common.GUI_SIZE);
        this.setTitle("To do list");
        this.pack();
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(null);
        createGUI();

    }

    /**
     * Helper method that creates the GUI
     */
    public void createGUI()
    {
        //creates the banner at the top of the frame
        JLabel banner = new JLabel("To Do List", SwingConstants.CENTER);
        banner.putClientProperty("Flatlaf.style", "font: $h1.font");
        banner.setFont( FlatUIUtils.nonUIResource( UIManager.getFont( "h1.font" ) ) );
        banner.setBorder(BorderFactory.createBevelBorder(1));
        banner.setBounds(
            0, 0, 
            Common.BANNER_SIZE.width, Common.BANNER_SIZE.height
            );

        
        //tasks on the screen
        JPanel taskPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        //scrolling implementation
        JScrollPane scrollPane = new JScrollPane(taskPanel);

        scrollPane.setBounds(0, 70, Common.TASKPANEL_SIZE.width, Common.TASKPANEL_SIZE.height);
        scrollPane.setMaximumSize(Common.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //only uses scroll bar when needed
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(Color.RED);

        JScrollBar vScrollBar = scrollPane.getVerticalScrollBar();
        vScrollBar.setUnitIncrement(20);
        
        //add task button
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(10, 580, Common.ADDTASK_BUTTON_SIZE.width, Common.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);

        //save button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds((Common.GUI_SIZE.width - (Common.ADDTASK_BUTTON_SIZE.width * 2) - 30), 580,
            Common.ADDTASK_BUTTON_SIZE.width, Common.ADDTASK_BUTTON_SIZE.height);
        saveButton.addActionListener(this);

        //load button
        JButton loadButton = new JButton("Load");
        loadButton.setBounds((Common.GUI_SIZE.width - (Common.ADDTASK_BUTTON_SIZE.width) - 20), 580,
            Common.ADDTASK_BUTTON_SIZE.width, Common.ADDTASK_BUTTON_SIZE.height);
        loadButton.addActionListener(this);

        //searchField panel
        JPanel searchField = new JPanel();
        searchField.setBounds((70 + Common.ADDTASK_BUTTON_SIZE.width), 580, 250, Common.ADDTASK_BUTTON_SIZE.height);
        searchField.setBackground(Color.DARK_GRAY);
        searchField.setLayout(new BorderLayout());
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));

        //small label to explain use of the searchTextField
        searchLabel = new JLabel("Name file or search with Load button");
        searchLabel.setPreferredSize(new Dimension(60, 30));
        searchLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        //configures searchTextField
        searchTextField = new JTextField("Search(M_D_YY)");
        searchTextField.setPreferredSize(new Dimension(150, 60));

        //adds elements to searchField
        searchField.add(searchLabel, BorderLayout.NORTH);
        searchField.add(searchTextField, BorderLayout.CENTER);
        

        //adding to contentpane
        this.getContentPane().add(banner);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
        this.getContentPane().add(saveButton);
        this.getContentPane().add(loadButton);
        this.getContentPane().add(searchField);
    }

    /**
     * Method overridden from ActionListener interface.
     * @param e ActionEvent object 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //identifies the button used for the ActionEvent
        String cmd = e.getActionCommand();
        //save button
        if(cmd.equals("Save"))
        {
            System.out.println("Now Saving...");
            ArrayList<TaskPiece> tasks = new ArrayList<TaskPiece>();

            Component[] taskItems = taskComponentPanel.getComponents(); //gets all components from the panel
            for(Component item: taskItems)
            {
                System.out.println(item.toString());
                tasks.add((TaskPiece) item);
            }
            //now save
            SaveData data = new SaveData(tasks, searchTextField.getText());//saves them in data

            try {
                ToDoListResourceManager.save(data, searchTextField.getText());
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        //load button
        if(cmd.equals("Load"))
        {
            System.out.println("Now Loading...");

            //clear all components
            taskComponentPanel.removeAll();

            //load
            SaveData newData = null;
            try
            {
                newData = ToDoListResourceManager.load(searchTextField.getText());
            }
            catch(Exception f)
            {
                f.printStackTrace();
                searchTextField.setText("Search Failed");
            }
            
            //produce newTask items
            ArrayList<TaskPiece> newTasks = newData.getTasks();

            //takes all the TaskPiece objects from the old data and adds them to the taskComponentPanel
            for(TaskPiece task: newTasks)
            {
                //makes a new Object with new parent panel
                taskComponentPanel.add(new TaskPiece(taskComponentPanel, task.getText()));
            }
            repaint();
            revalidate();
        }
        //add task button
        if(cmd.equals("Add Task"))
        {
            TaskPiece taskPiece = new TaskPiece(taskComponentPanel);

            taskComponentPanel.add(taskPiece);
            taskPiece.getTaskField().requestFocus();
            repaint(); //cleans the old stuff
            revalidate(); //tells layout manager to fix the layout
            System.out.println("Task Added");
        }
    }
}
