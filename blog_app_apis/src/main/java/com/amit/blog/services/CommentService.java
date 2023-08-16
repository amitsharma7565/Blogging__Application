package com.amit.blog.services;

import com.amit.blog.payloads.CommentDto;

public interface CommentService {
CommentDto createComment(CommentDto commentDto, Integer postId);
void deleteComment(Integer commentId);
}
