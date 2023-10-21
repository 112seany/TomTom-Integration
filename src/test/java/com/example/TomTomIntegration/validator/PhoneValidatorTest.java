package com.example.TomTomIntegration.validator;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.example.TomTomIntegration.helper.TestHelper.getPoiCreationRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneValidatorTest {

    private Validator tested;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        tested = factory.getValidator();
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testPhoneValidator(String phone, boolean isValid) {
        PoiCreationRequest request = getPoiCreationRequest();
        request.setPhone(phone);

        Set<ConstraintViolation<PoiCreationRequest>> violations = tested.validate(request);
        assertEquals(violations.isEmpty(), isValid);
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("+1 503-227-0080", true),
                Arguments.of("+AC 7 4060 8306", false),
                Arguments.of(null, false)
        );
    }
}
