package springbootfacebookclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springbootfacebookclone.POJO.Login;
import springbootfacebookclone.POJO.PostMapper;
import springbootfacebookclone.model.Person;
import springbootfacebookclone.service.PersonServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class PersonController {
    @Autowired
    private PersonServiceImpl userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("person", new Person());
        mav.addObject("login", new Login());

        return mav;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("home");
        mav.addObject("post", new PostMapper());

        return mav;
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, HttpServletResponse response,
                                @ModelAttribute("person") Person user) {

        HttpSession httpSession = request.getSession();

        if(userService.createUser(user)){
            httpSession.setAttribute("message", "Successfully registered!!!");
        }else{
            httpSession.setAttribute("message", "Failed to register or email already exist");
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("login", new Login());

        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public String loginProcess(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("login") Login login) {

        Person user = userService.getUser(login.getEmail(), login.getPassword());

        HttpSession httpSession = request.getSession();

        if (null != user) {
            httpSession.setAttribute("user", user);
            return "redirect:/home";
        } else {
            httpSession.setAttribute("message", "Email or Password is wrong!!!");
            return "redirect:/";
        }

    }

}
