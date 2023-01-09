package com.group5.memegenerator.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.factory.MemeCompetitionDAOFactory;
import com.group5.memegenerator.model.factory.MemeCompetitionFactory;

public class MemeCompetitionTest {
	IMemeCompetition memeCompetition;

	public MemeCompetitionTest() {
		memeCompetition = MemeCompetitionFactory.instance()
				.makeMemeCompetition(MemeCompetitionDAOFactory.instance().makeMockMemeCompetitionDAO());
	}

	@Test
	public void startCompetitionTest() {
		String winnerId = "1";
		String winnerMemeId = "1";
		String competitionName = "Test name";
		String competitionCategory = "";
		DatabaseResponse result = DatabaseResponse.SUCCESS;
		try {
			result = memeCompetition.startCompetition(winnerId, winnerMemeId, competitionName, competitionCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assertions.assertEquals(result, DatabaseResponse.ERROR);

		competitionCategory = "categoryId";
		try {
			result = memeCompetition.startCompetition(winnerId, winnerMemeId, competitionName, competitionCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assertions.assertEquals(result, DatabaseResponse.SUCCESS);

	}

	@Test
	public void addMemesToCompetitionTest() {

		String memeId = "1";
		String competitionId = "";
		DatabaseResponse result = memeCompetition.addMemesToCompetition(memeId, competitionId);
		Assertions.assertEquals(result, DatabaseResponse.ERROR);

		competitionId = "2";
		result = memeCompetition.addMemesToCompetition(memeId, competitionId);
		Assertions.assertEquals(result, DatabaseResponse.SUCCESS);

	}

	@Test
	public void getMemesByCompetitionIdTest() {
		String competitionId = "1";
		List<IMeme> memes = memeCompetition.getMemesByCompetitionId(competitionId);
		Assertions.assertEquals(5, memes.size());

		competitionId = "2";
		memes = memeCompetition.getMemesByCompetitionId(competitionId);
		Assertions.assertEquals(memes.size(), 0);

	}

	@Test
	public void competeTest() {
		String competitionId = "1";
		List<IMeme> memes = memeCompetition.compete(competitionId);
		Assertions.assertEquals(memes.size(), 2);
		Assertions.assertFalse(memes.get(0).getMemeId() == memes.get(1).getMemeId());
	}

	@Test
	public void voteOnMemeTest() {
		DatabaseResponse response = memeCompetition.voteOnMeme("1", "1");
		Assertions.assertEquals(response, DatabaseResponse.SUCCESS);
		response = memeCompetition.voteOnMeme("", "1");
		Assertions.assertEquals(response, DatabaseResponse.ERROR);
	}

	@Test
	public void getWinnerByCompetitionIdTest() throws Exception {
		String competitionId = "1";
		IMeme meme = memeCompetition.getWinnerByCompetitionId(competitionId);
		Assertions.assertEquals(meme.getMemeId(), "1");

		competitionId = "2";
		meme = memeCompetition.getWinnerByCompetitionId(competitionId);
		Assertions.assertEquals(meme.getMemeId(), "2");
	}

	@Test
	public void isCompetitionOverTest() {
		String competitionId = "1";
		boolean result = memeCompetition.isCompetitionOver(competitionId);
		Assertions.assertEquals(result, true);

	}

	@Test
	public void getLatestCompetitionTest() {
		String competitionId = memeCompetition.getLatestCompetition();
		Assertions.assertEquals(competitionId, "1");
		Assertions.assertNotEquals(competitionId, "2");

	}

	@Test
	public void getCompetitionNameByCompetitionIdTest() {
		String competitionName = memeCompetition.getCompetitionNameByCompetitionId("1");
		Assertions.assertEquals(competitionName, "Test Competition");
		competitionName = memeCompetition.getCompetitionNameByCompetitionId("2");
		Assertions.assertEquals(competitionName, "Test Competition 2");

	}

	@Test
	public void getAllCompetitionsTest() {
		List<IMemeCompetition> competitions = null;
		try {
			competitions = memeCompetition.getAllCompetitions();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assertions.assertEquals(competitions.size(), 5);
	}
}
