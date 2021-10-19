package androidx.appcompat.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.b;
import androidx.appcompat.widget.aj;
import androidx.core.app.a;
import androidx.core.app.d;
import androidx.core.app.m;
import androidx.fragment.app.FragmentActivity;

public class AppCompatActivity extends FragmentActivity implements b, m.a {
    private c a;
    private int b = 0;
    private Resources c;

    public void b(m mVar) {
    }

    @Deprecated
    public void d() {
    }

    @Override // androidx.appcompat.app.b
    public void onSupportActionModeFinished(b bVar) {
    }

    @Override // androidx.appcompat.app.b
    public void onSupportActionModeStarted(b bVar) {
    }

    @Override // androidx.appcompat.app.b
    public b onWindowStartingSupportActionMode(b.a aVar) {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        c e = e();
        e.h();
        e.a(bundle);
        if (e.i() && this.b != 0) {
            if (Build.VERSION.SDK_INT >= 23) {
                onApplyThemeResource(getTheme(), this.b, false);
            } else {
                setTheme(this.b);
            }
        }
        super.onCreate(bundle);
    }

    @Override // android.view.ContextThemeWrapper, android.app.Activity
    public void setTheme(int i) {
        super.setTheme(i);
        this.b = i;
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        e().b(bundle);
    }

    public ActionBar a() {
        return e().a();
    }

    public MenuInflater getMenuInflater() {
        return e().b();
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        e().b(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        e().a(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        e().a(view, layoutParams);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        e().b(view, layoutParams);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        e().a(configuration);
        if (this.c != null) {
            this.c.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onPostResume() {
        super.onPostResume();
        e().e();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onStart() {
        super.onStart();
        e().c();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onStop() {
        super.onStop();
        e().d();
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        return (T) e().a(i);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        ActionBar a2 = a();
        if (menuItem.getItemId() != 16908332 || a2 == null || (a2.a() & 4) == 0) {
            return false;
        }
        return b();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        e().g();
    }

    /* access modifiers changed from: protected */
    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        e().a(charSequence);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void supportInvalidateOptionsMenu() {
        e().f();
    }

    public void invalidateOptionsMenu() {
        e().f();
    }

    public void a(m mVar) {
        mVar.a((Activity) this);
    }

    public boolean b() {
        Intent c2 = c();
        if (c2 == null) {
            return false;
        }
        if (a(c2)) {
            m a2 = m.a((Context) this);
            a(a2);
            b(a2);
            a2.a();
            try {
                a.a((Activity) this);
                return true;
            } catch (IllegalStateException unused) {
                finish();
                return true;
            }
        } else {
            b(c2);
            return true;
        }
    }

    @Override // androidx.core.app.m.a
    public Intent c() {
        return d.a(this);
    }

    public boolean a(Intent intent) {
        return d.a(this, intent);
    }

    public void b(Intent intent) {
        d.b(this, intent);
    }

    public void onContentChanged() {
        d();
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        e().c(bundle);
    }

    public c e() {
        if (this.a == null) {
            this.a = c.a(this, this);
        }
        return this.a;
    }

    @Override // androidx.core.app.ComponentActivity
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        ActionBar a2 = a();
        if (keyCode != 82 || a2 == null || !a2.a(keyEvent)) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    public Resources getResources() {
        if (this.c == null && aj.a()) {
            this.c = new aj(this, super.getResources());
        }
        Resources resources = this.c;
        return resources == null ? super.getResources() : resources;
    }

    private boolean a(int i, KeyEvent keyEvent) {
        Window window;
        return Build.VERSION.SDK_INT < 26 && !keyEvent.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) && keyEvent.getRepeatCount() == 0 && !KeyEvent.isModifierKey(keyEvent.getKeyCode()) && (window = getWindow()) != null && window.getDecorView() != null && window.getDecorView().dispatchKeyShortcutEvent(keyEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void openOptionsMenu() {
        ActionBar a2 = a();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (a2 == null || !a2.c()) {
            super.openOptionsMenu();
        }
    }

    public void closeOptionsMenu() {
        ActionBar a2 = a();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (a2 == null || !a2.d()) {
            super.closeOptionsMenu();
        }
    }
}
