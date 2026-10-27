package br.com.giovanni.loja.config;

import java.math.BigDecimal;

/** Singleton: configuração única e imutável, criada com segurança pela JVM. */
public final class ConfiguracaoLoja {
    private ConfiguracaoLoja() {}
    private static class Holder {
        private static final ConfiguracaoLoja INSTANCIA = new ConfiguracaoLoja();
    }
    public static ConfiguracaoLoja getInstancia() { return Holder.INSTANCIA; }
    public String getNome() { return "Loja do Giovanni"; }
    public BigDecimal getLimiteFreteGratis() { return new BigDecimal("200.00"); }
}
