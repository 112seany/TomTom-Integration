package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiSearchRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PageablePoiResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
public class PoiCrudControllerTest {

    @Mock
    private PoiFacade poiFacade;

    @InjectMocks
    private PoiCrudController poiCrudController;

    private static PoiResponse poiResponse;

    private static PoiCreationRequest poiCreationRequest;

    private static PoiUpdateRequest updateRequest;

    private static PoiSearchRequest poiSearchRequest;

    @BeforeAll
    public static void setUp() {
        poiCreationRequest = getPoiCreationRequest();
        poiResponse = getPoiCreationResponse();
        updateRequest = getPoiUpdateRequest();
        poiSearchRequest = getPoiSearchRequest();
    }

    @Test
    public void createPoi_shouldReturnPoiCreationResponse() {
        when(poiFacade.createPoi(poiCreationRequest)).thenReturn(poiResponse);

        ResponseEntity<PoiResponse> actual = poiCrudController.createPoi(poiCreationRequest);

        assertEquals(actual, ResponseEntity.status(CREATED).body(poiResponse));
    }

    @Test
    public void getPoiById_shouldReturnPoiResponse() {
        when(poiFacade.getPoiById(ID)).thenReturn(poiResponse);

        ResponseEntity<PoiResponse> actual = poiCrudController.getPoiById(ID);

        assertEquals(actual, ResponseEntity.ok(poiResponse));
    }

    @Test
    public void updatePoi_shouldReturnUpdatedPoiResponse() {
        when(poiFacade.updatePoi(ID, updateRequest)).thenReturn(poiResponse);

        ResponseEntity<PoiResponse> actual = poiCrudController.updatePoi(ID, updateRequest);

        assertEquals(actual, ResponseEntity.ok(poiResponse));
    }

    @Test
    public void deletePoi_shouldDeletePoiByGivenId() {
        poiCrudController.deletePoi(ID);

        verify(poiFacade).deletePoi(ID);
    }

    @Test
    public void getPoiList_shouldReturnPageablePoiResponse() {
        when(poiFacade.getPoiList(poiSearchRequest, PageRequest.of(0, 1))).thenReturn(getPageablePoiResponse());

        ResponseEntity<PageablePoiResponse> actual = poiCrudController.getPoiList(poiSearchRequest, 0, 1);

        assertEquals(actual, ResponseEntity.ok(getPageablePoiResponse()));
    }

    @Test
    public void getPoiList_shouldThrowExceptionIfPageSizeLessOrEqualsZero() {
        assertThrows(IllegalArgumentException.class, () -> poiCrudController.getPoiList(poiSearchRequest, 0, 0),
                WRONG_SIZE_MESSAGE);
    }

    @Test
    public void getPoiList_shouldThrowExceptionIfPageLessZero() {
        assertThrows(IllegalArgumentException.class, () -> poiCrudController.getPoiList(poiSearchRequest, -1, 10),
                WRONG_PAGE_INDEX_MESSAGE);
    }
}
