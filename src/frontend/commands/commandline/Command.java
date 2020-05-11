package frontend.commands.commandline;

import frontend.UserInterface;

import java.io.IOException;

/**
 * This is the interface for a command a User can call and is processed by the UserInterface
 * @author Jonas Lewandrowski
 * @version 1.2
 */
public interface Command {
    /**
     * Checks if an input matches this command
     * and, if true, extracts its arguments for executing
     *
     * @param input is the input to match
     * @return whether the input matches this command and is ready to be executed
     * @throws IOException if the args of the input were invalid
     */
    boolean parseInput(String input)
            throws IOException, IOException;

    /**
     * Executes this command with the given frontend and backend
     *
     * @param frontend is the frontend to run on
     * @throws IOException if the args of the input were invalid
     */
    void execute(UserInterface frontend)
            throws IOException;

    /**Checks for a match with the regex pattern of the command
     * @param input String to check
     * @return Wether the string matches a pattern of any command
     * @throws IOException the Input matches only a command name
     */
    boolean matches(String input) throws IOException, IOException;

    /** Checks if input matches only the command name but not the parameters
     * @param input input bro
     * @return if input matches only the command name but not the parameters
     */
    boolean commandNameMatches(String input);
}
