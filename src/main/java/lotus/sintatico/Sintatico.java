package lotus.sintatico;

import lotus.flags.Flag;
import lotus.lexico.TokenBox;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static lotus.sintatico.Util.*;

public class Sintatico {

    private LinkedList<TokenBox> filaTk;
    private Stack<String> pilha;

    public Sintatico(List<TokenBox> listOfTokens) {
        this.filaTk = new LinkedList<>(listOfTokens); //inicia a fila
        this.pilha = new Stack<>();// inicia a pilha
        // add $ e lotus para no final mostrar que o processo deu certo
        pilha.push("$");
        pilha.push("LOTUS");
    }

    public void analiseSintatico() {

        while (!pilha.empty()) {
            var tk = filaTk.getFirst().getToken();
            var line = filaTk.getFirst().getLine();
            var column = filaTk.getFirst().getColumn();
            var topo = pilha.peek();
            if (isTerminal(topo)) {
                terminalNoTopo(tk, line, column);
            } else {
                naoTerminalNoTopo(tk, line, column);
            }
        }
    }

    private void naoTerminalNoTopo(String tk, int line, int column) {
        var topo = pilha.peek();
        var topoId = naoTerminal.get(topo);
        var tokenId = terminal.get(tk);
        var indice = getTabelaSintatica().get(topoId).get(tokenId); // checa a tabela sintatica

        // Caso o índice seja menor que zero significa que essa combinação terminal + nãoTerminal não
        // existe. Logo, erro sintático!
        if (indice < 0) {
            erro(topo, tk, line, column);
        }
        var regraDeProducao = new LinkedList<>(getRegrasProducao().get(indice));
        log((!isTerminal(topo) ? "Nao terminal \"" : "token \"") + topo + "\" foi removido na pilha");
        pilha.pop();
        while (!regraDeProducao.isEmpty()) {
            var regra = regraDeProducao.removeLast();
            log(
                    (!isTerminal(regra) ? "Nao terminal \"" : "token \"") + regra + "\" foi colocada na pilha");
            pilha.push(regra);
        }
    }

    private void terminalNoTopo(String tk, int line, int column) {
        var topo = pilha.peek();
        var topoId = terminal.get(topo);
        var tkId = terminal.get(tk);
        // fixme: não reconhece o terminal 'id', procurar no gals e no html
        if (tkId.equals(topoId)) {
            filaTk.removeFirst();
            pilha.pop();
            log("Token \"" + tk + "\" foi removido da Lista de tokens");
            log("Token \"" + topo + "\" foi removido da Pilha de tokens");
        } else {
            erro(topo, tk, line, column);
        }

    }

    private boolean isTerminal(String s) {
        return terminal.containsKey(s);
    }

    private void log(String msg) {
        if (Flag.SINTATICO.getStatus() || Flag.TODOS.getStatus()) {
            System.out.println(msg);
        }
    }

    private void erro(String topo, String tk, Integer line, Integer column) {
        throw new SintaticException("[" + line + ", " + column + "]" +
                " foi encontrado o token: \"" + tk + "\", porem era esperado: \"" + topo + "\"");
    }

}