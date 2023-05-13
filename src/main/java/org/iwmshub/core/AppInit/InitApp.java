package org.iwmshub.core.AppInit;
import org.iwmshub.core.database.InitDb;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;


@ApplicationScoped
public class InitApp  {
    void onStart(@Observes StartupEvent event) throws InterruptedException {
        // Your custom code to run on application startup
        InitDb db = new InitDb();
        db.start();
        System.out.println("Quarkus 1111application is starting");
    }   
}
