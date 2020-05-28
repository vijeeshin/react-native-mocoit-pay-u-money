package com.mocoitlabs.payumoney;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MocoitPayUMoneyPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
         List<NativeModule> modules = new ArrayList<>();
        modules.add(new PayumoneyModule(reactApplicationContext));
        return modules;
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ViewManager> modules = new ArrayList<>();
        return Collections.emptyList();

    }
}
