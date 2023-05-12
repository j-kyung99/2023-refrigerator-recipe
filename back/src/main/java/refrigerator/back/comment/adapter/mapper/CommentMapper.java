package refrigerator.back.comment.adapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import refrigerator.back.comment.adapter.in.dto.response.InCommentDTO;
import refrigerator.back.comment.adapter.out.dto.OutCommentDTO;
import refrigerator.back.comment.application.domain.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto toCommentDto(OutCommentDTO dto);

    @Mapping(source = "dto.commentId", target = "commentID")
    InCommentDTO toInCommentDto(CommentDto dto, String date, Boolean isMyComment);

}
