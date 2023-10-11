package com.example.TomTomIntegration.facade;

import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
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

    @Override
    public PoiTomTomResponse getPoi(String place) {
        return poiService.getPoi(place);
    }

    @Override
    public PoiResponse createPoi(PoiCreationRequest poiCreationRequest) {
        return poiService.createPoi(poiCreationRequest);
    }

    @Override
    public PoiResponse getPoiById(Long poiId) {
        return poiService.getPoiById(poiId);
    }

    @Override
    public void deletePoi(Long poiId) {
        poiService.deletePoi(poiId);
    }

    @Override
    public PoiResponse updatePoi(Long poiId, PoiUpdateRequest request) {
        return poiService.updatePoi(poiId, request);
    }

    @Override
    public PageablePoiResponse getPoiList(String name, PageRequest pageRequest) {
        List<PoiResponse> poiList = poiService.getPoiList(name, pageRequest);

        return PageablePoiResponse.builder()
                .page(pageRequest.getPageNumber())
                .size(pageRequest.getPageSize())
                .numOfResults(poiList.size())
                .pois(poiList)
                .build();
    }
}
