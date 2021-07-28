package com.example.demo.controller;

import com.example.demo.model.NumberConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NumberConverterController {
    private final NumberConverter numberConverter = new NumberConverter();

    @GetMapping("/convert")
    public ResponseEntity<String> convert(@RequestParam(name = "val") int value,
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
}
