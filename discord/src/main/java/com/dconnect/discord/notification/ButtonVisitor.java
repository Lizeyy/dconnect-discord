package com.dconnect.discord.notification;

import discord4j.core.object.component.LayoutComponent;

public interface ButtonVisitor {

    LayoutComponent prepareButtonsInvitation(String invitationId);
}
