import java.io.*;
import java.util.*;

public class Classificacoes {
    private Stack<Integer> stack = new Stack<>();
    private Map<String, Integer> variables = new HashMap<>();
    private Map<String, ClassObj> objects = new HashMap<>();

    public void execute(String[] instruction) {

        switch (instruction[0]) {
            case "const":
                int value = Integer.parseInt(instruction[1]);
                stack.push(value);
                break;

            case "load":
                String varName = instruction[1];
                stack.push(variables.get(varName));
                break;

            case "store":
                varName = instruction[1];
                variables.put(varName, stack.pop());
                break;

            case "add":
                int v2 = stack.pop();
                int v1 = stack.pop();
                stack.push(v1 + v2);
                break;

            case "sub":
                v2 = stack.pop();
                v1 = stack.pop();
                stack.push(v1 - v2);
                break;

            case "mul":
                v2 = stack.pop();
                v1 = stack.pop();
                stack.push(v1 * v2);
                break;

            case "div":
                v2 = stack.pop();
                v1 = stack.pop();
                stack.push(v1 / v2);
                break;

            case "new":
                String className = instruction[1];
                ClassObj newObj = new ClassObj();
                objects.put(className, newObj);
                stack.push(objects.size());  // Exemplo para armazenar referência
                break;

            case "get":
                String attrName = instruction[1];
                //fazer
                break;

            case "set":
                attrName = instruction[1];
                int attrValue = stack.pop();
                //fazer
                break;

            case "call":
                String methodName = instruction[1];
                // Implementar lógica de chamada de método aqui
                break;

            case "gt":
                v2 = stack.pop();
                v1 = stack.pop();
                stack.push(v1 > v2 ? 1 : 0);
                break;

            case "ge":
                v2 = stack.pop();
                v1 = stack.pop();
                stack.push(v1 >= v2 ? 1 : 0);
                break;

            case "lt":
                v2 = stack.pop();
                v1 = stack.pop();
                stack.push(v1 < v2 ? 1 : 0);
                break;

            case "le":
                v2 = stack.pop();
                v1 = stack.pop();
                stack.push(v1 <= v2 ? 1 : 0);
                break;

            case "eq":
                v2 = stack.pop();
                v1 = stack.pop();
                stack.push(v1 == v2 ? 1 : 0);
                break;

            case "ne":
                v2 = stack.pop();
                v1 = stack.pop();
                stack.push(v1 != v2 ? 1 : 0);
                break;

            case "pop":
                stack.pop();  // Descarta o topo da pilha
                break;

            case "if":
                int n = Integer.parseInt(instruction[1]);
                if (stack.pop() == 0) {
                    // Saltar as próximas 'n' instruções
                    // skipInstructions(n);
                }
                break;

            case "else":
                n = Integer.parseInt(instruction[1]);
                // Similar à lógica do "if"
                break;

            case "ret":
                // Lógica de retorno
                break;

            default:
                throw new RuntimeException("Instrução desconhecida: " + instruction[0]);

        }
    }
}
