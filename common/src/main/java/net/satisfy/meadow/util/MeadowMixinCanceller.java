package net.satisfy.meadow.util;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import de.cristelknight.doapi.DoApiEP;

import java.util.List;

public class MeadowMixinCanceller implements MixinCanceller {
    private static final boolean isMMVLoaded = DoApiEP.isModLoaded("moremobvariants");
    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        if(!isMMVLoaded) return false;
        return mixinClassName.equals("com.github.nyuppo.mixin.SheepRendererMixin")
                || mixinClassName.equals("com.github.nyuppo.mixin.SheepWoolFeatureMixin")
                || mixinClassName.equals("com.github.nyuppo.mixin.SheepVariantsMixin");
    }
}
