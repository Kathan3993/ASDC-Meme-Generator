package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IExploreSectionDAO;
import com.group5.memegenerator.model.ExploreSection;
import com.group5.memegenerator.model.IExploreSection;
import com.group5.memegenerator.model.ILike;

public class ExploreSectionFactory implements IExploreSectionFactory {

    private static IExploreSectionFactory exploreSectionFactory = null;

    private ExploreSectionFactory() {
    }

    public static IExploreSectionFactory instance() {
        if (exploreSectionFactory == null) {
            exploreSectionFactory = new ExploreSectionFactory();
        }
        return exploreSectionFactory;
    }

    @Override
    public IExploreSection makeExploreSection(IExploreSectionDAO exploreSectionDAO, ILike like) {
        return new ExploreSection(exploreSectionDAO, like);
    }
}
