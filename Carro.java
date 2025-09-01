/**
 * Classe que representa um carro disponível para locação.
 * Esta classe contém informações sobre veículos de uma locadora,
 * incluindo dados sobre tipo, modelo, grupo e preços.
 * 
 * @author João Pedro Madruga e Wellington Borges
 * @version 1.0.1
 * @since 2025-08-25
 */
public class Carro {
    private String tipo;
    private String modelo;
    private String grupo;
    private double precoDiaria;
    private String categoria;
    private boolean disponivel;
    private int anoFabricacao;

    /**
     * Construtor completo da classe Carro.
     * 
     * @param tipo Tipo do carro (Sedan, Hatch, SUV, etc.)
     * @param modelo Modelo específico do carro
     * @param grupo Grupo de classificação (AT, AM, SM, SG, etc.)
     * @param precoDiaria Preço da diária de locação em reais
     * @param categoria Categoria do carro (Executivo, Compacto, Manual, Luxo)
     * @param disponivel Status de disponibilidade para locação
     * @param anoFabricacao Ano de fabricação do veículo
     */
    public Carro(String tipo, String modelo, String grupo, double precoDiaria, 
                 String categoria, boolean disponivel, int anoFabricacao) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.grupo = grupo;
        this.precoDiaria = precoDiaria;
        this.categoria = categoria;
        this.disponivel = disponivel;
        this.anoFabricacao = anoFabricacao;
    }

    // Getters

    /**
     * Obtém o tipo do carro.
     * @return String representando o tipo do carro
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtém o modelo do carro.
     * @return String representando o modelo do carro
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Obtém o grupo de classificação do carro.
     * @return String representando o grupo do carro
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * Obtém o preço da diária de locação.
     * @return double representando o preço da diária
     */
    public double getPrecoDiaria() {
        return precoDiaria;
    }

    /**
     * Obtém a categoria do carro.
     * @return String representando a categoria do carro
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Verifica se o carro está disponível para locação.
     * @return boolean indicando disponibilidade
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Obtém o ano de fabricação do carro.
     * @return int representando o ano de fabricação
     */
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    // Setters

    /**
     * Define o tipo do carro.
     * @param tipo Novo tipo do carro
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Define o modelo do carro.
     * @param modelo Novo modelo do carro
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Define o grupo de classificação do carro.
     * @param grupo Novo grupo do carro
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * Define o preço da diária de locação.
     * @param precoDiaria Novo preço da diária
     */
    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    /**
     * Define a categoria do carro.
     * @param categoria Nova categoria do carro
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Define a disponibilidade do carro para locação.
     * @param disponivel Novo status de disponibilidade
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Define o ano de fabricação do carro.
     * @param anoFabricacao Novo ano de fabricação
     */
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    /**
     * Calcula o preço total para um período de locação.
     * @param dias Número de dias de locação
     * @return double representando o preço total
     */
    public double calcularPrecoTotal(int dias) {
        return precoDiaria * dias;
    }

    /**
     * Verifica se o carro é considerado novo (fabricado nos últimos 3 anos).
     * @return boolean indicando se é um carro novo
     */
    public boolean isCarroNovo() {
        return (2025 - anoFabricacao) <= 3;
    }

    /**
     * Obtém uma descrição resumida do carro.
     * @return String com descrição do carro
     */
    public String getDescricaoResumida() {
        return String.format("%s %s (%s)", categoria, tipo, grupo);
    }

    /**
     * Representação textual completa do objeto Carro.
     * @return String formatada com todas as informações do carro
     */
    @Override
    public String toString() {
        return String.format("Carro{tipo='%s', modelo='%s', grupo='%s', " +
                           "precoDiaria=R$ %.2f, categoria='%s', " +
                           "disponivel=%s, anoFabricacao=%d}",
                           tipo, modelo, grupo, precoDiaria, categoria, 
                           disponivel ? "Sim" : "Não", anoFabricacao);
    }

    /**
     * Compara se dois objetos Carro são iguais baseado no modelo e grupo.
     * @param obj Objeto a ser comparado
     * @return boolean indicando se os objetos são iguais
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Carro carro = (Carro) obj;
        return modelo.equals(carro.modelo) && grupo.equals(carro.grupo);
    }

    /**
     * Gera código hash para o objeto baseado no modelo e grupo.
     * @return int representando o código hash
     */
    @Override
    public int hashCode() {
        return modelo.hashCode() + grupo.hashCode();
    }
}