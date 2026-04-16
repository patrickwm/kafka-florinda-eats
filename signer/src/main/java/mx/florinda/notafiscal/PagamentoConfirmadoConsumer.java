package mx.florinda.notafiscal;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PagamentoConfirmadoConsumer {

    @Inject
    Hash hash;

    @Incoming("pagamentosConfirmados")
    public Uni<Void> consume(PagamentoConfirmadoEvent event) {
        return Uni.createFrom()
                .item(() -> hash.geraHash(event.toString()))
                .invoke(hash -> System.out.println(hash))
                .replaceWithVoid();
    }

}
