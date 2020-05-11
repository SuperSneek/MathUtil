package frontend.commands.commandline;

import frontend.UserInterface;

import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Math.EuclidAlgoRythm;

/**
 * These are the Commands as used by the Command Line Interface
 *
 * @author Jonas Lewandrowski
 * @version 1.4
 */
public enum CommandLineCommands implements Command {

    CONVERT_FROM_BASE_TO_BASE("(convert) (.+) from (.+) to (.+)") {
        @Override
        public void execute(UserInterface frontend) throws IOException {
            String result = EuclidAlgoRythm.convertNumberToBase(arguments[0].toUpperCase(),
                    CommandLineParser.parseInt(arguments[1]),
                    CommandLineParser.parseInt(arguments[2]));
            frontend.showMessage(result);
        }
    },
    CONVERT_SUITE("(convertThis) (.+) from (.+)") {
        @Override
        public void execute(UserInterface frontend) throws IOException {
            String inputWord = arguments[0].toUpperCase();
            int fromBase = CommandLineParser.parseInt(arguments[1]);
            frontend.showMessage("Base 10 " + EuclidAlgoRythm.convertNumberToBase(inputWord, fromBase, 10));
            frontend.showMessage("Base 2 " + EuclidAlgoRythm.convertNumberToBase(inputWord, fromBase, 2));
            frontend.showMessage("Base 8 " + EuclidAlgoRythm.convertNumberToBase(inputWord, fromBase, 8));
            frontend.showMessage("Base 13 " + EuclidAlgoRythm.convertNumberToBase(inputWord, fromBase, 13));
            frontend.showMessage("Base 16 " + EuclidAlgoRythm.convertNumberToBase(inputWord, fromBase, 16));
        }
    },
    /** Quits the game */
    QUIT("quit") {
        @Override
        public void execute(UserInterface frontend) throws IOException {
            frontend.exit();
        }
    };

    /**
     * These are the arguments input by the user.
     * They are protected so the Methods inside the Command enums can still access them
     */
    protected String[] arguments;
    private final Pattern pattern;

    /**
     * Structure of the command enum
     *
     * @param regex the regex pattern of the Command
     */
    CommandLineCommands(String regex) {
        pattern = Pattern.compile(regex);
    }

    /**
     * @param input is the input to match
     * @return wether the string input matches any regex pattern
     * @throws java.io.IOException The command exists but the parameters are incorrect
     */
    public boolean parseInput(String input) throws IOException {

        Matcher matcher = pattern.matcher(input);

        if (!matches(input)) {
            return false;
        }

        matcher.matches();

        arguments = new String[matcher.groupCount()];

        for (int i = 1; i < matcher.groupCount(); i++) {
            arguments[i - 1] = matcher.group(i + 1).replaceAll("\\(?\\)?", "");
        }
        return true;
    }


    /**
     * Checks for a match with the regex pattern of the command
     *
     * @param input String to check
     * @return Wether the string matches a pattern of any command
     * @throws IOException the Input matches only a command name
     */
    @Override
    public final boolean matches(final String input) throws IOException {
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            if (commandNameMatches(input)) {
                throw new IOException("No command matches these parameters");
            }
            return false;
        }

        return true;
    }

    /**
     * Checks wether the String matches this commands command name
     *
     * @param input String input by the user
     * @return Wether the String matches this commands command name
     */
    @Override
    public boolean commandNameMatches(String input) {
        String[] words = input.split(" ");
        String patternString = pattern.toString().replaceAll("\\(?\\)?", "");
        String[] cmdWord = patternString.split(" ");
        if (!words[0].equals(cmdWord[0])) {
            return false;
        }
        return true;
    }

}
