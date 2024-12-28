package gsm.gistory.domain.subs.service;

import gsm.gistory.domain.subs.dto.response.SubscribeResponse;

public interface SubscribeService {
    SubscribeResponse subscribe(String name, String authorization, boolean subClick);
}
