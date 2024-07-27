package vagapov.user_service.validation.impl;

import org.springframework.stereotype.Service;
import vagapov.user_service.validation.ValidationService;

import java.util.UUID;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validRequest(Object request) {

    }

    @Override
    public void validVersion(Integer version, UUID userGuid) {

    }
}
