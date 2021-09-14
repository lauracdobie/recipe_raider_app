package com.example.codeclan.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    private String name;
    private String thumbURL;
    private String idMeal;
    private String strArea;
    private String strInstructions;
    private ArrayList<String> ingredients;
    private ArrayList<String> measures;
}
