package lk.earth.gpacalculator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@RestController
@RequestMapping("/gpa")
public class GpaCalculatorController {

    @GetMapping
    public HashMap usage(){
        HashMap<String,String> usage=new HashMap<>();
        usage.put("SEM 1 Subjects","is,web,cs,math,commuinication, pc");
        usage.put("SEM 2 Subjects","db,pro,math,sad");
        usage.put("Grade","A+,A,A-,B+,B,B-,C+,C,C-,D+,D,D-,F");
        return usage;

    }

    @PostMapping("/sem2")
    public HashMap sem2(@RequestBody HashMap<String,String> data){
        String db=data.get("db");//A+,A,A-
        String pro=data.get("pro");
        String math=data.get("math");
        String sad=data.get("sad");

        HashMap<String,String> result=new HashMap<>();
        Double GPA=0.0;
        GPA += getGpa(db);
        GPA += getGpa(pro);
        GPA += getGpa(math);
        GPA += getGpa(sad);
        GPA=GPA/4;

        result.put("GPA",GPA.toString());
        result.put("Class",getClass(GPA));

        return result;

    }

    @PostMapping("/sem1")
    public HashMap sem1(@RequestBody HashMap<String,String> data){
        String is=data.get("is");//A+,A,A-
        String web=data.get("web");
        String cs=data.get("cs");
        String math=data.get("math");
        String commuinication=data.get("commuinication");
        String pc=data.get("pc");

        HashMap<String,String> result=new HashMap<>();
        Double GPA=0.0;
        GPA += getGpa(is);
        GPA += getGpa(web);
        GPA += getGpa(cs);
        GPA += getGpa(math);
        GPA += getGpa(commuinication);
        GPA += getGpa(pc);
        GPA=GPA/6;

        result.put("GPA",GPA.toString());
        result.put("Class",getClass(GPA));

        return result;

    }

    private Double getGpa(String grade){
        switch(grade){
            case "A+": return 4.0;
            case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "D+": return  1.3;
            case "D": return 1.0;
            case "D-": return 0.7;
            case "F": return 0.0;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Grade");
    }
    private String getClass(Double gpa){
        if(gpa>=3.7){
            return "First Class";
        }
        if(gpa>=3.3){
            return "Second Class in the Upper Division";
        }
        if(gpa>=3.0){
            return "Second Class in the Lower Division";
        }
        return "You don't have a Class Peasant";
    }
}
