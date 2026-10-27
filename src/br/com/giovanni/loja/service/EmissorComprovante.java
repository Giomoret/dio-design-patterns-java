package br.com.giovanni.loja.service;

import br.com.giovanni.loja.config.ConfiguracaoLoja;
import br.com.giovanni.loja.model.Pedido;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public final class EmissorComprovante {
    public String emitir(Pedido pedido, String modalidade, BigDecimal frete, BigDecimal total) {
        NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        return ConfiguracaoLoja.getInstancia().getNome()
                + "\nCliente: " + pedido.getCliente()
                + "\nModalidade: " + modalidade
                + "\nSubtotal: " + moeda.format(pedido.getSubtotal())
                + "\nFrete: " + moeda.format(frete)
                + "\nTotal: " + moeda.format(total);
    }
}
