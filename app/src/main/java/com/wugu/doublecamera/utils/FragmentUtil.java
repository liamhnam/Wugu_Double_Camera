package com.wugu.doublecamera.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtil {
    public static void showFragment(int i, FragmentActivity fragmentActivity, Fragment fragment, boolean z) {
        if (fragmentActivity == null || fragment == null) {
            return;
        }
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        if (!fragment.isAdded() && supportFragmentManager.findFragmentByTag(fragment.getClass().getSimpleName()) == null) {
            fragmentTransactionBeginTransaction.add(i, fragment, fragment.getClass().getSimpleName());
        }
        if (z) {
            fragmentTransactionBeginTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransactionBeginTransaction.show(fragment);
        fragmentTransactionBeginTransaction.commit();
    }

    public static void hideFragment(FragmentActivity fragmentActivity, Fragment fragment) {
        if (fragment == null || !fragment.isAdded()) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.hide(fragment);
        fragmentTransactionBeginTransaction.commit();
    }

    public static void removeFragment(FragmentActivity fragmentActivity, Fragment fragment) {
        if (fragment == null || !fragment.isAdded()) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.remove(fragment);
        fragmentTransactionBeginTransaction.commit();
    }

    public static void replaceFragment(int i, FragmentActivity fragmentActivity, Fragment fragment) {
        if (fragmentActivity == null || fragment == null) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(i, fragment);
        fragmentTransactionBeginTransaction.commit();
    }

    public static boolean popBackStack(FragmentActivity fragmentActivity) {
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        if (supportFragmentManager.getBackStackEntryCount() <= 0) {
            return false;
        }
        supportFragmentManager.popBackStack();
        return true;
    }

    public static void clearBackStack(FragmentActivity fragmentActivity) {
        fragmentActivity.getSupportFragmentManager().popBackStackImmediate((String) null, 1);
    }

    public static Fragment getFragmentStackTop(FragmentActivity fragmentActivity) {
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        int backStackEntryCount = supportFragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            return supportFragmentManager.findFragmentByTag(supportFragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName());
        }
        return null;
    }

    public static int getBackStackFragmentCount(FragmentActivity fragmentActivity) {
        return fragmentActivity.getSupportFragmentManager().getBackStackEntryCount();
    }
}
