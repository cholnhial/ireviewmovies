package com.cholnhial.ireviewmovies.service;

import com.cholnhial.ireviewmovies.model.MovieReview;
import com.cholnhial.ireviewmovies.repository.MovieReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class MovieReviewService {

    private final UserService userService;
    private final MovieReviewRepository movieReviewRepository;

    public MovieReview save(MovieReview movieReview) {

        // new entity
        if(movieReview.getId() == null) {
            movieReview.setCreatedDateTime(ZonedDateTime.now());
            movieReview.setUser(userService.getCurrentLoggedInUser());
        }

      return  this.movieReviewRepository.saveAndFlush(movieReview);
    }

    public Page<MovieReview> findAllByUserId(Long userId, Pageable pageable) {
        return this.movieReviewRepository.findAllByUserId(userId, pageable);
    }

    public void deleteMovieReviewById(Long id) {
        this.movieReviewRepository.deleteById(id);
    }


    public Optional<MovieReview> findOneById(Long id) {
        return this.movieReviewRepository.findById(id);
    }

    public Float getAverageRatingByTMDBMovieId(Long TMdbMovieId) {
        return movieReviewRepository.getAverageRatingByTMDBMovieId(TMdbMovieId);
    }

    /**
     * Returns a list of reviews by TMDb movie id
     *
     *
     * @param movieId The TMDB movie ID
     * @param pageable paging information
     * @return
     */
    public Page<MovieReview> findAllByTMDBMovieId(Long movieId, Pageable pageable) {
        return movieReviewRepository.findAllBytMDBMovieId(movieId, pageable);
    }

}
