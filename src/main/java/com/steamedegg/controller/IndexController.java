package com.steamedegg.controller;

import com.steamedegg.databaseservice.AppDao;
import com.steamedegg.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juntao Peng
 */
@Controller
public class IndexController {

    AppDao appDao;

    @Autowired
    public IndexController(AppDao appDao) {
        this.appDao = appDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView service() {
        ModelAndView mv = new ModelAndView("index");
        List<App> appList = this.appDao.queryAllApp(0, 20);
        int totalDocumentNumber = this.appDao.queryDocumentNumber();
        int pageNumber = totalDocumentNumber / 20 + 1;
        List<Integer> firstPages = new ArrayList<>();
        for (int i = 1; i<=5; i++) {
            firstPages.add(i);
        }
        List<Integer> secondPages = new ArrayList<>();
        for (int i = pageNumber - 1; i<=pageNumber; i++) {
            secondPages.add(i);
        }
        mv.addObject("appList", appList);
        mv.addObject("firstPages", firstPages);
        mv.addObject("secondPages", secondPages);
        return mv;
    }
}
