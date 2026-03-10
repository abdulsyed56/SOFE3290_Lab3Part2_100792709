package com.ontariotechu.sofe3980U;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BinaryController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("operand1", "");
        model.addAttribute("operand2", "");
        model.addAttribute("operator", " ");
        model.addAttribute("result", "");
        model.addAttribute("operand1Focused", false);
        return "calculator";
    }

    @PostMapping("/")
    public String result(
            @RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
            @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2,
            @RequestParam(name = "operator", required = false, defaultValue = " ") String operator,
            Model model) {

        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        Binary result;

        switch (operator) {
            case "+":
                result = Binary.add(number1, number2);
                break;
            case "*":
                result = Binary.multiply(number1, number2);
                break;
            case "&":
                result = Binary.and(number1, number2);
                break;
            case "|":
                result = Binary.or(number1, number2);
                break;
            default:
                result = new Binary("0");
                break;
        }

        model.addAttribute("operand1", number1.getValue());
        model.addAttribute("operand2", number2.getValue());
        model.addAttribute("operator", operator);
        model.addAttribute("result", result.getValue());
        model.addAttribute("operand1Focused", true);

        return "calculator";
    }
}
