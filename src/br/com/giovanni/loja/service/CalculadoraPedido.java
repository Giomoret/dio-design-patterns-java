package br.com.giovanni.loja.service;

import br.com.giovanni.loja.model.Pedido;
import br.com.giovanni.loja.strategy.FreteStrategy;
import java.math.BigDecimal;

public final class CalculadoraPedido {
    public BigDecimal calcularFrete(Pedido pedido, FreteStrategy estrategia) {
        return estrategia.calcular(pedido);
    }
    public BigDecimal calcularTotal(Pedido pedido, BigDecimal frete) {
        return pedido.getSubtotal().add(frete);
    }
}
