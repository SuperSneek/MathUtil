package frontend;


/**
 * Generic Interface for the implementation of User Interfaces
 *
 * @author Jonas Lewandrowski
 * @version 1.0
 */
public interface UserInterface {

    /**
     * Shows the user a message
     *
     * @param message the message to display
     */
    void showMessage(Object message);

    /**
     * quits the program
     */
    void exit();

}
