package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.b;
import androidx.appcompat.view.g;
import androidx.appcompat.view.h;
import androidx.appcompat.view.menu.h;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.o;
import androidx.appcompat.widget.x;
import androidx.core.f.r;
import androidx.core.f.v;
import androidx.core.f.w;
import androidx.core.f.y;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.azeckoski.reflectutils.transcoders.JSONTranscoder;

public class j extends ActionBar implements ActionBarOverlayLayout.a {
    static final /* synthetic */ boolean s = (!j.class.desiredAssertionStatus());
    private static final Interpolator t = new AccelerateInterpolator();
    private static final Interpolator u = new DecelerateInterpolator();
    private boolean A;
    private boolean B;
    private ArrayList<ActionBar.a> C = new ArrayList<>();
    private boolean D;
    private int E = 0;
    private boolean F;
    private boolean G = true;
    private boolean H;
    Context a;
    ActionBarOverlayLayout b;
    ActionBarContainer c;
    o d;
    ActionBarContextView e;
    View f;
    x g;
    a h;
    b i;
    b.a j;
    boolean k = true;
    boolean l;
    boolean m;
    h n;
    boolean o;
    final w p = new androidx.core.f.x() {
        /* class androidx.appcompat.app.j.AnonymousClass1 */

        @Override // androidx.core.f.w, androidx.core.f.x
        public void b(View view) {
            if (j.this.k && j.this.f != null) {
                j.this.f.setTranslationY(0.0f);
                j.this.c.setTranslationY(0.0f);
            }
            j.this.c.setVisibility(8);
            j.this.c.setTransitioning(false);
            j jVar = j.this;
            jVar.n = null;
            jVar.h();
            if (j.this.b != null) {
                r.p(j.this.b);
            }
        }
    };
    final w q = new androidx.core.f.x() {
        /* class androidx.appcompat.app.j.AnonymousClass2 */

        @Override // androidx.core.f.w, androidx.core.f.x
        public void b(View view) {
            j jVar = j.this;
            jVar.n = null;
            jVar.c.requestLayout();
        }
    };
    final y r = new y() {
        /* class androidx.appcompat.app.j.AnonymousClass3 */

        @Override // androidx.core.f.y
        public void a(View view) {
            ((View) j.this.c.getParent()).invalidate();
        }
    };
    private Context v;
    private Activity w;
    private Dialog x;
    private ArrayList<Object> y = new ArrayList<>();
    private int z = -1;

