package lotus.arquivo;

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

    public void criaArq(String codigo) throws IOException {

        File file = new File("C:\\Users\\NUCK\\Documents\\Compiladores\\Lotus\\teste.asm");
        file.createNewFile();

        FileWriter fw = new FileWriter(file.getAbsolutePath());
        var bw = new BufferedWriter(fw);

        bw.write(codigo);
        bw.close();
    }

}