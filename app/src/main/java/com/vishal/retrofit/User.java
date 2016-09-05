package com.vishal.retrofit;

/**
 * Created by Minuteman on 8/26/2016.
 */
public class User {


    String name;
    String email;
    Address address;
    Company company;

    public Company getCompany() {
        return company;
    }

    public static class Company{


        String name;
        String catchPhrase;
        String bs;

        public String getName() {
            return name;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public String getBs() {
            return bs;
        }

        @Override
        public String toString() {
            return "CompneyInfo:"+ name +"\n"+catchPhrase+"\n" +bs;
        }
    }

    public Address getAddress() {
        return address;
    }

    public static class Address {


        String city;
        Geo geo;

        public Geo getGeo() {
            return geo;
        }

        public static class Geo {
            String lng;
            String lat;

            public String getLng() {
                return "Langitude:" + lng;
            }

            public String getLat() {
                return "Latitude:" + lat;
            }
        }

        public String getCity() {
            return city;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
