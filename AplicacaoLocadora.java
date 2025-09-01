import java.io.*;
import java.util.*;

/**
 * Aplicação principal para gerenciamento de dados de carros de locadora.
 * Esta classe lê dados de um arquivo texto, processa as informações e
 * extrai estatísticas significativas sobre a frota de veículos.
 * 
 * Funcionalidades implementadas:
 * - Leitura de arquivo de dados
 * - Cálculo de estatísticas (mín, máx, média de preços)
 * - Ordenação por diferentes critérios
 * - Filtragem por disponibilidade e categoria
 * - Análise de distribuição da frota
 * 
 * @author João Pedro Madruga e Wellington Borges
 * @version 1.0.1
 * @since 2025-08-25
 */
public class AplicacaoLocadora {
    
    private static ArrayList<Carro> carros = new ArrayList<>();
    
    /**
     * Método principal da aplicação.
     * Recebe o nome do arquivo como argumento de linha de comando.
     * 
     * @param args Array de argumentos, onde args[0] deve ser o nome do arquivo
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java AplicacaoLocadora <nome_do_arquivo>");
            System.out.println("Exemplo: java AplicacaoLocadora carros.txt");
            return;
        }
        
        String nomeArquivo = args[0];
        
        try {
            carregarDados(nomeArquivo);
            exibirEstatisticas();
            
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo '" + nomeArquivo + "' não encontrado.");
            System.err.println("Verifique se o arquivo existe e está no diretório correto.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro: Formato de número inválido no arquivo.");
            System.err.println("Verifique se os preços e anos estão no formato correto.");
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Carrega dados do arquivo especificado para a lista de carros.
     * Formato esperado: tipo;modelo;grupo;precoDiaria;categoria;disponivel;anoFabricacao
     * 
     * @param nomeArquivo Nome do arquivo a ser lido
     * @throws IOException Se houver erro na leitura do arquivo
     */
    private static void carregarDados(String nomeArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
        String linha;
        int numeroLinha = 0;
        
        System.out.println("=== CARREGANDO DADOS DO ARQUIVO: " + nomeArquivo + " ===\n");
        
        while ((linha = reader.readLine()) != null) {
            numeroLinha++;
            linha = linha.trim();
            
            // Ignorar linhas vazias ou comentários
            if (linha.isEmpty() || linha.startsWith("#")) {
                continue;
            }
            
            try {
                String[] dados = linha.split(";");
                
                if (dados.length != 7) {
                    System.err.println("Aviso: Linha " + numeroLinha + 
                                     " possui formato inválido e será ignorada.");
                    continue;
                }
                
                String tipo = dados[0].trim();
                String modelo = dados[1].trim();
                String grupo = dados[2].trim();
                double precoDiaria = Double.parseDouble(dados[3].trim());
                String categoria = dados[4].trim();
                boolean disponivel = Boolean.parseBoolean(dados[5].trim());
                int anoFabricacao = Integer.parseInt(dados[6].trim());
                
                Carro carro = new Carro(tipo, modelo, grupo, precoDiaria, 
                                      categoria, disponivel, anoFabricacao);
                carros.add(carro);
                
            } catch (NumberFormatException e) {
                System.err.println("Erro na linha " + numeroLinha + 
                                 ": Formato numérico inválido - " + linha);
            }
        }
        
        reader.close();
        System.out.println(carros.size() + " carros carregados com sucesso!\n");
    }
    
    /**
     * Exibe estatísticas detalhadas sobre a frota de carros.
     * Inclui análises de preços, distribuição por categoria, 
     * disponibilidade e outras métricas relevantes.
     */
    private static void exibirEstatisticas() {
        if (carros.isEmpty()) {
            System.out.println("Nenhum dado válido foi carregado.");
            return;
        }
        
        System.out.println("===============================================");
        System.out.println("         RELATÓRIO DA FROTA DE VEÍCULOS");
        System.out.println("===============================================\n");
        
        // 1. Estatísticas de Preços
        exibirEstatisticasPrecos();
        
        // 2. Distribuição por Categoria
        exibirDistribuicaoPorCategoria();
        
        // 3. Análise de Disponibilidade
        exibirAnaliseDisponibilidade();
        
        // 4. Análise de Idade da Frota
        exibirAnaliseIdadeFrota();
        
        // 5. Top 5 carros mais caros
        exibirTop5MaisCaros();
        
        // 6. Top 5 carros mais baratos disponíveis
        exibirTop5MaisBaratos();
        
        // 7. Distribuição por Tipo de Veículo
        exibirDistribuicaoPorTipo();
        
        System.out.println("===============================================");
        System.out.println("              FIM DO RELATÓRIO");
        System.out.println("===============================================");
    }
    
