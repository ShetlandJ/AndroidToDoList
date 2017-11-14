package myfirstgame.todolist;

import android.content.Context;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by James on 14/11/2017.
 */

class SelectableBottomNavigationView extends BottomNavigationView {

    public SelectableBottomNavigationView(Context context) {
        super(context);
    }

    public SelectableBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectableBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSelected(int index) {
        try {
            Field f = BottomNavigationView.class.getDeclaredField("mMenuView");
            f.setAccessible(true);
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) f.get(this);

            try {
                Method method = menuView.getClass().getDeclaredMethod("activateNewButton", Integer.TYPE);
                method.setAccessible(true);
                method.invoke(menuView, index);
            } catch (SecurityException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
