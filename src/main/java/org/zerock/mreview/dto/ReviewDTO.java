package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long reviewnum;

    private Long mno;

    //멤버 id
    private Long mid;

    //멤버 닉네임
    private String nickname;

    //멤버 이메일
    private String email;

    private int grade;

    private String text;

    private LocalDateTime regDate, modDate;
}
