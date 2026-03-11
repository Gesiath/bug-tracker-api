package com.gesiath.bugtrackerapi.service.impl;

import com.gesiath.bugtrackerapi.dto.comment.CommentCreateRequest;
import com.gesiath.bugtrackerapi.dto.comment.CommentResponse;
import com.gesiath.bugtrackerapi.entity.Comment;
import com.gesiath.bugtrackerapi.entity.Issue;
import com.gesiath.bugtrackerapi.entity.User;
import com.gesiath.bugtrackerapi.exception.CustomDataNotFoundException;
import com.gesiath.bugtrackerapi.mapper.CommentMapper;
import com.gesiath.bugtrackerapi.repository.CommentRepository;
import com.gesiath.bugtrackerapi.repository.IssueRepository;
import com.gesiath.bugtrackerapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final IssueRepository issueRepository;

    @Override
    public Page<CommentResponse> getByIssue(UUID issueId, Pageable pageable) {

        return commentRepository.findByIssueId(issueId, pageable)
                .map(CommentMapper::toResponse);

    }

    @Override
    public CommentResponse create(UUID issueId, CommentCreateRequest dto) {

        Issue issue = findIssue(issueId);

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Comment comment = CommentMapper.toEntity(dto);

        comment.setIssue(issue);
        comment.setAuthor(user);

        commentRepository.save(comment);

        return CommentMapper.toResponse(comment);

    }

    @Override
    public void delete(UUID id) {

        Comment comment = findComment(id);

        comment.setDeleted(true);

        commentRepository.save(comment);

    }

    private Comment findComment(UUID id){

        return commentRepository.findById(id)
                .orElseThrow(() -> new CustomDataNotFoundException("Comment not found"));

    }

    private Issue findIssue(UUID id){

        return issueRepository.findById(id)
                .orElseThrow(() -> new CustomDataNotFoundException("Issue not found"));

    }

}
