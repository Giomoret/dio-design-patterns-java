package br.com.giovanni.loja.facade;

import br.com.giovanni.loja.model.Pedido;
import br.com.giovanni.loja.service.CalculadoraPedido;
import br.com.giovanni.loja.service.EmissorComprovante;
import br.com.giovanni.loja.strategy.FreteStrategy;
import java.math.BigDecimal;
import java.util.Objects;

/** Facade: reúne criação, cálculo e emissão em uma operação simples. */
public final class PedidoFacade {
    private final CalculadoraPedido calculadora = new CalculadoraPedido();
    private final EmissorComprovante emissor = new EmissorComprovante();
    public String finalizarPedido(String cliente, BigDecimal subtotal, FreteStrategy estrategia) {
        Objects.requireNonNull(estrategia, "Escolha uma modalidade de frete.");
        Pedido pedido = new Pedido(cliente, subtotal);
        BigDecimal frete = calculadora.calcularFrete(pedido, estrategia);
        BigDecimal total = calculadora.calcularTotal(pedido, frete);
        return emissor.emitir(pedido, estrategia.getDescricao(), frete, total);
    }
}
