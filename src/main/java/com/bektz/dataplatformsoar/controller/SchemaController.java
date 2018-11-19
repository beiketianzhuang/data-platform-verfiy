package com.bektz.dataplatformsoar.controller;

import com.bektz.dataplatformsoar.req.SchemaReq;
import com.bektz.dataplatformsoar.resp.GenericResponse;
import com.bektz.dataplatformsoar.resp.SchemaResp;
import com.bektz.dataplatformsoar.service.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;


@Controller
public class SchemaController {

    @Autowired
    private SchemaService schemaService;

    @PostMapping(value = "/schemas")
    public ResponseEntity<GenericResponse> addSchema(@RequestBody @Valid SchemaReq schemaReq, BindingResult result) {
        schemaService.addSchema(schemaReq);
        return ResponseEntity.ok(GenericResponse.SUCCESS);
    }

    @GetMapping(value = "/schemas/{schema}")
    public String allTablesBySchema(@PathVariable("schema") String shcema, Model model) {
        SchemaResp schemaResp = schemaService.getSchemaResp(shcema);
        model.addAttribute("schemaResps", Collections.singletonList(schemaResp));
        return "index::table_refresh";
    }

    @GetMapping(value = "/schemas/all")
    public String allSchemas(Model model) {
        List<SchemaResp> schemaResps = schemaService.getSchemaResps();
        model.addAttribute("schemaResps", schemaResps);
        return "index::schema_refresh";
    }
}
