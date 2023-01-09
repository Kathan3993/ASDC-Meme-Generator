package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IMemeCompetitionDAO;
import com.group5.memegenerator.model.IMemeCompetition;
import com.group5.memegenerator.model.MemeCompetition;

public class MemeCompetitionFactory implements IMemeCompetitionFactory {
    private static IMemeCompetitionFactory memeCompetitionFactory = null;

    private MemeCompetitionFactory() {
    }

    public static IMemeCompetitionFactory instance() {
        if (memeCompetitionFactory == null) {
            memeCompetitionFactory = new MemeCompetitionFactory();
        }
        return memeCompetitionFactory;
    }

    @Override
    public IMemeCompetition makeMemeCompetition(IMemeCompetitionDAO memeCompetitionDAO) {
        return new MemeCompetition(memeCompetitionDAO);
    }

}
