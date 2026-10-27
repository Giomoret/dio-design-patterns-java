# Design Patterns em Java: sistema de pedidos

Projeto desenvolvido para o desafio de Padrões de Projeto da DIO.

**Autor:** Giovanni Alvarenga Moretto

## Objetivo

Aplicar Singleton, Strategy e Facade em uma simulação simples de pedidos. O programa calcula o frete e emite um comprovante no terminal. Foi criado em Java puro, sem bibliotecas externas.

## Padrões aplicados

| Padrão | Implementação | Finalidade |
| --- | --- | --- |
| Singleton | ConfiguracaoLoja | Compartilhar uma instância imutável com nome da loja e limite de frete grátis. O holder estático cria a instância apenas quando solicitada. |
| Strategy | FreteStrategy, FreteNormal, FreteExpresso e RetiradaNaLoja | Trocar a regra de entrega por meio de uma interface. |
| Facade | PedidoFacade | Reunir validação do pedido, cálculo e emissão do comprovante em uma operação. |

A fachada utiliza CalculadoraPedido e EmissorComprovante. Assim, quem chama finalizarPedido não precisa coordenar essas etapas.

## Regras

- O cliente deve ser informado e o subtotal deve ser positivo.
- Entrega normal: R$ 15,00; grátis a partir de R$ 200,00.
- Entrega expressa: R$ 30,00, independentemente do subtotal.
- Retirada na loja: grátis.
- Valores monetários usam BigDecimal para evitar imprecisões de ponto flutuante.

## Como executar

Instale um **JDK 11 ou superior**, com JAVA_HOME apontando para a pasta do JDK ou java e javac disponíveis no PATH. Não é necessário Maven ou Spring.

No Windows, abra o PowerShell na pasta do projeto e execute:

~~~powershell
.\executar.ps1
~~~

Se a política local impedir scripts, compile diretamente no PowerShell:

~~~powershell
New-Item -ItemType Directory -Force -Path out
$fontes = @(Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
javac -encoding UTF-8 -d out $fontes
java -cp out br.com.giovanni.loja.Main
~~~

No Linux ou macOS:

~~~sh
mkdir -p out
find src -name '*.java' > fontes.txt
javac -encoding UTF-8 -d out @fontes.txt
java -cp out br.com.giovanni.loja.Main
~~~

Também é possível abrir a pasta em uma IDE Java e executar Main.java.

## Resultados esperados

O exemplo executa quatro pedidos:

| Subtotal | Modalidade | Frete | Total |
| --- | --- | --- | --- |
| R$ 150,00 | Normal | R$ 15,00 | R$ 165,00 |
| R$ 150,00 | Expressa | R$ 30,00 | R$ 180,00 |
| R$ 150,00 | Retirada | R$ 0,00 | R$ 150,00 |
| R$ 200,00 | Normal | R$ 0,00 | R$ 200,00 |

O espaçamento da moeda no terminal pode variar conforme o JDK.

## Como estender

Para adicionar uma entrega agendada, crie uma classe que implemente FreteStrategy e passe sua instância à fachada. Não é necessário alterar a lógica da fachada ou das outras modalidades. Isso exemplifica o princípio aberto/fechado (OCP).

## Organização

- config: configuração compartilhada.
- model: pedido e validações.
- strategy: interface e regras de frete.
- service: cálculo e emissão de comprovante.
- facade: coordenação da operação.
- Main.java: demonstração de uso.

## Escopo

É uma simulação didática: não cobra pagamentos, não persiste pedidos e não possui interface web. O método finalizarPedido gera o comprovante em memória.

## Referências para estudo

- Desafio da DIO: Explorando Padrões de Projetos na Prática com Java.
- [Laboratório oficial: Java puro](https://github.com/digitalinnovationone/lab-padroes-projeto-java)
- [Laboratório oficial: Spring](https://github.com/digitalinnovationone/lab-padroes-projeto-spring)
- Java Design Patterns: Elements of Reusable Object-Oriented Software, de Gamma, Helm, Johnson e Vlissides (referência conceitual).

## Entrega na DIO

1. Crie um repositório no GitHub chamado dio-design-patterns-java.
2. Envie o conteúdo desta pasta, incluindo src, README.md, .gitignore e executar.ps1.
3. Copie a URL do repositório e envie no campo do desafio da DIO.

Descrição sugerida para o repositório: Sistema de pedidos em Java puro com Singleton, Strategy e Facade, desenvolvido para o desafio de Design Patterns da DIO.

## Validação

Compilado com JDK 25 usando `--release 11` para verificar compatibilidade com Java 11.
As 14 verificações cobrem identidade do Singleton, frete abaixo/no/acima do limite,
frete expresso, retirada, entradas inválidas e integração da fachada com o comprovante.

Depois de executar o projeto, rode no PowerShell (java e javac no PATH):

```powershell
javac --release 11 -encoding UTF-8 -cp out -d out tests/Validacao.java
java -cp out Validacao
```

Resultado esperado: `14 verificacoes aprovadas.`
