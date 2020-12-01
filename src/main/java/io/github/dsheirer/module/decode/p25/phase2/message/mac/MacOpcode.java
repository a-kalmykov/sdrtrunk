/*
 *
 *  * ******************************************************************************
 *  * Copyright (C) 2014-2019 Dennis Sheirer
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *  * *****************************************************************************
 *
 *
 */

package io.github.dsheirer.module.decode.p25.phase2.message.mac;

import java.util.Map;
import java.util.TreeMap;

/**
 * MAC opcode is used with MAC_IDLE, MAC_ACTIVE and MAC_HANGTIME PDU format messages
 */
public enum MacOpcode
{
    PUSH_TO_TALK(-1, "PUSH-TO-TALK", -1),
    END_PUSH_TO_TALK(-1, "END_PUSH-TO-TALK", -1),

    TDMA_0_NULL_INFORMATION_MESSAGE(0, "NULL INFORMATION", -1),
    TDMA_1_GROUP_VOICE_CHANNEL_USER_ABBREVIATED(1, "GROUP VOICE CHANNEL USER ABBREVIATED", 7),
    TDMA_2_UNIT_TO_UNIT_VOICE_CHANNEL_USER(2, "UNIT-TO-UNIT VOICE CHANNEL USER", 8),
    TDMA_3_TELEPHONE_INTERCONNECT_VOICE_CHANNEL_USER(3, "TELEPHONE INTERCONNECT VOICE CHANNEL USER", 7),
    TDMA_5_GROUP_VOICE_CHANNEL_GRANT_UPDATE_MULTIPLE(5, "GROUP VOICE CHANNEL GRANT UPDATE MULTIPLE", 16),
    TDMA_17_INDIRECT_GROUP_PAGING(17, "INDIRECT GROUP PAGING", Integer.MIN_VALUE),
    TDMA_18_INDIVIDUAL_PAGING_MESSAGE_WITH_PRIORITY(18, "INDIVIDUAL PAGING MESSAGE WITH PRIORITY", Integer.MIN_VALUE),
    TDMA_33_GROUP_VOICE_CHANNEL_USER_EXTENDED(33, "GROUP VOICE CHANNEL USER EXTENDED", 14),
    TDMA_34_UNIT_TO_UNIT_VOICE_CHANNEL_USER_EXTENDED(34, "UNIT-TO-UNIT VOICE CHANNEL USER EXTENDED", 15),
    TDMA_37_GROUP_VOICE_CHANNEL_GRANT_UPDATE_MULTIPLE_EXPLICIT(37, "TDMA GROUP VOICE CHANNEL GRANT UPDATE EXPLICIT", 15),
    TDMA_48_POWER_CONTROL_SIGNAL_QUALITY(48, "POWER CONTROL SIGNAL QUALITY", 5),
    TDMA_49_MAC_RELEASE(49, "MAC RELEASE", 7),
    TDMA_PARTITION_0_UNKNOWN_OPCODE(-1, "UNKNOWN TDMA OPCODE", -1),

