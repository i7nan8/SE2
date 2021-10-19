package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class c {
    private static int a = -1;

    public abstract <T extends View> T a(int i);

    public abstract ActionBar a();

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(View view);

    public abstract void a(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void a(CharSequence charSequence);

    public abstract MenuInflater b();

    public abstract void b(int i);

    public abstract void b(Bundle bundle);

    public abstract void b(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void c();

    public abstract void c(Bundle bundle);

    public abstract boolean c(int i);

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract boolean i();

    public static c a(Activity activity, b bVar) {
        return new AppCompatDelegateImpl(activity, activity.getWindow(), bVar);
    }

    public static c a(Dialog dialog, b bVar) {
        return new AppCompatDelegateImpl(dialog.getContext(), dialog.getWindow(), bVar);
    }

    c() {
    }

    public static int j() {
        return a;
    }
}
