package br.com.giovanni.loja;

import br.com.giovanni.loja.facade.PedidoFacade;
import br.com.giovanni.loja.strategy.*;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        PedidoFacade loja = new PedidoFacade();
        FreteStrategy[] modalidades = { new FreteNormal(), new FreteExpresso(), new RetiradaNaLoja() };
        for (FreteStrategy modalidade : modalidades) {
            System.out.println(loja.finalizarPedido("Giovanni", new BigDecimal("150.00"), modalidade));
            System.out.println();
        }
        System.out.println("Pedido que atinge o limite de frete normal gratis:");
        System.out.println(loja.finalizarPedido("Giovanni", new BigDecimal("200.00"), new FreteNormal()));
    }
}