    PHASE1_64_GROUP_VOICE_CHANNEL_GRANT_ABBREVIATED(64, "GROUP VOICE CHANNEL GRANT ABBREVIATED", 9),
    PHASE1_65_GROUP_VOICE_SERVICE_REQUEST(65, "GROUP VOICE SERVICE REQUEST", 7),
    PHASE1_66_GROUP_VOICE_CHANNEL_GRANT_UPDATE(66, "GROUP VOICE CHANNEL GRANT UPDATE", 9),
    PHASE1_68_UNIT_TO_UNIT_VOICE_CHANNEL_GRANT_ABBREVIATED(68, "UNIT-TO-UNIT VOICE CHANNEL GRANT ABBREVIATED", 9),
    PHASE1_69_UNIT_TO_UNIT_ANSWER_REQUEST_ABBREVIATED(69, "UNIT-TO-UNIT ANSWER REQUEST ABBREVIATED", 8),
    PHASE1_70_UNIT_TO_UNIT_VOICE_CHANNEL_GRANT_UPDATE_ABBREVIATED(70, "UNIT-TO-UNIT VOICE CHANNEL GRANT UPDATE ABBREVIATED", 9),
    PHASE1_74_TELEPHONE_INTERCONNECT_ANSWER_REQUEST(74, "TELEPHONE INTERCONNECT_ANSWER REQUEST", 9),
    PHASE1_76_RADIO_UNIT_MONITOR_COMMAND_ABBREVIATED(76, "RADIO UNIT MONITOR COMMAND ABBREVIATED", 10),
    PHASE1_84_SNDCP_DATA_CHANNEL_GRANT(84, "SNDCP DATA CHANNEL GRANT", 9),
    PHASE1_85_SNDCP_DATA_PAGE_REQUEST(85, "SNDCP DATA PAGE REQUEST", 7),
    PHASE1_88_STATUS_UPDATE_ABBREVIATED(88, "STATUS UPDATE ABBREVIATED", 10),
    PHASE1_90_STATUS_QUERY_ABBREVIATED(90, "STATUS QUERY ABBREVIATED", 7),
    PHASE1_92_MESSAGE_UPDATE_ABBREVIATED(92, "MESSAGE UPDATE ABBREVIATED", 10),
    OBSOLETE_PHASE1_93_RADIO_UNIT_MONITOR_COMMAND(93, "RADIO UNIT MONITOR COMMAND", 8),
    PHASE1_94_RADIO_UNIT_MONITOR_COMMAND_ENHANCED(94, "RADIO UNIT MONITOR ENHANCED COMMAND ABBREVIATED", 14),
    PHASE1_95_CALL_ALERT_ABBREVIATED(95, "CALL ALERT ABBREVIATED", 7),
    PHASE1_96_ACK_RESPONSE(96, "ACK RESPONSE", 9),
    PHASE1_97_QUEUED_RESPONSE(97, "QUEUED RESPONSE", 9),
    PHASE1_100_EXTENDED_FUNCTION_COMMAND_ABBREVIATED(100, "EXTENDED FUNCTION COMMAND ABBREVIATED", 9),
    PHASE1_103_DENY_RESPONSE(103, "DENY RESPONSE", 9),
    PHASE1_106_GROUP_AFFILIATION_QUERY_ABBREVIATED(106, "GROUP AFFILIATION QUERY ABBREVIATED", 7),
    PHASE1_109_UNIT_REGISTRATION_COMMAND_ABBREVIATED(109, "UNIT REGISTRATION COMMAND ABBREVIATED", 7),
    PHASE1_115_IDENTIFIER_UPDATE_TDMA(115, "IDENTIFIER UPDATE TDMA", 9),
    PHASE1_116_IDENTIFIER_UPDATE_V_UHF(116, "IDENTIFIER UPDATE V/UHF", 9),
    PHASE1_117_TIME_AND_DATE_ANNOUNCEMENT(117, "TIME AND DATE ANNOUNCEMENT", 9),
    PHASE1_120_SYSTEM_SERVICE_BROADCAST(120, "SYSTEM SERVICE BROADCAST", 9),
    PHASE1_121_SECONDARY_CONTROL_CHANNEL_BROADCAST_ABBREVIATED(121, "SECONDARY CONTROL CHANNEL BROADCAST", 9),
    PHASE1_122_RFSS_STATUS_BROADCAST_ABBREVIATED(122, "RFSS STATUS BROADCAST ABBREVIATED", 9),
    PHASE1_123_NETWORK_STATUS_BROADCAST_ABBREVIATED(123, "NETWORK STATUS BROADCAST ABBREVIATED", 11),
    PHASE1_124_ADJACENT_STATUS_BROADCAST_ABBREVIATED(124, "ADJACENT STATUS BROADCAST ABBREVIATED", 9),
    PHASE1_125_IDENTIFIER_UPDATE(125, "IDENTIFIER UPDATE", 9),
    PHASE1_PARTITION_1_UNKNOWN_OPCODE(-1, "UNKNOWN PHASE 1 OPCODE", -1),

    VENDOR_PARTITION_2_UNKNOWN_OPCODE(-1, "UNKNOWN VENDOR OPCODE", -1),

