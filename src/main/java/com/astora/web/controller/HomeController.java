package com.astora.web.controller;

import com.astora.web.dao.UserDao;
import com.astora.web.dao.model.Role;
import com.astora.web.dao.model.User;
import com.astora.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:maresjan694@gmail.com">Jan Mares</a>, 16.10.2017
 */
@Controller
public class HomeController extends BaseUserController {

    private RoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("/")
    public ModelAndView renderHome(Authentication authentication) throws Exception {
        Map<String, Object> map = init(authentication);
        List<User> list = userDao.findAll();
        Role role = list.get(0).getRoleByRoleRoleId();
        return new ModelAndView("home", map);
    }

    @RequestMapping("/createTest")
    public ModelAndView createTest(Map<String, Object> map) {
        map.put("created", "Role successfully created.");

        Role roleDB = new Role();
        roleDB.setCreated(new Timestamp(2332));
        roleDB.setName("asd");
        roleService.create(roleDB);

        return new ModelAndView("home", map);
    }

    @RequestMapping("/updateTest")
    public ModelAndView updateTest(Map<String, Object> map) {
        map.put("updated", "Role successfully updated.");
        Role role = roleService.findById(5);
        role.setName("updateName");
        roleService.update(role);

        return new ModelAndView("home", map);
    }

    @RequestMapping("/findByIdTest")
    public ModelAndView findByIdTest(Map<String, Object> map) {
        map.put("role", "Role successfully found " + roleService.findById(3));

        return new ModelAndView("home", map);
    }

    @RequestMapping("/findAllTest")
    public ModelAndView findAllTest(Map<String, Object> map) {
        //List<User> mapa = user.findAll();
        map.put("roleList", "Roles successfully found " + roleService.findAll().toString());

        return new ModelAndView("home", map);
    }

    @RequestMapping("/deleteTest")
    public ModelAndView deleteTest(Map<String, Object> map) {
        map.put("deleted", "Role successfully deleted.");
        roleService.delete(3);

        return new ModelAndView("home", map);
    }
}
