package br.com.alura.aluraviagens.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceUtil {

    public static final String DRAWABLE = "drawable";

    public static Drawable devolveDrawable(Context context, String nomeDrawable) {
        Resources resources = context.getResources();
        int idDrawble = resources.getIdentifier(nomeDrawable, DRAWABLE,context.getPackageName());
        return resources.getDrawable(idDrawble);
    }
}
