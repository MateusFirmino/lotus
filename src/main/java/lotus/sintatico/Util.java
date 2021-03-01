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
        naoTerminal.put("lotus", 0);
        naoTerminal.put("recursive_statement", 1);
        naoTerminal.put("statement", 2);
        naoTerminal.put("write", 3);
        naoTerminal.put("read", 4);
        naoTerminal.put("conditional", 5);
        naoTerminal.put("if", 6);
        naoTerminal.put("else", 7);
        naoTerminal.put("loop", 8);
        naoTerminal.put("while", 9);
        naoTerminal.put("declaration", 10);
        naoTerminal.put("assignment", 11);
        naoTerminal.put("basic_expression", 12);
        naoTerminal.put("basic_operator", 13);
        naoTerminal.put("string_expression", 14);
        naoTerminal.put("any_expression", 15);
        naoTerminal.put("logical_expression", 16);
        naoTerminal.put("id_or_number", 17);
        naoTerminal.put("logical_operator", 18);


    }


    public static Map<String, Integer> getTerminal() {
        return terminal;
    }

    public static Map<String, Integer> getNaoTerminal() {
        return naoTerminal;
    }

    public static List<List<String>> getRegrasProducao() {
        return asList(
                asList("init_program", "recursive_statement", "close_program"),
                List.of(),
                asList("statement", "recursive_statement"),
                List.of(),
                asList("write"),
                asList("read"),
                asList("conditional"),
                asList("loop"),
                asList("declaration"),
                asList("assignment"),
                asList("write", "(", "any_expression", ")", ";"),
                asList("read", "(", "variavel", ")", ";"),
                asList("if", "else", "endif"),
                asList("if", "(", "logical_expression", ")", "recursive_statement"),
                asList("else", "recursive_statement"),
                List.of(),
                asList("while", "endloop"),
                asList("loop", "(", "logical_expression", ")", "recursive_statement"),
                asList("var", "variavel", ";"),
                asList("variavel", "=", "basic_expression", ";"),
                asList("variavel", "basic_operator"),
                asList("number", "basic_operator"),
                asList("+", "basic_expression"),
                asList("-", "basic_expression"),
                asList("(", "basic_expression ", ")", "basic_operator"),
                asList("+", "basic_expression"),
                asList("-", "basic_expression"),
                asList("*", "basic_expression"),
                asList("/", "basic_expression"),
                List.of(),
                asList("string_literal"),
                asList("variavel"),
                asList("number"),
                asList("basic_expression"),
                asList("string_expression"),
                asList("<"),
                asList(">"),
                asList("id_or_number", "logical_operator", "id_or_number")
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
