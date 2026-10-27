package br.com.giovanni.loja.strategy;

import br.com.giovanni.loja.model.Pedido;
import java.math.BigDecimal;

public final class RetiradaNaLoja implements FreteStrategy {
    public BigDecimal calcular(Pedido pedido) { return BigDecimal.ZERO; }
    public String getDescricao() { return "Retirada na loja"; }
}
