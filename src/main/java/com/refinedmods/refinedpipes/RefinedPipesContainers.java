package com.refinedmods.refinedpipes;

import com.refinedmods.refinedpipes.container.ExtractorAttachmentContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

public class RefinedPipesContainers {
    @ObjectHolder(RefinedPipes.ID + ":extractor_attachment")
    public static final ContainerType<ExtractorAttachmentContainer> EXTRACTOR_ATTACHMENT = null;
}
