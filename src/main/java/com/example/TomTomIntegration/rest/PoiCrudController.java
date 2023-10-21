package com.example.TomTomIntegration.rest;

import com.example.TomTomIntegration.facade.PoiFacade;
import com.example.TomTomIntegration.rest.request.PoiCreationRequest;
import com.example.TomTomIntegration.rest.request.PoiSearchRequest;
import com.example.TomTomIntegration.rest.request.PoiUpdateRequest;
import com.example.TomTomIntegration.rest.response.PageablePoiResponse;
import com.example.TomTomIntegration.rest.response.PoiResponse;
import com.example.TomTomIntegration.rest.swagger.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("/poi/place")
public class PoiCrudController {

    private final PoiFacade poiFacade;

    @CreatePoiAPI
    @PostMapping
    public ResponseEntity<PoiResponse> createPoi(@RequestBody @Valid PoiCreationRequest request) {
        return ResponseEntity.status(CREATED).body(poiFacade.createPoi(request));
    }

    @GetPoiByIdAPI
    @GetMapping("/{poiId}")
    public ResponseEntity<PoiResponse> getPoiById(@PathVariable(value = "poiId") Long poiId) {
        return ResponseEntity.ok(poiFacade.getPoiById(poiId));
    }

    @UpdatePoiAPI
    @PutMapping("/{poiId}")
    public ResponseEntity<PoiResponse> updatePoi(@PathVariable(value = "poiId") Long poiId, @RequestBody @Valid PoiUpdateRequest request) {
        return ResponseEntity.ok(poiFacade.updatePoi(poiId, request));
    }

    @DeletePoiAPI
    @DeleteMapping("/{poiId}")
    public ResponseEntity<Void> deletePoi(@PathVariable(value = "poiId") Long poiId) {
        poiFacade.deletePoi(poiId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ResponseBody
    @GetPoiList
    public ResponseEntity<PageablePoiResponse> getPoiList(@Valid PoiSearchRequest searchRequest,
                                                          @RequestParam(required = false, defaultValue = "0") int page,
                                                          @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(poiFacade.getPoiList(searchRequest, PageRequest.of(page, size)));
    }
}
