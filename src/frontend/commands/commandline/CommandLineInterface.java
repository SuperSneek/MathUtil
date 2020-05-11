package frontend.commands.commandline;

import edu.kit.informatik.Terminal;
import frontend.UserInterface;

import java.io.IOException;

/**
 * This is a Command Line Interface which reads input from the Terminal
 *
 * @author Jonas Lewandrowski
 * @version 1.0
 */
public class CommandLineInterface implements UserInterface {

    private static boolean running = true;

    private Command[] commands;


    /**
     * creates a new Command Line Interface and starts it
     */
    public CommandLineInterface() {
        run(CommandLineCommands.values());
    }

    /**
     * This method loops and recognizes any new input the use submits through the Terminal
     * It then formats the input into a command Name and a Collection of parameters
     *
     * @param args configurations of this program. this program takes no configurations
     */
    public static void main(String[] args) {

        new CommandLineInterface();

    }

    /**
     * @param commands all commands that can be run
     */
    private void run(Command[] commands) {

        this.commands = commands;

        while (running) {

            String input = Terminal.readLine();

            try {
                boolean ranSuccessFull = false;
                for (Command command : commands) {
                    if (command.parseInput(input)) {
                        ranSuccessFull = true;
                        command.execute(this);
                        break;
                    }
                }
                if (!ranSuccessFull) {
                    throw new IOException("This command doesnt exist");
                }
            } catch (IOException ex) {
                Terminal.printError(ex.getMessage());
            }
        }
    }

    /** Prints some other object to the console
     * @param obj object to convert to string and print
     */
    @Override
    public void showMessage(Object obj) {
        Terminal.printLine(obj.toString().toLowerCase());
    }

    /**
     * Quits the program
     */
    public void exit() {
        running = false;
    }

}