    /**
     * Exibe estatísticas relacionadas aos preços das diárias.
     */
    private static void exibirEstatisticasPrecos() {
        DoubleSummaryStatistics stats = carros.stream()
                .mapToDouble(Carro::getPrecoDiaria)
                .summaryStatistics();
        
        System.out.println("ESTATÍSTICAS DE PREÇOS DAS DIÁRIAS");
        System.out.println("─────────────────────────────────────");
        System.out.printf("Preço Mínimo: R$ %.2f%n", stats.getMin());
        System.out.printf("Preço Máximo: R$ %.2f%n", stats.getMax());
        System.out.printf("Preço Médio: R$ %.2f%n", stats.getAverage());
        System.out.printf("Total de Veículos: %d%n", stats.getCount());
        System.out.printf("Faturamento Potencial Diário: R$ %.2f%n\n", stats.getSum());
    }
    
    /**
     * Exibe a distribuição de veículos por categoria.
     */
    private static void exibirDistribuicaoPorCategoria() {
        Map<String, Long> distribuicao = new HashMap<>();
        
        for (Carro carro : carros) {
            distribuicao.merge(carro.getCategoria(), 1L, Long::sum);
        }
        
        System.out.println("DISTRIBUIÇÃO POR CATEGORIA");
        System.out.println("─────────────────────────────");
        distribuicao.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> {
                    double percentual = (entry.getValue() * 100.0) / carros.size();
                    System.out.printf("%-15s: %2d veículos (%.1f%%)%n", 
                                    entry.getKey(), entry.getValue(), percentual);
                });
        System.out.println();
    }
    
    /**
     * Exibe análise de disponibilidade dos veículos.
     */
    private static void exibirAnaliseDisponibilidade() {
        long disponiveis = carros.stream().mapToLong(c -> c.isDisponivel() ? 1 : 0).sum();
        long indisponiveis = carros.size() - disponiveis;
        
        System.out.println("ANÁLISE DE DISPONIBILIDADE");
        System.out.println("─────────────────────────────");
        System.out.printf("Veículos Disponíveis: %d (%.1f%%)%n", 
                         disponiveis, (disponiveis * 100.0) / carros.size());
        System.out.printf("Veículos Indisponíveis: %d (%.1f%%)%n", 
                         indisponiveis, (indisponiveis * 100.0) / carros.size());
        
        if (disponiveis > 0) {
            double receitaPotencial = carros.stream()
                    .filter(Carro::isDisponivel)
                    .mapToDouble(Carro::getPrecoDiaria)
                    .sum();
            System.out.printf("Receita Potencial (Disponíveis): R$ %.2f/dia%n", receitaPotencial);
        }
        System.out.println();
    }
    
    /**
     * Exibe os 5 carros mais caros da frota.
     */
    private static void exibirTop5MaisCaros() {
        System.out.println("TOP 5 CARROS MAIS CAROS");
        System.out.println("──────────────────────────");
        
        carros.stream()
                .sorted((c1, c2) -> Double.compare(c2.getPrecoDiaria(), c1.getPrecoDiaria()))
                .limit(5)
                .forEach(carro -> {
                    System.out.printf("R$ %6.2f - %s %s (%s)%n",
                                    carro.getPrecoDiaria(),
                                    carro.getCategoria(),
                                    carro.getTipo(),
                                    carro.getGrupo());
                });
        System.out.println();
    }
    
    /**
     * Exibe os 5 carros mais baratos que estão disponíveis.
     */
    private static void exibirTop5MaisBaratos() {
        System.out.println("TOP 5 CARROS MAIS BARATOS (DISPONÍVEIS)");
        System.out.println("──────────────────────────────────────────");
        
        carros.stream()
                .filter(Carro::isDisponivel)
                .sorted(Comparator.comparingDouble(Carro::getPrecoDiaria))
                .limit(5)
                .forEach(carro -> {
                    System.out.printf("R$ %6.2f - %s %s (%s)%n",
                                    carro.getPrecoDiaria(),
                                    carro.getCategoria(),
                                    carro.getTipo(),
                                    carro.getGrupo());
                });
        System.out.println();
    }
}