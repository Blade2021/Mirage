package rsystems.Mirage.tasks;

import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.objects.PlayerInfoRequest;


import java.io.IOException;

import static rsystems.Mirage.service.HandleRequest.requestInfo;

public class RaiderIORequestTask implements Runnable {

    @Override
    public void run() {

        if(!MirageApplication.queue.isEmpty()){

            System.out.printf("Queue not empty. Queue Size [ %d ]%n",MirageApplication.queue.size());
            PlayerInfoRequest request = MirageApplication.queue.entrySet().iterator().next().getValue();

            try {
                requestInfo(request.getChracterName(),request.getRealmName(),request.getDuid());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
