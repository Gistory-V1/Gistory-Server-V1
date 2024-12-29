package gsm.gistory.domain.subs.controller;

import gsm.gistory.domain.subs.dto.request.SubscribeRequest;
import gsm.gistory.domain.subs.dto.response.SubscribeResponse;
import gsm.gistory.domain.subs.dto.response.UnsubscribeResponse;
import gsm.gistory.domain.subs.service.SubscribeService;
import gsm.gistory.domain.subs.service.UnsubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subs")
public class SubscriptionController {

    private final SubscribeService subscribeService;
    private final UnsubscribeService unsubscribeService;

    @PostMapping
    public ResponseEntity<SubscribeResponse> subscribe(
            @RequestHeader("Authorization") String authorization,
            @RequestBody SubscribeRequest request) {

        SubscribeResponse response = subscribeService.subscribe(
                request.getName(),
                authorization,
                request.isSubClick()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<UnsubscribeResponse> unsubscribe(
            @RequestHeader("Authorization") String authorization,
            @RequestBody SubscribeRequest request) {

        UnsubscribeResponse response = unsubscribeService.unsubscribe(
                request.getName(),
                authorization,
                request.isSubClick()
        );

        return ResponseEntity.ok(response);
    }
}
