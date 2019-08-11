package com.easyjob.controller;

import com.easyjob.model.Student;
import com.easyjob.model.Teacher;
import com.easyjob.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register-teacher")
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView show(){
        ModelAndView mv = new ModelAndView("RegisterTeacher");
        Teacher t = new Teacher();
        mv.addObject("teacher", t);
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerTeacher(@ModelAttribute @Valid Teacher t,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            return "RegisterTeacher";
        }else{
            attributes.addFlashAttribute("mensagem", "Registro efetuado com sucesso");
            teacherRepository.save(t);
        return "redirect:/register-teacher";
        }
    }
}
