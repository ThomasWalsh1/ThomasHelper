import javax.swing.*;
import org.jsoup.Jsoup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class used to describe a TaskPiece object.
 * Uses buttons to manipulate the current TaskPiece object.
 * 
 * @author Thomas Walsh
 */
public class TaskPiece extends JPanel implements ActionListener{

    public JButton removeButton;
    public JCheckBox checkBox;
    public JTextPane taskField;

    private JPanel parent;
    private String newText;

    /**
     * First constructor for TaskPiece:
     * Used for creating new TaskPiece when not loading
     * @param parent The parent panel where the TaskPiece is displayed
     */
    public TaskPiece(JPanel parent)
    {
        taskField = new JTextPane();
        generatePiece(parent);
    }

    /**
     * Second constructor for TaskPiece:
     * Used for regenerating TaskPiece object when loading
     * @param parent The parent panel where the TaskPiece is displayed
     * @param taskText The text to be displayed in the TaskPiece object
     */
    public TaskPiece(JPanel parent, String taskText)
    {
        taskField = new JTextPane();
        newText = Jsoup.parse(taskText).text();
        generatePiece(parent);
    }

    /**
     * Helper method to generate the GUI of the TaskPiece object
     * @param parent The parent panel where the TaskPiece is displayed
     */
    private void generatePiece(JPanel parent)
    {
        this.parent = parent;

        //taskField
        taskField.setPreferredSize(new Dimension(700, 50));
        taskField.setContentType("text/html");
        taskField.setBorder(BorderFactory.createLineBorder(Color.black, 3)); 
        taskField.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

        //checkbox
        checkBox = new JCheckBox("X");
        checkBox.setPreferredSize(new Dimension(60, 60));

        //delete button
        removeButton = new JButton("X");
        removeButton.setPreferredSize(new Dimension(60, 60));

        //Used for loading
        if(newText != null)
        {
            taskField.setText(newText);
        }

        //add to TaskPiece object
        add(taskField);
        add(checkBox);
        add(removeButton);

        //add actionlisteners
        checkBox.addActionListener(this);
        removeButton.addActionListener(this);
    }

    /**
     * Helper method to return the taskField
     * @return returns the taskField associated with this TaskPiece object
     */
    public JTextPane getTaskField()
    {
        return taskField;
    }
    /**
     * Helper methodto return the text associated with this TaskPiece object
     * @return Text assiciated with this object as String
     */
    public String getText()
    {
        return taskField.getText();
    }

    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == removeButton)
        {
            parent.remove(this);
            parent.repaint();
            parent.revalidate();
        }
        if(checkBox.isSelected()){
            // replaces all html tags to empty string to grab the main text
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            // add strikethrough text
            taskField.setText("<html><s>"+ taskText + "</s></html>");
        }else if(!checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText(taskText);
        }

    }

}
