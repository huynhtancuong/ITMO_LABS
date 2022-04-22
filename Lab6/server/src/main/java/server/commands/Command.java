package server.commands;

/**
 * A interface for Command structure
 */
public interface Command {
    /**
     *
     * @return Description of command
     */
    String getDescription();

    String getUsage();

    /**
     * @return Name of command
     */
    String getName();

    /**
     * @param argument argument of the command
     * @return exit status of command
     */
    boolean execute(String stringArgument, Object commandObjectArgument);
}
