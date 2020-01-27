package com.example.landmark.models;

import java.util.ArrayList;
import java.util.Random;

public class LandMarkMockFactory {


    private static ArrayList<LandMarkModel> getMockData() {
        ArrayList<LandMarkModel> list = new ArrayList<LandMarkModel>();

        list.add(new LandMarkModel("Eiffel Tower", "The Eiffel Tower (/ˈaɪfəl/ EYE-fəl; French: tour Eiffel [tuʁ‿ɛfɛl] (About this soundlisten)) is a wrought-iron lattice tower on the Champ de Mars in Paris, France. It is named after the engineer Gustave Eiffel, whose company designed and built the tower.", 48.858222,2.2945));
        list.add(new LandMarkModel("name2", "description2", 124,124));

        return list;
    }

    public static LandMarkModel getRandom() {
        return getMockData().get(0);
    }
}
