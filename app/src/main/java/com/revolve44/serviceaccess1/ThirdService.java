//package com.revolve44.serviceaccess1;
//
//import android.accessibilityservice.AccessibilityService;
//import android.accessibilityservice.AccessibilityServiceInfo;
//import android.accessibilityservice.GestureDescription;
//import android.app.Service;
//import android.content.Intent;
//import android.graphics.Path;
//import android.os.Build;
//import android.os.IBinder;
//import android.view.accessibility.AccessibilityEvent;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//
//public class ThirdService extends AccessibilityService {
//
//    private static TouchSimulateService instance;
//
//    @Override
//    public void onServiceConnected() {
//        if (instance == null) {
//            instance = this;
//
//            // 액티비티의 비정상 에러 탐지
//            Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this));
//        }
//        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
//        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
//        info.feedbackType = AccessibilityServiceInfo.DEFAULT | AccessibilityServiceInfo.FEEDBACK_HAPTIC;
//        info.notificationTimeout = 100; // millisecond
//        setServiceInfo(info);
//    }
//
//    @Override
//    public void onAccessibilityEvent(AccessibilityEvent event) {
//        if (instance == null) {
//            instance = this;
//        }
//    }
//
//    @Override
//    public void onInterrupt() {
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    //region Common method field
//
//    /**
//     * Create a gesture event to simulate touch event.
//     *
//     * @param x The X coordinate to generate the touch event.
//     * @param y The Y coordinate to generate the touch event.
//     * @return The gesture to simulate touch event.
//     */
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    GestureDescription createClick(float x, float y) {
//        // for a single tap a duration of 1 ms is enough
//        final int DURATION = 1;
//
//        Path clickPath = new Path();
//        clickPath.moveTo(x, y);
//        GestureDescription.StrokeDescription clickStroke =
//                new GestureDescription.StrokeDescription(clickPath, 0, DURATION);
//        GestureDescription.Builder clickBuilder = new GestureDescription.Builder();
//        clickBuilder.addStroke(clickStroke);
//        return clickBuilder.build();
//    }
//
//    /**
//     * Create a double tab gesture event to simulate touch event.
//     *
//     * @param x The X coordinate to generate the touch event.
//     * @param y The Y coordinate to generate the touch event.
//     * @return The gesture to simulate touch event.
//     */
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    GestureDescription createDoubleTab(float x, float y) {
//        final int DURATION = 1;
//        Path oneTabPath = new Path();
//        Path twoTabPath = new Path();
//        oneTabPath.moveTo(x, y);
//        twoTabPath.moveTo(x, y);
//
//        GestureDescription.Builder clickBuilder = new GestureDescription.Builder();
//        GestureDescription.StrokeDescription oneTab = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            oneTab = new GestureDescription.StrokeDescription(oneTabPath, 0, DURATION);
//        }
//        GestureDescription.StrokeDescription twoTab = new GestureDescription.StrokeDescription(twoTabPath, 300, DURATION);
//        clickBuilder.addStroke(oneTab);
//        clickBuilder.addStroke(twoTab);
//        return clickBuilder.build();
//    }
//
//    //endregion Common method field
//
//    //region Exception Handler
//
//    private class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
//
//        private Context context;
//
//        private UncaughtExceptionHandler(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public void uncaughtException(Thread thread, Throwable ex) {
//            stopService(new Intent(context, TouchSimulateService.class));
//            System.exit(0);
//        }
//    }
//
//    //endregion Exception Handler
//
//    //region Getter & Setter
//
//    static TouchSimulateService getInstance() {
//        return instance;
//    }
//
//}
