package com.brainstation23.structure.controller;

import com.brainstation23.structure.service.AbstractCRUDApi;
import com.brainstation23.structure.domain.request.BaseRequest;
import com.brainstation23.structure.domain.response.BaseResponse;
import com.brainstation23.structure.repository.schema.AbstractEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
public class AbstractController <ENTITY extends AbstractEntity,REQUEST extends BaseRequest, RESPONSE extends BaseResponse>{
    private final AbstractCRUDApi<ENTITY,REQUEST, RESPONSE> api;

    public AbstractController(AbstractCRUDApi<ENTITY, REQUEST, RESPONSE> api) {
        this.api = api;
    }

    @PostMapping
    public RESPONSE save(@RequestBody REQUEST request) {
        return api.save(request);
    }

    @PutMapping("/{id}")
    public RESPONSE update(@PathVariable Long id, @RequestBody REQUEST request){
        return api.update(id,request);
    };

    @GetMapping("/{id}")
    public RESPONSE getById(@PathVariable Long id) {
        return api.getById(id);
    }

    @GetMapping("/list")
    public List<RESPONSE> list() {
        return api.list();
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return api.delete(id);
    }

    @GetMapping("/modified")
    public List<RESPONSE> modified(@RequestParam("modifiedSince") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
        return api.modifiedSince(time);
    }
}
