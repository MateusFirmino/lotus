package lotus;

import lotus.arquivo.Arquivo;
import lotus.flags.Flags;
//import lotus.intermediario.Final;
//import lotus.intermediario.Intermediario;
import lotus.lexico.Tokenizer;
//import lotus.semantico.Semantico;
//import lotus.sintatico.Sintatico;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        new Flags().verificaTk(args);
        String path = new Flags().validatePath(args);
        var listaDeTokens = new Tokenizer().splitTk(new Arquivo().lerAquivo(path));
//        new Sintatico(listaDeTokens).analiseSintatico();
//        var semantico = new Semantico(listaDeTokens);
//        semantico.analise();
//        var intermed = new Intermediario(listaDeTokens, semantico.getDeclarados());
//        var listIntermed = intermed.geraCod();
//        var fim = new Final();
//        fim.codFinal(listIntermed);

        //  listaDeTokens.forEach(System.out::println);
        //  System.out.println(foo);
    }
}