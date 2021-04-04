package lotus.semantico;

import lotus.flags.Flag;
import lotus.lexico.TokenBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static lotus.lexico.Tokenizer.isId;


public class Semantico {
    List<TokenBox> tokens;
    HashMap<String, Boolean> declarados;

    public Semantico(List<TokenBox> tokens) {
        this.tokens = new ArrayList<>(tokens);
        this.declarados = new HashMap<>();
    }

    public void analise() {

        int i = 0;
        while (i < tokens.size()) {
            var tk = tokens.get(i);
            //verifica se é um inteiro
            if (tk.getToken().equals("var")) {
                var tkProximo = tokens.get(i + 1);
                if (declarados.containsKey(tkProximo.getLexeme())) { // verifica se a variavel ja foi declarada
                    erro("variavel " + tkProximo.getLexeme() + " ja foi declarada", tkProximo.getLine(), tkProximo.getColumn());
                }
                declarados.put(tkProximo.getLexeme(), false);
                log("A variavel " + tkProximo.getLexeme() + " foi declarada");
            } else if (tk.getToken().equals("if") || tk.getToken().equals("loop")) {
                while (!tokens.get(i++).getToken().equals(")")) { // verifica se a variavel dentro do escopo ja foi declarado
                    if (tokens.get(i).getToken().equals("variavel")) {
                        if (!declarados.containsKey(tokens.get(i).getLexeme())) {
                            erro("variavel " + tokens.get(i).getLexeme() + " nao foi declarada",
                                    tokens.get(i).getLine(), tokens.get(i).getColumn());
                        }
                    }
                }
            } else if (tk.getToken().equals("variavel")) { //verifica se é um variavel
                if (!declarados.containsKey(tk.getLexeme())) {
                    erro("variavel " + tk.getLexeme() + " nao foi declarada", tk.getLine(), tk.getColumn());
                }

                var buffer = new StringBuilder();
                if (tokens.get(++i).getToken().equals("=")) {
                    i++;
                    while (!tokens.get(i).getToken().equals(";")) {
                        String valor = tokens.get(i).getLexeme();
                        if (isId(valor)) {
                            if (!declarados.containsKey(tk.getLexeme())) {
                                erro("variavel " + tk.getLexeme() + " nao foi declarada",
                                        tk.getLine(), tk.getColumn());
                            }
                            if (declarados.get(valor)) {
                                buffer.append(valor);
                            } else {
                                buffer.append("0");
                            }
                        } else {
                            buffer.append(valor);
                        }
                        i++;
                    }
                    if (buffer.toString().equals("0")) {
                        declarados.put(tk.getLexeme(), false);
                    } else if (buffer.toString().contains("/0")) {
                        erro("divisao por zero ", tk.getLine(), tk.getColumn());
                    } else {
                        declarados.put(tk.getLexeme(), true);
                    }
                    log("variavel " + tk.getLexeme() + " recebeu valor: " + buffer.toString());
                }
            } else if (tk.getToken().equals("read")) {
                var tkProximo = tokens.get(i + 2);
                // log("entrou no read " + tkProximo);
                declarados.put(tkProximo.getLexeme(), true);
            }
            i++;
        }
    }


    private void log(String msg) {
        if (Flag.SEMANTICO.getStatus() || Flag.TODOS.getStatus()) {
            System.out.println(msg);
        }
    }

    private void erro(String erro, Integer line, Integer column) {
        throw new SemanticoException("[" + line + ", " + column + "] " + erro);
    }

    public List<String> getDeclarados() {

        return new ArrayList<>(declarados.keySet());
    }
}
