package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.b;
import androidx.appcompat.view.f;
import androidx.appcompat.view.g;
import androidx.appcompat.view.i;
import androidx.appcompat.view.menu.h;
import androidx.appcompat.view.menu.o;
import androidx.appcompat.view.menu.p;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.ae;
import androidx.appcompat.widget.aj;
import androidx.appcompat.widget.ak;
import androidx.appcompat.widget.n;
import androidx.appcompat.widget.r;
import androidx.core.f.d;
import androidx.core.f.o;
import androidx.core.f.r;
import androidx.core.f.v;
import androidx.core.f.w;
import androidx.core.f.x;
import androidx.core.f.z;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.trusteer.tas.TasDefs;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* access modifiers changed from: package-private */
public class AppCompatDelegateImpl extends c implements LayoutInflater.Factory2, h.a {
    private static final boolean u = (Build.VERSION.SDK_INT < 21);
    private static final int[] v = {16842836};
    private static boolean w = true;
    private f A;
    private boolean B = true;
    private boolean C;
    private ViewGroup D;
    private TextView E;
    private View F;
    private boolean G;
    private boolean H;
    private boolean I;
    private PanelFeatureState[] J;
    private PanelFeatureState K;
    private boolean L;
    private int M = -100;
    private boolean N;
    private d O;
    private final Runnable P = new Runnable() {
        /* class androidx.appcompat.app.AppCompatDelegateImpl.AnonymousClass2 */

        public void run() {
            if ((AppCompatDelegateImpl.this.t & 1) != 0) {
                AppCompatDelegateImpl.this.g(0);
            }
            if ((AppCompatDelegateImpl.this.t & TasDefs.ADDITIONAL_DATA_MAX_LENGTH) != 0) {
                AppCompatDelegateImpl.this.g(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.s = false;
            appCompatDelegateImpl.t = 0;
        }
    };
    private boolean Q;
    private Rect R;
    private Rect S;
    private AppCompatViewInflater T;
    final Context a;
    final Window b;
    final Window.Callback c;
    final Window.Callback d;
    final b e;
    ActionBar f;
    MenuInflater g;
    androidx.appcompat.view.b h;
    ActionBarContextView i;
    PopupWindow j;
    Runnable k;
    v l = null;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    int t;
    private CharSequence x;
    private n y;
    private a z;

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup) {
    }

    static {
        if (u && !w) {
            final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                /* class androidx.appcompat.app.AppCompatDelegateImpl.AnonymousClass1 */

                public void uncaughtException(Thread thread, Throwable th) {
                    if (a(th)) {
                        Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                        notFoundException.initCause(th.getCause());
                        notFoundException.setStackTrace(th.getStackTrace());
                        defaultUncaughtExceptionHandler.uncaughtException(thread, notFoundException);
                        return;
                    }
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }

                private boolean a(Throwable th) {
                    String message;
                    if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                        return false;
                    }
                    if (message.contains("drawable") || message.contains("Drawable")) {
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    AppCompatDelegateImpl(Context context, Window window, b bVar) {
        this.a = context;
        this.b = window;
        this.e = bVar;
        this.c = this.b.getCallback();
        Window.Callback callback = this.c;
        if (!(callback instanceof c)) {
            this.d = new c(callback);
            this.b.setCallback(this.d);
            ae a2 = ae.a(context, (AttributeSet) null, v);
            Drawable b2 = a2.b(0);
            if (b2 != null) {
                this.b.setBackgroundDrawable(b2);
            }
            a2.a();
            return;
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    @Override // androidx.appcompat.app.c
    public void a(Bundle bundle) {
        Window.Callback callback = this.c;
        if (callback instanceof Activity) {
            String str = null;
            try {
                str = androidx.core.app.d.b((Activity) callback);
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                ActionBar k2 = k();
                if (k2 == null) {
                    this.Q = true;
                } else {
                    k2.c(true);
                }
            }
        }
        if (bundle != null && this.M == -100) {
            this.M = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    @Override // androidx.appcompat.app.c
    public void b(Bundle bundle) {
        u();
    }

    @Override // androidx.appcompat.app.c
    public ActionBar a() {
        t();
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public final ActionBar k() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public final Window.Callback l() {
        return this.b.getCallback();
    }

    private void t() {
        u();
        if (this.m && this.f == null) {
            Window.Callback callback = this.c;
            if (callback instanceof Activity) {
                this.f = new j((Activity) callback, this.n);
            } else if (callback instanceof Dialog) {
                this.f = new j((Dialog) callback);
            }
            ActionBar actionBar = this.f;
            if (actionBar != null) {
                actionBar.c(this.Q);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Context m() {
        ActionBar a2 = a();
        Context b2 = a2 != null ? a2.b() : null;
        return b2 == null ? this.a : b2;
    }

    @Override // androidx.appcompat.app.c
    public MenuInflater b() {
        if (this.g == null) {
            t();
            ActionBar actionBar = this.f;
            this.g = new g(actionBar != null ? actionBar.b() : this.a);
        }
        return this.g;
    }

    @Override // androidx.appcompat.app.c
    public <T extends View> T a(int i2) {
        u();
        return (T) this.b.findViewById(i2);
    }

    @Override // androidx.appcompat.app.c
    public void a(Configuration configuration) {
        ActionBar a2;
        if (this.m && this.C && (a2 = a()) != null) {
            a2.a(configuration);
        }
        androidx.appcompat.widget.f.a().a(this.a);
        i();
    }

    @Override // androidx.appcompat.app.c
    public void c() {
        i();
    }

    @Override // androidx.appcompat.app.c
    public void d() {
        ActionBar a2 = a();
        if (a2 != null) {
            a2.d(false);
        }
        d dVar = this.O;
        if (dVar != null) {
            dVar.d();
        }
    }

    @Override // androidx.appcompat.app.c
    public void e() {
        ActionBar a2 = a();
        if (a2 != null) {
            a2.d(true);
        }
    }

    @Override // androidx.appcompat.app.c
    public void a(View view) {
        u();
        ViewGroup viewGroup = (ViewGroup) this.D.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.c.onContentChanged();
    }

    @Override // androidx.appcompat.app.c
    public void b(int i2) {
        u();
        ViewGroup viewGroup = (ViewGroup) this.D.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.a).inflate(i2, viewGroup);
        this.c.onContentChanged();
    }

    @Override // androidx.appcompat.app.c
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        u();
        ViewGroup viewGroup = (ViewGroup) this.D.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.c.onContentChanged();
    }

    @Override // androidx.appcompat.app.c
    public void b(View view, ViewGroup.LayoutParams layoutParams) {
        u();
        ((ViewGroup) this.D.findViewById(16908290)).addView(view, layoutParams);
        this.c.onContentChanged();
    }

    @Override // androidx.appcompat.app.c
    public void c(Bundle bundle) {
        int i2 = this.M;
        if (i2 != -100) {
            bundle.putInt("appcompat:local_night_mode", i2);
        }
    }

    @Override // androidx.appcompat.app.c
    public void g() {
        if (this.s) {
            this.b.getDecorView().removeCallbacks(this.P);
        }
        this.r = true;
        ActionBar actionBar = this.f;
        if (actionBar != null) {
            actionBar.g();
        }
        d dVar = this.O;
        if (dVar != null) {
            dVar.d();
        }
    }

    private void u() {
        if (!this.C) {
            this.D = v();
            CharSequence n2 = n();
            if (!TextUtils.isEmpty(n2)) {
                n nVar = this.y;
                if (nVar != null) {
                    nVar.setWindowTitle(n2);
                } else if (k() != null) {
                    k().a(n2);
                } else {
                    TextView textView = this.E;
                    if (textView != null) {
                        textView.setText(n2);
                    }
                }
            }
            w();
            a(this.D);
            this.C = true;
            PanelFeatureState a2 = a(0, false);
            if (this.r) {
                return;
            }
            if (a2 == null || a2.j == null) {
                j(108);
            }
        }
    }

    private ViewGroup v() {
        ViewGroup viewGroup;
        Context context;
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme);
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowActionBar)) {
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
                c(1);
            } else if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBar, false)) {
                c(108);
            }
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
                c(109);
            }
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
                c(10);
            }
            this.p = obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            this.b.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.a);
            if (this.q) {
                if (this.o) {
                    viewGroup = (ViewGroup) from.inflate(R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null);
                } else {
                    viewGroup = (ViewGroup) from.inflate(R.layout.abc_screen_simple, (ViewGroup) null);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    r.a(viewGroup, new o() {
                        /* class androidx.appcompat.app.AppCompatDelegateImpl.AnonymousClass3 */

                        @Override // androidx.core.f.o
                        public z onApplyWindowInsets(View view, z zVar) {
                            int b = zVar.b();
                            int h = AppCompatDelegateImpl.this.h(b);
                            if (b != h) {
                                zVar = zVar.a(zVar.a(), h, zVar.c(), zVar.d());
                            }
                            return r.a(view, zVar);
                        }
                    });
                } else {
                    ((androidx.appcompat.widget.r) viewGroup).setOnFitSystemWindowsListener(new r.a() {
                        /* class androidx.appcompat.app.AppCompatDelegateImpl.AnonymousClass4 */

                        @Override // androidx.appcompat.widget.r.a
                        public void a(Rect rect) {
                            rect.top = AppCompatDelegateImpl.this.h(rect.top);
                        }
                    });
                }
            } else if (this.p) {
                viewGroup = (ViewGroup) from.inflate(R.layout.abc_dialog_title_material, (ViewGroup) null);
                this.n = false;
                this.m = false;
            } else if (this.m) {
                TypedValue typedValue = new TypedValue();
                this.a.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                if (typedValue.resourceId != 0) {
                    context = new androidx.appcompat.view.d(this.a, typedValue.resourceId);
                } else {
                    context = this.a;
                }
                viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.abc_screen_toolbar, (ViewGroup) null);
                this.y = (n) viewGroup.findViewById(R.id.decor_content_parent);
                this.y.setWindowCallback(l());
                if (this.n) {
                    this.y.a(109);
                }
                if (this.G) {
                    this.y.a(2);
                }
                if (this.H) {
                    this.y.a(5);
                }
            } else {
                viewGroup = null;
            }
            if (viewGroup != null) {
                if (this.y == null) {
                    this.E = (TextView) viewGroup.findViewById(R.id.title);
                }
                ak.b(viewGroup);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R.id.action_bar_activity_content);
                ViewGroup viewGroup2 = (ViewGroup) this.b.findViewById(16908290);
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup2.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup2 instanceof FrameLayout) {
                        ((FrameLayout) viewGroup2).setForeground(null);
                    }
                }
                this.b.setContentView(viewGroup);
                contentFrameLayout.setAttachListener(new ContentFrameLayout.a() {
                    /* class androidx.appcompat.app.AppCompatDelegateImpl.AnonymousClass5 */

                    @Override // androidx.appcompat.widget.ContentFrameLayout.a
                    public void a() {
                    }

                    @Override // androidx.appcompat.widget.ContentFrameLayout.a
                    public void b() {
                        AppCompatDelegateImpl.this.s();
                    }
                });
                return viewGroup;
            }
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.m + ", windowActionBarOverlay: " + this.n + ", android:windowIsFloating: " + this.p + ", windowActionModeOverlay: " + this.o + ", windowNoTitle: " + this.q + " }");
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    private void w() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.D.findViewById(16908290);
        View decorView = this.b.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    @Override // androidx.appcompat.app.c
    public boolean c(int i2) {
        int k2 = k(i2);
        if (this.q && k2 == 108) {
            return false;
        }
        if (this.m && k2 == 1) {
            this.m = false;
        }
        switch (k2) {
            case 1:
                x();
                this.q = true;
                return true;
            case 2:
                x();
                this.G = true;
                return true;
            case 5:
                x();
                this.H = true;
                return true;
            case 10:
                x();
                this.o = true;
                return true;
            case 108:
                x();
                this.m = true;
                return true;
            case 109:
                x();
                this.n = true;
                return true;
            default:
                return this.b.requestFeature(k2);
        }
    }

    @Override // androidx.appcompat.app.c
    public final void a(CharSequence charSequence) {
        this.x = charSequence;
        n nVar = this.y;
        if (nVar != null) {
            nVar.setWindowTitle(charSequence);
        } else if (k() != null) {
            k().a(charSequence);
        } else {
            TextView textView = this.E;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final CharSequence n() {
        Window.Callback callback = this.c;
        if (callback instanceof Activity) {
            return ((Activity) callback).getTitle();
        }
        return this.x;
    }

    /* access modifiers changed from: package-private */
    public void d(int i2) {
        if (i2 == 108) {
            ActionBar a2 = a();
            if (a2 != null) {
                a2.e(false);
            }
        } else if (i2 == 0) {
            PanelFeatureState a3 = a(i2, true);
            if (a3.o) {
                a(a3, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void e(int i2) {
        ActionBar a2;
        if (i2 == 108 && (a2 = a()) != null) {
            a2.e(true);
        }
    }

    @Override // androidx.appcompat.view.menu.h.a
    public boolean onMenuItemSelected(h hVar, MenuItem menuItem) {
        PanelFeatureState a2;
        Window.Callback l2 = l();
        if (l2 == null || this.r || (a2 = a((Menu) hVar.getRootMenu())) == null) {
            return false;
        }
        return l2.onMenuItemSelected(a2.a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.h.a
    public void onMenuModeChange(h hVar) {
        a(hVar, true);
    }

    public androidx.appcompat.view.b a(b.a aVar) {
        b bVar;
        if (aVar != null) {
            androidx.appcompat.view.b bVar2 = this.h;
            if (bVar2 != null) {
                bVar2.c();
            }
            b bVar3 = new b(aVar);
            ActionBar a2 = a();
            if (a2 != null) {
                this.h = a2.a(bVar3);
                androidx.appcompat.view.b bVar4 = this.h;
                if (!(bVar4 == null || (bVar = this.e) == null)) {
                    bVar.onSupportActionModeStarted(bVar4);
                }
            }
            if (this.h == null) {
                this.h = b(bVar3);
            }
            return this.h;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    @Override // androidx.appcompat.app.c
    public void f() {
        ActionBar a2 = a();
        if (a2 == null || !a2.e()) {
            j(0);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    public androidx.appcompat.view.b b(b.a aVar) {
        androidx.appcompat.view.b bVar;
        androidx.appcompat.view.b bVar2;
        b bVar3;
        Context context;
        q();
        androidx.appcompat.view.b bVar4 = this.h;
        if (bVar4 != null) {
            bVar4.c();
        }
        if (!(aVar instanceof b)) {
            aVar = new b(aVar);
        }
        b bVar5 = this.e;
        if (bVar5 != null && !this.r) {
            try {
                bVar = bVar5.onWindowStartingSupportActionMode(aVar);
            } catch (AbstractMethodError unused) {
            }
            if (bVar == null) {
                this.h = bVar;
            } else {
                boolean z2 = true;
                if (this.i == null) {
                    if (this.p) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme = this.a.getTheme();
                        theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            Resources.Theme newTheme = this.a.getResources().newTheme();
                            newTheme.setTo(theme);
                            newTheme.applyStyle(typedValue.resourceId, true);
                            context = new androidx.appcompat.view.d(this.a, 0);
                            context.getTheme().setTo(newTheme);
                        } else {
                            context = this.a;
                        }
                        this.i = new ActionBarContextView(context);
                        this.j = new PopupWindow(context, (AttributeSet) null, R.attr.actionModePopupWindowStyle);
                        androidx.core.widget.h.a(this.j, 2);
                        this.j.setContentView(this.i);
                        this.j.setWidth(-1);
                        context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
                        this.i.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics()));
                        this.j.setHeight(-2);
                        this.k = new Runnable() {
                            /* class androidx.appcompat.app.AppCompatDelegateImpl.AnonymousClass6 */

                            public void run() {
                                AppCompatDelegateImpl.this.j.showAtLocation(AppCompatDelegateImpl.this.i, 55, 0, 0);
                                AppCompatDelegateImpl.this.q();
                                if (AppCompatDelegateImpl.this.o()) {
                                    AppCompatDelegateImpl.this.i.setAlpha(0.0f);
                                    AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                                    appCompatDelegateImpl.l = androidx.core.f.r.l(appCompatDelegateImpl.i).a(1.0f);
                                    AppCompatDelegateImpl.this.l.a(new x() {
                                        /* class androidx.appcompat.app.AppCompatDelegateImpl.AnonymousClass6.AnonymousClass1 */

                                        @Override // androidx.core.f.w, androidx.core.f.x
                                        public void a(View view) {
                                            AppCompatDelegateImpl.this.i.setVisibility(0);
                                        }

                                        @Override // androidx.core.f.w, androidx.core.f.x
                                        public void b(View view) {
                                            AppCompatDelegateImpl.this.i.setAlpha(1.0f);
                                            AppCompatDelegateImpl.this.l.a((w) null);
                                            AppCompatDelegateImpl.this.l = null;
                                        }
                                    });
                                    return;
                                }
                                AppCompatDelegateImpl.this.i.setAlpha(1.0f);
                                AppCompatDelegateImpl.this.i.setVisibility(0);
                            }
                        };
                    } else {
                        ViewStubCompat viewStubCompat = (ViewStubCompat) this.D.findViewById(R.id.action_mode_bar_stub);
                        if (viewStubCompat != null) {
                            viewStubCompat.setLayoutInflater(LayoutInflater.from(m()));
                            this.i = (ActionBarContextView) viewStubCompat.a();
                        }
                    }
                }
                if (this.i != null) {
                    q();
                    this.i.c();
                    Context context2 = this.i.getContext();
                    ActionBarContextView actionBarContextView = this.i;
                    if (this.j != null) {
                        z2 = false;
                    }
                    androidx.appcompat.view.e eVar = new androidx.appcompat.view.e(context2, actionBarContextView, aVar, z2);
                    if (aVar.a(eVar, eVar.b())) {
                        eVar.d();
                        this.i.a(eVar);
                        this.h = eVar;
                        if (o()) {
                            this.i.setAlpha(0.0f);
                            this.l = androidx.core.f.r.l(this.i).a(1.0f);
                            this.l.a(new x() {
                                /* class androidx.appcompat.app.AppCompatDelegateImpl.AnonymousClass7 */

                                @Override // androidx.core.f.w, androidx.core.f.x
                                public void a(View view) {
                                    AppCompatDelegateImpl.this.i.setVisibility(0);
                                    AppCompatDelegateImpl.this.i.sendAccessibilityEvent(32);
                                    if (AppCompatDelegateImpl.this.i.getParent() instanceof View) {
                                        androidx.core.f.r.p((View) AppCompatDelegateImpl.this.i.getParent());
                                    }
                                }

                                @Override // androidx.core.f.w, androidx.core.f.x
                                public void b(View view) {
                                    AppCompatDelegateImpl.this.i.setAlpha(1.0f);
                                    AppCompatDelegateImpl.this.l.a((w) null);
                                    AppCompatDelegateImpl.this.l = null;
                                }
                            });
                        } else {
                            this.i.setAlpha(1.0f);
                            this.i.setVisibility(0);
                            this.i.sendAccessibilityEvent(32);
                            if (this.i.getParent() instanceof View) {
                                androidx.core.f.r.p((View) this.i.getParent());
                            }
                        }
                        if (this.j != null) {
                            this.b.getDecorView().post(this.k);
                        }
                    } else {
                        this.h = null;
                    }
                }
            }
            bVar2 = this.h;
            if (!(bVar2 == null || (bVar3 = this.e) == null)) {
                bVar3.onSupportActionModeStarted(bVar2);
            }
            return this.h;
        }
        bVar = null;
        if (bVar == null) {
        }
        bVar2 = this.h;
        bVar3.onSupportActionModeStarted(bVar2);
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public final boolean o() {
        ViewGroup viewGroup;
        return this.C && (viewGroup = this.D) != null && androidx.core.f.r.x(viewGroup);
    }

    public boolean p() {
        return this.B;
    }

    /* access modifiers changed from: package-private */
    public void q() {
        v vVar = this.l;
        if (vVar != null) {
            vVar.b();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean r() {
        androidx.appcompat.view.b bVar = this.h;
        if (bVar != null) {
            bVar.c();
            return true;
        }
        ActionBar a2 = a();
        if (a2 == null || !a2.f()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, KeyEvent keyEvent) {
        ActionBar a2 = a();
        if (a2 != null && a2.a(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.K;
        if (panelFeatureState == null || !a(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.K == null) {
                PanelFeatureState a3 = a(0, true);
                b(a3, keyEvent);
                boolean a4 = a(a3, keyEvent.getKeyCode(), keyEvent, 1);
                a3.m = false;
                if (a4) {
                    return true;
                }
            }
            return false;
        }
        PanelFeatureState panelFeatureState2 = this.K;
        if (panelFeatureState2 != null) {
            panelFeatureState2.n = true;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a(KeyEvent keyEvent) {
        View decorView;
        Window.Callback callback = this.c;
        boolean z2 = true;
        if (((callback instanceof d.a) || (callback instanceof d)) && (decorView = this.b.getDecorView()) != null && androidx.core.f.d.a(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.c.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z2 = false;
        }
        return z2 ? c(keyCode, keyEvent) : b(keyCode, keyEvent);
    }

    /* access modifiers changed from: package-private */
    public boolean b(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z2 = this.L;
            this.L = false;
            PanelFeatureState a2 = a(0, false);
            if (a2 != null && a2.o) {
                if (!z2) {
                    a(a2, true);
                }
                return true;
            } else if (r()) {
                return true;
            }
        } else if (i2 == 82) {
            e(0, keyEvent);
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean c(int i2, KeyEvent keyEvent) {
        boolean z2 = true;
        if (i2 == 4) {
            if ((keyEvent.getFlags() & 128) == 0) {
                z2 = false;
            }
            this.L = z2;
        } else if (i2 == 82) {
            d(0, keyEvent);
            return true;
        }
        return false;
    }

    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z2;
        boolean z3 = false;
        if (this.T == null) {
            String string = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme).getString(R.styleable.AppCompatTheme_viewInflaterClass);
            if (string == null || AppCompatViewInflater.class.getName().equals(string)) {
                this.T = new AppCompatViewInflater();
            } else {
                try {
                    this.T = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.T = new AppCompatViewInflater();
                }
            }
        }
        if (u) {
            if (!(attributeSet instanceof XmlPullParser)) {
                z3 = a((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z3 = true;
            }
            z2 = z3;
        } else {
            z2 = false;
        }
        return this.T.createView(view, str, context, attributeSet, z2, u, true, aj.a());
    }

    private boolean a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.b.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || androidx.core.f.r.A((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    @Override // androidx.appcompat.app.c
    public void h() {
        LayoutInflater from = LayoutInflater.from(this.a);
        if (from.getFactory() == null) {
            androidx.core.f.e.a(from, this);
        } else if (!(from.getFactory2() instanceof AppCompatDelegateImpl)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return a(view, str, context, attributeSet);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private void a(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (!panelFeatureState.o && !this.r) {
            if (panelFeatureState.a == 0) {
                if ((this.a.getResources().getConfiguration().screenLayout & 15) == 4) {
                    return;
                }
            }
            Window.Callback l2 = l();
            if (l2 == null || l2.onMenuOpened(panelFeatureState.a, panelFeatureState.j)) {
                WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
                if (windowManager != null && b(panelFeatureState, keyEvent)) {
                    if (panelFeatureState.g == null || panelFeatureState.q) {
                        if (panelFeatureState.g == null) {
                            if (!a(panelFeatureState) || panelFeatureState.g == null) {
                                return;
                            }
                        } else if (panelFeatureState.q && panelFeatureState.g.getChildCount() > 0) {
                            panelFeatureState.g.removeAllViews();
                        }
                        if (c(panelFeatureState) && panelFeatureState.a()) {
                            ViewGroup.LayoutParams layoutParams2 = panelFeatureState.h.getLayoutParams();
                            if (layoutParams2 == null) {
                                layoutParams2 = new ViewGroup.LayoutParams(-2, -2);
                            }
                            panelFeatureState.g.setBackgroundResource(panelFeatureState.b);
                            ViewParent parent = panelFeatureState.h.getParent();
                            if (parent != null && (parent instanceof ViewGroup)) {
                                ((ViewGroup) parent).removeView(panelFeatureState.h);
                            }
                            panelFeatureState.g.addView(panelFeatureState.h, layoutParams2);
                            if (!panelFeatureState.h.hasFocus()) {
                                panelFeatureState.h.requestFocus();
                            }
                        } else {
                            return;
                        }
                    } else if (!(panelFeatureState.i == null || (layoutParams = panelFeatureState.i.getLayoutParams()) == null || layoutParams.width != -1)) {
                        i2 = -1;
                        panelFeatureState.n = false;
                        WindowManager.LayoutParams layoutParams3 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.d, panelFeatureState.e, CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE, 8519680, -3);
                        layoutParams3.gravity = panelFeatureState.c;
                        layoutParams3.windowAnimations = panelFeatureState.f;
                        windowManager.addView(panelFeatureState.g, layoutParams3);
                        panelFeatureState.o = true;
                        return;
                    }
                    i2 = -2;
                    panelFeatureState.n = false;
                    WindowManager.LayoutParams layoutParams32 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.d, panelFeatureState.e, CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE, 8519680, -3);
                    layoutParams32.gravity = panelFeatureState.c;
                    layoutParams32.windowAnimations = panelFeatureState.f;
                    windowManager.addView(panelFeatureState.g, layoutParams32);
                    panelFeatureState.o = true;
                    return;
                }
                return;
            }
            a(panelFeatureState, true);
        }
    }

    private boolean a(PanelFeatureState panelFeatureState) {
        panelFeatureState.a(m());
        panelFeatureState.g = new e(panelFeatureState.l);
        panelFeatureState.c = 81;
        return true;
    }

    private void a(h hVar, boolean z2) {
        n nVar = this.y;
        if (nVar == null || !nVar.e() || (ViewConfiguration.get(this.a).hasPermanentMenuKey() && !this.y.g())) {
            PanelFeatureState a2 = a(0, true);
            a2.q = true;
            a(a2, false);
            a(a2, (KeyEvent) null);
            return;
        }
        Window.Callback l2 = l();
        if (this.y.f() && z2) {
            this.y.i();
            if (!this.r) {
                l2.onPanelClosed(108, a(0, true).j);
            }
        } else if (l2 != null && !this.r) {
            if (this.s && (this.t & 1) != 0) {
                this.b.getDecorView().removeCallbacks(this.P);
                this.P.run();
            }
            PanelFeatureState a3 = a(0, true);
            if (a3.j != null && !a3.r && l2.onPreparePanel(0, a3.i, a3.j)) {
                l2.onMenuOpened(108, a3.j);
                this.y.h();
            }
        }
    }

    private boolean b(PanelFeatureState panelFeatureState) {
        Context context = this.a;
        if ((panelFeatureState.a == 0 || panelFeatureState.a == 108) && this.y != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
            Resources.Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            if (theme2 != null) {
                androidx.appcompat.view.d dVar = new androidx.appcompat.view.d(context, 0);
                dVar.getTheme().setTo(theme2);
                context = dVar;
            }
        }
        h hVar = new h(context);
        hVar.setCallback(this);
        panelFeatureState.a(hVar);
        return true;
    }

    private boolean c(PanelFeatureState panelFeatureState) {
        if (panelFeatureState.i != null) {
            panelFeatureState.h = panelFeatureState.i;
            return true;
        } else if (panelFeatureState.j == null) {
            return false;
        } else {
            if (this.A == null) {
                this.A = new f();
            }
            panelFeatureState.h = (View) panelFeatureState.a(this.A);
            if (panelFeatureState.h != null) {
                return true;
            }
            return false;
        }
    }

    private boolean b(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        n nVar;
        n nVar2;
        n nVar3;
        if (this.r) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.K;
        if (!(panelFeatureState2 == null || panelFeatureState2 == panelFeatureState)) {
            a(panelFeatureState2, false);
        }
        Window.Callback l2 = l();
        if (l2 != null) {
            panelFeatureState.i = l2.onCreatePanelView(panelFeatureState.a);
        }
        boolean z2 = panelFeatureState.a == 0 || panelFeatureState.a == 108;
        if (z2 && (nVar3 = this.y) != null) {
            nVar3.j();
        }
        if (panelFeatureState.i == null && (!z2 || !(k() instanceof g))) {
            if (panelFeatureState.j == null || panelFeatureState.r) {
                if (panelFeatureState.j == null && (!b(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z2 && this.y != null) {
                    if (this.z == null) {
                        this.z = new a();
                    }
                    this.y.a(panelFeatureState.j, this.z);
                }
                panelFeatureState.j.stopDispatchingItemsChanged();
                if (!l2.onCreatePanelMenu(panelFeatureState.a, panelFeatureState.j)) {
                    panelFeatureState.a((h) null);
                    if (z2 && (nVar2 = this.y) != null) {
                        nVar2.a(null, this.z);
                    }
                    return false;
                }
                panelFeatureState.r = false;
            }
            panelFeatureState.j.stopDispatchingItemsChanged();
            if (panelFeatureState.s != null) {
                panelFeatureState.j.restoreActionViewStates(panelFeatureState.s);
                panelFeatureState.s = null;
            }
            if (!l2.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                if (z2 && (nVar = this.y) != null) {
                    nVar.a(null, this.z);
                }
                panelFeatureState.j.startDispatchingItemsChanged();
                return false;
            }
            panelFeatureState.p = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.j.setQwertyMode(panelFeatureState.p);
            panelFeatureState.j.startDispatchingItemsChanged();
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.K = panelFeatureState;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(h hVar) {
        if (!this.I) {
            this.I = true;
            this.y.k();
            Window.Callback l2 = l();
            if (l2 != null && !this.r) {
                l2.onPanelClosed(108, hVar);
            }
            this.I = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void f(int i2) {
        a(a(i2, true), true);
    }

    /* access modifiers changed from: package-private */
    public void a(PanelFeatureState panelFeatureState, boolean z2) {
        n nVar;
        if (!z2 || panelFeatureState.a != 0 || (nVar = this.y) == null || !nVar.f()) {
            WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
            if (!(windowManager == null || !panelFeatureState.o || panelFeatureState.g == null)) {
                windowManager.removeView(panelFeatureState.g);
                if (z2) {
                    a(panelFeatureState.a, panelFeatureState, null);
                }
            }
            panelFeatureState.m = false;
            panelFeatureState.n = false;
            panelFeatureState.o = false;
            panelFeatureState.h = null;
            panelFeatureState.q = true;
            if (this.K == panelFeatureState) {
                this.K = null;
                return;
            }
            return;
        }
        a(panelFeatureState.j);
    }

    private boolean d(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState a2 = a(i2, true);
        if (!a2.o) {
            return b(a2, keyEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c  */
    private boolean e(int i2, KeyEvent keyEvent) {
        boolean z2;
        boolean z3;
        n nVar;
        if (this.h != null) {
            return false;
        }
        PanelFeatureState a2 = a(i2, true);
        if (i2 == 0 && (nVar = this.y) != null && nVar.e() && !ViewConfiguration.get(this.a).hasPermanentMenuKey()) {
            if (this.y.f()) {
                z2 = this.y.i();
            } else if (!this.r && b(a2, keyEvent)) {
                z2 = this.y.h();
            }
            if (z2) {
            }
            return z2;
        } else if (a2.o || a2.n) {
            z2 = a2.o;
            a(a2, true);
            if (z2) {
                AudioManager audioManager = (AudioManager) this.a.getSystemService("audio");
                if (audioManager != null) {
                    audioManager.playSoundEffect(0);
                } else {
                    Log.w("AppCompatDelegate", "Couldn't get audio manager");
                }
            }
            return z2;
        } else if (a2.m) {
            if (a2.r) {
                a2.m = false;
                z3 = b(a2, keyEvent);
            } else {
                z3 = true;
            }
            if (z3) {
                a(a2, keyEvent);
                z2 = true;
                if (z2) {
                }
                return z2;
            }
        }
        z2 = false;
        if (z2) {
        }
        return z2;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.J;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.r) {
            this.c.onPanelClosed(i2, menu);
        }
    }

    /* access modifiers changed from: package-private */
    public PanelFeatureState a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.J;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public PanelFeatureState a(int i2, boolean z2) {
        PanelFeatureState[] panelFeatureStateArr = this.J;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[(i2 + 1)];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.J = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i2);
        panelFeatureStateArr[i2] = panelFeatureState2;
        return panelFeatureState2;
    }

    private boolean a(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        boolean z2 = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || b(panelFeatureState, keyEvent)) && panelFeatureState.j != null) {
            z2 = panelFeatureState.j.performShortcut(i2, keyEvent, i3);
        }
        if (z2 && (i3 & 1) == 0 && this.y == null) {
            a(panelFeatureState, true);
        }
        return z2;
    }

    private void j(int i2) {
        this.t = (1 << i2) | this.t;
        if (!this.s) {
            androidx.core.f.r.a(this.b.getDecorView(), this.P);
            this.s = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void g(int i2) {
        PanelFeatureState a2;
        PanelFeatureState a3 = a(i2, true);
        if (a3.j != null) {
            Bundle bundle = new Bundle();
            a3.j.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                a3.s = bundle;
            }
            a3.j.stopDispatchingItemsChanged();
            a3.j.clear();
        }
        a3.r = true;
        a3.q = true;
        if ((i2 == 108 || i2 == 0) && this.y != null && (a2 = a(0, false)) != null) {
            a2.m = false;
            b(a2, (KeyEvent) null);
        }
    }

    /* access modifiers changed from: package-private */
    public int h(int i2) {
        boolean z2;
        boolean z3;
        ActionBarContextView actionBarContextView = this.i;
        int i3 = 0;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z2 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.i.getLayoutParams();
            z2 = true;
            if (this.i.isShown()) {
                if (this.R == null) {
                    this.R = new Rect();
                    this.S = new Rect();
                }
                Rect rect = this.R;
                Rect rect2 = this.S;
                rect.set(0, i2, 0, 0);
                ak.a(this.D, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i2 : 0)) {
                    marginLayoutParams.topMargin = i2;
                    View view = this.F;
                    if (view == null) {
                        this.F = new View(this.a);
                        this.F.setBackgroundColor(this.a.getResources().getColor(R.color.abc_input_method_navigation_guard));
                        this.D.addView(this.F, -1, new ViewGroup.LayoutParams(-1, i2));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.F.setLayoutParams(layoutParams);
                        }
                    }
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (this.F == null) {
                    z2 = false;
                }
                if (!this.o && z2) {
                    i2 = 0;
                }
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z3 = true;
                z2 = false;
            } else {
                z3 = false;
                z2 = false;
            }
            if (z3) {
                this.i.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.F;
        if (view2 != null) {
            if (!z2) {
                i3 = 8;
            }
            view2.setVisibility(i3);
        }
        return i2;
    }

    private void x() {
        if (this.C) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private int k(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i2 != 9) {
            return i2;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    /* access modifiers changed from: package-private */
    public void s() {
        n nVar = this.y;
        if (nVar != null) {
            nVar.k();
        }
        if (this.j != null) {
            this.b.getDecorView().removeCallbacks(this.k);
            if (this.j.isShowing()) {
                try {
                    this.j.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.j = null;
        }
        q();
        PanelFeatureState a2 = a(0, false);
        if (a2 != null && a2.j != null) {
            a2.j.close();
        }
    }

    @Override // androidx.appcompat.app.c
    public boolean i() {
        int y2 = y();
        int i2 = i(y2);
        boolean l2 = i2 != -1 ? l(i2) : false;
        if (y2 == 0) {
            z();
            this.O.c();
        }
        this.N = true;
        return l2;
    }

    /* access modifiers changed from: package-private */
    public int i(int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != 0) {
            return i2;
        }
        if (Build.VERSION.SDK_INT >= 23 && ((UiModeManager) this.a.getSystemService(UiModeManager.class)).getNightMode() == 0) {
            return -1;
        }
        z();
        return this.O.a();
    }

    private int y() {
        int i2 = this.M;
        return i2 != -100 ? i2 : j();
    }

    private boolean l(int i2) {
        Resources resources = this.a.getResources();
        Configuration configuration = resources.getConfiguration();
        int i3 = configuration.uiMode & 48;
        int i4 = i2 == 2 ? 32 : 16;
        if (i3 == i4) {
            return false;
        }
        if (A()) {
            ((Activity) this.a).recreate();
            return true;
        }
        Configuration configuration2 = new Configuration(configuration);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration2.uiMode = i4 | (configuration2.uiMode & -49);
        resources.updateConfiguration(configuration2, displayMetrics);
        if (Build.VERSION.SDK_INT >= 26) {
            return true;
        }
        f.a(resources);
        return true;
    }

    private void z() {
        if (this.O == null) {
            this.O = new d(i.a(this.a));
        }
    }

    private boolean A() {
        if (this.N) {
            Context context = this.a;
            if (context instanceof Activity) {
                try {
                    if ((context.getPackageManager().getActivityInfo(new ComponentName(this.a, this.a.getClass()), 0).configChanges & 512) == 0) {
                        return true;
                    }
                    return false;
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public class b implements b.a {
        private b.a b;

        public b(b.a aVar) {
            this.b = aVar;
        }

        @Override // androidx.appcompat.view.b.a
        public boolean a(androidx.appcompat.view.b bVar, Menu menu) {
            return this.b.a(bVar, menu);
        }

        @Override // androidx.appcompat.view.b.a
        public boolean b(androidx.appcompat.view.b bVar, Menu menu) {
            return this.b.b(bVar, menu);
        }

        @Override // androidx.appcompat.view.b.a
        public boolean a(androidx.appcompat.view.b bVar, MenuItem menuItem) {
            return this.b.a(bVar, menuItem);
        }

        @Override // androidx.appcompat.view.b.a
        public void a(androidx.appcompat.view.b bVar) {
            this.b.a(bVar);
            if (AppCompatDelegateImpl.this.j != null) {
                AppCompatDelegateImpl.this.b.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.k);
            }
            if (AppCompatDelegateImpl.this.i != null) {
                AppCompatDelegateImpl.this.q();
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                appCompatDelegateImpl.l = androidx.core.f.r.l(appCompatDelegateImpl.i).a(0.0f);
                AppCompatDelegateImpl.this.l.a(new x() {
                    /* class androidx.appcompat.app.AppCompatDelegateImpl.b.AnonymousClass1 */

                    @Override // androidx.core.f.w, androidx.core.f.x
                    public void b(View view) {
                        AppCompatDelegateImpl.this.i.setVisibility(8);
                        if (AppCompatDelegateImpl.this.j != null) {
                            AppCompatDelegateImpl.this.j.dismiss();
                        } else if (AppCompatDelegateImpl.this.i.getParent() instanceof View) {
                            androidx.core.f.r.p((View) AppCompatDelegateImpl.this.i.getParent());
                        }
                        AppCompatDelegateImpl.this.i.removeAllViews();
                        AppCompatDelegateImpl.this.l.a((w) null);
                        AppCompatDelegateImpl.this.l = null;
                    }
                });
            }
            if (AppCompatDelegateImpl.this.e != null) {
                AppCompatDelegateImpl.this.e.onSupportActionModeFinished(AppCompatDelegateImpl.this.h);
            }
            AppCompatDelegateImpl.this.h = null;
        }
    }

    /* access modifiers changed from: private */
    public final class f implements o.a {
        f() {
        }

        @Override // androidx.appcompat.view.menu.o.a
        public void a(h hVar, boolean z) {
            h rootMenu = hVar.getRootMenu();
            boolean z2 = rootMenu != hVar;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                hVar = rootMenu;
            }
            PanelFeatureState a2 = appCompatDelegateImpl.a((Menu) hVar);
            if (a2 == null) {
                return;
            }
            if (z2) {
                AppCompatDelegateImpl.this.a(a2.a, a2, rootMenu);
                AppCompatDelegateImpl.this.a(a2, true);
                return;
            }
            AppCompatDelegateImpl.this.a(a2, z);
        }

        @Override // androidx.appcompat.view.menu.o.a
        public boolean a(h hVar) {
            Window.Callback l;
            if (hVar != null || !AppCompatDelegateImpl.this.m || (l = AppCompatDelegateImpl.this.l()) == null || AppCompatDelegateImpl.this.r) {
                return true;
            }
            l.onMenuOpened(108, hVar);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public final class a implements o.a {
        a() {
        }

        @Override // androidx.appcompat.view.menu.o.a
        public boolean a(h hVar) {
            Window.Callback l = AppCompatDelegateImpl.this.l();
            if (l == null) {
                return true;
            }
            l.onMenuOpened(108, hVar);
            return true;
        }

        @Override // androidx.appcompat.view.menu.o.a
        public void a(h hVar, boolean z) {
            AppCompatDelegateImpl.this.a(hVar);
        }
    }

    /* access modifiers changed from: protected */
    public static final class PanelFeatureState {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        h j;
        androidx.appcompat.view.menu.f k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q = false;
        boolean r;
        Bundle s;

        PanelFeatureState(int i2) {
            this.a = i2;
        }

        public boolean a() {
            if (this.h == null) {
                return false;
            }
            if (this.i == null && this.k.a().getCount() <= 0) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            } else {
                newTheme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
            }
            androidx.appcompat.view.d dVar = new androidx.appcompat.view.d(context, 0);
            dVar.getTheme().setTo(newTheme);
            this.l = dVar;
            TypedArray obtainStyledAttributes = dVar.obtainStyledAttributes(R.styleable.AppCompatTheme);
            this.b = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: package-private */
        public void a(h hVar) {
            androidx.appcompat.view.menu.f fVar;
            h hVar2 = this.j;
            if (hVar != hVar2) {
                if (hVar2 != null) {
                    hVar2.removeMenuPresenter(this.k);
                }
                this.j = hVar;
                if (hVar != null && (fVar = this.k) != null) {
                    hVar.addMenuPresenter(fVar);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public p a(o.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                this.k = new androidx.appcompat.view.menu.f(this.l, R.layout.abc_list_menu_item_layout);
                this.k.setCallback(aVar);
                this.j.addMenuPresenter(this.k);
            }
            return this.k.a(this.g);
        }

        /* access modifiers changed from: private */
        public static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
                /* class androidx.appcompat.app.AppCompatDelegateImpl.PanelFeatureState.SavedState.AnonymousClass1 */

                /* renamed from: a */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                /* renamed from: a */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }

                /* renamed from: a */
                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            };
            int a;
            boolean b;
            Bundle c;

            public int describeContents() {
                return 0;
            }

            SavedState() {
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b ? 1 : 0);
                if (this.b) {
                    parcel.writeBundle(this.c);
                }
            }

            static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.a = parcel.readInt();
                boolean z = true;
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.b = z;
                if (savedState.b) {
                    savedState.c = parcel.readBundle(classLoader);
                }
                return savedState;
            }
        }
    }

    /* access modifiers changed from: private */
    public class e extends ContentFrameLayout {
        public e(Context context) {
            super(context);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.f(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(androidx.appcompat.a.a.a.b(getContext(), i));
        }

        private boolean a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }
    }

    class c extends i {
        @Override // androidx.appcompat.view.i
        public void onContentChanged() {
        }

        c(Window.Callback callback) {
            super(callback);
        }

        @Override // androidx.appcompat.view.i
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // androidx.appcompat.view.i
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.a(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // androidx.appcompat.view.i
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof h)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // androidx.appcompat.view.i
        public boolean onPreparePanel(int i, View view, Menu menu) {
            h hVar = menu instanceof h ? (h) menu : null;
            if (i == 0 && hVar == null) {
                return false;
            }
            if (hVar != null) {
                hVar.setOverrideVisibleItems(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (hVar != null) {
                hVar.setOverrideVisibleItems(false);
            }
            return onPreparePanel;
        }

        @Override // androidx.appcompat.view.i
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.e(i);
            return true;
        }

        @Override // androidx.appcompat.view.i
        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.d(i);
        }

        @Override // androidx.appcompat.view.i
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            if (AppCompatDelegateImpl.this.p()) {
                return a(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        /* access modifiers changed from: package-private */
        public final ActionMode a(ActionMode.Callback callback) {
            f.a aVar = new f.a(AppCompatDelegateImpl.this.a, callback);
            androidx.appcompat.view.b a2 = AppCompatDelegateImpl.this.a(aVar);
            if (a2 != null) {
                return aVar.b(a2);
            }
            return null;
        }

        @Override // androidx.appcompat.view.i
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (!AppCompatDelegateImpl.this.p() || i != 0) {
                return super.onWindowStartingActionMode(callback, i);
            }
            return a(callback);
        }

        @Override // androidx.appcompat.view.i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            PanelFeatureState a2 = AppCompatDelegateImpl.this.a(0, true);
            if (a2 == null || a2.j == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, a2.j, i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final class d {
        private i b;
        private boolean c;
        private BroadcastReceiver d;
        private IntentFilter e;

        d(i iVar) {
            this.b = iVar;
            this.c = iVar.a();
        }

        /* access modifiers changed from: package-private */
        public int a() {
            this.c = this.b.a();
            return this.c ? 2 : 1;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            boolean a2 = this.b.a();
            if (a2 != this.c) {
                this.c = a2;
                AppCompatDelegateImpl.this.i();
            }
        }

        /* access modifiers changed from: package-private */
        public void c() {
            d();
            if (this.d == null) {
                this.d = new BroadcastReceiver() {
                    /* class androidx.appcompat.app.AppCompatDelegateImpl.d.AnonymousClass1 */

                    public void onReceive(Context context, Intent intent) {
                        d.this.b();
                    }
                };
            }
            if (this.e == null) {
                this.e = new IntentFilter();
                this.e.addAction("android.intent.action.TIME_SET");
                this.e.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.e.addAction("android.intent.action.TIME_TICK");
            }
            AppCompatDelegateImpl.this.a.registerReceiver(this.d, this.e);
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (this.d != null) {
                AppCompatDelegateImpl.this.a.unregisterReceiver(this.d);
                this.d = null;
            }
        }
    }
}
