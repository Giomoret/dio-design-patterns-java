package br.com.giovanni.loja.strategy;

import br.com.giovanni.loja.model.Pedido;
import java.math.BigDecimal;

public final class FreteExpresso implements FreteStrategy {
    public BigDecimal calcular(Pedido pedido) { return new BigDecimal("30.00"); }
    public String getDescricao() { return "Entrega expressa"; }
}
