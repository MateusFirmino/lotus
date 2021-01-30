package lotus.lexico;

import lotus.arquivo.Arquivo;
import lotus.flags.Flag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    int lineCounter = 1;
    int columnCounter = 1;

    List<String> line = new ArrayList<>();
    List<TokenBox> listOfTokens = new ArrayList<>();

    public static boolean isKeyword(String s) {

        switch (s) {
            case "lt_begin":
            case "lt_exit":
            case "lt_create":
            case "lt_if":
            case "lt_else":
            case "lt_endif":
            case "lt_while":
            case "lt_endwhile":
            case "lt_show":
            case "lt_read":
                return true;
        }

        return false;
    }

    public static boolean isSymbol(String s) {
        switch (s) {
            case ";":
            case "(":
            case ")":
            case "=":
            case "+":
            case "-":
            case "*":
            case "/":
            case "<":
            case ">":
                return true;
        }

        return false;
    }

    public static boolean isId(String s) {
        if (!Character.isLetter(s.charAt(0))) {
            return false;
        }
        return s.chars().allMatch(Character::isLetterOrDigit);
    }

    public static boolean isNumber(String s) {
        return s.chars().allMatch(Character::isDigit);
    }

    public  List<TokenBox> splitTk(List<String> codigo) {

        String splitter = "[+*\\-<>/=\\s)(\";]"; //criterios de divisao

        String regex = "((?<=" + splitter + ")|"  /*lookahead*/ + "(?=" + splitter + "))"; /*lookbehind*/

        for (String s : codigo) {

            if (s.isEmpty()) { // tratamento para linha vazia
                lineCounter++;
                continue;
            }

            line = Arrays.asList(s.split(regex));

            for (int i = 0; i < line.size(); i++) { //percorre a lista de linha quebradas pelo regex
                String lexeme = line.get(i);

                if (lexeme.isBlank()) continue; // tratamento para espaços

                if (isKeyword(lexeme) || isSymbol(lexeme)) { //add caso for um simbolo ou palavra chave
                    listOfTokens.add(new TokenBox(lexeme, lexeme, lineCounter, columnCounter));
                    columnCounter += lexeme.length();
                } else if (isId(lexeme)) { // add caso for um id
                    listOfTokens.add(new TokenBox("id", lexeme, lineCounter, columnCounter));
                    columnCounter += lexeme.length();
                } else if (isNumber(lexeme)) { // add caso for um numeral
                    listOfTokens.add(new TokenBox("number", lexeme, lineCounter, columnCounter));
                    columnCounter += lexeme.length();
                } else if (lexeme.equals("\"")) { // add strings
                    StringBuilder string = new StringBuilder();

                    do {
                        string.append(line.get(i));

                        if (i + 1 == line.size()) { // caso chegue no final de linha sem encontrar o fecha aspas
                            System.err.println("LEXICAL ERROR at [" + lineCounter + ", " + columnCounter + "] " +
                                    "Faltou aspas.");
                            System.exit(-1);
                        }

                    } while (!line.get(++i).equals("\"")); //enquanto o proximo lexema for diferente de fecha aspas

                    string.append('"');
                    listOfTokens.add(new TokenBox("string", string.toString(), lineCounter, columnCounter));
                    columnCounter += string.length();

                } else { // caso seja  um lexema que não se encaixa na gramatica
                    System.err.println("LEXICAL ERROR at [" + lineCounter + ", " + columnCounter + "] " +
                            lexeme + " não foi reconhecido.");
                    System.exit(-1);
                }
            }
            lineCounter++;
            columnCounter = 1;
        }
        listOfTokens.add(new TokenBox("$","$",lineCounter,columnCounter));
        if (Flag.TOKENS.getStatus()){
            for (TokenBox listOfToken : listOfTokens) { //printa a lista de tokens
                System.out.println(listOfToken.toString());
            }
        }
        return listOfTokens;
    }
}

