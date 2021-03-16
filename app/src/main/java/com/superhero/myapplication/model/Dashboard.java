package com.superhero.myapplication.model;

import java.util.Comparator;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Dashboard {
    public String response;
    public String id;

    public Dashboard(String response, String id, String name, Powerstats powerstats, Biography biography, Appearance appearance, Work work, Connections connections, Image image) {
        this.response = response;
        this.id = id;
        this.name = name;
        this.powerstats = powerstats;
        this.biography = biography;
        this.appearance = appearance;
        this.work = work;
        this.connections = connections;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Powerstats getPowerstats() {
        return powerstats;
    }

    public void setPowerstats(Powerstats powerstats) {
        this.powerstats = powerstats;
    }

    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Connections getConnections() {
        return connections;
    }

    public void setConnections(Connections connections) {
        this.connections = connections;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String name;
    public Powerstats powerstats;
    public Biography biography;
    public Appearance appearance;
    public Work work;
    public  Connections connections;
    public Image image;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }


        public class Powerstats{
            public String intelligence;
            public String strength;
            public String speed;
            public String durability;
            public String power;
            public String combat;

            public String getIntelligence() {
                return intelligence;
            }

            public void setIntelligence(String intelligence) {
                this.intelligence = intelligence;
            }

            public String getStrength() {
                return strength;
            }

            public void setStrength(String strength) {
                this.strength = strength;
            }

            public String getSpeed() {
                return speed;
            }

            public void setSpeed(String speed) {
                this.speed = speed;
            }

            public String getDurability() {
                return durability;
            }

            public void setDurability(String durability) {
                this.durability = durability;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getCombat() {
                return combat;
            }

            public void setCombat(String combat) {
                this.combat = combat;
            }
        }

        public class Biography{
            @SerializedName("full-name")
            public String fullName;
            @SerializedName("alter-egos")
            public String alterEgos;
            public List<String> aliases;
            @SerializedName("place-of-birth")
            public String placeOfBirth;
            @SerializedName("first-appearance")
            public String firstAppearance;
            public String publisher;
            public String alignment;

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public String getAlterEgos() {
                return alterEgos;
            }

            public void setAlterEgos(String alterEgos) {
                this.alterEgos = alterEgos;
            }

            public List<String> getAliases() {
                return aliases;
            }

            public void setAliases(List<String> aliases) {
                this.aliases = aliases;
            }

            public String getPlaceOfBirth() {
                return placeOfBirth;
            }

            public void setPlaceOfBirth(String placeOfBirth) {
                this.placeOfBirth = placeOfBirth;
            }

            public String getFirstAppearance() {
                return firstAppearance;
            }

            public void setFirstAppearance(String firstAppearance) {
                this.firstAppearance = firstAppearance;
            }

            public String getPublisher() {
                return publisher;
            }

            public void setPublisher(String publisher) {
                this.publisher = publisher;
            }

            public String getAlignment() {
                return alignment;
            }

            public void setAlignment(String alignment) {
                this.alignment = alignment;
            }
        }

        public class Appearance{
            public String gender;
            public String race;
            public List<String> height;
            public List<String> weight;
            @SerializedName("eye-color")
            public String eyeColor;
            @SerializedName("hair-color")
            public String hairColor;

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getRace() {
                return race;
            }

            public void setRace(String race) {
                this.race = race;
            }

            public List<String> getHeight() {
                return height;
            }

            public void setHeight(List<String> height) {
                this.height = height;
            }

            public List<String> getWeight() {
                return weight;
            }

            public void setWeight(List<String> weight) {
                this.weight = weight;
            }

            public String getEyeColor() {
                return eyeColor;
            }

            public void setEyeColor(String eyeColor) {
                this.eyeColor = eyeColor;
            }

            public String getHairColor() {
                return hairColor;
            }

            public void setHairColor(String hairColor) {
                this.hairColor = hairColor;
            }
        }

        public class Work{
            public String occupation;
            public String base;

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getBase() {
                return base;
            }

            public void setBase(String base) {
                this.base = base;
            }
        }

        public class Connections{
            @SerializedName("group-affiliation")
            public String groupAffiliation;
            public String relatives;

            public String getGroupAffiliation() {
                return groupAffiliation;
            }

            public void setGroupAffiliation(String groupAffiliation) {
                this.groupAffiliation = groupAffiliation;
            }

            public String getRelatives() {
                return relatives;
            }

            public void setRelatives(String relatives) {
                this.relatives = relatives;
            }
        }

        public class Image{
            public String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }


        public static final Comparator<Dashboard> ASCENDING = new Comparator<Dashboard>() {


            @Override
            public int compare(Dashboard t1, Dashboard t2) {
                return t1.getName().compareTo(t2.getName());
            }
        };


    public static final Comparator<Dashboard> DESCENDING = new Comparator<Dashboard>() {


        @Override
        public int compare(Dashboard t1, Dashboard t2) {
            return t2.getName().compareTo(t1.getName());
        }
    };
    }
