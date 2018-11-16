package com.misutesu.project.version2.base;

import android.view.MotionEvent;

public abstract class TwoFingerMoveDetector {

    public static final int NO_EVENT = 0;
    public static final int MOVE_DIRECTION_X = 1;
    public static final int MOVE_DIRECTION_Y = 2;

    private int minTriggerDistance;
    private boolean isTwoMoving = false;
    private int twoMoveDirection = 0;
    private float lastX[] = new float[2];
    private float lastY[] = new float[2];

    //设置最小触发距离
    public TwoFingerMoveDetector(int minTriggerDistance) {
        this.minTriggerDistance = minTriggerDistance;
    }

    public abstract boolean onTwoFingerMove(MotionEvent e, int moveDirection, float distanceX, float distanceY);

    public boolean onTouchEvent(MotionEvent event) {
        boolean result = false;
        if (event.getPointerCount() == 2) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_POINTER_2_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!isTwoMoving) {
                        if (((event.getX(0) - lastX[0] > 0 && event.getX(1) - lastX[1] > 0)
                                || (event.getX(0) - lastX[0] < 0 && event.getX(1) - lastX[1] < 0))
                                && Math.abs(event.getX(0) - lastX[0]) > minTriggerDistance
                                && Math.abs(event.getX(1) - lastX[1]) > minTriggerDistance) {
                            isTwoMoving = true;
                            twoMoveDirection = MOVE_DIRECTION_X;
                        }
                        if (((event.getY(0) - lastY[0] > 0 && event.getY(1) - lastY[1] > 0)
                                || (event.getY(0) - lastY[0] < 0 && event.getY(1) - lastY[1] < 0))
                                && Math.abs(event.getY(0) - lastY[0]) > minTriggerDistance
                                && Math.abs(event.getY(1) - lastY[1]) > minTriggerDistance) {
                            isTwoMoving = true;
                            twoMoveDirection = MOVE_DIRECTION_Y;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_POINTER_UP:
                    isTwoMoving = false;
                    twoMoveDirection = NO_EVENT;
                    break;
            }

            if (isTwoMoving) {
                float translateX = 0.0f;
                float translateY = 0.0f;
                switch (twoMoveDirection) {
                    case MOVE_DIRECTION_X:
                        if (Math.abs(event.getX(0) - lastX[0]) > Math.abs(event.getX(1) - lastX[1])) {
                            translateX = event.getX(0) - lastX[0];
                        } else {
                            translateX = event.getX(1) - lastX[1];
                        }
                        break;
                    case MOVE_DIRECTION_Y:
                        if (Math.abs(event.getY(0) - lastY[0]) > Math.abs(event.getY(1) - lastY[1])) {
                            translateY = event.getY(0) - lastY[0];
                        } else {
                            translateY = event.getY(1) - lastY[1];
                        }
                        break;
                }
                result = onTwoFingerMove(event, twoMoveDirection, translateX, translateY);
            }

            lastX[0] = event.getX(0);
            lastX[1] = event.getX(1);
            lastY[0] = event.getY(0);
            lastY[1] = event.getY(1);
        }
        return result;
    }
}
