package com.mz.controller;

import com.mz.domain.BallHistory;
import com.mz.domain.Note;
import com.mz.service.BallHistoryService;
import com.mz.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author mz
 * @Description：
 * @date 2018/6/23
 * @time 16:10
 */
@Controller
public class IndexController {

    @Autowired
    private BallHistoryService mBallHistoryService;

    @Autowired
    private NoteService mNoteService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") Integer pageNum,
                        @RequestParam(defaultValue = "3") Integer pageSize) {

        Sort sort = new Sort(Sort.Direction.DESC,"code");
        Pageable pageable = new PageRequest(pageNum, pageSize, sort);

        Page<BallHistory> page = mBallHistoryService.findAll(pageable);

        //获得当前页
        model.addAttribute("pageNum", page.getNumber());
        //获得一页显示的条数
        model.addAttribute("pageSize", page.getSize());
        //是否是第一页
        model.addAttribute("isFirstPage", page.isFirst());
        //获得总页数
        model.addAttribute("totalPages", page.getTotalPages());
        //是否是最后一页
        model.addAttribute("isLastPage", page.isLast());
        //每页内容
        model.addAttribute("list", page.getContent());

        Note note = mNoteService.findNew();

        model.addAttribute("note",note);

        return "index";
    }

    @GetMapping("/detail")
    public String detail(String code, Model model) {
        System.out.println("code:" + code);
        BallHistory history = mBallHistoryService.findOne(code);
        model.addAttribute("history",history);
        return "detail";
    }

}
