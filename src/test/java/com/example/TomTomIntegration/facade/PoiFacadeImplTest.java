package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.dto.PoiDTO;
import com.example.TomTomIntegration.gateway.resources.PoiTomTomDTO;
import com.example.TomTomIntegration.mapper.PoiMapper;
import com.example.TomTomIntegration.mapper.PoiTomTomMapper;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiSearchRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PageablePoiResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import com.example.TomTomIntegration.service.PoiService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static com.example.TomTomIntegration.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PoiFacadeImplTest {

    @Mock
    private PoiService poiService;

    @Mock
    private PoiMapper poiMapper;

    @Mock
    private PoiTomTomMapper poiTomTomMapper;

    @InjectMocks
    private PoiFacadeImpl poiFacade;

    private static PoiTomTomResponse poiTomTomResponse;

    private static PoiCreationRequest poiCreationRequest;

    private static PoiResponse poiResponse;

    private static PoiUpdateRequest updateRequest;

    private static PoiDTO poiDTO;

    private static PoiTomTomDTO poiTomTomDTO;

    private static PoiSearchRequest poiSearchRequest;

    @BeforeAll
    public static void setUp() {
        poiTomTomResponse = getPoiTomTomResponse();
        poiCreationRequest = getPoiCreationRequest();
        poiResponse = getPoiCreationResponse();
        updateRequest = getPoiUpdateRequest();
        poiDTO = getPoiDto();
        poiTomTomDTO = getPoiTomTomDto();
        poiSearchRequest = getPoiSearchRequest();
    }

    @Test
    public void getPoi_shouldReturnPoiResponse() {
        when(poiService.getPoi(POI)).thenReturn(poiTomTomDTO);
        when(poiTomTomMapper.mapToResponse(poiTomTomDTO)).thenReturn(poiTomTomResponse);

        PoiTomTomResponse actual = poiFacade.getPoi(POI);

        assertEquals(poiTomTomResponse, actual);
    }

    @Test
    public void createPoi_shouldReturnPoiCreationResponse() {
        when(poiService.createPoi(poiCreationRequest)).thenReturn(poiDTO);
        when(poiMapper.mapToPoiResponse(poiDTO)).thenReturn(poiResponse);

        PoiResponse actual = poiFacade.createPoi(poiCreationRequest);

        assertEquals(actual, poiResponse);
    }

    @Test
    public void getPoiById_shouldReturnPoiCreationResponse() {
        when(poiService.getPoiById(ID)).thenReturn(poiDTO);
        when(poiMapper.mapToPoiResponse(poiDTO)).thenReturn(poiResponse);

        PoiResponse actual = poiFacade.getPoiById(ID);

        assertEquals(actual, poiResponse);
    }

    @Test
    public void updatePoi_shouldReturnUpdatedPoiResponse() {
        when(poiService.updatePoi(ID, updateRequest)).thenReturn(poiDTO);
        when(poiMapper.mapToPoiResponse(poiDTO)).thenReturn(poiResponse);

        PoiResponse actual = poiFacade.updatePoi(ID, updateRequest);

        assertEquals(actual, poiResponse);
    }

    @Test
    public void deletePoi_shouldDeletePoiByGivenId() {
        poiFacade.deletePoi(ID);

        verify(poiService).deletePoi(ID);
    }

    @Test
    public void getPoiList_shouldReturnPageablePoiResponse() {
        when(poiService.getPoiList(poiSearchRequest, PageRequest.of(0, 1))).thenReturn(getPoiDtoList());
        when(poiMapper.mapToPoiResponseList(getPoiDtoList())).thenReturn(getPoiResponseList());

        PageablePoiResponse actual = poiFacade.getPoiList(poiSearchRequest, PageRequest.of(0, 1));

        assertEquals(actual, getPageablePoiResponse());
    }
}