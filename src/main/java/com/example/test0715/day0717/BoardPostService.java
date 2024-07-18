package com.example.test0715.day0717;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardPostService {
  List<BoardPost> boardPosts = new ArrayList<>(); // 얘를 디비 대신에 하고 있음!
    private Long nextPostId = 1L;
    private Long nextCommentId = 1L;

    public BoardPostDto createBoardPost(BoardPostDto boardPostDto) {
        BoardPost boardPost = convertToBoardPostEntity(boardPostDto);
        boardPost.setId(nextPostId);
        boardPost.setCreatedDate(LocalDateTime.now());
        boardPosts.add(boardPost);
        return convertToBoardPostDto(boardPost);
    };

    private static BoardPost convertToBoardPostEntity(BoardPostDto boardPostDto) {
        BoardPost boardPost = new BoardPost();
        boardPost.setTitle(boardPostDto.getTitle());
        boardPost.setContent(boardPostDto.getContent());
        boardPost.setAuthor(boardPostDto.getAuthor());
        if(boardPostDto.getComments()!=null){
            boardPostDto.getComments().forEach(commentDto -> {
                Comment comment = convertToCommentEntity(commentDto);
                comment.setBoardPost(boardPost);
                boardPost.addComment(comment);

            });
        }
        return boardPost;
    }

    private static Comment convertToCommentEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setAuthor(commentDto.getAuthor());
        return comment;
    }

    private static BoardPostDto convertToBoardPostDto(BoardPost boardPost){
        BoardPostDto boardPostDto = new BoardPostDto();
        boardPostDto.setId(boardPost.getId());
        boardPostDto.setTitle(boardPost.getTitle());
        boardPostDto.setContent(boardPost.getContent());
        boardPostDto.setAuthor(boardPost.getAuthor());
        boardPostDto.setCreatedDate(boardPost.getCreatedDate());
        boardPostDto.setUpdateDate(boardPost.getUpdateDate());

        if(boardPost.getComments()!=null){
            boardPostDto.setComments(
                    boardPost.getComments().stream().map(BoardPostService::convertToCommentDto)
                            // this::convertToCommentDto
                            .collect(Collectors.toList())
            );
        }
        return boardPostDto;
    }

    private static CommentDto convertToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setAuthor(comment.getAuthor());
        return commentDto;
    }
}


//@Service
//public class BoardPostService {
//    List<BoardPost> boardPosts = new ArrayList<>();
//    private Long nextPostId = 1L;
//    private Long nextCommentId = 1L;
//
//    public BoardPostDto createBoardPost(BoardPostDto boardPostDto) {
//        BoardPost boardPost = convertToBoardPostEntity(boardPostDto);
//
//
//    }
//
//    private static BoardPost convertToBoardPostEntity(BoardPostDto boardPostDto) {
//        BoardPost boardPost = new BoardPost();
//        boardPost.setTitle(boardPostDto.getTitle());
//        boardPost.setContent(boardPostDto.getContent());
//        boardPost.setAuthor(boardPostDto.getAuthor());
//        if(boardPostDto.getComments()!=null){
//            boardPostDto.getComments().forEach(commentDto -> {
//                Comment comment = convertToCommentEntity(commentDto);
//                comment.setBoardPost(boardPost);
//                boardPost.addComment(comment);
//
//            });
//        }
//        return boardPost;
//    }
//}