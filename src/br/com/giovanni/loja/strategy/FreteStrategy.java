package br.com.giovanni.loja.strategy;

import br.com.giovanni.loja.model.Pedido;
import java.math.BigDecimal;

public interface FreteStrategy {
    BigDecimal calcular(Pedido pedido);
    String getDescricao();
}
