package lotus.flags;

import static lotus.flags.Flag.*;

public class Flags {

    public void verificaTk(String[] cmd) {

        for (String s : cmd) {

            if (s.equals("--tk")) {
                TOKENS.setStatus(true);
            }
            else if (s.equals("--smt")) {
                SEMANTICO.setStatus(true);
            }
            else if (s.equals("--sin")) {
                SINTATICO.setStatus(true);
            }
        }
    }
    public String validatePath(String[] cmd){
        for (String arg: cmd) {
            if(arg.contains(".txt")) return arg;
        }
        throw new IllegalArgumentException("Arquivo não reconhecido");
    }

}