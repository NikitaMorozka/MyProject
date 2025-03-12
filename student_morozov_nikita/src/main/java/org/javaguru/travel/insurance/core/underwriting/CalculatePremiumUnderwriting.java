package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

public interface CalculatePremiumUnderwriting {
    ListRisks calculateUnderwriting(TravelCalculatePremiumRequest travelCalculatePremiumRequest);
}
