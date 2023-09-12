import javax.swing.JOptionPane;

class Carro {
    private String modelo;
    private String marca;
    private double valorPorKm;

    public void cadastrarCarro() {
        //lógica para cadastrar o carro
        modelo = JOptionPane.showInputDialog("Informe o modelo do carro:");
        marca = JOptionPane.showInputDialog("Informe a marca do carro:");
        valorPorKm = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor por km do carro (utilize o ponto no lugar da virgula):"));
    }

    public String mostrarDadosCarro() {
        return "Modelo: " + modelo + "\nMarca: " + marca + "\nValor por Km: " + valorPorKm;
    }

    double getValorPorKm() {
        return valorPorKm;
    }
}


class Cliente {
    private String nome;
    private String cpf;
    private String telefone;

    public void cadastrarUsuario() {
        //lógica para cadastrar o cliente
        nome = JOptionPane.showInputDialog("Informe o nome do cliente:");
        cpf = JOptionPane.showInputDialog("Informe o CPF do cliente:");
        telefone = JOptionPane.showInputDialog("Informe o telefone do cliente:");
    }

    public String mostrarDadosUsuario() {
        return "Nome: " + nome + "\nCPF: " + cpf + "\nTelefone: " + telefone;
    }
}

class Aluguel {
    private Carro carro;
    private Cliente cliente;
    private int qtdDias;
    private int kmInicial;
    private int kmFinal;
    private double valorTotalAluguel;

    public void iniciarLocacao() {
        cliente = new Cliente();
        cliente.cadastrarUsuario();

        carro = new Carro();
        carro.cadastrarCarro();

        qtdDias = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de dias da locação:"));
        kmInicial = Integer.parseInt(JOptionPane.showInputDialog("Informe o km inicial do veículo:"));
    }

public void fecharLocacao() {
    boolean inputValid = false;

    while (!inputValid) {
        try {
            kmFinal = Integer.parseInt(JOptionPane.showInputDialog("Informe o km final do veículo:"));
            inputValid = true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido para o km final.");
        }
    }

    int desconto = 0;
    if (qtdDias > 20) {
        desconto = 20;
    } else if (qtdDias > 10) {
        desconto = 10;
    }

    double valorPorKm = carro.getValorPorKm();
    double valorSemDesconto = (kmFinal - kmInicial) * valorPorKm;
    double valorComDesconto = valorSemDesconto * (1 - desconto / 100.0);
    valorTotalAluguel = valorComDesconto;
}



    public String mostrarResumoLocacao() {
        String resumo = "Resumo Aluguel\n";
        resumo += "Cliente\n" + cliente.mostrarDadosUsuario() + "\n";
        resumo += "Carro\n" + carro.mostrarDadosCarro() + "\n";
        resumo += "Aluguel\n";
        resumo += "Quantidade de dias: " + qtdDias + "\n";
        resumo += "Km Inicial: " + kmInicial + "\n";
        resumo += "Km Final: " + kmFinal + "\n";
        resumo += "Valor Total: R$" + valorTotalAluguel + "\n";
        return resumo;
    }
}

public class Main {
    public static void main(String[] args) {
        Aluguel aluguel = new Aluguel();
        aluguel.iniciarLocacao();
        aluguel.fecharLocacao();

        String resumo = aluguel.mostrarResumoLocacao();
        JOptionPane.showMessageDialog(null, resumo);
    }
}