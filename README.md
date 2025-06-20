# 💳 Sistema Bancário em Java

Este é um projeto simples e didático de um sistema bancário, desenvolvido em Java com foco em Programação Orientada a Objetos (POO). O sistema permite a criação de contas, autenticação de usuários (admin e comuns), e operações bancárias como **depósito**, **saque**, **transferência** e **encerramento de conta**.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🚧 Em Desenvolvimento

Este projeto está sendo ativamente aprimorado com as seguintes funcionalidades planejadas:

- [x] Criptografia de senha (SHA-256)
- [x] Diferenciação entre Usuário **Administrador** e **Comum**
- [ ] Interface gráfica (Swing ou JavaFX)
- [ ] Validador de senha (força mínima, regras de complexidade)
- [ ] Recuperação de senha por email
- [ ] Notificações por email (para operações importantes)
- [ ] Autenticação com camadas de segurança (token, captcha...)

---

## 🧱 Estrutura do Projeto

O sistema é dividido em camadas e classes responsáveis por diferentes funcionalidades:

- `User` – Armazena dados de login, senha e tipo de usuário.
- `UserService` – Gerencia autenticação, cadastro e remoção de usuários (JSON).
- `CriptografiaUtil` – Aplica hash SHA-256 nas senhas.
- `Banco` – Controla a lista de contas bancárias.
- `ContaBancaria` – Contém regras e ações da conta (depósito, saque, etc.).
- `Menu` e `Main` – Interface por console com menus para admin e usuário comum.
- `TypeUser` – Enum que define o tipo de usuário.

---

## ⚙️ Funcionalidades Implementadas

### ✅ Usuário (User)
- Cadastro com login e senha criptografada
- Autenticação segura
- Armazenamento persistente em `usuarios.json`
- Permissões baseadas no tipo (ADMIN ou COMUM)

### ✅ Conta Bancária
- Criação de conta com nome, CPF e data de nascimento
- Validação de idade mínima (18 anos)
- Depósito, saque e transferência (após depósito inicial)
- Encerramento de conta (saldo zero obrigatório)
- Exibição de dados formatados (incluindo CPF)

### ✅ Banco
- Lista de contas com busca por CPF
- Verificação de CPF duplicado
- Exibição de contas cadastradas

---

## 📦 Exemplo de Uso:

ContaBancaria c1 = new ContaBancaria("Mariana", "00000000001", "22/02/2001", "senha123");

ContaBancaria c2 = new ContaBancaria("Rodolfo", "00000000002", "08/10/2000", "senha123");

Banco banco = new Banco();

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



----------------------------------------
🛠 Tecnologias Utilizadas:

Java (JDK 8+)

POO (Programação Orientada a Objetos)

Coleções Java (ArrayList)

Datas com LocalDate, Period

Gson (persistência de usuários em JSON)

SHA-256 para criptografia de senhas

Futuras Tecnologias:
Java Swing ou JavaFX (interface gráfica)

JavaMail API (e-mail de recuperação)

Autenticação via token, captcha ou OTP

----------------------------------------
📁 Estrutura de Pastas
sistema-bancario-java/

- Main.java

- Menu.java

- Banco.java

- ContaBancaria.java

- User.java

- UserService.java

- CriptografiaUtil.java

- TypeUser.java

- usuarios.json

interface/ (em breve)

   - LoginUI.java
 
   - UsuarioAdm.java

   - UsuarioComum.java






✍️ Autor
Desenvolvido por Oséias Barreto
📧 Contato: https://www.linkedin.com/in/barreto-oseias/

📌 Observações
Este é um projeto com fins educacionais, voltado para prática de conceitos de Java, orientação a objetos, segurança básica e estruturação de código limpo.
Fique à vontade para clonar, testar, sugerir melhorias ou contribuir com novas ideias! 🚀

