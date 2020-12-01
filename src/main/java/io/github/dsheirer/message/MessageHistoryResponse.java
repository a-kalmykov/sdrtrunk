/*
 * *****************************************************************************
 *  Copyright (C) 2014-2020 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

package io.github.dsheirer.message;

import io.github.dsheirer.module.ModuleEventBusMessage;

import java.util.List;

/**
 * Response to message history request.
 */
public class MessageHistoryResponse extends ModuleEventBusMessage
{
    private List<IMessage> mMessages;

    /**
     * Constructs an instance
     * @param messages from the history
     */
    public MessageHistoryResponse(List<IMessage> messages)
    {
        mMessages = messages;
    }

    /**
     * Message history
     */
    public List<IMessage> getMessages()
    {
        return mMessages;
    }
}
