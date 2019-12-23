package com.steamedegg.controller;

import com.steamedegg.databaseservice.AppDao;
import com.steamedegg.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juntao Peng
 */
@Controller
public class DetailController {

    AppDao appDao;

    @Autowired
    public DetailController(AppDao appDao) {
        this.appDao = appDao;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView service(@RequestParam("appid") int appid) {
        ModelAndView mv = new ModelAndView("detail");
        App app = this.appDao.queryAppByAppId(appid);
        mv.addObject("app", app);
        return mv;
    }
}
