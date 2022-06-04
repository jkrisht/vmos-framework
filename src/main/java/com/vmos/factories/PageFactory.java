package com.vmos.factories;

import com.vmos.pages.HomePage;
import com.vmos.pages.RegisterSignInPage;
import com.vmos.utils.VmosDriver;

public class PageFactory {
    synchronized public static HomePage homePage(VmosDriver driver) {
        return new HomePage(driver, BundleFile.HOME_PAGE);
    }

    synchronized public static RegisterSignInPage registerSignInPage(VmosDriver driver) {
        return new RegisterSignInPage(driver, BundleFile.REGISTER_SIGNIN_PAGE);
    }
}
