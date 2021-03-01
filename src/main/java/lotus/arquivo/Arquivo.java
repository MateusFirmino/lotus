package lotus.arquivo;

import lotus.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    public List<String> lerAquivo(String url) {
        List<String> codigo = new ArrayList<>();
        try (var arquivo = new BufferedReader(new FileReader(url))) {

            while (arquivo.ready()) {
                codigo.add(arquivo.readLine());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Não foi possível ler o arquivo" + e.getMessage());
        }
        return codigo;
    }
}