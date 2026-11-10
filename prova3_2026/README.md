# Resolção do Desafio SAEP: Sistema de Gestão para Clínica Veterinária

**Avaliação Prática de Desempenho dos Estudantes - SAEP**

**Tecnologias**: PHP 8.5, PostgreSQL, PDO (Prepared Statements)

**Arquitetura**: MVC Artesanal, Autoload PSR-4 via composer

---

## Apresentação do Projeto e Visão Geral

 Este documento apresenta a resolução integral para o Sistema de Gestão de Clinica Veterinária - SQCV

- Segurança LGPD
- Sessão com Tempo de Expiração
- Código Limpo e Principio SOLID
- Uso de Credenciais
- Prevenção Anti-Vulnerabilidade (XSS, SQL Injection, CSRF)

## Entregas

### Identificação do Projeto
- **Nome do sistema:** SisVet- sistema de gestão de clinica veterinária
- **Público alvo:** Veterinários, recepcionistas e gestores de clínica
- **Problema:** Eliminação dos controles manuais, duplicidade de registro, perda de agendamentos, risco de vazamentos de dados (LGPD)

### Requisitos Funcionais (RF)
- **RF-01:** Autenticação do Usuário com Sessão e Expiração => O sistema deve autenticar o usuário com email e senha(hash de criptografia). Exibir Alerta em Caso de Erro. Sessão deve expirar automaticamento após 15 minutos de inatividade.
- **RF-02:** Logout => encerramento da sessão , destruindo os dados do usuário (cookies).
- **RF-03:** Painel Principal (DASHBOARD) => tela inicial após o login, deve exibir o nome do usuário logado.
- **RF-04:** Cadastro de Tutores (LGPD): Cadastrar informações dos tutores criptografando dados sensíveis (CPF).
- **RF-05:** Busca e listagem de tutores => Campo de pesquisa por termo e listagem dos resultados
- **RF-06:** Edição dos tutores => permitir a alteração dis dados dos tutores
- **RF-07:** Cadastro dos pets com vínculo em tutor => cadastro de novo pet associado a um tutor.
- **RF-08:** Listagem dos pets => exibir a lista de pets cadastrados
- **RF-09:** Agendamento e listagem de agendamento => o sistema deve permitir cadastrar um agendamento e listar ordenadas por data de todos os agendamentos
- **RF-10:** Atualização de Agendamento => permitir alterar informações do agendamento

### Requisitos Não Funcionais (RNF)
- **RNF-01:** Arquitetura MVC
- **RNF-02:** Mapeamento via PSR-04
- **RNF-03:** Proteção e Criptografia
- **RNF-04:** Armazamento em Banco de Dados Relacional

### Diagrama de Entidades Relacionais (DER)

```mermaid

erDiagram
    title "DER - Sistema de Gestão de Clínica Veterinária"

    usuarios {
        int id PK
        varchar nome
        varchar email UNIQUE
        varchar senha_hash
        varchar perfil
        timestamp criado_em
    }

    tutores {
        int id PK
        varchar nome
        varchar cpf_criptografado
        varchar telefone
        varchar email
        timestamp criado_em
    }

    pets{
        int id PK
        int tutor_id FK
        varchar nome
        varchar especie
        varchar raca
        date data_nascimento
        timestamp cadastrado_em
    }

    agendamentos {
        int id PK
        int tutor_id FK
        int pet_id FK
        int usuario_id FK
        date data_agendamento
        time hora_agendamento
        varchar servico
        varchar status
        text observacao
        timestamp criado_em
    }

    usuarios ||--o{ agendamentos : "agenda/gerencia"
    tutores ||--o{ pets : "possui (1:N)"
    tutores ||--o{ agendamentos : "responsavel (1:N)"
    pets ||--o{ agendamentos : "paciente (1:N)"
```

## Banco de Dados - Script de Criação e População






