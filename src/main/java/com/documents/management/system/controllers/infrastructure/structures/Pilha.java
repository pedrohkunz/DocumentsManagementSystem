import java.util.ArrayDeque;
import java.util.Deque;

public class Pilha {
    
    private Pilha<String> PilhaDocumentos;

    public GerenciaDocumentos() {
        this.PilhaDocumentos = new ArrayDeque<>();
    }

    public void AddDocumento(String nomeDocumento) {
        PilhaDocumentos.push(nomeDocumento);
        System.out.println("documento " + nomeDocumento + "adicionado")
    }

    public String removeDocumento(){
        if (PilhaDocumentos.isEmpty()) {
            System.out.println("A pilha esta vazia")
            return null
        }
        String documento = PilhaDocumentos.pop();
        return documento;
    }

    Public String elementoTopo(){
        if(PilhaDocumentos.isEmpty()){
            System.out.println("Pilha Vazia")
            return null
        }

        String documento = PilhaDocumentos.peek();
        system.out.println("documento no topo é " + nomeDocumento);
        return nomeDocumento;
    }

    public boolean pilhaVazia() {

        return PilhaDocumentos.isEmpty();

    }

    public int tamanhoPilha() {
        return PilhaDocumentos.size();
    }

    public static void main (String[] args){
        GerenciaDocumentos gerenciador = new GerenciaDocumentos

        gerenciador.AddDocumento("ata 1")
        gerenciador.AddDocumento("ata 2")
        gerenciador.AddDocumento("ata 3")
        gerenciador.AddDocumento("ata 4")
        gerenciador.AddDocumento("ata 5")

        system..ou.println("------")
        gerenciador.elementoTopo();
        System.out.println("tamanho pilha: " + gerenciador.tamanhoPilha());
        system..ou.println("------")

        gerenciador.removeDocumento();
        gerenciador.removeDocumento();
        gerenciador.removeDocumento();
        gerenciador.removeDocumento();
        gerenciador.removeDocumento();

        system..ou.println("------")

        system.out.println("pilha " + gerenciador.pilhaVazia)


    }




}
