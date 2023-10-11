package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PageablePoiResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.swagger.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/poi/place")
public class PoiCrudController {

    private final PoiFacade poiFacade;

    @CreatePoiAPI
    @PostMapping
    public PoiResponse createPoi(@RequestBody @Valid PoiCreationRequest request) {
        return poiFacade.createPoi(request);
    }

    @GetPoiByIdAPI
    @GetMapping("/{poiId}")
    public PoiResponse getPoiById(@PathVariable(value = "poiId") Long poiId) {
        return poiFacade.getPoiById(poiId);
    }

    @UpdatePoiAPI
    @PutMapping("/{poiId}")
    public PoiResponse updatePoi(@PathVariable(value = "poiId") Long poiId, @RequestBody @Valid PoiUpdateRequest request) {
        return poiFacade.updatePoi(poiId, request);
    }

    @DeletePoiAPI
    @DeleteMapping("/{poiId}")
    public void deletePoi(@PathVariable(value = "poiId") Long poiId) {
        poiFacade.deletePoi(poiId);
    }

    @GetMapping
    @ResponseBody
    @GetPoiList
    public PageablePoiResponse getPoiList(@RequestParam(required = false) String name,
                                          @RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "10") int size) {
        return poiFacade.getPoiList(name, PageRequest.of(page, size));
    }
}
