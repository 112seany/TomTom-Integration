package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.entity.PoiEntity;
import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
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

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoiCrudControllerTest {

    @Mock
    private PoiFacade poiFacade;

    @InjectMocks
    private PoiCrudController poiCrudController;

    private static PoiResponse poiCreationResponse;

    private static PoiCreationRequest poiCreationRequest;

    private static PoiUpdateRequest updateRequest;

    private static PoiEntity entity;

    @BeforeAll
    public static void setUp() {
        poiCreationRequest = getPoiCreationRequest();
        poiCreationResponse = getPoiCreationResponse();
        updateRequest = getPoiUpdateRequest();
        entity = getPoiEntity();
    }

    @Test
    public void createPoi_shouldReturnPoiCreationResponse() {
        when(poiFacade.createPoi(poiCreationRequest)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiCrudController.createPoi(poiCreationRequest);

        assertEquals(actual, poiCreationResponse);
    }

    @Test
    public void getPoiById_shouldReturnPoiResponse() {
        when(poiFacade.getPoiById(ID)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiCrudController.getPoiById(ID);

        assertEquals(actual, poiCreationResponse);
    }

    @Test
    public void updatePoi_shouldReturnUpdatedPoiResponse() {
        when(poiFacade.updatePoi(ID, updateRequest)).thenReturn(poiCreationResponse);

        PoiResponse actual = poiCrudController.updatePoi(ID, updateRequest);

        assertEquals(actual, poiCreationResponse);
    }

    @Test
    public void deletePoi_shouldDeletePoiByGivenId() {
        poiCrudController.deletePoi(ID);

        verify(poiFacade).deletePoi(ID);
    }

    @Test
    public void getPoiList_shouldReturnPageablePoiResponse() {
        when(poiFacade.getPoiList(TEST_NAME_FOR_GET_POI_LIST_METHOD, PageRequest.of(0, 1))).thenReturn(getPageablePoiResponse());

        PageablePoiResponse actual = poiCrudController.getPoiList(TEST_NAME_FOR_GET_POI_LIST_METHOD, 0, 1);

        assertEquals(actual, getPageablePoiResponse());
    }

    @Test
    public void getPoiList_shouldThrowExceptionIfPageSizeLessOrEqualsZero() {
        assertThrows(IllegalArgumentException.class, () -> poiCrudController.getPoiList(TEST_NAME_FOR_GET_POI_LIST_METHOD, 0, 0),
                WRONG_SIZE_MESSAGE);
    }

    @Test
    public void getPoiList_shouldThrowExceptionIfPageLessZero() {
        assertThrows(IllegalArgumentException.class, () -> poiCrudController.getPoiList(TEST_NAME_FOR_GET_POI_LIST_METHOD, -1, 10),
                WRONG_PAGE_INDEX_MESSAGE);
    }
}
