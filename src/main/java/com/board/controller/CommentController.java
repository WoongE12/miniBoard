package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.dto.CommentDto;
import com.board.dto.UserDto;
import com.board.service.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService cs;

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createComment(@RequestParam("b_id") int b_id, 
	                            @RequestParam("comm_content") String comm_content, 
	                            HttpSession session, RedirectAttributes rttr) {
	    try {
	        UserDto userDto = (UserDto) session.getAttribute("dto");
	        if (userDto == null) {
	            return "redirect:/board/read?b_id=" + b_id; 
	        }

	        CommentDto dto = new CommentDto();
	        dto.setB_id(b_id);
	        dto.setU_id(userDto.getU_id());
	        dto.setComm_content(comm_content);
	        cs.commentCreate(dto);
	        return "redirect:/board/read?b_id=" + b_id; 
	        
	    } catch (Exception e) {
	        logger.error("Error creating comment", e);
	        return "redirect:/board/read?b_id=" + b_id; // 에러 발생 시에도 게시글 읽기 페이지로
	    }
	}
	
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateComment(@RequestParam("comm_id") int comm_id, @RequestParam("comm_content") String comm_content, 
                                @RequestParam("b_id") int b_id, HttpSession session, RedirectAttributes rttr) {
        try {
            UserDto userDto = (UserDto) session.getAttribute("dto");
            if (userDto == null) {
                return "redirect:/board/read?b_id=" + b_id; 
            }

            CommentDto existingComment = cs.getCommentById(comm_id);
            if (existingComment != null && userDto.getU_id().equals(existingComment.getU_id())) {
                CommentDto dto = new CommentDto();
                dto.setComm_id(comm_id);
                dto.setComm_content(comm_content);
                dto.setU_id(userDto.getU_id());
                cs.commentUpdate(dto);
            }

        } catch (Exception e) {
            logger.error("Error updating comment", e);
        }
        return "redirect:/board/read?b_id=" + b_id;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteComment(@RequestParam("comm_id") int comm_id, @RequestParam("b_id") int b_id, 
                                HttpSession session, RedirectAttributes rttr) {
        try {
            UserDto userDto = (UserDto) session.getAttribute("dto");
            if (userDto == null) {
                return "redirect:/board/read?b_id=" + b_id; 
            }

            CommentDto existingComment = cs.getCommentById(comm_id);
            if (existingComment != null && userDto.getU_id().equals(existingComment.getU_id())) {
                cs.commentDelete(comm_id);
            }

        } catch (Exception e) {
            logger.error("Error deleting comment", e);
        }
        return "redirect:/board/read?b_id=" + b_id; 
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public String listAllComments(@RequestParam("b_id") int b_id, HttpSession session, Model model) {
        logger.info("Fetching all comments for b_id: " + b_id);
        try {
            List<CommentDto> comments = cs.commentList(b_id);
            model.addAttribute("comments", comments);
            logger.info("Successfully fetched comments: " + comments.size());
        } catch (Exception e) {
            logger.error("Error listing all comments", e);
        }
        return "board/read"; 
    }
    
//    @RequestMapping(value = "/reComment", method = RequestMethod.POST)
//    public String createReComment(@RequestParam("parent_id") int parent_id,
//                                   @RequestParam("comm_content") String comm_content,
//                                   HttpSession session, RedirectAttributes rttr) {
//        try {
//            UserDto userDto = (UserDto) session.getAttribute("dto");
//            if (userDto == null) {
//                return "redirect:/board/read?b_id=" + b_id; 
//            }
//
//            CommentDto dto = new CommentDto();
//            dto.setComm_content(comm_content);
//            dto.setU_id(userDto.getU_id());
//            dto.setParent_id(parent_id);
//            cs.reCommentCreate(dto);
//
//        } catch (Exception e) {
//            logger.error("Error creating recomment", e);
//        }
//        return "redirect:/board/read?b_id=" + parent_id; 
//    }

}