package net.satisfyu.meadow.entity.cow;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.entity.Entity;
import net.satisfyu.meadow.entity.cow.albino_cow.AlbinoCowEntity;

public class MeadowCowEntityMdodel<T extends Entity> extends CowEntityModel<T> {

    public MeadowCowEntityMdodel(ModelPart root) {
        super(root);
    }
}
