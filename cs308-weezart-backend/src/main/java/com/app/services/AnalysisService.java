package com.app.services;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.models.Album;
import com.app.models.Song;
import com.app.models.UserSong;

@Service
public interface AnalysisService {
	//List<Song> analysisReleaseDate(String userId, String StartYear, String FinishYear);
	List<Song> analysisReleaseDateManual(String userId, int StartYear, int FinishYear);
	List<Song> analysisGenreManual(String userId, String genre);
	List<Song> analysisLatest5Manual(String userId);
	List<Song> analysisTop5Manual(String userId);
	
	Map<String, Long> analysisDailyAddedSongs(String userId);
	Map<String, Long> analysisDailyLikedSongs(String userId);
	Map<String, Double> analysisDailyAverageRating(String userId);
	
	List<Integer> analysisSongCounts(String userId);
	List<Integer> analysisConstrainedSongCounts(String userId, String dateConstraint);
	
	
	
	//List<Album> analysisLatest5Album(String userId);
}
