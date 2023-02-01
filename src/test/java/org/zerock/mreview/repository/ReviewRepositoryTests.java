package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews()
    {
        IntStream.rangeClosed(1,200).forEach(i->{

            Long mno = (long) (Math.random() * 100) + 1; //영화 번호
            Long mid = (long) (Math.random() * 100) + 1; //리뷰한사람 번호

            Member member = Member.builder().mid(mid).build();

            int count = (int) (Math.random() * 5) + 1;

            Review movieReview = Review.builder()
                    .movie(Movie.builder().mno(mno).build())
                    .member(member)
                    .grade(count)
                    .text("이 영화에 대한 느낌..."+i)
                    .build();

            reviewRepository.save(movieReview);
        });

    }
    @Test
    public void testGetMovieReviews()
    {
        Movie movie = Movie.builder().mno(92L).build();
        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {

            System.out.print(movieReview.getReviewnum());
            System.out.print("\t" + movieReview.getGrade());
            System.out.print("\t" + movieReview.getText());
            System.out.print("\t" + movieReview.getMember().getEmail()); //다시 DB연결 필요
            System.out.println("---------------------------");

        });

    }

}
