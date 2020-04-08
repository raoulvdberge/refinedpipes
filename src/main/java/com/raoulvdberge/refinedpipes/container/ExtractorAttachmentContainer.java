package com.raoulvdberge.refinedpipes.container;

import com.raoulvdberge.refinedpipes.RefinedPipes;
import com.raoulvdberge.refinedpipes.RefinedPipesContainers;
import com.raoulvdberge.refinedpipes.container.slot.FilterSlot;
import com.raoulvdberge.refinedpipes.message.ChangeBlacklistWhitelistMessage;
import com.raoulvdberge.refinedpipes.message.ChangeRedstoneModeMessage;
import com.raoulvdberge.refinedpipes.network.pipe.attachment.extractor.BlacklistWhitelist;
import com.raoulvdberge.refinedpipes.network.pipe.attachment.extractor.ExtractorAttachmentType;
import com.raoulvdberge.refinedpipes.network.pipe.attachment.extractor.RedstoneMode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

public class ExtractorAttachmentContainer extends BaseContainer {
    private final BlockPos pos;
    private final Direction dir;
    private final ExtractorAttachmentType extractorAttachmentType;

    private RedstoneMode redstoneMode;
    private BlacklistWhitelist blacklistWhitelist;

    public ExtractorAttachmentContainer(int windowId, PlayerEntity player, BlockPos pos, Direction dir, RedstoneMode redstoneMode, BlacklistWhitelist blacklistWhitelist, ExtractorAttachmentType type, ItemStackHandler itemFilters) {
        super(RefinedPipesContainers.EXTRACTOR_ATTACHMENT, windowId);

        addPlayerInventory(player, 8, 111);

        int x = 44;
        int y = 19;
        for (int i = 1; i <= type.getFilterSlots(); ++i) {
            addSlot(new FilterSlot(itemFilters, i - 1, x, y));

            if (i % 5 == 0) {
                x = 44;
                y += 18;
            } else {
                x += 18;
            }
        }

        this.pos = pos;
        this.dir = dir;
        this.extractorAttachmentType = type;

        this.redstoneMode = redstoneMode;
        this.blacklistWhitelist = blacklistWhitelist;
    }

    public ExtractorAttachmentType getExtractorAttachmentType() {
        return extractorAttachmentType;
    }

    public RedstoneMode getRedstoneMode() {
        return redstoneMode;
    }

    public BlacklistWhitelist getBlacklistWhitelist() {
        return blacklistWhitelist;
    }

    public void setRedstoneMode(RedstoneMode redstoneMode) {
        this.redstoneMode = redstoneMode;

        RefinedPipes.NETWORK.sendToServer(new ChangeRedstoneModeMessage(pos, dir, redstoneMode));
    }

    public void setBlacklistWhitelist(BlacklistWhitelist blacklistWhitelist) {
        this.blacklistWhitelist = blacklistWhitelist;

        RefinedPipes.NETWORK.sendToServer(new ChangeBlacklistWhitelistMessage(pos, dir, blacklistWhitelist));
    }
}
