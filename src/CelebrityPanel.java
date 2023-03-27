import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 * CelebrityPanel for the game Celebrity
 * 
 * @author cody.henrichsen
 * @version 2.9 18/09/2018 Adjusted the listener functionality.
 */
public class CelebrityPanel extends JPanel {
  
  /**
   * The button pressed when making a guess.
   */
  private JButton guessButton;
  
  /**
   * The button pressed to restart the game
   */
  private JButton resetButton;
  
  /**
   * The label used to identify what to type in the field
   */
  private JLabel guessLabel;
  
  /**
   * The label to hold the static text for the timer
   */
  private JLabel staticTimerLabel;
  
  /**
   * The label to hold the dynamic text of the timer.
   */
  private JLabel dynamicTimerLabel;
  
  /**
   * Timer for display
   */
  private Timer countdownTimer;
  
  /**
   * Listener for the countdownTimer
   */
  private ActionListener timerListener;
  
  /**
   * Holds the user and program input text area and allows the text to scroll.
   */
  private JScrollPane cluePane;
  
  /**
   * The text area for user and program information
   */
  private JTextArea clueArea;
  
  /**
   * The user interaction field.
   */
  private JTextField guessField;
  
  /**
   * Layout manager for the panel. Uses constraints between components to
   * align or spring from edges.
   */
  private SpringLayout panelLayout;
  
  /**
   * The String used when a user correctly guesses the Celebrity
   */
  private String success;
  
  /**
   * The String used when a user has not guessed correctly.
   */
  private String tryAgain;
  
  /**
   * The current value of the time in seconds.
   */
  private int seconds;
  
  /**
   * Reference to the game instance.
   */
  private CelebrityGame controller;
  
  /**
   * Builds the CelebrityPanel and initializes all data members.
   * 
   * @param controllerRef
   *            Reference to the Game passed when the CelebrityPanel is
   *            instantiated in the Frame.
   */
  public CelebrityPanel(CelebrityGame controllerRef) {
    super();
  }
  
  /**
   * Helper method to add all components to the panel and adjust GUI settings
   * including scroll bars, and line wrap.
   */
  private void setupPanel() {
     
  }
  
  /**
   * Using a helper method to hold all the constraints for the GUI components
   * in the panel
   */
  private void setupLayout() {
    panelLayout.putConstraint(SpringLayout.NORTH, cluePane, 15, SpringLayout.NORTH, this);
    panelLayout.putConstraint(SpringLayout.WEST, cluePane, 15, SpringLayout.WEST, this);
    panelLayout.putConstraint(SpringLayout.SOUTH, cluePane, -100, SpringLayout.SOUTH, this);
    panelLayout.putConstraint(SpringLayout.EAST, cluePane, -15, SpringLayout.EAST, this);
    panelLayout.putConstraint(SpringLayout.NORTH, guessButton, 10, SpringLayout.SOUTH, guessLabel);
    panelLayout.putConstraint(SpringLayout.SOUTH, guessButton, -15, SpringLayout.SOUTH, this);
    panelLayout.putConstraint(SpringLayout.NORTH, resetButton, 0, SpringLayout.NORTH, guessButton);
    panelLayout.putConstraint(SpringLayout.EAST, guessButton, 0, SpringLayout.EAST, cluePane);
    panelLayout.putConstraint(SpringLayout.WEST, resetButton, 0, SpringLayout.WEST, cluePane);
    panelLayout.putConstraint(SpringLayout.NORTH, guessLabel, 10, SpringLayout.SOUTH, cluePane);
    panelLayout.putConstraint(SpringLayout.WEST, guessLabel, 0, SpringLayout.WEST, cluePane);
    panelLayout.putConstraint(SpringLayout.SOUTH, resetButton, 0, SpringLayout.SOUTH, guessButton);
    panelLayout.putConstraint(SpringLayout.NORTH, guessField, 0, SpringLayout.NORTH, guessLabel);
    panelLayout.putConstraint(SpringLayout.WEST, guessField, 5, SpringLayout.EAST, guessLabel);
    panelLayout.putConstraint(SpringLayout.EAST, guessField, 0, SpringLayout.EAST, cluePane);
    panelLayout.putConstraint(SpringLayout.NORTH, staticTimerLabel, 15, SpringLayout.NORTH, resetButton);
    panelLayout.putConstraint(SpringLayout.WEST, staticTimerLabel, 10, SpringLayout.EAST, resetButton);
    panelLayout.putConstraint(SpringLayout.NORTH, dynamicTimerLabel, 0, SpringLayout.NORTH, staticTimerLabel);
    panelLayout.putConstraint(SpringLayout.WEST, dynamicTimerLabel, 5, SpringLayout.EAST, staticTimerLabel);
  }
  
  /*
   * Attaches listeners to the GUI components of the program
   */
  private void setupListeners() {

  }
  
  /**
   * Helper method for when the ActionListener attached to the timer fires.
   * Sets the text of the label to match the remaining time and a message at
   * the end.
   */
  private void timerFires() {

  }
  
  /**
   * Method to add a clue to the screen from the game instance
   * 
   * @param clue
   *            The clue to add to the screen.
   */
  public void addClue(String clue) {

  }
  
  /**
   * Method to allow both button and enter press in the guessField
   * to provide the same functionality.
   */
  private void updateScreen() {

  }
}
