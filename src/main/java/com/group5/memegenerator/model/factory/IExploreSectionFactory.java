package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IExploreSectionDAO;
import com.group5.memegenerator.model.IExploreSection;
import com.group5.memegenerator.model.ILike;

public interface IExploreSectionFactory {

    IExploreSection makeExploreSection(IExploreSectionDAO exploreSectionDAO, ILike like);
}
