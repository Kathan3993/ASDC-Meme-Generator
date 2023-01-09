package com.group5.memegenerator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class MemeTemplateDto {


    @Getter @Setter private List<MemeTemplate> memeTemplates;

    public MemeTemplateDto() {
        this.memeTemplates = new ArrayList<>();
    }

    public List<IMemeTemplate> getIMemeTemplates() {
        List<IMemeTemplate> memes = new ArrayList<>();

        for (MemeTemplate memeTemplate : this.memeTemplates) {
            memes.add(memeTemplate);
        }
        return memes;
    }

    public void setIMemeTemplates(List<IMemeTemplate> memeTemplates) {

        for (IMemeTemplate memeTemplate : memeTemplates) {
            this.memeTemplates.add((MemeTemplate) memeTemplate);
        }
    }
}