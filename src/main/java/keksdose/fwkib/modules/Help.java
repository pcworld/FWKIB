package keksdose.fwkib.modules;

import java.util.Arrays;


public class Help implements Command {
    private static final String[] commands = { "brati", "hash", "haskell", "haskell-url", "help", "home", "stats",
            "pwgen", "rsagen-pri", "rsagen-pub", "security", "uuid","fehler","tv","tv-nau" };

    @Override
    public String apply(String message) {
        return Arrays.toString(commands);

    }

}