package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;

public interface ValidationList {
    List<ValidationError> validationList(TravelCalculatePremiumRequestV1 request);
}
