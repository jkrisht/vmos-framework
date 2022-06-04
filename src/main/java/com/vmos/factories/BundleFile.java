package com.vmos.factories;

public enum BundleFile {
    API_RESOURCES("ApiResources"),
    HOME_PAGE("HomePage"),
    REGISTER_SIGNIN_PAGE("RegisterSignInPage");

    private final String name;

    BundleFile(String fileName) {
        this.name = fileName;
    }

    public String getName() {
        return this.name;
    }
}
