package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.menu.h;
import androidx.appcompat.view.menu.o;
import androidx.appcompat.widget.o;
import androidx.core.f.r;
import java.util.ArrayList;

class g extends ActionBar {
    o a;
    Window.Callback b;
    private boolean c;
    private boolean d;
    private ArrayList<ActionBar.a> e;
    private final Runnable f;

    @Override // androidx.appcompat.app.ActionBar
    public void a(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void c(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void d(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(float f2) {
        r.a(this.a.a(), f2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context b() {
        return this.a.b();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(Configuration configuration) {
        super.a(configuration);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(CharSequence charSequence) {
        this.a.a(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public int a() {
        return this.a.o();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean c() {
        return this.a.k();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean d() {
        return this.a.l();
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean e() {
        this.a.a().removeCallbacks(this.f);
        r.a(this.a.a(), this.f);
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean f() {
        if (!this.a.c()) {
            return false;
        }
        this.a.d();
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            c();
        }
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean a(int i, KeyEvent keyEvent) {
        Menu h = h();
        if (h == null) {
            return false;
        }
        boolean z = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z = false;
        }
        h.setQwertyMode(z);
        return h.performShortcut(i, keyEvent, 0);
    }

    /* access modifiers changed from: package-private */
    @Override // androidx.appcompat.app.ActionBar
    public void g() {
        this.a.a().removeCallbacks(this.f);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void e(boolean z) {
        if (z != this.d) {
            this.d = z;
            int size = this.e.size();
            for (int i = 0; i < size; i++) {
                this.e.get(i).a(z);
            }
        }
    }

    private Menu h() {
        if (!this.c) {
            this.a.a(new a(), new b());
            this.c = true;
        }
        return this.a.q();
    }

    /* access modifiers changed from: private */
    public final class a implements o.a {
        private boolean b;

        a() {
        }

        @Override // androidx.appcompat.view.menu.o.a
        public boolean a(h hVar) {
            if (g.this.b == null) {
                return false;
            }
            g.this.b.onMenuOpened(108, hVar);
            return true;
        }

        @Override // androidx.appcompat.view.menu.o.a
        public void a(h hVar, boolean z) {
            if (!this.b) {
                this.b = true;
                g.this.a.n();
                if (g.this.b != null) {
                    g.this.b.onPanelClosed(108, hVar);
                }
                this.b = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public final class b implements h.a {
        @Override // androidx.appcompat.view.menu.h.a
        public boolean onMenuItemSelected(h hVar, MenuItem menuItem) {
            return false;
        }

        b() {
        }

        @Override // androidx.appcompat.view.menu.h.a
        public void onMenuModeChange(h hVar) {
            if (g.this.b == null) {
                return;
            }
            if (g.this.a.i()) {
                g.this.b.onPanelClosed(108, hVar);
            } else if (g.this.b.onPreparePanel(0, null, hVar)) {
                g.this.b.onMenuOpened(108, hVar);
            }
        }
    }
}
