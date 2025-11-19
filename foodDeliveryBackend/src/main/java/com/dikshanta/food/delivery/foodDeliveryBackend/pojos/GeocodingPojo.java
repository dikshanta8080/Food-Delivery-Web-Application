package com.dikshanta.food.delivery.foodDeliveryBackend.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GeocodingPojo {
    public int place_id;
    public String licence;
    public String osm_type;
    public int osm_id;
    public String lat;
    public String lon;
    @JsonProperty("class")
    public String myclass;
    public String type;
    public int place_rank;
    public double importance;
    public String addresstype;
    public String name;
    public String display_name;
    public ArrayList<String> boundingbox;
}

