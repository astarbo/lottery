package com.mz.controller;

import com.mz.domain.Note;
import com.mz.domain.User;
import com.mz.service.NoteService;
import com.mz.utils.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author mz
 * @Description：
 * @date 2018/6/24
 * @time 23:10
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService mNoteService;

    @GetMapping("/bbs_index")
    public String index(Model model) {
        List<Note> notes = mNoteService.findAll();
        model.addAttribute("notes", notes);
        return "bbs_index";
    }

    @PostMapping("/publish")
    public String publish(Note note, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        note.setUsername(user.getUsername());
        note.setNid(UUIDUtil.getId());
        note.setIpaddress(request.getRemoteAddr());
        note.setTime(new Date().toLocaleString());

        mNoteService.save(note);
        return "redirect:/note/bbs_index";
    }

    @GetMapping("/detail")
    public String findNoteById(String nid,Model model) {
        System.out.println("nid:"+nid);
        Note note = mNoteService.findOne(nid);
        model.addAttribute("note",note);
        return "bbs_detail";
    }
}
