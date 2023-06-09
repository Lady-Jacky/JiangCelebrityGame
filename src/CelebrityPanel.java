import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * CelebrityPanel for the game Celebrity
 *
 * @author cody.henrichsen
 * @version 2.9 18/09/2018 Adjusted the listener functionality.
 */
public class CelebrityPanel extends JPanel implements ActionListener {

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
    private int score = 0;

    /**
     * Builds the CelebrityPanel and initializes all data members.
     *
     * @param controllerRef Reference to the Game passed when the CelebrityPanel is
     *                      instantiated in the Frame.
     */
    public CelebrityPanel(CelebrityGame controllerRef) {
        super();
        controller = controllerRef;
        panelLayout = new SpringLayout();
        guessLabel = new JLabel("Guess:");
        staticTimerLabel = new JLabel("Time remaining: ");
        dynamicTimerLabel = new JLabel("30");
        guessButton = new JButton("Submit guess");
        resetButton = new JButton("Start again");
        clueArea = new JTextArea("", 30, 20);
        cluePane = new JScrollPane(clueArea);
        guessField = new JTextField("Enter guess here", 30);
        success = "\nYou guessed correctly!!! \nNext Celebrity clue is: ";
        tryAgain = "\nYou have chosen poorly, try again!\nThe clue is: ";
        seconds = 30;
        countdownTimer = new Timer(1000, null);

        setupPanel();
        setupLayout();
        setupListeners();
    }

    /**
     * Helper method to add all components to the panel and adjust GUI settings
     * including scroll bars, and line wrap.
     */
    private void setupPanel() {
        setLayout(panelLayout);
        add(guessLabel);
        add(cluePane);
        add(guessField);
        add(guessButton);
        add(resetButton);
        add(dynamicTimerLabel);
        add(staticTimerLabel);

        //Changes the font to be larger than default
        staticTimerLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        dynamicTimerLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

        // These lines allow vertical scrolling but not horizontal.
        cluePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cluePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // These lines allow word and line wrapping for the clue area.
        clueArea.setWrapStyleWord(true);
        clueArea.setLineWrap(true);

        // The clue area is set to not be editable by the user :D
        clueArea.setEditable(false);
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
        guessButton.addActionListener(this);
        countdownTimer.addActionListener(this);
        resetButton.addActionListener(this);
    }

    /**
     * Helper method for when the ActionListener attached to the timer fires.
     * Sets the text of the label to match the remaining time and a message at
     * the end.
     */
    private void timerFires() {
        if (seconds > 0 && score != 2) {
            seconds -= 1;
            dynamicTimerLabel.setText("" + seconds);
        } else if(seconds >= 0 && score == 2) {
            staticTimerLabel.setText("You Win!");
            dynamicTimerLabel.setVisible(false);
            guessField.setEnabled(false);
            guessButton.setEnabled(false);
            countdownTimer.stop();
        } else {
            staticTimerLabel.setText("Times up! YOU LOSE");
            dynamicTimerLabel.setVisible(false);
            guessField.setEnabled(false);
            guessButton.setEnabled(false);
            countdownTimer.stop();
        }
    }

    /**
     * Method to add a clue to the screen from the game instance
     *
     * @param clue The clue to add to the screen.
     */
    public void addClue(String clue) {
        countdownTimer.start();
        clueArea.setText("The clue is: " + clue);
    }

    /**
     * Method to allow both button and enter press in the guessField
     * to provide the same functionality.
     */
    private void updateScreen() {
        String guess = guessField.getText();
        clueArea.append("\nYou guessed: " + guess);
        if (controller.processGuess(guess)) {
            clueArea.setBackground(Color.cyan);
            clueArea.append("\n" + success + controller.sendClue());
            score++;
        } else {
            clueArea.setBackground(Color.white);
            clueArea.append(tryAgain + controller.sendClue());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton clicked = (JButton) source;
            String button = clicked.getText();
            if (button.equals("Submit guess")) {
                updateScreen();
            } else if(button.equals("Start again")) {
                controller.getGameWindow().dispose();
                Rectangle coords = controller.getGameWindow().getBounds();
                controller = new CelebrityGame();
                controller.getGameWindow().setBounds(coords);
            }
        } else if (source instanceof Timer) {
            timerFires();
        }
    }

}
