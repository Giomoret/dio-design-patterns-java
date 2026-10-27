import br.com.giovanni.loja.config.ConfiguracaoLoja;
import br.com.giovanni.loja.model.Pedido;
import br.com.giovanni.loja.facade.PedidoFacade;
import br.com.giovanni.loja.strategy.*;
import java.math.BigDecimal;
public class Validacao {
    static int checks;
    static void check(boolean ok) { checks++; if (!ok) throw new AssertionError("Falha " + checks); }
    static void valor(String expected, BigDecimal actual) { check(new BigDecimal(expected).compareTo(actual) == 0); }
    static void invalido(Runnable action) { try { action.run(); } catch (IllegalArgumentException | NullPointerException e) { checks++; return; } throw new AssertionError("Entrada invalida aceita"); }
    public static void main(String[] args) {
        check(ConfiguracaoLoja.getInstancia() == ConfiguracaoLoja.getInstancia());
        FreteNormal normal = new FreteNormal();
        valor("15", normal.calcular(new Pedido("Cliente", new BigDecimal("199.99"))));
        valor("0", normal.calcular(new Pedido("Cliente", new BigDecimal("200"))));
        valor("0", normal.calcular(new Pedido("Cliente", new BigDecimal("200.01"))));
        valor("30", new FreteExpresso().calcular(new Pedido("Cliente", new BigDecimal("250"))));
        valor("0", new RetiradaNaLoja().calcular(new Pedido("Cliente", BigDecimal.ONE)));
        invalido(() -> new Pedido(" ", BigDecimal.ONE));
        invalido(() -> new Pedido(null, BigDecimal.ONE));
        invalido(() -> new Pedido("Cliente", BigDecimal.ZERO));
        invalido(() -> new Pedido("Cliente", new BigDecimal("-1")));
        invalido(() -> new Pedido("Cliente", null));
        invalido(() -> new PedidoFacade().finalizarPedido("Cliente", BigDecimal.ONE, null));
        String comprovante = new PedidoFacade().finalizarPedido(" Cliente ", new BigDecimal("150"), normal);
        check(comprovante.contains("Cliente: Cliente"));
        check(comprovante.contains("165,00"));
        System.out.println(checks + " verificacoes aprovadas.");
    }
}