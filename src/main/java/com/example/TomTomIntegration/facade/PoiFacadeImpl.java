package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.mapper.PoiMapper;
import com.example.TomTomIntegration.mapper.PoiTomTomMapper;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiSearchRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PageablePoiResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.response.PoiTomTomResponse;
import com.example.TomTomIntegration.service.PoiService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PoiFacadeImpl implements PoiFacade {

    private final PoiService poiService;

    private final PoiMapper poiMapper;

    private final PoiTomTomMapper poiTomTomMapper;

    @Override
    public PoiTomTomResponse getPoi(String place) {
        return poiTomTomMapper.mapToResponse(poiService.getPoi(place));
    }

    @Override
    public PoiResponse createPoi(PoiCreationRequest poiCreationRequest) {
        return poiMapper.mapToPoiResponse(poiService.createPoi(poiCreationRequest));
    }

    @Override
    public PoiResponse getPoiById(Long poiId) {
        return poiMapper.mapToPoiResponse(poiService.getPoiById(poiId));
    }

    @Override
    public void deletePoi(Long poiId) {
        poiService.deletePoi(poiId);
    }

    @Override
    public PoiResponse updatePoi(Long poiId, PoiUpdateRequest request) {
        return poiMapper.mapToPoiResponse(poiService.updatePoi(poiId, request));
    }

    @Override
    public PageablePoiResponse getPoiList(PoiSearchRequest searchRequest, PageRequest pageRequest) {
        List<PoiResponse> poiList = poiMapper.mapToPoiResponseList(poiService.getPoiList(searchRequest, pageRequest));

        return PageablePoiResponse.builder()
                .page(pageRequest.getPageNumber())
                .size(pageRequest.getPageSize())
                .numOfResults(poiList.size())
                .pois(poiList)
                .build();
    }
}
