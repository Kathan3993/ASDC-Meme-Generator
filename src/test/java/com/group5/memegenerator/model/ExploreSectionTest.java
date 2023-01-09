package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IExploreSectionDAO;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.factory.ExploreSectionDAOFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.factory.ExploreSectionFactory;
import com.group5.memegenerator.model.factory.MemeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExploreSectionTest {

    @Test
    public void loadMotLikedMemesFromDatabaseTest() {
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeMockLikeDAO();
        ILike like = MemeFactory.instance().makeLike(likeDAO);
        IExploreSectionDAO exploreSectionDAO = ExploreSectionDAOFactory.instance().makeMockExploreSectionDAO();
        IExploreSection exploreSection = ExploreSectionFactory.instance().makeExploreSection(exploreSectionDAO, like);

        Assertions.assertNotEquals(null, exploreSection.loadMotLikedMemesFromDatabase());
    }

    @Test
    public void loadMostLikedRecentMemesFromDatabaseTest() {
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeMockLikeDAO();
        ILike like = MemeFactory.instance().makeLike(likeDAO);
        IExploreSectionDAO exploreSectionDAO = ExploreSectionDAOFactory.instance().makeMockExploreSectionDAO();
        IExploreSection exploreSection = ExploreSectionFactory.instance().makeExploreSection(exploreSectionDAO, like);

        Assertions.assertNotEquals(null, exploreSection.loadMostLikedRecentMemesFromDatabase());
    }

    @Test
    public void loadTopRankedMemesFromDatabase() {
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeMockLikeDAO();
        ILike like = MemeFactory.instance().makeLike(likeDAO);
        IExploreSectionDAO exploreSectionDAO = ExploreSectionDAOFactory.instance().makeMockExploreSectionDAO();
        IExploreSection exploreSection = ExploreSectionFactory.instance().makeExploreSection(exploreSectionDAO, like);

        Assertions.assertNotEquals(null, exploreSection.loadTopRankedMemesFromDatabase());
    }

}