    static boolean a(boolean z2, boolean z3, boolean z4) {
        if (z4) {
            return true;
        }
        return !z2 && !z3;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public void m() {
    }

    public j(Activity activity, boolean z2) {
        this.w = activity;
        View decorView = activity.getWindow().getDecorView();
        a(decorView);
        if (!z2) {
            this.f = decorView.findViewById(16908290);
        }
    }

    public j(Dialog dialog) {
        this.x = dialog;
        a(dialog.getWindow().getDecorView());
    }

    private void a(View view) {
        this.b = (ActionBarOverlayLayout) view.findViewById(R.id.decor_content_parent);
        ActionBarOverlayLayout actionBarOverlayLayout = this.b;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.d = b(view.findViewById(R.id.action_bar));
        this.e = (ActionBarContextView) view.findViewById(R.id.action_context_bar);
        this.c = (ActionBarContainer) view.findViewById(R.id.action_bar_container);
        o oVar = this.d;
        if (oVar == null || this.e == null || this.c == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.a = oVar.b();
        boolean z2 = (this.d.o() & 4) != 0;
        if (z2) {
            this.A = true;
        }
        androidx.appcompat.view.a a2 = androidx.appcompat.view.a.a(this.a);
        a(a2.f() || z2);
        k(a2.d());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(R.styleable.ActionBar_hideOnContentScroll, false)) {
            b(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            a((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private o b(View view) {
        if (view instanceof o) {
            return (o) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != null ? view.getClass().getSimpleName() : JSONTranscoder.NULL);
        throw new IllegalStateException(sb.toString());
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(float f2) {
        r.a(this.c, f2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(Configuration configuration) {
        k(androidx.appcompat.view.a.a(this.a).d());
    }

    private void k(boolean z2) {
        this.D = z2;
        if (!this.D) {
            this.d.a((x) null);
            this.c.setTabContainer(this.g);
        } else {
            this.c.setTabContainer(null);
            this.d.a(this.g);
        }
        boolean z3 = true;
        boolean z4 = i() == 2;
        x xVar = this.g;
        if (xVar != null) {
            if (z4) {
                xVar.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.b;
                if (actionBarOverlayLayout != null) {
                    r.p(actionBarOverlayLayout);
                }
            } else {
                xVar.setVisibility(8);
            }
        }
        this.d.a(!this.D && z4);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.b;
        if (this.D || !z4) {
            z3 = false;
        }
        actionBarOverlayLayout2.setHasNonEmbeddedTabs(z3);
    }

    /* access modifiers changed from: package-private */
    public void h() {
        b.a aVar = this.j;
        if (aVar != null) {
            aVar.a(this.i);
            this.i = null;
            this.j = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public void a(int i2) {
        this.E = i2;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void d(boolean z2) {
        h hVar;
        this.H = z2;
        if (!z2 && (hVar = this.n) != null) {
            hVar.c();
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void e(boolean z2) {
        if (z2 != this.B) {
            this.B = z2;
            int size = this.C.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.C.get(i2).a(z2);
            }
        }
    }

    public void f(boolean z2) {
        a(z2 ? 4 : 0, 4);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(boolean z2) {
        this.d.b(z2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void a(CharSequence charSequence) {
        this.d.a(charSequence);
    }

    public void a(int i2, int i3) {
        int o2 = this.d.o();
        if ((i3 & 4) != 0) {
            this.A = true;
        }
        this.d.c((i2 & i3) | ((i3 ^ -1) & o2));
    }

    public int i() {
        return this.d.p();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int a() {
        return this.d.o();
    }

    @Override // androidx.appcompat.app.ActionBar
    public b a(b.a aVar) {
        a aVar2 = this.h;
        if (aVar2 != null) {
            aVar2.c();
        }
        this.b.setHideOnContentScrollEnabled(false);
        this.e.c();
        a aVar3 = new a(this.e.getContext(), aVar);
        if (!aVar3.e()) {
            return null;
        }
        this.h = aVar3;
        aVar3.d();
        this.e.a(aVar3);
        j(true);
        this.e.sendAccessibilityEvent(32);
        return aVar3;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public void g(boolean z2) {
        this.k = z2;
    }

    private void n() {
        if (!this.F) {
            this.F = true;
            ActionBarOverlayLayout actionBarOverlayLayout = this.b;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(true);
            }
            l(false);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public void j() {
        if (this.m) {
            this.m = false;
            l(true);
        }
    }

    private void o() {
        if (this.F) {
            this.F = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.b;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            l(false);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public void k() {
        if (!this.m) {
            this.m = true;
            l(true);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void b(boolean z2) {
        if (!z2 || this.b.a()) {
            this.o = z2;
            this.b.setHideOnContentScrollEnabled(z2);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    private void l(boolean z2) {
        if (a(this.l, this.m, this.F)) {
            if (!this.G) {
                this.G = true;
                h(z2);
            }
        } else if (this.G) {
            this.G = false;
            i(z2);
        }
    }

    public void h(boolean z2) {
        View view;
        View view2;
        h hVar = this.n;
        if (hVar != null) {
            hVar.c();
        }
        this.c.setVisibility(0);
        if (this.E != 0 || (!this.H && !z2)) {
            this.c.setAlpha(1.0f);
            this.c.setTranslationY(0.0f);
            if (this.k && (view = this.f) != null) {
                view.setTranslationY(0.0f);
            }
            this.q.b(null);
        } else {
            this.c.setTranslationY(0.0f);
            float f2 = (float) (-this.c.getHeight());
            if (z2) {
                int[] iArr = {0, 0};
                this.c.getLocationInWindow(iArr);
                f2 -= (float) iArr[1];
            }
            this.c.setTranslationY(f2);
            h hVar2 = new h();
            v b2 = r.l(this.c).b(0.0f);
            b2.a(this.r);
            hVar2.a(b2);
            if (this.k && (view2 = this.f) != null) {
                view2.setTranslationY(f2);
                hVar2.a(r.l(this.f).b(0.0f));
            }
            hVar2.a(u);
            hVar2.a(250);
            hVar2.a(this.q);
            this.n = hVar2;
            hVar2.a();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.b;
        if (actionBarOverlayLayout != null) {
            r.p(actionBarOverlayLayout);
        }
    }

    public void i(boolean z2) {
        View view;
        h hVar = this.n;
        if (hVar != null) {
            hVar.c();
        }
        if (this.E != 0 || (!this.H && !z2)) {
            this.p.b(null);
            return;
        }
        this.c.setAlpha(1.0f);
        this.c.setTransitioning(true);
        h hVar2 = new h();
        float f2 = (float) (-this.c.getHeight());
        if (z2) {
            int[] iArr = {0, 0};
            this.c.getLocationInWindow(iArr);
            f2 -= (float) iArr[1];
        }
        v b2 = r.l(this.c).b(f2);
        b2.a(this.r);
        hVar2.a(b2);
        if (this.k && (view = this.f) != null) {
            hVar2.a(r.l(view).b(f2));
        }
        hVar2.a(t);
        hVar2.a(250);
        hVar2.a(this.p);
        this.n = hVar2;
        hVar2.a();
    }

    public void j(boolean z2) {
        v vVar;
        v vVar2;
        if (z2) {
            n();
        } else {
            o();
        }
        if (p()) {
            if (z2) {
                vVar = this.d.a(4, 100);
                vVar2 = this.e.a(0, 200);
            } else {
                vVar2 = this.d.a(0, 200);
                vVar = this.e.a(8, 100);
            }
            h hVar = new h();
            hVar.a(vVar, vVar2);
            hVar.a();
        } else if (z2) {
            this.d.d(4);
            this.e.setVisibility(0);
        } else {
            this.d.d(0);
            this.e.setVisibility(8);
        }
    }

    private boolean p() {
        return r.x(this.c);
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context b() {
        if (this.v == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.v = new ContextThemeWrapper(this.a, i2);
            } else {
                this.v = this.a;
            }
        }
        return this.v;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public void l() {
        h hVar = this.n;
        if (hVar != null) {
            hVar.c();
            this.n = null;
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean f() {
        o oVar = this.d;
        if (oVar == null || !oVar.c()) {
            return false;
        }
        this.d.d();
        return true;
    }

    public class a extends b implements h.a {
        private final Context b;
        private final androidx.appcompat.view.menu.h c;
        private b.a d;
        private WeakReference<View> e;

        public a(Context context, b.a aVar) {
            this.b = context;
            this.d = aVar;
            this.c = new androidx.appcompat.view.menu.h(context).setDefaultShowAsAction(1);
            this.c.setCallback(this);
        }

        @Override // androidx.appcompat.view.b
        public MenuInflater a() {
            return new g(this.b);
        }

        @Override // androidx.appcompat.view.b
        public Menu b() {
            return this.c;
        }

        @Override // androidx.appcompat.view.b
        public void c() {
            if (j.this.h == this) {
                if (!j.a(j.this.l, j.this.m, false)) {
                    j jVar = j.this;
                    jVar.i = this;
                    jVar.j = this.d;
                } else {
                    this.d.a(this);
                }
                this.d = null;
                j.this.j(false);
                j.this.e.b();
                j.this.d.a().sendAccessibilityEvent(32);
                j.this.b.setHideOnContentScrollEnabled(j.this.o);
                j.this.h = null;
            }
        }

        @Override // androidx.appcompat.view.b
        public void d() {
            if (j.this.h == this) {
                this.c.stopDispatchingItemsChanged();
                try {
                    this.d.b(this, this.c);
                } finally {
                    this.c.startDispatchingItemsChanged();
                }
            }
        }

        public boolean e() {
            this.c.stopDispatchingItemsChanged();
            try {
                return this.d.a(this, this.c);
            } finally {
                this.c.startDispatchingItemsChanged();
            }
        }

        @Override // androidx.appcompat.view.b
        public void a(View view) {
            j.this.e.setCustomView(view);
            this.e = new WeakReference<>(view);
        }

        @Override // androidx.appcompat.view.b
        public void a(CharSequence charSequence) {
            j.this.e.setSubtitle(charSequence);
        }

        @Override // androidx.appcompat.view.b
        public void b(CharSequence charSequence) {
            j.this.e.setTitle(charSequence);
        }

        @Override // androidx.appcompat.view.b
        public void a(int i) {
            b(j.this.a.getResources().getString(i));
        }

        @Override // androidx.appcompat.view.b
        public void b(int i) {
            a((CharSequence) j.this.a.getResources().getString(i));
        }

        @Override // androidx.appcompat.view.b
        public CharSequence f() {
            return j.this.e.getTitle();
        }

        @Override // androidx.appcompat.view.b
        public CharSequence g() {
            return j.this.e.getSubtitle();
        }

        @Override // androidx.appcompat.view.b
        public void a(boolean z) {
            super.a(z);
            j.this.e.setTitleOptional(z);
        }

        @Override // androidx.appcompat.view.b
        public boolean h() {
            return j.this.e.d();
        }

        @Override // androidx.appcompat.view.b
        public View i() {
            WeakReference<View> weakReference = this.e;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // androidx.appcompat.view.menu.h.a
        public boolean onMenuItemSelected(androidx.appcompat.view.menu.h hVar, MenuItem menuItem) {
            b.a aVar = this.d;
            if (aVar != null) {
                return aVar.a(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.h.a
        public void onMenuModeChange(androidx.appcompat.view.menu.h hVar) {
            if (this.d != null) {
                d();
                j.this.e.a();
            }
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void c(boolean z2) {
        if (!this.A) {
            f(z2);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean a(int i2, KeyEvent keyEvent) {
        Menu b2;
        a aVar = this.h;
        if (aVar == null || (b2 = aVar.b()) == null) {
            return false;
        }
        boolean z2 = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z2 = false;
        }
        b2.setQwertyMode(z2);
        return b2.performShortcut(i2, keyEvent, 0);
    }
}
