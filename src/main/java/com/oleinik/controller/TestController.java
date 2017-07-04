package com.oleinik.controller;

import com.oleinik.entity.LangUnit;
import com.oleinik.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    private int getIntSize(String size) {

        switch (size) {
            case "five":
                return 5;
            case "ten":
                return 10;
            case "twenty":
                return 20;
            default:
                throw new IllegalArgumentException("Invalid test size: " + size);
        }
    }

    @RequestMapping(value = "/test/generic", method = RequestMethod.GET)
    public ModelAndView createGenericTest(String size) {

        int intSize = getIntSize(size);

        testService.generateGenericTest(intSize);

        ModelAndView view = new ModelAndView("test");
        view.addObject("size", String.valueOf(intSize));
        view.addObject("firstUnit", testService.getUnitFromTest(0));

        return view;
    }

    @RequestMapping(value = "/test/custom", method = RequestMethod.GET)
    public ModelAndView createCustomTest(String size) {
        int intSize = getIntSize(size);

        testService.generateCustomTest(intSize);

        ModelAndView view = new ModelAndView("test");
        view.addObject("size", String.valueOf(intSize));
        view.addObject("firstUnit", testService.getUnitFromTest(0));
        return view;
    }


    @RequestMapping(value = "/test/unit/{index}",
            method = RequestMethod.GET)
    @ResponseBody public LangUnit getExpression(@PathVariable int index) {
        return testService.getUnitFromTest(index);
    }

}
