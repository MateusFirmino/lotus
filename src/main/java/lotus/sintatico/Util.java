package lotus.sintatico;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Util {

    public static Map<String, Integer> terminal;
    public static Map<String, Integer> naoTerminal;
    public static Map<String, String> palavraChave;

    static {
        terminal = new HashMap<>(); // horizontal
        terminal.put("$", 0);
        terminal.put("variavel", 1);
        terminal.put("keyword", 2);
        terminal.put("number", 3);
        terminal.put("string_literal", 4);
        terminal.put("init_program", 5);    // estava escrito errado
        terminal.put("close_program", 6);   // estava escrito errado
        terminal.put("if", 7);
        terminal.put("else", 8);
        terminal.put("endif", 9);
        terminal.put("loop", 10);
        terminal.put("endloop", 11);
        terminal.put("write", 12);
        terminal.put("read", 13);
        terminal.put("var", 14);
        terminal.put(";", 15);
        terminal.put("=", 16);
        terminal.put("+", 17);
        terminal.put("-", 18);
        terminal.put("*", 19);
        terminal.put("/", 20);
        terminal.put("<", 21);
        terminal.put(">", 22);
        terminal.put(")", 23);
        terminal.put("(", 24);

        naoTerminal = new HashMap<>(); // vertical
        naoTerminal.put("LOTUS", 0);
        naoTerminal.put("RECURSIVE_STATEMENT", 1);
        naoTerminal.put("STATEMENT", 2);
        naoTerminal.put("WRITE", 3);
        naoTerminal.put("READ", 4);
        naoTerminal.put("CONDITIONAL", 5);
        naoTerminal.put("IF", 6);
        naoTerminal.put("ELSE", 7);
        naoTerminal.put("LOOP", 8);
        naoTerminal.put("WHILE", 9);
        naoTerminal.put("DECLARATION", 10);
        naoTerminal.put("ASSIGNMENT", 11);
        naoTerminal.put("BASIC_EXPRESSION", 12);
        naoTerminal.put("BASIC_OPERATOR", 13);
        naoTerminal.put("STRING_EXPRESSION", 14);
        naoTerminal.put("ANY_EXPRESSION", 15);
        naoTerminal.put("LOGICAL_EXPRESSION", 16);
        naoTerminal.put("ID_OR_NUMBER", 17);
        naoTerminal.put("LOGICAL_OPERATOR", 18);


    }


    public static Map<String, Integer> getTerminal() {
        return terminal;
    }

    public static Map<String, Integer> getNaoTerminal() {
        return naoTerminal;
    }

    public static List<List<String>> getRegrasProducao() {
        return asList(
                asList("init_program", "RECURSIVE_STATEMENT", "close_program"),
                List.of(),
                asList("STATEMENT", "RECURSIVE_STATEMENT"),
                List.of(),
                asList("WRITE"),
                asList("READ"),
                asList("CONDITIONAL"),
                asList("LOOP"),
                asList("DECLARATION"),
                asList("ASSIGNMENT"),
                asList("write", "(", "ANY_EXPRESSION", ")", ";"),
                asList("read", "(", "variavel", ")", ";"),
                asList("IF", "ELSE", "endif"),
                asList("if", "(", "LOGICAL_EXPRESSION", ")", "RECURSIVE_STATEMENT"),
                asList("else", "RECURSIVE_STATEMENT"),
                List.of(),
                asList("WHILE", "endloop"),
                asList("loop", "(", "LOGICAL_EXPRESSION", ")", "RECURSIVE_STATEMENT"),
                asList("var", "variavel", ";"),
                asList("variavel", "=", "BASIC_EXPRESSION", ";"),
                asList("variavel", "BASIC_OPERATOR"),
                asList("number", "BASIC_OPERATOR"),
                asList("+", "BASIC_EXPRESSION"),
                asList("-", "BASIC_EXPRESSION"),
                asList("(", "BASIC_EXPRESSION ", ")", "BASIC_OPERATOR"),
                asList("+", "BASIC_EXPRESSION"),
                asList("-", "BASIC_EXPRESSION"),
                asList("*", "BASIC_EXPRESSION"),
                asList("/", "BASIC_EXPRESSION"),
                List.of(),
                asList("string_literal"),
                asList("variavel"),
                asList("number"),
                asList("BASIC_EXPRESSION"),
                asList("STRING_EXPRESSION"),
                asList("<"),
                asList(">"),
                asList("ID_OR_NUMBER", "LOGICAL_OPERATOR", "ID_OR_NUMBER")
        );
    }

    public static List<List<Integer>> getTabelaSintatica() {

        return asList(
                asList(1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, 2, -1, -1, -1, -1, 3, 2, 3, 3, 2, 3, 2, 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, 9, -1, -1, -1, -1, -1, 6, -1, -1, 7, -1, 4, 5, 8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, -1, -1, -1, -1, -1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, -1, -1, -1, -1, -1, -1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, -1, -1, -1, -1, -1, -1, -1, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, 20, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, 23, -1, -1, -1, -1, -1, 24),
                asList(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 29, -1, 25, 26, 27, 28, -1, -1, 29, -1),
                asList(-1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, 33, -1, 33, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, 33, -1, -1, -1, -1, -1, 33),
                asList(-1, 37, -1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, 31, -1, 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
                asList(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 35, 36, -1, -1));

    }
}
