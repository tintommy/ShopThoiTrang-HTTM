package ptithcm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ptithcm.entity.SanPhamEntity;
import ptithcm.service.SanPhamService;

@Controller
public class testAPI {
	@Autowired
	SanPhamService sanPhamService;
	
    @RequestMapping(value = "/api/data")
    
    public @ResponseBody String getNumbers() {
    	
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(3);
        numbers.add(5);
        System.out.println(numbers);
        return numbers.toString();
    }
    
    @RequestMapping(value = "/api/array",method = RequestMethod.GET)
    public @ResponseBody String receiveArray(@RequestParam("array") List<Integer> array) {
        System.out.print(array.toString());
        return "Nhận được mảng có " + array.size() + " phần tử.";
    }
}