    PHASE1_192_GROUP_VOICE_CHANNEL_GRANT_EXTENDED(192, "GROUP VOICE CHANNEL GRANT EXTENDED", 11),
    PHASE1_195_GROUP_VOICE_CHANNEL_GRANT_UPDATE_EXPLICIT(195, "GROUP VOICE CHANNEL GRANT UPDATE EXPLICIT", 8),
    PHASE1_196_UNIT_TO_UNIT_VOICE_CHANNEL_GRANT_EXTENDED(196, "UNIT-TO-UNIT VOICE CHANNEL GRANT ABBREVIATED", 15),
    PHASE1_197_UNIT_TO_UNIT_ANSWER_REQUEST_EXTENDED(197, "UNIT-TO-UNIT ANSWER REQUEST EXTENDED", 12),
    PHASE1_198_UNIT_TO_UNIT_VOICE_CHANNEL_GRANT_UPDATE_EXTENDED(198, "UNIT-TO-UNIT VOICE CHANNEL GRANT EXTENDED", 15),
    PHASE1_204_RADIO_UNIT_MONITOR_COMMAND_EXTENDED(204, "RADIO UNIT MONITOR COMMAND EXTENDED", 14),
    PHASE1_214_SNDCP_DATA_CHANNEL_ANNOUNCEMENT_EXPLICIT(214, "SNDCP DATA CHANNEL ANNOUNCEMENT EXPLICIT", 9),
    PHASE1_216_STATUS_UPDATE_EXTENDED(216, "STATUS UPDATE EXTENDED", 14),
    PHASE1_218_STATUS_QUERY_EXTENDED(218, "STATUS QUERY EXTENDED", 11),
    PHASE1_220_MESSAGE_UPDATE_EXTENDED(220, "MESSAGE UPDATE EXTENDED", 14),
    PHASE1_223_CALL_ALERT_EXTENDED(223, "CALL ALERT EXTENDED", 11),
    PHASE1_228_EXTENDED_FUNCTION_COMMAND_EXTENDED(228, "EXTENDED FUNCTION COMMAND EXTENDED", 14),
    PHASE1_233_SECONDARY_CONTROL_CHANNEL_BROADCAST_EXPLICIT(233, "SECONDARY CONTROL CHANNEL BROADCAST EXPLICIT", 8),
    PHASE1_234_GROUP_AFFILIATION_QUERY_EXTENDED(234, "GROUP AFFILIATION QUERY EXTENDED", 11),
    PHASE1_250_RFSS_STATUS_BROADCAST_EXTENDED(250, "RFSS STATUS BROADCAST EXTENDED", 11),
    PHASE1_251_NETWORK_STATUS_BROADCAST_EXTENDED(251, "NETWORK STATUS BROADCAST EXTENDED", 13),
    PHASE1_252_ADJACENT_STATUS_BROADCAST_EXTENDED(252, "ADJACENT STATUS BROADCAST EXTENDED", 11),
    PHASE1_EXTENDED_PARTITION_3_UNKNOWN_OPCODE(-1, "UNKNOWN EXTENDED PHASE 1 OPCODE", 1),

    UNKNOWN(-1, "UNKNOWN", -1);

    private static final Map<Integer,MacOpcode> LOOKUP_MAP = new TreeMap<>();

    static
    {
        for(MacOpcode macOpcode : MacOpcode.values())
        {
            if(macOpcode.getValue() != -1)
            {
                LOOKUP_MAP.put(macOpcode.getValue(), macOpcode);
            }
        }
    }

    private int mValue;
    private String mLabel;
    private int mLength;

    MacOpcode(int value, String label, int length)
    {
        mValue = value;
        mLabel = label;
        mLength = length;
    }

    @Override
    public String toString()
    {
        return mLabel;
    }

    public int getValue()
    {
        return mValue;
    }

    /**
     * Length of the message in octets/bytes for the opcode
     */
    public int getLength()
    {
        return mLength;
    }

    /**
     * Indicates if the opcode is for a variable-length message
     */
    public boolean isVariableLength()
    {
        return getLength() == Integer.MIN_VALUE;
    }

    /**
     * Lookup the MAC opcode from an integer value
     */
    public static MacOpcode fromValue(int value)
    {
        MacOpcode mapValue = LOOKUP_MAP.get(value);
        if (mapValue != null)
        {
            return mapValue;
        }

        if(0 <= value && value <= 63)
        {
            return TDMA_PARTITION_0_UNKNOWN_OPCODE;
        }
        else if(64 <= value && value <= 127)
        {
            return PHASE1_PARTITION_1_UNKNOWN_OPCODE;
        }
        else if(128 <= value && value <= 191)
        {
            return VENDOR_PARTITION_2_UNKNOWN_OPCODE;
        }
        else if(192 <= value && value <= 255)
        {
            return PHASE1_EXTENDED_PARTITION_3_UNKNOWN_OPCODE;
        }

        return UNKNOWN;
    }
}
