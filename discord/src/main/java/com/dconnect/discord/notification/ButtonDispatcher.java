package com.dconnect.discord.notification;

import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.object.component.LayoutComponent;
import discord4j.core.object.reaction.ReactionEmoji;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import static com.dconnect.discord.configuration.ButtonProperties.ButtonId.ACCEPT_INVITATION;
import static com.dconnect.discord.configuration.ButtonProperties.ButtonId.CANCEL_INVITATION;
import static com.dconnect.discord.configuration.ButtonProperties.Emoji.ACCEPT_EMOJI;
import static com.dconnect.discord.configuration.ButtonProperties.Emoji.CANCEL_EMOJI;


@Component
@RequiredArgsConstructor
public class ButtonDispatcher implements ButtonVisitor {

    @Override
    public LayoutComponent prepareButtonsInvitation(String invitationId) {
        return ActionRow.of(
                createButtonSuccess(ACCEPT_INVITATION + invitationId, ACCEPT_EMOJI),
                createButtonDanger(CANCEL_INVITATION + invitationId, CANCEL_EMOJI)
        );
    }

    private Button createButtonSuccess(String id, String reactionEmoji) {
        return Button.success(id, createReactionEmoji(reactionEmoji));
    }

    private Button createButtonDanger(String id, String reactionEmoji) {
        return Button.danger(id, createReactionEmoji(reactionEmoji));
    }

    private ReactionEmoji createReactionEmoji(String reaction) {
        return ReactionEmoji.unicode(reaction);
    }
}
