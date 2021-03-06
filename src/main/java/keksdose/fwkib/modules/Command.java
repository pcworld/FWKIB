package keksdose.fwkib.modules;

import java.util.function.Function;

@FunctionalInterface
public interface Command extends Function<String, String> {

  public abstract String apply(String message);

  public default String help(String message) {
    return "so geht das nicht :(";
  }

}
