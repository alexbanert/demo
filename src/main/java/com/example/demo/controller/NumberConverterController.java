package com.example.demo.controller;

import com.example.demo.model.NumberConverter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NumberConverterController {
    private final NumberConverter numberConverter = new NumberConverter();

    @ApiOperation(value = "Convert number to roman or hex",
            notes = "Returns converted number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful conversion"),
            @ApiResponse(code = 400, message = "wrong input parameters")
    })
    @GetMapping("/convert")
    public ResponseEntity<String> convert(@ApiParam("Value to convert")
                                          @RequestParam(name = "val", defaultValue = "1") int value,
                                          @ApiParam("Conversion type, either roman or hex")
                                          @RequestParam(name = "type") String type) {
        switch (type.toLowerCase()) {
            case "hex":
                return new ResponseEntity<>(numberConverter.convertToHex(value), HttpStatus.OK);

            case "roman":
                if (value > 3999)
                    return new ResponseEntity<>("Roman number can't be bigger than 3999", HttpStatus.BAD_REQUEST);
                else if (value == 0)
                    return new ResponseEntity<>("Roman number can't be 0", HttpStatus.BAD_REQUEST);
                else
                    return new ResponseEntity<>(numberConverter.convertToRoman(Math.abs(value)), HttpStatus.OK);

            default:
                return new ResponseEntity<>("Bad type parameter", HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        String message = name + " parameter is missing";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
