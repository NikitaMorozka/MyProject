package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmptyRisksValidatorTest {
    @Mock
    private TravelCalculatePremiumRequest request;
    @Mock private ErrorValidationFactory errorsHandler;

    @InjectMocks
    EmptyRisksValidator emptyRisksValidatorTest;

    @Test
    @DisplayName("Тест: список рисков пуст")
    public void shouldReturnValidationErrorWhenSelectedRisksIsEmpty(){
        when(request.getSelectedRisks()).thenReturn(List.of());
        when(errorsHandler.processing("ERROR_CODE_7")).thenReturn(new ValidationError("ERROR_CODE_7","Must not be empty!"));

        Optional<ValidationError> validationError = emptyRisksValidatorTest.validation(request);

        assertEquals(validationError.get().getErrorCode(), "ERROR_CODE_7");
        assertEquals(validationError.get().getDescription(), "Must not be empty!");
    }

    @Test
    @DisplayName("Тест: в список рисков переданы значения")
    public void shouldPassValidationWhenSelectedRisksAreProvided(){
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));

        Optional<ValidationError> validationError = emptyRisksValidatorTest.validation(request);

        assertTrue(validationError.isEmpty());
    }

    @Test
    @DisplayName("Тест: список рисков имеет пустое значение")
    public void shouldReturnValidationErrorRisksIsEmpty(){
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL","","TRAVEL_LOSS_BAGGAGE"));
        when(errorsHandler.processing("ERROR_CODE_7")).thenReturn(new ValidationError("ERROR_CODE_7","Must not be empty!"));

        Optional<ValidationError> validationError = emptyRisksValidatorTest.validation(request);

        assertEquals(validationError.get().getErrorCode(), "ERROR_CODE_7");
        assertEquals(validationError.get().getDescription(), "Must not be empty!");
    }
}
