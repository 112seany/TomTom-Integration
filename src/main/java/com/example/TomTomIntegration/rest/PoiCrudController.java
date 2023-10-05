package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.swagger.CreatePoiAPI;
import com.example.TomTomIntegration.rest.swagger.DeletePoiAPI;
import com.example.TomTomIntegration.rest.swagger.GetPoiByIdAPI;
import com.example.TomTomIntegration.rest.swagger.UpdatePoiAPI;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<PoiResponse> getPoiList(@RequestParam(value = "name") String name) {
        return poiFacade.getPoiList(name);
    }
}
