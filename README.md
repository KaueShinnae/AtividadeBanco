# AtividadeBanco

Sistema Bancário com Herança em Java
Este projeto é um exemplo de um sistema bancário simples implementado em Java, utilizando o conceito de herança para criar diferentes tipos de contas bancárias. O objetivo é demonstrar como classes podem herdar atributos e métodos de uma classe base, enquanto implementam regras específicas para cada tipo de conta.

O que o código faz?
O código simula um sistema bancário com diferentes tipos de contas, cada uma com suas próprias regras para depósitos, saques e exibição de informações. A estrutura do projeto é baseada em uma classe abstrata chamada ContaBancaria, que serve como base para as demais classes de contas.

Classes Principais:
ContaBancaria (Classe Abstrata):

Atributos: numeroConta, titular, saldo.

Métodos:

depositar(double valor): Adiciona um valor ao saldo da conta.

sacar(double valor): Método abstrato que será implementado pelas subclasses.

exibirInformacoes(): Exibe os dados da conta (número da conta, titular e saldo).

Subclasses de ContaBancaria:

ContaCorrente:

Permite o uso de cheque especial caso o saldo seja insuficiente para o saque.

ContaPoupanca:

Só permite saques se o saldo for suficiente (não pode ficar negativo).

ContaInvestimento:

Aplica uma taxa de 2% sobre o valor sacado.

Subclasses Especializadas:

ContaSalario (herda de ContaCorrente):

Permite apenas um saque gratuito por mês. Saques adicionais são cobrados com uma taxa de R$ 5,00.

ContaInvestimentoAltoRisco (herda de ContaInvestimento):

Aplica uma taxa de 5% sobre o valor sacado e exige um saldo mínimo de R$ 10.000,00 para realizar saques.

Como funciona?
Cada tipo de conta tem suas próprias regras para saques, que são implementadas nos métodos sacar das subclasses. Por exemplo:

Na ContaCorrente, se o saldo for insuficiente, o saque pode ser realizado usando o limite do cheque especial.

Na ContaPoupanca, o saque só é permitido se o saldo for suficiente.

Na ContaInvestimento, uma taxa de 2% é aplicada sobre o valor sacado.

Na ContaSalario, apenas um saque por mês é gratuito, e saques adicionais são cobrados com uma taxa.

Na ContaInvestimentoAltoRisco, além da taxa de 5%, é necessário ter um saldo mínimo de R$ 10.000,00 para realizar saques.
