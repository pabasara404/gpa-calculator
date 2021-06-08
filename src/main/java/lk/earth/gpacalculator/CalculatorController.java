package lk.earth.gpacalculator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @PostMapping("/{function}")
    public Integer cal(@PathVariable String function, @RequestBody HashMap<String,Integer> data){
       try{
           Integer num1=data.get("num1");
           Integer num2=data.get("num2");
           switch(function){
               case "add": return num1+num2;
               case "mul": return num1*num2;
               case "div": return num1/num2;
               case "sub": return num1-num2;

           }
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Method is not valid!");
       }
       catch(ArithmeticException e){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Numbers are not valid");
       }
    }
}
