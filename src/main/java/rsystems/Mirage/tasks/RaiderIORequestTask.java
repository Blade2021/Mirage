package rsystems.Mirage.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rsystems.Mirage.MirageApplication;
import rsystems.Mirage.domain.Player;
import rsystems.Mirage.domain.RaiderIOResponse;
import rsystems.Mirage.objects.PlayerInfoRequest;
import rsystems.Mirage.service.HandleRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static rsystems.Mirage.service.HandleRequest.requestInfo;

public class RaiderIORequestTask implements Runnable {

    @Override
    public void run() {

        if(!MirageApplication.queue.isEmpty()){

            System.out.println("Queue not empty");
            PlayerInfoRequest request = MirageApplication.queue.entrySet().iterator().next().getValue();
            System.out.println("Submitting request for " + request.getChracterName());

            requestInfo(request.getChracterName(),request.getRealmName(),request.getDuid());
        }
    }
}
