import java.io.*;
import java.util.*;

public class BoolInterpreter {

    private Classificacoes classificacoes = new Classificacoes();

    public static void main(String[] args) {
        if (args.length != 1) {
            return;
        }
        BoolInterpreter interpreter = new BoolInterpreter();
        interpreter.run(args[0]);
    }

    // lendo o compilado
    private void run(String inputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] instruction = line.trim().split(" ");
                classificacoes.execute(instruction);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
