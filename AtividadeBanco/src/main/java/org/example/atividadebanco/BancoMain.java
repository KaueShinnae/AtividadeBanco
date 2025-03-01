package org.example.atividadebanco;

// Classe abstrata ContaBancaria
abstract class ContaBancaria {
    protected String numeroConta;
    protected String titular;
    protected double saldo;

    // Construtor
    public ContaBancaria(String numeroConta, String titular, double saldo) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
    }

    // Método depositar
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    // Método abstrato sacar
    public abstract boolean sacar(double valor);

    // Método exibirInformacoes
    public void exibirInformacoes() {
        System.out.println("------------------------------");
        System.out.println("Conta: " + numeroConta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: R$ " + saldo);
    }

    // Getters
    public double getSaldo() {
        return saldo;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getTitular() {
        return titular;
    }
}

// Classe ContaCorrente
class ContaCorrente extends ContaBancaria {
    protected double limiteChequeEspecial;

    public ContaCorrente(String numeroConta, String titular, double saldo, double limiteChequeEspecial) {
        super(numeroConta, titular, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para saque.");
            return false;
        }

        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
            return true;
        } else if (saldo + limiteChequeEspecial >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado com uso de cheque especial.");
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque.");
            return false;
        }
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Limite de Cheque Especial: R$ " + limiteChequeEspecial);
    }
}

// Classe ContaPoupanca
class ContaPoupanca extends ContaBancaria {

    public ContaPoupanca(String numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para saque.");
            return false;
        }

        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque.");
            return false;
        }
    }
}

// Classe ContaInvestimento
class ContaInvestimento extends ContaBancaria {
    protected double taxaRetirada = 0.02; // 2% de taxa

    public ContaInvestimento(String numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para saque.");
            return false;
        }

        double valorComTaxa = valor * (1 + taxaRetirada);

        if (saldo >= valorComTaxa) {
            saldo -= valorComTaxa;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
            System.out.println("Taxa de retirada: R$ " + (valor * taxaRetirada));
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque.");
            return false;
        }
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Taxa de retirada: " + (taxaRetirada * 100) + "%");
    }
}

// Classe ContaSalario
class ContaSalario extends ContaCorrente {
    private int saquesMensais = 0;
    private static final double TAXA_SAQUE = 5.0;

    public ContaSalario(String numeroConta, String titular, double saldo, double limiteChequeEspecial) {
        super(numeroConta, titular, saldo, limiteChequeEspecial);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para saque.");
            return false;
        }

        double valorEfetivo = valor;

        if (saquesMensais > 0) {
            valorEfetivo += TAXA_SAQUE;
            System.out.println("Taxa de saque adicional: R$ " + TAXA_SAQUE);
        }

        boolean sucesso = false;

        if (saldo >= valorEfetivo) {
            saldo -= valorEfetivo;
            sucesso = true;
        } else if (saldo + limiteChequeEspecial >= valorEfetivo) {
            saldo -= valorEfetivo;
            System.out.println("Saque realizado com uso de cheque especial.");
            sucesso = true;
        } else {
            System.out.println("Saldo insuficiente para saque.");
            return false;
        }

        if (sucesso) {
            saquesMensais++;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
            System.out.println("Saques mensais realizados: " + saquesMensais);
            return true;
        }

        return false;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Saques gratuitos restantes: " + (saquesMensais == 0 ? 1 : 0));
    }

    // Método para reset mensal (simulação)
    public void resetarSaquesMensais() {
        saquesMensais = 0;
        System.out.println("Saques mensais resetados.");
    }
}

// Classe ContaInvestimentoAltoRisco
class ContaInvestimentoAltoRisco extends ContaInvestimento {
    private static final double SALDO_MINIMO = 10000.0;

    public ContaInvestimentoAltoRisco(String numeroConta, String titular, double saldo) {
        super(numeroConta, titular, saldo);
        this.taxaRetirada = 0.05; // 5% de taxa
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido para saque.");
            return false;
        }

        if (saldo < SALDO_MINIMO) {
            System.out.println("Saldo mínimo de R$ " + SALDO_MINIMO + " não atingido para saque.");
            return false;
        }

        double valorComTaxa = valor * (1 + taxaRetirada);

        if (saldo - valorComTaxa < SALDO_MINIMO) {
            System.out.println("Este saque deixaria o saldo abaixo do mínimo requerido.");
            return false;
        }

        if (saldo >= valorComTaxa) {
            saldo -= valorComTaxa;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
            System.out.println("Taxa de retirada: R$ " + (valor * taxaRetirada));
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque.");
            return false;
        }
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Saldo mínimo para saque: R$ " + SALDO_MINIMO);
    }
}

public class BancoMain {
    public static void main(String[] args) {
        System.out.println("===== SISTEMA BANCÁRIO =====");

        ContaCorrente cc = new ContaCorrente("CC-001", "João", 50.0, 20.0);
        System.out.println("\nTestando Conta Corrente:");
        cc.exibirInformacoes();
        cc.depositar(30.0);
        cc.sacar(70.0); // Usa cheque especial
        cc.sacar(100.0); // Deve falhar
        cc.exibirInformacoes();

        ContaPoupanca cp = new ContaPoupanca("CP-001", "Maria", 100.0);
        System.out.println("\nTestando Conta Poupança:");
        cp.exibirInformacoes();
        cp.depositar(50.0);
        cp.sacar(120.0);
        cp.sacar(200.0); // Deve falhar
        cp.exibirInformacoes();

        ContaInvestimento ci = new ContaInvestimento("CI-001", "Pedro", 100.0);
        System.out.println("\nTestando Conta Investimento:");
        ci.exibirInformacoes();
        ci.depositar(50.0);
        ci.sacar(80.0); // Vai sacar 80 + 2% de taxa
        ci.exibirInformacoes();

        ContaSalario cs = new ContaSalario("CS-001", "Ana", 200.0, 50.0);
        System.out.println("\nTestando Conta Salário:");
        cs.exibirInformacoes();
        cs.sacar(50.0); // Primeiro saque (gratuito)
        cs.sacar(40.0); // Segundo saque (com taxa)
        cs.exibirInformacoes();
        cs.resetarSaquesMensais();
        cs.sacar(30.0); // Novo mês, primeiro saque (gratuito)
        cs.exibirInformacoes();

        ContaInvestimentoAltoRisco ciar = new ContaInvestimentoAltoRisco("CIAR-001", "Carlos", 15000.0);
        System.out.println("\nTestando Conta Investimento de Alto Risco:");
        ciar.exibirInformacoes();
        ciar.sacar(1000.0); // Vai sacar 1000 + 5% de taxa
        ciar.exibirInformacoes();

        ContaInvestimentoAltoRisco ciar2 = new ContaInvestimentoAltoRisco("CIAR-002", "Luciana", 9000.0);
        System.out.println("\nTestando Conta Investimento de Alto Risco com saldo abaixo do mínimo:");
        ciar2.exibirInformacoes();
        ciar2.sacar(1000.0); // Deve falhar por saldo mínimo
        ciar2.depositar(2000.0);
        ciar2.exibirInformacoes();
        ciar2.sacar(1000.0); // Agora deve funcionar
        ciar2.exibirInformacoes();
    }
}