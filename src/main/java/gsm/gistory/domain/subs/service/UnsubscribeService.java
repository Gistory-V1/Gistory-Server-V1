package gsm.gistory.domain.subs.service;

import gsm.gistory.domain.subs.dto.response.UnsubscribeResponse;

public interface UnsubscribeService {
    UnsubscribeResponse unsubscribe(String name, String authorization, boolean subClick);
}
