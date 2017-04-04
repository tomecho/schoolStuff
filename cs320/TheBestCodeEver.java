import java.awt.*;
import java.awt.event.*;

import static java.util.Collections.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.JTextComponent;

/**
 *
 * This implementation is awesome...or maybe not?
 */
public class TheBestCodeEver {

    // Store all collected numbers
    private static ArrayList<Integer> data = new ArrayList<Integer>();

    public static void main(String ... args) {
        /**
         * Create a simple GUI that includes:
         * - a text field and a button that allows the user to enter numbers.
         * - a button that allows the user to clear all entered numbers.
         * - a panel with labels and text fields for count, median, and mean.
         * - a text area that shows all numbers.
         */
        // Create the main frame of the application, and set size and position
        JFrame jfMain = new JFrame("Simple stats");
        jfMain.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jfMain.setSize(600,400);
        jfMain.setLocationRelativeTo(null);
       
        // Panel that shows stats about the numbers
        JPanel jpStats = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JTextField jtfCount = new JTextField(5);
        jtfCount.setEditable(false);
        final JTextField jtfMedian = new JTextField(5);
        jtfMedian.setEditable(false);
        final JTextField jtfMean = new JTextField(5);
        jtfMean.setEditable(false);
        jpStats.add(new JLabel("Numbers:"));
        jpStats.add(jtfCount);
        jpStats.add(new JLabel("Median:"));
        jpStats.add(jtfMedian);
        jpStats.add(new JLabel("Mean:"));
        jpStats.add(jtfMean);
        jfMain.getContentPane().add(jpStats, BorderLayout.CENTER);
 
        // TextArea that shows all the numbers
        final JTextArea jtaNumbers = new JTextArea(10,50);
        jtaNumbers.setEditable(false);
        jfMain.getContentPane().add(jtaNumbers, BorderLayout.SOUTH);


        // Panel with a text field/button to enter numbers and a button to reset the application
        JButton jbReset = new JButton("Reset");
        jbReset.addActionListener(new ActionListener() {
            // The interface ActionListener defines a call-back method actionPerformed,
            // which is invoked if the user interacts with the GUI component -- in this
            // case, if the user clicks the button.
            public void actionPerformed(ActionEvent e) {
                // Clear the ArrayList and all text fields
                data.clear();
                jtaNumbers.setText("");
                jtfCount.setText("");
                jtfMedian.setText("");
                jtfMean.setText("");
            }
        });
        final JTextField jtfNumber = new JTextField(5);
        JButton jbAdd = new JButton("Add number");
        jbAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Parse input and add number to the ArrayList
                //
                // What if the user doesn't enter a number...?
                // Input validation and error handling is important -- you can
                // ignore it for now, though.
                Integer num = Integer.parseInt(jtfNumber.getText());
                data.add(num);
                jtaNumbers.append(num + ",");

                // Compute and set the count
                int count = data.size();
                jtfCount.setText("" + count);

                // Compute and set the mean
                double sum= 0;
                for (Integer value : data) {
                    sum += value;
                }
                double mean = sum / count;
                jtfMean.setText("" + mean);

                // Compute and set the median
                sort(data);
                double median;
                if (count%2 == 0) {
                    median = (data.get(count >> 1) + data.get((count >> 1) - 1)) / 2.;
                } else {
                    median = data.get(count >> 1);
                }
                jtfMedian.setText("" + median);
            }
        });
        JPanel jpInput = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jpInput.add(jtfNumber);
        jpInput.add(jbAdd);
        jpInput.add(jbReset);
        jfMain.getContentPane().add(jpInput, BorderLayout.NORTH);
 
        // Show the frame
        jfMain.setVisible(true);
    }
}
