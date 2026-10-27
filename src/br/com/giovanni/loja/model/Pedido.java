package br.com.giovanni.loja.model;

import java.math.BigDecimal;

public final class Pedido {
    private final String cliente;
    private final BigDecimal subtotal;
    public Pedido(String cliente, BigDecimal subtotal) {
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o cliente.");
        }
        if (subtotal == null || subtotal.signum() <= 0) {
            throw new IllegalArgumentException("O subtotal deve ser positivo.");
        }
        this.cliente = cliente.trim();
        this.subtotal = subtotal;
    }
    public String getCliente() { return cliente; }
    public BigDecimal getSubtotal() { return subtotal; }
}
