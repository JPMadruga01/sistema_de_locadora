# sistema_de_locadora

Sistema de Locadora de Carros

Sistema de gerenciamento de dados para locadora de carros desenvolvido em Java, aplicando conceitos fundamentais de Programação Orientada a Objetos.

Índice

Sobre o Projeto
Funcionalidades
Tecnologias
Estrutura do Projeto
Como Executar
Uso
Arquivos de Dados
Estatísticas Geradas
Diagrama UML
Autores
Licença

Sobre o Projeto
Este projeto foi desenvolvido como parte do exercício E1 da disciplina Programação Orientada a Objetos (2025/II) da PUCRS. O sistema processa dados de uma frota de veículos de locadora e gera estatísticas relevantes para o negócio.

Objetivos

Aplicar conceitos de POO em Java
Trabalhar com Collections (ArrayList)
Implementar leitura e processamento de arquivos
Gerar estatísticas significativas sobre dados
Documentar código com JavaDoc

Funcionalidades
Análises Implementadas

Estatísticas de Preços: Valores mínimo, máximo, médio e faturamento potencial
Distribuição por Categoria: Percentual de veículos por categoria comercial
Análise de Disponibilidade: Status da frota e receita potencial
Análise da Idade: Controle de renovação da frota
Rankings: Top 5 carros mais caros e mais baratos
Distribuição por Tipo: Composição da frota por tipo de veículo

Recursos Técnicos

Leitura robusta de arquivos com tratamento de erros
Processamento com Java Streams API
Validação de dados de entrada
Relatórios formatados e informativos
Geração de arquivo JAR executável

Tecnologias

Java 8+
Collections Framework
Stream API
JavaDoc
Maven (opcional para build)

Estrutura do Projeto
projeto-locadora/
├── src/
│   ├── Carro.java                 # Classe modelo do veículo
│   └── AplicacaoLocadora.java     # Aplicação principal
├── data/
│   ├── carros.txt                 # Primeiro conjunto de dados
│   └── carros2.txt                # Segundo conjunto de dados
├── docs/
│   ├── diagrama-uml.png           # Diagrama de classes UML
│   └── relatorio.md               # Relatório detalhado
├── MANIFEST.MF                    # Manifest para JAR executável
└── README.md                      # Este arquivo
Como Executar
Pré-requisitos

Java JDK 8 ou superior instalado
Terminal/Prompt de comando

Compilação
bash# Clone ou baixe o projeto
git clone <url-do-repositorio>
cd projeto-locadora

# Compile os arquivos Java
javac *.java
Execução Simples
bash# Execute com o primeiro arquivo de dados
java AplicacaoLocadora carros.txt

# Execute com o segundo arquivo de dados
java AplicacaoLocadora carros2.txt
Criação e Execução do JAR
bash# Crie o arquivo JAR
jar cfm locadora.jar MANIFEST.MF *.class

# Execute o JAR
java -jar locadora.jar carros.txt
Uso
Formato da Linha de Comando
bashjava AplicacaoLocadora <nome_do_arquivo>
Exemplo de Execução
bash$ java AplicacaoLocadora carros.txt

=== CARREGANDO DADOS DO ARQUIVO: carros.txt ===

15 carros carregados com sucesso!

===============================================
         RELATÓRIO DA FROTA DE VEÍCULOS
===============================================

ESTATÍSTICAS DE PREÇOS DAS DIÁRIAS
─────────────────────────────────────
Preço Mínimo: R$ 88,75
Preço Máximo: R$ 295,50
Preço Médio: R$ 165,43
Total de Veículos: 15
Faturamento Potencial Diário: R$ 2481,49

...
Arquivos de Dados
Formato
tipo;modelo;grupo;precoDiaria;categoria;disponivel;anoFabricacao
Exemplo
Sedan;Toyota Corolla;AT;158.67;Executivo;true;2023
Hatch;Fiat Mobi;AM;92.69;Compacto;true;2022
SUV;Renault Duster;SM;132.78;Manual;true;2021
Conjuntos Disponíveis
carros.txt

15 registros de veículos tradicionais
Categorias: Executivo, Compacto, Manual, Luxo, Utilitário
Faixa de preços: R$ 88,75 - R$ 295,50

carros2.txt

15 registros com perfil diferenciado
Categorias: Premium, Econômico, Intermediário, Esportivo, Familiar
Faixa de preços: R$ 75,50 - R$ 680,00

📈 Estatísticas Geradas
AnáliseDescriçãoUtilidadePreçosMin, máx, média, faturamentoOtimização de precificaçãoCategoriaDistribuição percentualPlanejamento da frotaDisponibilidadeStatus e receita potencialControle operacionalIdade da FrotaAnálise temporal dos veículosRenovação estratégicaRankingsTop carros por preçoMarketing e recomendaçõesTiposComposição por tipoBalanceamento da frota
Diagrama UML
O projeto inclui um diagrama de classes UML que mostra:

Classe Carro: Atributos e métodos do modelo de dados
Classe AplicacaoLocadora: Lógica de processamento e estatísticas
Relacionamentos: Dependência e associação entre as classes

AplicacaoLocadora ----uses----> Carro
AplicacaoLocadora --manages--> 0..* Carro
Conceitos de POO Aplicados
Encapsulamento

Atributos privados com acesso controlado
Métodos getters e setters
Validação de dados

Abstração

Classe Carro representa conceito real
Métodos de negócio (calcularPrecoTotal, isCarroNovo)
Interface limpa para uso

Polimorfismo

Sobrescrita de toString(), equals(), hashCode()
Sobrecarga de construtores

Boas Práticas

Documentação JavaDoc completa
Tratamento robusto de exceções
Uso eficiente de Collections
Código limpo e legível

Tratamento de Erros
O sistema trata diversos cenários de erro:

Arquivo não encontrado
Formato de dados inválido
Números mal formatados
Linhas com estrutura incorreta
Arquivos vazios

Dependências
O projeto usa apenas bibliotecas padrão do Java:

java.io.* - Leitura de arquivos
java.util.* - Collections e utilitários
java.util.stream.* - Processamento funcional

Autores

João Pedro Madruga
Wellington Borges

Disciplina: Programação Orientada a Objetos
Instituição: PUCRS/Escola Politécnica
Período: 2025/II


