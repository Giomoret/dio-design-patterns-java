package br.com.giovanni.loja.strategy;

import br.com.giovanni.loja.config.ConfiguracaoLoja;
import br.com.giovanni.loja.model.Pedido;
import java.math.BigDecimal;

public final class FreteNormal implements FreteStrategy {
    public BigDecimal calcular(Pedido pedido) {
        return pedido.getSubtotal().compareTo(ConfiguracaoLoja.getInstancia().getLimiteFreteGratis()) >= 0
                ? BigDecimal.ZERO : new BigDecimal("15.00");
    }
    public String getDescricao() { return "Entrega normal"; }
}
