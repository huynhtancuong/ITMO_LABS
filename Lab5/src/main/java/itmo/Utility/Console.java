package itmo.Utility;

import itmo.Exceptions.ScriptRecursionException;
import itmo.run.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    private CommandManager commandManager;
    private Scanner userScanner;
    private TicketAsker ticketAsker;
    private List<String> scriptStack = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner scanner, TicketAsker ticketAsker) {
        this.commandManager = commandManager;
        this.userScanner = scanner;
        this.ticketAsker = ticketAsker;
    }

    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                Console.print(App.PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        }
        catch (NoSuchElementException exception) {
            Console.printError("User input not found");
        }
        catch (IllegalStateException exception) {
            Console.printError("Unexpected error");
        }
    }


    /**
     * Mode for catching commands from a script.
     * @param argument Its argument.
     * @return Exit code.
     */
    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = ticketAsker.getUserScanner();
            ticketAsker.setUserScanner(scriptScanner);
            ticketAsker.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(App.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            ticketAsker.setUserScanner(tmpScanner);
            ticketAsker.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printError("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            Console.printError("Файл со скриптом пуст!");
        } catch (ScriptRecursionException exception) {
            Console.printError("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            Console.printError("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }


    /**
     * Launchs the command.
     * @param userCommand Command to launch.
     * @return Exit code.
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.add(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.update(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "add_if_max":
                if (!commandManager.addIfMax(userCommand[1])) return 1;
                break;
            case "remove_greater":
                if (!commandManager.removeGreater(userCommand[1])) return 1;
                break;
            case "remove_lower":
                if (!commandManager.removeLower(userCommand[1])) return 1;
                break;
            case "remove_all_by_person":
                if (!commandManager.removeAllByPerson(userCommand[1])) return 1;
                break;
            case "history":
                if (!commandManager.history(userCommand[1])) return 1;
                break;
            case "sum_of_health":
                if (!commandManager.sumOfHealth(userCommand[1])) return 1;
                break;
            case "min_by_price":
                if (!commandManager.minByPrice(userCommand[1])) return 1;
                break;
            case "filter_by_price":
                if (!commandManager.filterByPrice(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }
    public static void print(Object toOut) {
        System.out.print(toOut);
    }
    public static void println(Object toOut) {
        System.out.println(toOut);
    }
    public static void printError(Object toOut) {
        System.out.println("Error: " +  toOut);
    }
    public static void printTable(Object toOut1, Object toOut2) {
        System.out.printf("%-37s%-1s%n", toOut1, toOut2);
    }

    @Override
    public String toString() {
        return "Console (class for launch command)";
    }
}
