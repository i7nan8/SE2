package androidx.appcompat.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R;
import androidx.appcompat.view.b;
import androidx.core.f.d;

public class d extends Dialog implements b {
    private c mDelegate;
    private final d.a mKeyDispatcher;

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

    public d(Context context) {
        this(context, 0);
    }

    public d(Context context, int i) {
        super(context, getThemeResId(context, i));
        this.mKeyDispatcher = new d.a() {
            /* class androidx.appcompat.app.d.AnonymousClass1 */

            @Override // androidx.core.f.d.a
            public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
                return d.this.superDispatchKeyEvent(keyEvent);
            }
        };
        getDelegate().a((Bundle) null);
        getDelegate().i();
    }

    protected d(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mKeyDispatcher = new d.a() {
            /* class androidx.appcompat.app.d.AnonymousClass1 */

            @Override // androidx.core.f.d.a
            public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
                return d.this.superDispatchKeyEvent(keyEvent);
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        getDelegate().h();
        super.onCreate(bundle);
        getDelegate().a(bundle);
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().a();
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        getDelegate().b(i);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        getDelegate().a(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().a(view, layoutParams);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        return (T) getDelegate().a(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        getDelegate().a(charSequence);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        getDelegate().a(getContext().getString(i));
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().b(view, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        getDelegate().d();
    }

    public boolean supportRequestWindowFeature(int i) {
        return getDelegate().c(i);
    }

    public void invalidateOptionsMenu() {
        getDelegate().f();
    }

    public c getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = c.a(this, this);
        }
        return this.mDelegate;
    }

    private static int getThemeResId(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    /* access modifiers changed from: package-private */
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return androidx.core.f.d.a(this.mKeyDispatcher, getWindow().getDecorView(), this, keyEvent);
    }
}
