package vagapov.user_service.validation;

import java.util.UUID;

public interface ValidationService {
    void validRequest(Object value);

    void validVersion(Integer version, UUID userGuid);
}
