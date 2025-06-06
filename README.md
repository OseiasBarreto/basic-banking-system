# 💳 Sistema Bancário em Java

Este é um projeto simples de um sistema bancário desenvolvido em Java, utilizando os princípios da Programação Orientada a Objetos (POO). Ele simula a criação e manipulação de contas bancárias, incluindo operações como depósito, saque, transferência e encerramento de conta.

---

## 🚧 Em Desenvolvimento

O projeto está sendo expandido com as seguintes funcionalidades:

- [ ] Interface gráfica de usuário (UI)
- [ ] Diferenciação entre **Usuário Administrador** e **Usuário Comum**
- [ ] Recuperação de senha por email
- [ ] Validador de senha (força mínima e regras)
- [ ] Notificações por email (operações importantes)
- [ ] Autenticação segura

---

## 🧱 Estrutura do Projeto

O projeto é composto por três classes principais:

- `ContaBancaria`: Representa a conta do usuário, com atributos e regras de negócio.
- `Banco`: Gerencia uma coleção de contas bancárias.
- `Main`: Classe principal para testar as funcionalidades do sistema.

## ⚙️ Funcionalidades Implementadas

### ✅ ContaBancaria
- Criação de conta com nome, CPF e data de nascimento.
- Validação de idade mínima (18 anos).
- Depósito (valor positivo).
- Saque (após depósito e com saldo suficiente).
- Transferência entre contas (após depósito e com saldo).
- Encerramento da conta (saldo zerado e conta ativa).
- Formatação de CPF.
- Método `toString()` para exibir os dados da conta.

### ✅ Banco
- Armazena contas bancárias em uma lista.
- Adiciona novas contas com verificação de CPF duplicado e formato.
- Busca conta pelo CPF.
- Lista todas as contas cadastradas.

### ✅ Main
- Cria contas.
- Realiza operações (depósito, saque, transferência).
- Lista contas.
- Mostra dados com `toString()`.

---

## 📦 Exemplo de Uso

```java
ContaBancaria c1 = new ContaBancaria("Mariana", "00000000001", "22/02/2001");
ContaBancaria c2 = new ContaBancaria("Rodolfo", "00000000002", "08/10/2000");

banco.adicionarConta(c1);
banco.adicionarConta(c2);

c1.depositar(2000);
c1.transferir(c2, 500);
c2.transferir(c1, 400);
c1.sacar(1900);
c1.encerrarConta();

banco.listarContas();

System.out.println(c1.toString());
System.out.println(c2.toString());
```

---

## 🛠 Tecnologias Utilizadas

- Java (JDK 8 ou superior)
- Programação Orientada a Objetos (POO)
- Java Collections (ArrayList)
- Java Time (LocalDate, Period)
- [Futuramente] JavaFX ou Swing para interface gráfica
- [Futuramente] JavaMail API para envio de emails
- [Futuramente] Criptografia (para senhas)

---

## 📁 Organização dos Arquivos

```
📂 projeto-banco
├── Banco.java
├── ContaBancaria.java
├── Main.java
└── (em breve) interface/
    ├── LoginUI.java
    ├── UsuarioAdm.java
    └── UsuarioComum.java
```

---

## ✍️ Autor

- Desenvolvido por [Seu Nome Aqui]

---

## 📌 Observações

Esse projeto é acadêmico, com fins educativos. Ele serve como base para desenvolvimento de sistemas bancários com autenticação e controle de usuários, e está sendo continuamente aprimorado com novas funcionalidades.