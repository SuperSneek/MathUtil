package frontend.commands.commandline;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * Parses user Input into formats used in the backend
 *
 * @author Jonas Lewandrowski
 * @version 1.1
 */
public class CommandLineParser {
    

    /**
     * Parse string to int
     *
     * @param input input string that should be a number
     * @return int value of provided string
     * @throws IOException string isnt a number
     */
    public static int parseInt(String input) throws IOException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IOException("");
        }
    }

}
